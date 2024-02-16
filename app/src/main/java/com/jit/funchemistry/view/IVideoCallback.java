package com.jit.funchemistry.view;

import com.jit.funchemistry.base.IBaseCallBack;

/**
 * @author XiaNingIf
 * @date 2021/3/20
 */
public interface IVideoCallback extends IBaseCallBack {
    /**
     * 加载视频
     * @param url
     * @param stringID
     */
    void onVideoLoaded(String url,int stringID);
}
