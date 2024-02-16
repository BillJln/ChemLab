package com.jit.funchemistry.presenter.impl;

import com.bumptech.glide.load.engine.Engine;
import com.jit.funchemistry.model.Api;
import com.jit.funchemistry.presenter.ILessonPresenter;
import com.jit.funchemistry.utils.RetrofitManager;
import com.jit.funchemistry.view.ILessonCallback;

import java.util.IllegalFormatCodePointException;

import retrofit2.Retrofit;

/**
 * @author XiaNingIf
 * @date 2021/3/21
 */
public class LessonPresenterImpl implements ILessonPresenter {
    public static final  int DEFAULT_PAGE = 1;
    private  int mCurrentPage = DEFAULT_PAGE;
    private ILessonCallback mLessonCallback = null;
    private final Api mApi;

    enum LoadState{
        LOADING,SUCCESS,ERROR,NONE
    }

    private LoadState mCurrentState = LoadState.NONE;

    /**
     * 当前状态
     */
    private boolean mIsLoading = false;

    public LessonPresenterImpl(){
        Retrofit retrofit = RetrofitManager.getInstance().getRetrofit();
        mApi = retrofit.create(Api.class);
    }


    @Override
    public void getLessonContent() {
        if (mIsLoading){
            return;
        }
        mIsLoading = true;
        mCurrentPage++;

    }

    @Override
    public void reload() {

    }

    @Override
    public void loaderMore() {

    }

    @Override
    public void registerViewCallback(ILessonCallback callback) {
        this.mLessonCallback = callback;
        if (mCurrentState != LoadState.NONE){
            if(mCurrentState == LoadState.SUCCESS){
                onLessonLoadedSuccess();
            }else if(mCurrentState == LoadState.ERROR){
                onLoadedLessonError();
            }else if (mCurrentState == LoadState.LOADING){
                onLessonLoading();
            }
        }
    }

    private void onLessonLoading() {

    }

    private void onLoadedLessonError() {
    }

    private void onLessonLoadedSuccess() {
        if (mLessonCallback!=null){
            //mLessonCallback.onLessonLoaded();
        }
    }

    @Override
    public void unregisterViewCallback(ILessonCallback callback) {
        this.mLessonCallback = null;
    }
}
