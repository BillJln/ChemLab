package com.jit.funchemistry.ui.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestFutureTarget;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.jit.funchemistry.R;
import com.jit.funchemistry.model.domain.RecommendExperiment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author XiaNingIf
 * @date 2021/3/20
 */
public class ExperimentAdapter extends RecyclerView.Adapter<ExperimentAdapter.InnerHolder> {
    private List<RecommendExperiment> mData = new ArrayList<>();
    private onExperimentItemClickListener mExperimentItemClickListener = null;

    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recommend_experiment,parent,false);
        return new InnerHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        RecommendExperiment recommendExperiment = mData.get(position);
        holder.setData(recommendExperiment);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mExperimentItemClickListener.onExperimentItemClick(recommendExperiment,position);
            }
        });
    }

    public void setData(List<RecommendExperiment> mData) {
        this.mData.clear();
        this.mData.addAll(mData);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image_recommend_experiment)
        ImageView mImageRecommendExperiment;
        @BindView(R.id.recommend_experiment)
        TextView mRecommendExperiment;
        @BindView(R.id.text_from)
        TextView mTextFrom;
        @BindView(R.id.number_broadcast)
        TextView mNumberBroadcast;

        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void setData(RecommendExperiment recommendExperiment) {
            Context context = itemView.getContext();
            RoundedCorners roundedCorners= new RoundedCorners(25);
            RequestOptions options= RequestOptions.bitmapTransform(roundedCorners).override(300, 300);
            Glide.with(context).load(recommendExperiment.getImage_URL()).listener(new RequestListener<Drawable>() {
                @Override
                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                    return false;
                }

                @Override
                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                    if (mImageRecommendExperiment == null) {
                        return false;
                    }
                    if (mImageRecommendExperiment.getScaleType() != ImageView.ScaleType.FIT_XY) {
                        mImageRecommendExperiment.setScaleType(ImageView.ScaleType.FIT_XY);
                    }
//                    ViewGroup.LayoutParams params = mImageRecommendExperiment.getLayoutParams();
//                    int vw = mImageRecommendExperiment.getWidth() - mImageRecommendExperiment.getPaddingLeft() - mImageRecommendExperiment.getPaddingRight();
//                    float scale = (float) vw / (float) resource.getIntrinsicWidth();
//                    int vh = Math.round(resource.getIntrinsicHeight() * scale);
//                    params.height = vh + mImageRecommendExperiment.getPaddingTop() + mImageRecommendExperiment.getPaddingBottom();
//                    mImageRecommendExperiment.setLayoutParams(params);
                    return false;
                }
            }).apply(options).into(mImageRecommendExperiment);
            mRecommendExperiment.setText(recommendExperiment.getLesson_Name());
            mNumberBroadcast.setText(String.format("播放量：%d", recommendExperiment.getWatched_Number()));
            mTextFrom.setText("出品方：" + recommendExperiment.getMaker());
        }
    }

    public void setOnExperimentItemClickListener(ExperimentAdapter.onExperimentItemClickListener listener){
        this.mExperimentItemClickListener = listener;
    }

    public interface onExperimentItemClickListener{
        void onExperimentItemClick(RecommendExperiment item,int i);
    }
}
