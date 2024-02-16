package com.jit.funchemistry.base;

/**
 * @author XiaNingIf
 * @date 2021/3/18
 */
public interface IBaseCallBack {
    /**
     * 加载错误
     */
    void onError();

    /**
     * 加载中
     */
    void onLoading();

    /**
     * 加载结果为空
     */
    void onEmpty();
}
