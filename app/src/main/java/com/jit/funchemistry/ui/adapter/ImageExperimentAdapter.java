package com.jit.funchemistry.ui.adapter;

import android.view.ViewGroup;
import android.widget.ImageView;

import com.jit.funchemistry.model.domain.DataBean;
import com.jit.funchemistry.viewholder.ImageHolder;
import com.youth.banner.adapter.BannerAdapter;

import java.util.List;

/**
 * @author XiaNingIf
 * @date 2021/3/31
 */
public class ImageExperimentAdapter extends BannerAdapter<DataBean, ImageHolder> {
    public ImageExperimentAdapter(List<DataBean> mDatas) {
        super(mDatas);
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
        holder.imageView.setImageResource(data.getImage_URL());
    }
}
