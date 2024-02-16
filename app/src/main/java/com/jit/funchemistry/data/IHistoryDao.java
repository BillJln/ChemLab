package com.jit.funchemistry.data;

import android.provider.MediaStore;

import com.jit.funchemistry.model.domain.History;
import com.jit.funchemistry.model.domain.VideoHistory;

/**
 * @author XiaNingIf
 * @date 2021/3/24
 */
public interface IHistoryDao {
    /**
     * 设置回调接口
     *
     * @param callback
     */
    void setCallback(IHistoryDaoCallback callback);

    /**
     * 添加历史.
     *
     * @param history
     */
    void addHistory(VideoHistory history);

    /**
     * 删除历史
     *
     * @param history
     */
    void delHistory(VideoHistory history);


    /**
     * 清楚历史内容。
     */
    void clearHistory();


    /**
     * 获取历史内容.
     */
    void listHistories();

}
