package com.jit.funchemistry.view;

import com.jit.funchemistry.base.IBaseCallBack;
import com.jit.funchemistry.model.domain.RecommendExperiment;

/**
 * @author XiaNingIf
 * @date 2021/3/20
 */
public interface IExperimentCallback extends IBaseCallBack {
    /**
     * 加载试验界面信息
     * @param recommendExperiment
     */
    void onExperimentLoaded(RecommendExperiment recommendExperiment);
}
