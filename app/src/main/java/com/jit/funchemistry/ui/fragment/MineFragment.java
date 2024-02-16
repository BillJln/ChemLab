package com.jit.funchemistry.ui.fragment;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jit.funchemistry.R;
import com.jit.funchemistry.base.BaseFragment;
import com.jit.funchemistry.ui.activity.DownloadActivity;
import com.jit.funchemistry.ui.activity.FavouriteActivity;
import com.jit.funchemistry.ui.activity.FeedbackActivity;
import com.jit.funchemistry.ui.activity.HistoryActivity;
import com.jit.funchemistry.ui.activity.LoginActivity;
import com.jit.funchemistry.ui.activity.NoLoginActivity;
import com.jit.funchemistry.ui.activity.NoticeActivity;
import com.jit.funchemistry.ui.activity.SoftRelationActivity;
import com.jit.funchemistry.ui.activity.MyLearningActivity;
import com.jit.funchemistry.utils.TurnToPageUtil;

import butterknife.BindView;

import static com.jit.funchemistry.base.BaseApplication.isLogin;

/**
 * @author XiaNingIf
 * @date 2021/3/18
 */
public class MineFragment extends BaseFragment {
    @BindView(R.id.ivMore)
    ImageView mIvMore;
    @BindView(R.id.ivAvatar)
    de.hdodenhof.circleimageview.CircleImageView mIvAvatar;
    @BindView(R.id.tvLoginTips)
    TextView mTvLoginTips;
    @BindView(R.id.viewVerticalLine)
    View mViewVerticalLine;
    @BindView(R.id.tvFavorites)
    TextView mTvFavorites;
    @BindView(R.id.tvCache)
    TextView mTvCache;
    @BindView(R.id.viewHorizontalLine)
    View mViewHorizontalLine;
    @BindView(R.id.llScrollViewContent)
    LinearLayout mLlScrollViewContent;
    @BindView(R.id.tvWatchRecord)
    TextView mTvWatchRecord;
    @BindView(R.id.tvNotificationToggle)
    TextView mTvNotificationToggle;
    @BindView(R.id.tvFeedback)
    TextView mTvFeedback;
    @BindView(R.id.tvSoftRelation)
    TextView mTvSoftRelation;
    @BindView(R.id.tvContribute)
    TextView mTvContribute;

    @Override
    protected void initView(View rootView) {
        super.initView(rootView);
    }

    @Override
    protected void initListener() {
        setUpstate(State.SUCCESS);
        mIvAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        mTvContribute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isLogin){
                    Intent intent = new Intent(getContext(), MyLearningActivity.class);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(getContext(), NoLoginActivity.class);
                    startActivity(intent);
                }

            }
        });

        mTvFavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), FavouriteActivity.class);
                startActivity(intent);
            }
        });

        mTvSoftRelation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SoftRelationActivity.class);
                startActivity(intent);
            }
        });

        mTvFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), FeedbackActivity.class);
                startActivity(intent);
            }
        });

        mTvNotificationToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), NoticeActivity.class);
                startActivity(intent);
            }
        });

        mTvWatchRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), HistoryActivity.class);
                startActivity(intent);
            }
        });

        mTvCache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DownloadActivity.class);
                startActivity(intent);
            }
        });

        mIvMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TurnToPageUtil.ToInformationPage(getContext());
            }
        });
    }

    @Override
    protected void initPresenter() {
        super.initPresenter();
    }

    @Override
    protected View loadRootView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.base_fragment_layout,container,false);
    }

    @Override
    protected int getRootViewResId() {
        if (!isLogin){
            return R.layout.fragment_mine;
        }else{
            return R.layout.fragment_login_in;
        }

    }
}
