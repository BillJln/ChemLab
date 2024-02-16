package com.jit.funchemistry.base;

/**
 * @author XiaNingIf
 * @date 2021/3/18
 */
public interface IBasePresenter<T> {
    /**
     * 注册回调接口
     * @param callback
     */
    void registerViewCallback(T callback);

    /**
     * 注销回调接口
     * @param callback
     */
    void unregisterViewCallback(T callback);
}
