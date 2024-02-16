package com.jit.funchemistry.utils;

import com.jit.funchemistry.presenter.ILessonPresenter;
import com.jit.funchemistry.presenter.IVideoPresenter;
import com.jit.funchemistry.presenter.impl.LessonPresenterImpl;
import com.jit.funchemistry.presenter.impl.VideoPresenterImpl;

/**
 * @author XiaNingIf
 * @date 2021/3/20
 */
public class PresenterManager {
    private static final PresenterManager ourInstance = new PresenterManager();
    private final ILessonPresenter mLessonPresenter;
    private final IVideoPresenter mVideoPresenter;
    public static PresenterManager getInstance(){
        return ourInstance;
    }

    private PresenterManager(){
        mLessonPresenter = new LessonPresenterImpl();
        mVideoPresenter = new VideoPresenterImpl();
    }

    public ILessonPresenter getLessonPresenter(){
        return mLessonPresenter;
    }

    public IVideoPresenter getVideoPresenter(){
        return mVideoPresenter;
    }
}
