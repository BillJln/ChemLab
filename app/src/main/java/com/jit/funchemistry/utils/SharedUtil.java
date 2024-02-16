package com.jit.funchemistry.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.jit.funchemistry.base.BaseApplication;

import org.jetbrains.annotations.NotNull;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/**
 * @author XiaNingIf
 * @date 2021/3/18
 */
public class SharedUtil {
    public static SharedPreferences sharedPreferences = BaseApplication.getAppContext().getSharedPreferences(
            GlobalUtil.getAppPackage()+ "_preferences", Context.MODE_PRIVATE
    );

    /**
     * 简化SharedPreferences调用。
     *
     * @param action 拥有SharedPreferences.Editor对象上下文的回调代码块
     */
    public static final void edit(@NotNull SharedPreferences edit, @NotNull Function1 action) {
        Intrinsics.checkNotNullParameter(edit, "edit");
        Intrinsics.checkNotNullParameter(action, "action");
        SharedPreferences.Editor editor = edit.edit();
        Intrinsics.checkNotNullExpressionValue(editor, "editor");
        action.invoke(editor);
        editor.apply();
    }
}
