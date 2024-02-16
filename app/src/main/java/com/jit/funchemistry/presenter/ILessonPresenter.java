package com.jit.funchemistry.presenter;

import com.jit.funchemistry.base.IBasePresenter;
import com.jit.funchemistry.view.ILessonCallback;

/**
 * @author XiaNingIf
 * @date 2021/3/21
 */
public interface ILessonPresenter extends IBasePresenter<ILessonCallback> {
    /**
     * 加载课程内容
     */
    void getLessonContent();

    /**
     * 重新加载内容
     */
    void reload();

    /**
     * 加载更多
     */
    void loaderMore();
}
