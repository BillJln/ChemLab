package com.jit.funchemistry.utils;

import android.content.Context;

import com.jit.funchemistry.base.BaseApplication;

/**
 * @author XiaNingIf
 * @date 2021/3/18
 */
public class DensityUtil {
    /**
     * 根据手机的分辨率将dp转成为px。
     */
    public static int dp2px(Context context,float dp){
        float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dp * scale + 0.5f);
    }
}
