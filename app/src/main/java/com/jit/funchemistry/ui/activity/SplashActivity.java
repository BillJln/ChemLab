package com.jit.funchemistry.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.jit.funchemistry.R;
import com.jit.funchemistry.base.BaseActivity;
import com.jit.funchemistry.utils.ActivityUtil;
import com.jit.funchemistry.utils.AndroidUtil;
import com.jit.funchemistry.utils.AppPermissions;

import butterknife.BindView;
import kr.co.namee.permissiongen.PermissionFail;
import kr.co.namee.permissiongen.PermissionGen;

import static com.jit.funchemistry.utils.SharedUtil.sharedPreferences;

/**
 * @author XiaNingIf
 * @date 2021/3/18
 */
public class SplashActivity extends BaseActivity {
    @BindView(R.id.icon)
    ImageView mIcon;

    private long splashDuration = 3 * 1000L;
    private boolean isFirstEntryApp;

    private AlphaAnimation getAlphaAnimation(){
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.5f, 1.0f);
        alphaAnimation.setDuration(splashDuration);
        alphaAnimation.setFillAfter(true);
        return alphaAnimation;
    }

    private void scaleAnimation() {
        ScaleAnimation scaleAnimation = new ScaleAnimation(
                1f,
                1.05f,
                1f,
                1.05f,
                Animation.RELATIVE_TO_SELF,
                0.5f,
                Animation.RELATIVE_TO_SELF,
                0.5f
        );
        scaleAnimation.setDuration(splashDuration);
        scaleAnimation.setFillAfter(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        PermissionGen.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
//    }

//    @PermissionSuccess(requestCode = 100)
//    void permissionSuccess() {
//        intentToMainPage();
//    }

    private void intentToMainPage() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2400);
                    Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                    startActivity(intent);
                    SplashActivity.this.finish();
                    ActivityUtil.getInstance().removeActivity(SplashActivity.this);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

//    @PermissionFail(requestCode = 100)
//    void permissionFailure() {
//        Toast.makeText(this, "权限拒绝,无法正常使用", Toast.LENGTH_LONG).show();
//    }

//    /**
//     * 请求内存卡权限
//     */
//    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
//    private void requestStoragePermission() {
//        ActivityCompat.requestPermissions(SplashActivity.this
//                , AppPermissions.permission_storage,
//                AppPermissions.request_permission_storage);
//    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == AppPermissions.request_permission_storage) {
            if (AndroidUtil.verifyPermissions(grantResults)) {
                intentToMainPage();
            } else {
                Toast.makeText(this, "the permission denied!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public boolean get(){
        return sharedPreferences.getBoolean("is_first_entry_app", true);
    }
//    public void set(value){
//        SharedUtil.edit(){putBoolean("is_first_entry_app", value);
//    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView() {
        setStatusBarBackground(R.color.blue_splash);
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                PermissionGen.with(SplashActivity.this)
                        .addRequestCode(100)
                        .permissions(
                                Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.RECORD_AUDIO,
                                Manifest.permission.MODIFY_AUDIO_SETTINGS
                        )
                        .request();
            }
        }, 1000);
        mIcon.startAnimation(getAlphaAnimation());
        isFirstEntryApp = false;
        intentToMainPage();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_splash;
    }
}
