package com.jit.funchemistry.ui.activity;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;

import com.unity3d.player.UnityPlayer;
import com.unity3d.player.UnityPlayerActivity;

/**
 * @author XiaNingIf
 * @date 2021/3/31
 */
public class UnityActivity extends UnityPlayerActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static void backToShelf() {
        // TODO Auto-generated method stub
        //Log.i("unity","finish unityPlayer ");
        UnityPlayer.currentActivity.finish();
    }

}
