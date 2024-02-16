package com.jit.funchemistry.view;

import com.jit.funchemistry.base.IBaseCallBack;
import com.jit.funchemistry.model.domain.RecommendLesson;

/**
 * @author XiaNingIf
 * @date 2021/3/20
 */
public interface ILessonCallback extends IBaseCallBack {
    /**
     * 加载课程界面信息
     * @param recommendLesson
     */
    void onLessonLoaded(RecommendLesson recommendLesson);
}
