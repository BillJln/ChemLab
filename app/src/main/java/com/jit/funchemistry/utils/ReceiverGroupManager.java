package com.jit.funchemistry.utils;

import android.content.Context;


import com.jit.funchemistry.cover.CompleteCover;
import com.jit.funchemistry.cover.ControllerCover;
import com.jit.funchemistry.cover.ErrorCover;
import com.jit.funchemistry.cover.GestureCover;
import com.jit.funchemistry.cover.LoadingCover;
import com.kk.taurus.playerbase.receiver.GroupValue;
import com.kk.taurus.playerbase.receiver.ReceiverGroup;

import static com.jit.funchemistry.cover.DataInter.ReceiverKey.KEY_COMPLETE_COVER;
import static com.jit.funchemistry.cover.DataInter.ReceiverKey.KEY_CONTROLLER_COVER;
import static com.jit.funchemistry.cover.DataInter.ReceiverKey.KEY_ERROR_COVER;
import static com.jit.funchemistry.cover.DataInter.ReceiverKey.KEY_GESTURE_COVER;
import static com.jit.funchemistry.cover.DataInter.ReceiverKey.KEY_LOADING_COVER;


/**
 * Created by Taurus on 2018/4/18.
 */

public class ReceiverGroupManager {

    private static ReceiverGroupManager i;

    private ReceiverGroupManager(){
    }

    public static ReceiverGroupManager get(){
        if(null==i){
            synchronized (ReceiverGroupManager.class){
                if(null==i){
                    i = new ReceiverGroupManager();
                }
            }
        }
        return i;
    }

    public ReceiverGroup getLittleReceiverGroup(Context context){
        return getLiteReceiverGroup(context, null);
    }

    public ReceiverGroup getLittleReceiverGroup(Context context, GroupValue groupValue){
        ReceiverGroup receiverGroup = new ReceiverGroup(groupValue);
        receiverGroup.addReceiver(KEY_LOADING_COVER, new LoadingCover(context));
        receiverGroup.addReceiver(KEY_COMPLETE_COVER, new CompleteCover(context));
        receiverGroup.addReceiver(KEY_ERROR_COVER, new ErrorCover(context));
        return receiverGroup;
    }

    public ReceiverGroup getLiteReceiverGroup(Context context){
        return getLiteReceiverGroup(context, null);
    }

    public ReceiverGroup getLiteReceiverGroup(Context context, GroupValue groupValue){
        ReceiverGroup receiverGroup = new ReceiverGroup(groupValue);
        receiverGroup.addReceiver(KEY_LOADING_COVER, new LoadingCover(context));
        receiverGroup.addReceiver(KEY_CONTROLLER_COVER, new ControllerCover(context));
        receiverGroup.addReceiver(KEY_COMPLETE_COVER, new CompleteCover(context));
        receiverGroup.addReceiver(KEY_ERROR_COVER, new ErrorCover(context));
        return receiverGroup;
    }

    public ReceiverGroup getReceiverGroup(Context context){
        return getReceiverGroup(context, null);
    }

    public ReceiverGroup getReceiverGroup(Context context, GroupValue groupValue){
        ReceiverGroup receiverGroup = new ReceiverGroup(groupValue);
        receiverGroup.addReceiver(KEY_LOADING_COVER, new LoadingCover(context));
        receiverGroup.addReceiver(KEY_CONTROLLER_COVER, new ControllerCover(context));
        receiverGroup.addReceiver(KEY_GESTURE_COVER, new GestureCover(context));
        receiverGroup.addReceiver(KEY_COMPLETE_COVER, new CompleteCover(context));
        receiverGroup.addReceiver(KEY_ERROR_COVER, new ErrorCover(context));
        return receiverGroup;
    }

}
