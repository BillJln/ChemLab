package com.jit.funchemistry.utils;

import android.content.Context;

import com.unity3d.player.IUnityPlayerLifecycleEvents;
import com.unity3d.player.UnityPlayer;

/**
 * @author created by XiaNingIf
 * @data 2021/4/4
 */
public class MyUnityPlayer extends UnityPlayer {

    public MyUnityPlayer(Context context, IUnityPlayerLifecycleEvents iUnityPlayerLifecycleEvents) {
        super(context, iUnityPlayerLifecycleEvents);
    }

    @Override
    protected void kill() {
        super.kill();
    }
}
