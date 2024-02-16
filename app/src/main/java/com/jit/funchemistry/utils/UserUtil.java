package com.jit.funchemistry.utils;

import android.content.Context;
import android.net.Uri;
import android.os.Environment;

import com.jit.funchemistry.base.BaseApplication;
import com.lib.common.util.DataInter;
import com.lib.common.util.SharePreferencesUtil;

import java.io.File;

//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;


/**
 * @author WuZhongcheng
 * createTime 2019/6/27
 */
public class UserUtil {

    public static void saveFirstExit(Context context, boolean isFirstExit) {
        SharePreferencesUtil.setBooleanSharePreferences(context, Constants.KEY_IS_FIRST_EXIT, isFirstExit);
    }

    public static boolean isFirstExit(Context context) {
        return SharePreferencesUtil.getBooleanSharePreferences(context, Constants.KEY_IS_FIRST_EXIT, true);
    }

    public static void saveUserCachePath(Context context, String path) {
        SharePreferencesUtil.setStringSharePreferences(context, Constants.KEI_STORAGE_PATH, path);
    }

    public static String getUserCachePath(Context context) {
        return SharePreferencesUtil.getStringSharePreferences(context, Constants.KEI_STORAGE_PATH, Environment.getExternalStorageDirectory().getPath()+ File.separator + "ChinaMoive/Cache/M3u8");
    }

    public static void saveIsVip(Context context, boolean isVip) {
        SharePreferencesUtil.setBooleanSharePreferences(context,  Constants.KEY_IS_VIP, isVip);
    }

    public static boolean isVip(Context context) {
        return SharePreferencesUtil.getBooleanSharePreferences(context,  Constants.KEY_IS_VIP, false);
    }

    public static void saveLanguage(Context context, String language) {
        SharePreferencesUtil.setStringSharePreferences(context, Constants.LANGUAGE, language);
    }

    public static String getLanguage(Context context) {
        return SharePreferencesUtil.getStringSharePreferences(context, Constants.LANGUAGE, null);
    }

//    public static void saveUserInfo(Context context, LoginDto.DataBean dataBean, String userInfo) {
//        SharePreferencesUtil.setStringSharePreferences(context, DataInter.KEY.USER_TOKEN, dataBean.getToken());
//        SharePreferencesUtil.setStringSharePreferences(context, DataInter.KEY.INVATATION_CODE, dataBean.getInvitationCode());
//        SharePreferencesUtil.setStringSharePreferences(context, DataInter.KEY.USER_INFO, userInfo);
//        SharePreferencesUtil.setBooleanSharePreferences(context, DataInter.KEY.IS_LOGIN, true);
//    }

    public static String getUserInfo(Context context) {
        String userInfo = SharePreferencesUtil.getStringSharePreferences(context, DataInter.KEY.USER_INFO, "");
        return userInfo;
    }

    public static void exitLogin(Context context) {
        SharePreferencesUtil.setBooleanSharePreferences(context, DataInter.KEY.IS_LOGIN, false);
        SharePreferencesUtil.setStringSharePreferences(context, DataInter.KEY.USER_TOKEN, "");
        SharePreferencesUtil.setStringSharePreferences(context, DataInter.KEY.USER_INFO, "");
    }

    public static String getUserToken(Context context) {
        return SharePreferencesUtil.getStringSharePreferences(context, DataInter.KEY.USER_TOKEN, "");
    }

    public static String getInvitationCode(Context context) {
        return SharePreferencesUtil.getStringSharePreferences(context, DataInter.KEY.INVATATION_CODE, "");
    }

//    public static boolean isLogin() {
//        //注 此处已必返回false
//        if (TextUtils.isEmpty(getUserToken(MyApplication.getContext()))) {
//            return false;
//        }
//        return true;
//    }

    public static boolean isFirebaseLogin() {
        return false;
    }

    public static String getUserId() {
//        if (isFirebaseLogin()) { //getUserId
//            return FirebaseAuth.getInstance().getCurrentUser().getUid();
//        }
        return null;
    }

//    public static int getGroupId() {
//        if (isLogin()) { //getGroupId
//            String userCoinInfo = SharePreferencesUtil.getStringSharePreferences(MyApplication.getContext(), DataInter.KEY.USER_COIN_INFO, null);
//
//            PointDto.DataBean dataBean = new Gson().fromJson(userCoinInfo, PointDto.DataBean.class);
//            if (dataBean != null && !TextUtils.isEmpty(dataBean.getGroup_id())) {
//                return Integer.parseInt(dataBean.getGroup_id());
//            }
//        }
//        return 0;
//    }


    public static String getUserName() {
//        if (isFirebaseLogin()) { //getUserName
//            return FirebaseAuth.getInstance().getCurrentUser().getDisplayName();
//        }
        return null;
    }

    public static boolean checkAuth() {
        return isVip(BaseApplication.getAppContext());
//        return UserUtil.getGroupId() > 2 && UserUtil.getAuthTime();
//        InvitationDto.DataBean dataBean = getUserPrivilege();
//        Log.d("consumePlayCount", "checkAuth:"+new Gson().toJson(dataBean));
//        if (dataBean == null) {
//            ToastUtil.showMessage("请登录后观看");
//            return false;
//        }
//        if (dataBean.getResidue_day_number() > 0) {
//            return true;
//        }
//        if (dataBean.getWatch_num() > 0) {
//            return true;
//        }
//        return false;
    }

//    private static boolean getAuthTime() {
//        if (isLogin()) { //getAuthTime
//            String vipEndTime = getUserVipEndTime();
//            if (TextUtils.isEmpty(vipEndTime)) {
//                return false;
//            }
//            return (Long.parseLong(vipEndTime) > (System.currentTimeMillis() / 1000));
//        }
//        return false;
//    }

//    public static void saveUserCoin(PointDto.DataBean data) {
//        String info = new Gson().toJson(data);
//        SharePreferencesUtil.setStringSharePreferences(MyApplication.getContext(), DataInter.KEY.USER_COIN_INFO, info);
//    }
//
//    public static void saveUserPrivilege(InvitationDto.DataBean data) {
//        String info = new Gson().toJson(data);
//        SharePreferencesUtil.setStringSharePreferences(MyApplication.getContext(), DataInter.KEY.USER_PRIVILEGE_INFO, info);
//    }
//
//    public static InvitationDto.DataBean getUserPrivilege() {
//        String userId = getUserId();
//        String privilegeInfo = SharePreferencesUtil.getStringSharePreferences(MyApplication.getContext(), DataInter.KEY.USER_PRIVILEGE_INFO, null);
//        if (TextUtils.isEmpty(userId) && TextUtils.isEmpty(privilegeInfo)) {
//            InvitationDto.DataBean dataBean = new InvitationDto.DataBean();
//            dataBean.setWatch_num(5);
//            saveUserPrivilege(dataBean);
//            privilegeInfo = new Gson().toJson(dataBean);
//            Log.d("consumePlayCount", "getUserPrivilege:"+privilegeInfo);
//            return dataBean;
//        }
//        if (TextUtils.isEmpty(privilegeInfo)) {
//            return null;
//        }
//        InvitationDto.DataBean dataBean = new Gson().fromJson(privilegeInfo, InvitationDto.DataBean.class);
//        return dataBean;
//    }
//
//    public static String getUserVipEndTime() {
//        String userId = getUserId();
//        String coinInfo = SharePreferencesUtil.getStringSharePreferences(MyApplication.getContext(), DataInter.KEY.USER_COIN_INFO, null);
//        if (TextUtils.isEmpty(coinInfo) || TextUtils.isEmpty(userId)) {
//            return "";
//        }
//        PointDto.DataBean dataBean = new Gson().fromJson(coinInfo, PointDto.DataBean.class);
//        if (userId.equals(dataBean.getUser_id())) {
//            return dataBean.getUser_end_time();
//        }
//        return "";
//    }
//
//    public static String getUserCoin() {
//        String userId = getUserId();
//        String coinInfo = SharePreferencesUtil.getStringSharePreferences(MyApplication.getContext(), DataInter.KEY.USER_COIN_INFO, null);
//        if (TextUtils.isEmpty(coinInfo) || TextUtils.isEmpty(userId)) {
//            return "";
//        }
//        PointDto.DataBean dataBean = new Gson().fromJson(coinInfo, PointDto.DataBean.class);
//        if (userId.equals(dataBean.getUser_id())) {
//            return dataBean.getUser_points();
//        }
//        return null;
//    }
//
//    /**
//     * 兑换会员后，更新本地金币信息
//     *
//     * @param data
//     */
//    public static void updateUserCoin(BuyVipDto data) {
//        String userId = getUserId();
//        String coinInfo = SharePreferencesUtil.getStringSharePreferences(MyApplication.getContext(), DataInter.KEY.USER_COIN_INFO, null);
//        if (TextUtils.isEmpty(coinInfo) || TextUtils.isEmpty(userId)) {
//            return;
//        }
//        Gson gson = new Gson();
//        PointDto.DataBean dataBean = gson.fromJson(coinInfo, PointDto.DataBean.class);
//        if (dataBean != null && !TextUtils.isEmpty(dataBean.getUser_id()) && userId.equals(dataBean.getUser_id())) {
//            dataBean.setUser_points(data.getData().getItem_data().getUser_points());
//            dataBean.setGroup_id(data.getData().getItem_data().getGroup_id());
//            dataBean.setUser_end_time(data.getData().getItem_data().getUser_end_time());
//            saveUserCoin(dataBean);
//        }
//    }

    /**
     * 更新用户头像
     *
     * @param
     */
//    public static void updateUserIcon(String portraitThumb) {
//        String userInfo = UserUtil.getUserInfo(MyApplication.getContext());
//        Gson gson = new Gson();
//        if (!TextUtils.isEmpty(userInfo)) {
//            LoginDto.DataBean dataBean = gson.fromJson(userInfo, LoginDto.DataBean.class);
//            if (dataBean != null) {
//                dataBean.setUser_portrait_thumb(portraitThumb);
//                String newUserInfo = gson.toJson(dataBean);
//                saveUserInfo(MyApplication.getContext(), dataBean, newUserInfo);
//            }
//        }
//    }

    public static String getUserIcon() {
//        FirebaseUser mUser  = FirebaseAuth.getInstance().getCurrentUser();
//        if(mUser != null && mUser.getPhotoUrl() != null) {
//            return mUser.getPhotoUrl().toString();
//        }
        return null;
    }

    public static Uri getPhotoUrl() {
//        FirebaseUser mUser  = FirebaseAuth.getInstance().getCurrentUser();
//        if(mUser == null)
            return null;
//        else
//            return mUser.getPhotoUrl();
    }
}
