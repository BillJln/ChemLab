package com.jit.funchemistry.ui.activity;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jit.funchemistry.R;
import com.jit.funchemistry.base.BaseActivity;
import com.jit.funchemistry.cover.DataInter;
import com.jit.funchemistry.presenter.IVideoPresenter;
import com.jit.funchemistry.utils.ActivityUtil;
import com.jit.funchemistry.utils.PUtil;
import com.jit.funchemistry.utils.PresenterManager;
import com.jit.funchemistry.utils.ReceiverGroupManager;
import com.jit.funchemistry.view.IVideoCallback;
import com.kk.taurus.playerbase.assist.InterEvent;
import com.kk.taurus.playerbase.assist.OnVideoViewEventHandler;
import com.kk.taurus.playerbase.entity.DataSource;
import com.kk.taurus.playerbase.event.OnPlayerEventListener;
import com.kk.taurus.playerbase.player.IPlayer;
import com.kk.taurus.playerbase.receiver.ReceiverGroup;
import com.kk.taurus.playerbase.widget.BaseVideoView;

import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * @author XiaNingIf
 * @date 2021/3/20
 */
public class BaseVideoActivity extends BaseActivity implements OnPlayerEventListener, IVideoCallback {
    @BindView(R.id.baseVideoView)
    com.kk.taurus.playerbase.widget.BaseVideoView mBaseVideoView;
//    @BindView(R.id.magic_indicator_container)
//    LinearLayout mMagicIndicatorContainer;
//    @BindView(R.id.magic_indicator_bar)
//    net.lucode.hackware.magicindicator.MagicIndicator mMagicIndicatorBar;
    @BindView(R.id.touch_land)
    ConstraintLayout mTouchLand;
//    @BindView(R.id.dividend)
//    View mDividend;
    @BindView(R.id.text_word)
    TextView mTextWord;
    @BindView(R.id.iv_like)
    ImageView mIvLike;
    @BindView(R.id.iv_download)
    ImageView mIvDownload;
    @BindView(R.id.iv_experiment)
    ImageView mIvExperiment;
    @BindView(R.id.iv_share)
    ImageView mIvShare;

    private BaseVideoView mVideoView;
    private ReceiverGroup mReceiverGroup;

    private boolean userPause;
    private boolean isLandscape;
    private int margin;

    private boolean hasStart;
    //private RecyclerView mRecycler;

    private float mVolumeLeft = 0.5f;
    private float mVolumeRight = 0.5f;

    private IVideoPresenter mVideoPresenter;

//    //仅限于测试使用
    private String video_url;
    private int string_ID;
//    public void getVideoInformation(String url, int stringID){
//        video_url = url;
//        string_ID = stringID;
//    }


    @Override
    protected void initPresenter() {
        mVideoPresenter = PresenterManager.getInstance().getVideoPresenter();
        mVideoPresenter.registerViewCallback(this);
    }

    @Override
    protected void initEvent() {
        mIvLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mIvDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mIvExperiment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mIvShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    protected void initView() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN
                , WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mVideoView = findViewById(R.id.baseVideoView);
        //mRecycler = findViewById(R.id.setting_recycler);

        margin = PUtil.dip2px(this,2);

        updateVideo(false);

        mReceiverGroup = ReceiverGroupManager.get().getReceiverGroup(this);
        //添加弹幕cover
        //mReceiverGroup.addReceiver(DataInter.ReceiverKey.KEY_DANMU_COVER, new DanmuCover(this));
        mReceiverGroup.getGroupValue().putBoolean(DataInter.Key.KEY_CONTROLLER_TOP_ENABLE, true);
        mVideoView.setReceiverGroup(mReceiverGroup);
        mVideoView.setEventHandler(onVideoViewEventHandler);
        mVideoView.setOnPlayerEventListener(this);

        //添加弹幕数据生产者
        //mVideoView.getSuperContainer().addEventProducer(new DanmuDataProducer());

        mVideoView.setVolume(mVolumeLeft, mVolumeRight);
        //initMagicIndicator();
    }

    private void initPlay(){
        if(!hasStart){
            DataSource dataSource = new DataSource(video_url);
            mTextWord.setText(string_ID);
            //dataSource.setTitle("音乐和艺术如何改变世界");
            mVideoView.setDataSource(dataSource);
            mVideoView.start();
            hasStart = true;
        }
    }

    @Override
    public void onPlayerEvent(int eventCode, Bundle bundle) {
//        switch (eventCode){
//            case OnPlayerEventListener.PLAYER_EVENT_ON_VIDEO_RENDER_START:
//                if(mAdapter==null){
//                    mRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
//                    mAdapter = new SettingAdapter(this, SettingItem.initSettingList());
//                    mAdapter.setOnItemClickListener(this);
//                    mRecycler.setAdapter(mAdapter);
//                }
//                break;
//        }
    }

    private OnVideoViewEventHandler onVideoViewEventHandler = new OnVideoViewEventHandler(){
        @Override
        public void onAssistHandle(BaseVideoView assist, int eventCode, Bundle bundle) {
            super.onAssistHandle(assist, eventCode, bundle);
            switch (eventCode){
                case InterEvent.CODE_REQUEST_PAUSE:
                    userPause = true;
                    break;
                case DataInter.Event.EVENT_CODE_REQUEST_BACK:
                    if(isLandscape){
                        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    }else{
                        finish();
                    }
                    break;
                case DataInter.Event.EVENT_CODE_REQUEST_TOGGLE_SCREEN:
                    setRequestedOrientation(isLandscape ?
                            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT:
                            ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    break;
                case DataInter.Event.EVENT_CODE_ERROR_SHOW:
                    mVideoView.stop();
                    break;
            }
        }

        @Override
        public void requestRetry(BaseVideoView videoView, Bundle bundle) {
            if(PUtil.isTopActivity(BaseVideoActivity.this)){
                super.requestRetry(videoView, bundle);
            }
        }
    };

    private void replay(){
        mVideoView.setDataSource(new DataSource(video_url));
        mVideoView.start();
    }

    private void updateVideo(boolean landscape) {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) mVideoView.getLayoutParams();
        if(landscape){
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
            layoutParams.setMargins(0, 0, 0, 0);
        }else{
            layoutParams.width = PUtil.getScreenW(this) - (margin*2);
            layoutParams.height = layoutParams.width * 3/4;
            layoutParams.setMargins(margin, margin, margin, margin);
        }
        mVideoView.setLayoutParams(layoutParams);
    }

    @Override
    public void onBackPressed() {
        if(isLandscape){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            return;
        }
        super.onBackPressed();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if(newConfig.orientation==Configuration.ORIENTATION_LANDSCAPE){
            isLandscape = true;
            updateVideo(true);
        }else{
            isLandscape = false;
            updateVideo(false);
        }
        mReceiverGroup.getGroupValue().putBoolean(DataInter.Key.KEY_IS_LANDSCAPE, isLandscape);
    }

    @Override
    protected void onPause() {
        super.onPause();
        int state = mVideoView.getState();
        if(state == IPlayer.STATE_PLAYBACK_COMPLETE)
            return;
        if(mVideoView.isInPlaybackState()){
            mVideoView.pause();
        }else{
            mVideoView.stop();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        int state = mVideoView.getState();
        if(state == IPlayer.STATE_PLAYBACK_COMPLETE)
            return;
        if(mVideoView.isInPlaybackState()){
            if(!userPause)
                mVideoView.resume();
        }else{
            mVideoView.rePlay(0);
        }
        initPlay();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mVideoView.stopPlayback();
        ActivityUtil.getInstance().removeActivity(this);
    }

//    private void initMagicIndicator() {
//        CommonNavigator commonNavigator = new CommonNavigator(this);
//        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
//            @Override
//            public int getCount() {
//                return 0;
//            }
//
//            @Override
//            public IPagerTitleView getTitleView(Context context, int index) {
//                return null;
//            }
//
//            @Override
//            public IPagerIndicator getIndicator(Context context) {
//                return null;
//            }
//        });
//    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_base_video;
    }

    @Override
    public void onVideoLoaded(String url, int stringID) {
        video_url = url;
        string_ID = stringID;
    }

    @Override
    public void onError() {

    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onEmpty() {

    }
}
