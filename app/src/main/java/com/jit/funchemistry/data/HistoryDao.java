package com.jit.funchemistry.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.jit.funchemistry.base.BaseApplication;
import com.jit.funchemistry.model.domain.History;
import com.jit.funchemistry.model.domain.VideoHistory;
import com.jit.funchemistry.utils.Constants;
import com.jit.funchemistry.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author XiaNingIf
 * @date 2021/3/24
 */
public class HistoryDao implements IHistoryDao{
    private static final HistoryDao ourInstance = new HistoryDao();
    private static final String TAG = "HistoryDao";
    private final FunChemistryDBHelper mDBHelper;
    private IHistoryDaoCallback mCallback = null;
    private Object mLock = new Object();

    private static HistoryDao getInstance(){
        return ourInstance;
    }

    public HistoryDao(){
        mDBHelper = new FunChemistryDBHelper(BaseApplication.getAppContext());
    }

    @Override
    public void setCallback(IHistoryDaoCallback callback) {
        this.mCallback = callback;
    }

    @Override
    public void addHistory(VideoHistory history) {
        synchronized(mLock) {
            SQLiteDatabase db = null;
            boolean isSuccess = false;
            try {
                db = mDBHelper.getWritableDatabase();
                //先去删除
                int delResult = db.delete(Constants.HISTORY_VIDEO_NAME,Constants.HISTORY_TRACK_ID + "=?",
                        new String[]{ history.getVideoID()+""});
                LogUtils.d(TAG,"delResult -- > " + delResult);
                //删除以后再添加
                db.beginTransaction();
                ContentValues values = new ContentValues();
                //封装数据
                values.put(Constants.HISTORY_TRACK_ID,history.getVideoID());
                values.put(Constants.HISTORY_TITLE,history.getHistoryTitle());
                values.put(Constants.HISTORY_UPDATE_TIME,history.getHistoryTime());
                values.put(Constants.HISTORY_COVER,history.getPic_url());
                values.put(Constants.HISTORY_MAKER,history.getMaker());
                //插入数据
                db.insert(Constants.HISTORY_VIDEO_NAME,null,values);
                db.setTransactionSuccessful();
                isSuccess = true;
            } catch(Exception e) {
                isSuccess = false;
                e.printStackTrace();
            } finally {
                if(db != null) {
                    db.endTransaction();
                    db.close();
                }
                if(mCallback != null) {
                    mCallback.onHistoryAdd(isSuccess);
                }
            }
        }
    }

    @Override
    public void delHistory(VideoHistory history) {
        synchronized(mLock) {
            SQLiteDatabase db = null;
            boolean isDeleteSuccess = false;
            try {
                db = mDBHelper.getWritableDatabase();
                db.beginTransaction();
                int delete = db.delete(Constants.HISTORY_VIDEO_NAME,Constants.HISTORY_TRACK_ID + "=?",new String[]{
                        history.getVideoID() +
                                ""});
                LogUtils.d(TAG,"delete -- > " + delete);
                db.setTransactionSuccessful();
                isDeleteSuccess = true;
            } catch(Exception e) {
                e.printStackTrace();
                isDeleteSuccess = false;
            } finally {
                if(db != null) {
                    db.endTransaction();
                    db.close();
                }
                if(mCallback != null) {
                    mCallback.onHistoryDel(isDeleteSuccess);
                }
            }
        }
    }

    @Override
    public void clearHistory() {
        synchronized(mLock) {
            SQLiteDatabase db = null;
            boolean isDeleteSuccess = false;
            try {
                db = mDBHelper.getWritableDatabase();
                db.beginTransaction();
                db.delete(Constants.HISTORY_VIDEO_NAME,null,null);
                db.setTransactionSuccessful();
                isDeleteSuccess = true;
            } catch(Exception e) {
                e.printStackTrace();
                isDeleteSuccess = false;
            } finally {
                if(db != null) {
                    db.endTransaction();
                    db.close();
                }
                if(mCallback != null) {
                    mCallback.onHistoriesClean(isDeleteSuccess);
                }
            }
        }
    }

    @Override
    public void listHistories() {
        synchronized(mLock) {
            //从数据表中查出所有的历史记录
            SQLiteDatabase db = null;
            List<VideoHistory> histories = new ArrayList<>();
            try {
                db = mDBHelper.getReadableDatabase();
                db.beginTransaction();
                Cursor cursor = db.query(Constants.HISTORY_VIDEO_NAME,null,null,null,null,null,"_id desc");
                while(cursor.moveToNext()) {
//                    History history = new History();
//                    int trackId = cursor.getInt(cursor.getColumnIndex(Constants.HISTORY_TRACK_ID));
//                    track.setDataId(trackId);
//                    String title = cursor.getString(cursor.getColumnIndex(Constants.HISTORY_TITLE));
//                    track.setTrackTitle(title);
//                    int playCount = cursor.getInt(cursor.getColumnIndex(Constants.HISTORY_PLAY_COUNT));
//                    track.setPlayCount(playCount);
//                    int duration = cursor.getInt(cursor.getColumnIndex(Constants.HISTORY_DURATION));
//                    track.setDuration(duration);
//                    long updateTime = cursor.getLong(cursor.getColumnIndex(Constants.HISTORY_UPDATE_TIME));
//                    track.setUpdatedAt(updateTime);
//                    String cover = cursor.getString(cursor.getColumnIndex(Constants.HISTORY_COVER));
//                    track.setCoverUrlLarge(cover);
//                    track.setCoverUrlSmall(cover);
//                    track.setCoverUrlMiddle(cover);
//                    String author = cursor.getString(cursor.getColumnIndex(Constants.HISTORY_AUTHOR));
//                    Announcer announcer = new Announcer();
//                    announcer.setNickname(author);
//                    track.setAnnouncer(announcer);
//                    histories.add(track);
                }
                db.setTransactionSuccessful();
            } catch(Exception e) {
                e.printStackTrace();
            } finally {
                if(db != null) {
                    db.endTransaction();
                    db.close();
                }
                //通知出去
                if(mCallback != null) {
                    mCallback.onHistoriesLoaded(histories);
                }
            }
        }
    }
}
