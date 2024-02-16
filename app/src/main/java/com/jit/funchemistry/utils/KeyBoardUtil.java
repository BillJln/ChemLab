package com.jit.funchemistry.utils;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import org.jetbrains.annotations.NotNull;

/**
 * @author XiaNingIf
 * @date 2021/3/20
 */
public class KeyBoardUtil {
    public static void hide(@NotNull Context context, @NotNull View view){
        InputMethodManager im = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        im.hideSoftInputFromWindow(view.getWindowToken(),0);
    }

    public static void show(@NotNull Context context, View view){
        InputMethodManager im = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        im.showSoftInput(view,0);
    }
}
