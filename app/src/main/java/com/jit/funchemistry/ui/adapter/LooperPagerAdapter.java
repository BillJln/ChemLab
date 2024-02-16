package com.jit.funchemistry.ui.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.jit.funchemistry.model.domain.RecommendExperiment;
import com.jit.funchemistry.model.domain.RecommendLesson;

import java.util.ArrayList;
import java.util.List;

/**
 * @author XiaNingIf
 * @date 2021/3/31
 */
public class LooperPagerAdapter extends PagerAdapter {
    private List<RecommendExperiment> data = new ArrayList<>();
    private OnLooperPageItemClickListener mLooperPageItemClickListener = null;

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    public int getDataSize(){
        return data.size();
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        //处理越界问题
        int realPosition = position % data.size();
        int measuredHeight = container.getMeasuredHeight();
        int measuredWidth = container.getMeasuredWidth();
        //LogUtils.d(this,"measuredHeight----->"+measuredHeight+",measuredWidth----->"+measuredWidth);
        int ivSize = (measuredHeight>measuredWidth?measuredHeight:measuredWidth)/2;
        RecommendExperiment dataBean = data.get(realPosition);
        int coverUrl = dataBean.getImage_URL();
        ImageView iv = new ImageView(container.getContext());
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mLooperPageItemClickListener != null) {
                    RecommendExperiment item = data.get(realPosition);
                    mLooperPageItemClickListener.onLooperItemClick(item);
                }
            }
        });
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        iv.setLayoutParams(layoutParams);
        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Glide.with(container.getContext()).load(coverUrl).into(iv);
        container.addView(iv);
        return iv;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return false;
    }

    public void setData(List<RecommendExperiment> contents) {
        data.clear();
        data.addAll(contents);
        notifyDataSetChanged();
    }

    public void setOnLooperPageItemClickListener(LooperPagerAdapter.OnLooperPageItemClickListener listener){
        this.mLooperPageItemClickListener = listener;
    }

    public interface OnLooperPageItemClickListener{
        void onLooperItemClick(RecommendExperiment item);
    }
}
