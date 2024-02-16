package com.jit.funchemistry.presenter.impl;

import com.jit.funchemistry.presenter.IVideoPresenter;
import com.jit.funchemistry.view.IVideoCallback;

/**
 * @author XiaNingIf
 * @date 2021/3/18
 */
public class VideoPresenterImpl implements IVideoPresenter {

    private IVideoCallback mViewCallback = null;
    private String mUrl = null;
    private int mTargetID;

    enum LoadState{
        LOADING,SUCCESS,ERROR,NONE
    }

    private LoadState mCurrentState = LoadState.NONE;

    @Override
    public void getVideo(String url,int stringID) {
        this.onVideoLoading();
        this.mUrl = url;
        this.mTargetID = stringID;
        onVideoLoadedSuccess();
    }

    private void onVideoLoadedSuccess() {
        if (mViewCallback != null) {
            mViewCallback.onVideoLoaded(mUrl, mTargetID);
        }else{
            mCurrentState = LoadState.SUCCESS;
        }
    }

    private void onVideoLoading() {
        if (mViewCallback != null) {
            mViewCallback.onLoading();
        }else{
            mCurrentState = LoadState.LOADING;
        }
    }

    private void onLoadedVideoError() {
        if (mViewCallback != null) {
            mViewCallback.onError();
        }else{
            mCurrentState = LoadState.ERROR;
        }
    }

    @Override
    public void registerViewCallback(IVideoCallback callback) {
        this.mViewCallback = callback;
        if (mCurrentState != LoadState.NONE){
            //说明状态已经改变
            //通知更新UI
            if(mCurrentState == LoadState.SUCCESS){
                onVideoLoadedSuccess();
            }else if(mCurrentState == LoadState.ERROR){
                onLoadedVideoError();
            }else if (mCurrentState == LoadState.LOADING){
                onVideoLoading();
            }
        }
    }

    @Override
    public void unregisterViewCallback(IVideoCallback callback) {
        this.mViewCallback  = null;
    }
}
