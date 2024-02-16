package com.jit.funchemistry.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.jit.funchemistry.R;
import com.jit.funchemistry.model.domain.DataBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author created by XiaNingIf
 * @data 2021/4/3
 */
public class HistoryLessonAdapter extends RecyclerView.Adapter<HistoryLessonAdapter.InnerHolder> {
    private List<DataBean> mData = new ArrayList<>();
    private HistoryLessonAdapter.OnHistoryLessonItemClickListener mHistoryLessonItemClickListener = null;

    @NonNull
    @Override
    public HistoryLessonAdapter.InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history_lesson, parent, false);
        return new HistoryLessonAdapter.InnerHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryLessonAdapter.InnerHolder holder, int position) {
        DataBean DataBean = mData.get(position);
        holder.setData(DataBean);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHistoryLessonItemClickListener.onLessonItemClick(DataBean);
            }
        });
    }


//    /**
//     * 加载更多的内容
//     *
//     * @param moreResult
//     */
//    public void onMoreLoaded(@NotNull OnSellContent moreResult) {
//        List<OnSellContent.DataBean.TbkDgOptimusMaterialResponseBean.ResultListBean.MapDataBean> moreData = moreResult.getData().getTbk_dg_optimus_material_response().getResult_list().getMap_data();
//        //原来数据的长度
//        int oldDataSize = this.mData.size();
//        this.mData.addAll(moreData);
//        notifyItemRangeChanged(oldDataSize-1,moreData.size());
//    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setData(ArrayList<DataBean> mList) {
        this.mData.clear();
        this.mData.addAll(mList);
        notifyDataSetChanged();
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image_recommend_experiment)
        ImageView mImageDataBean;
        @BindView(R.id.recommend_experiment)
        TextView mDataBean;
        @BindView(R.id.text_from)
        TextView mTextFrom;
        @BindView(R.id.date_history_lesson)
        TextView mData;
        


        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        @SuppressLint({"DefaultLocale", "SetTextI18n"})
        public void setData(DataBean DataBean){
            Context context = itemView.getContext();
            RoundedCorners roundedCorners= new RoundedCorners(25);
            RequestOptions options= RequestOptions.bitmapTransform(roundedCorners).override(300, 300);
            Glide.with(context).load(DataBean.getImage_URL()).listener(new RequestListener<Drawable>() {
                @Override
                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                    return false;
                }

                @Override
                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                    if (mImageDataBean == null) {
                        return false;
                    }
                    if (mImageDataBean.getScaleType() != ImageView.ScaleType.FIT_XY) {
                        mImageDataBean.setScaleType(ImageView.ScaleType.FIT_XY);
                    }
//                    ViewGroup.LayoutParams params = mImageRecommendExperiment.getLayoutParams();
//                    int vw = mImageRecommendExperiment.getWidth() - mImageRecommendExperiment.getPaddingLeft() - mImageRecommendExperiment.getPaddingRight();
//                    float scale = (float) vw / (float) resource.getIntrinsicWidth();
//                    int vh = Math.round(resource.getIntrinsicHeight() * scale);
//                    params.height = vh + mImageRecommendExperiment.getPaddingTop() + mImageRecommendExperiment.getPaddingBottom();
//                    mImageRecommendExperiment.setLayoutParams(params);
                    return false;
                }
            }).apply(options).into(mImageDataBean);
            mDataBean.setText(DataBean.getLesson_Name());
            mTextFrom.setText("出品方：" + DataBean.getMaker());
            mData.setText(DataBean.getToday());

        }
    }

    public void setOnHistoryLessonItemClickListener(HistoryLessonAdapter.OnHistoryLessonItemClickListener listener){
        this.mHistoryLessonItemClickListener = listener;
    }

    public interface OnHistoryLessonItemClickListener{
        void onLessonItemClick(DataBean item);
    }
}
