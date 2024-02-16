package com.jit.funchemistry.ui.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jit.funchemistry.model.domain.DataBean;
import com.jit.funchemistry.model.domain.RecommendLesson;
import com.jit.funchemistry.viewholder.ImageHolder;
import com.youth.banner.adapter.BannerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author XiaNingIf
 * @date 2021/3/31
 */
public class ImageLessonAdapter extends BannerAdapter<DataBean, ImageHolder> {
    private ImageLessonAdapter.OnLessonItemClickListener mLessonItemClickListener = null;
    private List<DataBean> mData = new ArrayList<>();

    public ImageLessonAdapter(List<DataBean> mDatas) {
        super(mDatas);
        mData = mDatas;
    }

    //更新数据
    public void updateData(List<DataBean> data) {
        //这里的代码自己发挥，比如如下的写法等等
        mDatas.clear();
        mDatas.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public ImageHolder onCreateHolder(ViewGroup parent, int viewType) {
        ImageView imageView = new ImageView(parent.getContext());
        //注意，必须设置为match_parent，这个是viewpager2强制要求的
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        imageView.setLayoutParams(params);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return new ImageHolder(imageView);
    }

    @Override
    public void onBindView(ImageHolder holder, DataBean data, int position, int size) {
        DataBean dataBean = mData.get(position);
        holder.imageView.setImageResource(data.getImage_URL());
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLessonItemClickListener.onLessonItemClick(dataBean);
            }
        });
    }

    public void setOnLessonItemClickListener(ImageLessonAdapter.OnLessonItemClickListener listener){
        this.mLessonItemClickListener = listener;
    }

    public interface OnLessonItemClickListener{
        void onLessonItemClick(DataBean item);
    }
}
