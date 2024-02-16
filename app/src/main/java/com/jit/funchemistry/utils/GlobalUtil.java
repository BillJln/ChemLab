package com.jit.funchemistry.utils;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.jit.funchemistry.base.BaseApplication;

/**
 * @author XiaNingIf
 * @date 2021/3/18
 */
public class GlobalUtil {
    private String TAG = "GlobalUtil";

    /**
     * 获取当前应用程序的包名。
     *
     * @return 当前应用程序的包名。
     */
    public static String getAppPackage(){
        return BaseApplication.getAppContext().getPackageName();
    }

    /**
     * 获取当前应用程序的名称。
     * @return 当前应用程序的名称。
     */
    public static String getAppName(){
        return BaseApplication.getAppContext().getString(BaseApplication.getAppContext().getApplicationInfo().labelRes);
    }

    /**
     * 获取当前应用程序的版本名。
     * @return 当前应用程序的版本名。
     */
    public static String getAppVersionName() throws PackageManager.NameNotFoundException {
        return BaseApplication.getAppContext().getPackageManager().getPackageInfo(getAppPackage(),0).versionName;
    }

    /**
     * 获取趣味化学应用程序的版本名。
     * @return 趣味化学当前应用程序的版本名。
     */
    public static String getFunChemistryVersionName(){
        return "1.0.0";
    }

    /**
     * 获取趣味化学应用程序的版本号。
     * @return 趣味化学当前应用程序的版本号。
     */
    public static long getFunChemistryVersionCode(){
        return 1001000;
    }

    /**
     * 获取资源文件中定义的字符串。
     *
     * @param resId
     * 字符串资源id
     * @return 字符串资源id对应的字符串内容。
     */
    public static String getString(int resId){
        return BaseApplication.getAppContext().getResources().getString(resId);
    }

    /**
     * 判断某个应用是否安装。
     * @param packageName
     * 要检查是否安装的应用包名
     * @return 安装返回true，否则返回false。
     */
    public static Boolean isInstalled(String packageName){
        PackageInfo packageInfo = null;
        try {
            packageInfo = BaseApplication.getAppContext().getPackageManager().getPackageInfo(packageName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return packageInfo != null;
    }

    /**
     * 判断手机是否安装了QQ。
     */
    public static Boolean isQQInstalled(){
        return isInstalled("com.tencent.mobileqq");
    }

    /**
     * 判断手机是否安装了微信。
     */
    public static Boolean isWechatInstalled() {
        return isInstalled("com.tencent.mm");
    }

    /**
     * 判断手机是否安装了微博。
     * */
    public static Boolean isWeiboInstalled(){
        return isInstalled("com.sina.weibo");
    }
}
