package com.jit.funchemistry.presenter;

import com.jit.funchemistry.base.IBasePresenter;
import com.jit.funchemistry.view.ILessonCallback;

/**
 * @author XiaNingIf
 * @date 2021/3/31
 */
public interface IExperimentPresenter extends IBasePresenter<ILessonCallback> {
    /**
     * 获取试验ID
     * @param i
     */
    void getExperimentID(int i);
}
