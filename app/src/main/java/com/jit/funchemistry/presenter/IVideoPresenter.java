package com.jit.funchemistry.presenter;

import com.jit.funchemistry.base.IBasePresenter;
import com.jit.funchemistry.view.IVideoCallback;

/**
 * @author XiaNingIf
 * @date 2021/3/18
 */
public interface IVideoPresenter extends IBasePresenter<IVideoCallback> {
    /**
     * 获取视频信息
     * @param url
     * @param stringID
     */
    void getVideo(String url,int stringID);
}
