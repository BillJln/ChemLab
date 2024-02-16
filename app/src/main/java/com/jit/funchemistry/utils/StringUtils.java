/**
 * Copyright 2019 cloudletnovel Team
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.jit.funchemistry.utils;

import androidx.annotation.StringRes;

import com.luhuiguo.chinese.ChineseUtils;

import java.util.Locale;

/**
 * Created by wzc on 2019/9/10.
 */
public class StringUtils {

    public static boolean isEmpty(String s) {
        return s == null || s.isEmpty();
    }

    public static String getString(@StringRes int id){
        return AppUtils.getAppContext().getResources().getString(id);
    }

    public static String getString(@StringRes int id, Object... formatArgs){
        return AppUtils.getAppContext().getResources().getString(id,formatArgs);
    }

    public static String creatAcacheKey(Object... param) {
        String key = "";
        for (Object o : param) {
            key += "-" + o;
        }
        return key.replaceFirst("-","");
    }

    public static String selectTraditionalOrSimplified(String str) {
        String language = null;
        if(UserUtil.getLanguage(AppUtils.getAppContext()) == null) {
            language = Locale.getDefault().getLanguage();
        } else {
            language = UserUtil.getLanguage(AppUtils.getAppContext());
        }
        switch(language) {
            case "zh-TW":
            case "zh-HK":
                return s2t(str);
            default:
                return str;
        }
    }

    public static String searchTraditionalToSimplified(String str) {
        String language = null;
        if(UserUtil.getLanguage(AppUtils.getAppContext()) == null) {
            language = Locale.getDefault().getLanguage();
        } else {
            language = UserUtil.getLanguage(AppUtils.getAppContext());
        }
        switch(language) {
            case "zh-TW":
            case "zh-HK":
                return t2s(str);
            default:
                return str;
        }
    }

    private static String s2t(String str) {
        str = str.replace("<p>", "");
        return ChineseUtils.toTraditional(str);
    }

    public static String t2s(String str) {
        str = str.replace("<p>", "");
        return ChineseUtils.toSimplified(str);
    }

    /**
     * Return a String that only has two spaces.
     *
     * @return
     */
    public static String getTwoSpaces() {
        return "\u3000\u3000";
    }

}
