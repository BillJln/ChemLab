package com.jit.funchemistry.utils;

import android.content.Context;
import android.content.Intent;

import com.jit.funchemistry.model.domain.DataBean;
import com.jit.funchemistry.model.domain.RecommendExperiment;
import com.jit.funchemistry.model.domain.RecommendLesson;
import com.jit.funchemistry.presenter.ILessonPresenter;
import com.jit.funchemistry.presenter.IVideoPresenter;
import com.jit.funchemistry.ui.activity.BaseVideoActivity;
import com.jit.funchemistry.ui.activity.InformationActivity;
import com.jit.funchemistry.ui.activity.NoLoginActivity;
import com.jit.funchemistry.ui.activity.UnityActivity;
import com.unity3d.player.UnityPlayer;

import static com.jit.funchemistry.base.BaseApplication.isLogin;

/**
 * @author created by XiaNingIf
 * @data 2021/3/23
 */
public class TurnToPageUtil {
    public static void toVideoPage(Context context, RecommendLesson recommendLesson){
        String url = recommendLesson.getVideo_URL();
        int stringID = recommendLesson.getStringID();
        IVideoPresenter videoPresenter = PresenterManager.getInstance().getVideoPresenter();
        videoPresenter.getVideo(url,stringID);
        context.startActivity(new Intent(context, BaseVideoActivity.class));
    }

    public static void toVideoPage2(Context context, DataBean dataBean){
        String url = dataBean.getVideo_URL();
        int stringID = dataBean.getStringID();
        IVideoPresenter videoPresenter = PresenterManager.getInstance().getVideoPresenter();
        videoPresenter.getVideo(url,stringID);
        context.startActivity(new Intent(context, BaseVideoActivity.class));
    }

    public static void ToExperimentPage(Context context, RecommendExperiment item, int position) {
        //UnityPlayer.UnitySendMessage("AndroidMessageAccepter","message",position+"");
        context.startActivity(new Intent(context, UnityActivity.class));
    }

    public static void ToInformationPage(Context context) {
        if (isLogin){
            context.startActivity(new Intent(context, InformationActivity.class));
        }else{
            context.startActivity(new Intent(context, NoLoginActivity.class));
        }

    }
}
