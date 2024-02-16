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
import com.jit.funchemistry.model.domain.RecommendLesson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author XiaNingIf
 * @date 2021/3/20
 */
public class LessonAdapter extends RecyclerView.Adapter<LessonAdapter.InnerHolder> {
    private List<RecommendLesson> mData = new ArrayList<>();
    private OnLessonItemClickListener mLessonItemClickListener = null;

    @NonNull
    @Override
    public LessonAdapter.InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recommend_lesson, parent, false);
        return new InnerHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull LessonAdapter.InnerHolder holder, int position) {
        RecommendLesson recommendLesson = mData.get(position);
        holder.setData(recommendLesson);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLessonItemClickListener.onLessonItemClick(recommendLesson);
            }
        });
    }

    public void setData(ArrayList<RecommendLesson> mList){
        this.mData.clear();
        this.mData.addAll(mList);
        notifyDataSetChanged();
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

    public class InnerHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.lesson_container)
        ConstraintLayout mLessonContainer;
        @BindView(R.id.image_recommend_experiment)
        ImageView mImageRecommendLesson;
        @BindView(R.id.recommend_experiment)
        TextView mRecommendLesson;
        @BindView(R.id.text_from)
        TextView mTextFrom;
        @BindView(R.id.number_broadcast)
        TextView mNumberBroadcast;


        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        @SuppressLint({"DefaultLocale", "SetTextI18n"})
        public void setData(RecommendLesson recommendLesson){
            Context context = itemView.getContext();
            RoundedCorners roundedCorners= new RoundedCorners(25);
            RequestOptions options= RequestOptions.bitmapTransform(roundedCorners).override(300, 300);
            Glide.with(context).load(recommendLesson.getImage_URL()).listener(new RequestListener<Drawable>() {
                @Override
                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                    return false;
                }

                @Override
                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                    if (mImageRecommendLesson == null) {
                        return false;
                    }
                    if (mImageRecommendLesson.getScaleType() != ImageView.ScaleType.FIT_XY) {
                        mImageRecommendLesson.setScaleType(ImageView.ScaleType.FIT_XY);
                    }
//                    ViewGroup.LayoutParams params = mImageRecommendExperiment.getLayoutParams();
//                    int vw = mImageRecommendExperiment.getWidth() - mImageRecommendExperiment.getPaddingLeft() - mImageRecommendExperiment.getPaddingRight();
//                    float scale = (float) vw / (float) resource.getIntrinsicWidth();
//                    int vh = Math.round(resource.getIntrinsicHeight() * scale);
//                    params.height = vh + mImageRecommendExperiment.getPaddingTop() + mImageRecommendExperiment.getPaddingBottom();
//                    mImageRecommendExperiment.setLayoutParams(params);
                    return false;
                }
            }).apply(options).into(mImageRecommendLesson);
            mRecommendLesson.setText(recommendLesson.getLesson_Name());
            mNumberBroadcast.setText(String.format("播放量：%d", recommendLesson.getWatched_Number()));
            mTextFrom.setText("出品方：" + recommendLesson.getMaker());

        }
    }

    public void setOnLessonItemClickListener(LessonAdapter.OnLessonItemClickListener listener){
        this.mLessonItemClickListener = listener;
    }

    public interface OnLessonItemClickListener{
        void onLessonItemClick(RecommendLesson item);
    }
}
