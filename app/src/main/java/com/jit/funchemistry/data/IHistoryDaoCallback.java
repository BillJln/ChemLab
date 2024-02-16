package com.jit.funchemistry.data;

import com.jit.funchemistry.model.domain.History;
import com.jit.funchemistry.model.domain.VideoHistory;

import java.util.List;

/**
 * @author XiaNingIf
 * @date 2021/3/24
 */
public interface IHistoryDaoCallback {
    /**
     * 添加历史的结果
     *
     * @param isSuccess
     */
    void onHistoryAdd(boolean isSuccess);


    /**
     * 删除历史的结果
     *
     * @param isSuccess
     */
    void onHistoryDel(boolean isSuccess);


    /**
     * 历史数据加载的结果
     *
     * @param Histories
     */
    void onHistoriesLoaded(List<VideoHistory> Histories);


    /**
     * 历史内容清楚结果
     */
    void onHistoriesClean(boolean isSuccess);
}
