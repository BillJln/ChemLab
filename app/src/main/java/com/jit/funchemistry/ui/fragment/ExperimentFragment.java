package com.jit.funchemistry.ui.fragment;

import android.graphics.Rect;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jit.funchemistry.R;
import com.jit.funchemistry.base.BaseFragment;
import com.jit.funchemistry.model.domain.DataBean;
import com.jit.funchemistry.model.domain.RecommendExperiment;
import com.jit.funchemistry.ui.adapter.ExperimentAdapter;
import com.jit.funchemistry.ui.adapter.ImageExperimentAdapter;
import com.jit.funchemistry.ui.adapter.ImageLessonAdapter;
import com.jit.funchemistry.ui.adapter.LooperPagerAdapter;
import com.jit.funchemistry.utils.KeyBoardUtil;
import com.jit.funchemistry.utils.LogUtils;
import com.jit.funchemistry.utils.TurnToPageUtil;
import com.jit.funchemistry.view.IExperimentCallback;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.youth.banner.Banner;
import com.youth.banner.indicator.CircleIndicator;

import java.util.ArrayList;

import butterknife.BindView;

import static com.jit.funchemistry.utils.PUtil.dip2px;

/**
 * @author XiaNingIf
 * @date 2021/3/18
 */
public class ExperimentFragment extends BaseFragment implements IExperimentCallback, ExperimentAdapter.onExperimentItemClickListener {
    @BindView(R.id.search_container)
    RelativeLayout mSearchContainer;
    @BindView(R.id.search_input_box)
    EditText mSearchInputBox;
    @BindView(R.id.search_btn)
    TextView mSearchBtn;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
//    @BindView(R.id.looper_pager)
//    AutoLoopViewPager mLooperPager;
//    @BindView(R.id.looper_point_container)
//    LinearLayout mLooperPointContainer;
    @BindView(R.id.recommend_experiment_container)
    RecyclerView mRecommendExperimentContainer;
    @BindView(R.id.include)
    LinearLayout mInclude;
    @BindView(R.id.search_clean_btn)
    ImageView mSearchCleanBtn;
    @BindView(R.id.banner)
    Banner mBanner;

    private ArrayList<RecommendExperiment> experimentList = new ArrayList<>();
    private boolean isFirstEnter = true;
    private ExperimentAdapter mExperimentAdapter;
    private LooperPagerAdapter mLooperPagerAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initExperimentList();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected void initView(View rootView) {
        mExperimentAdapter = new ExperimentAdapter();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecommendExperimentContainer.setLayoutManager(layoutManager);
        mRecommendExperimentContainer.setAdapter(mExperimentAdapter);
        mRecommendExperimentContainer.addItemDecoration(new RecyclerView.ItemDecoration(){
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                outRect.top = dip2px(getContext(), 3.0f);
                outRect.bottom = dip2px(getContext(), 3.0f);
                outRect.left = dip2px(getContext(), 4.5f);
                outRect.right = dip2px(getContext(), 4.5f);
            }
        });
//        mRefreshLayout.setRefreshContent(new ClassicsHeader(getContext()),0,0);
//        mRefreshLayout.setRefreshContent(new ClassicsFooter(getContext()),0,0);
        mRefreshLayout.setEnableLoadMoreWhenContentNotFull(false);
        //上下刷新
        if (isFirstEnter){
            isFirstEnter = false;
            mRefreshLayout.setOnRefreshListener(refreshLayout ->
                    refreshLayout.finishLoadMore(2000));
            mRefreshLayout.setOnLoadMoreListener(refreshLayout ->
                    refreshLayout.finishLoadMore(2000));
        }
        mExperimentAdapter.setData(experimentList);

        //mLooperPagerAdapter = new LooperPagerAdapter();
        //mLooperPager.setAdapter(mLooperPagerAdapter);
        mBanner.addBannerLifecycleObserver(this)
                .setAdapter(new ImageExperimentAdapter(DataBean.getTestData1()))
                .setIndicator(new CircleIndicator(getContext()));

        //无回调测试用接口
        setUpstate(State.SUCCESS);
    }

    private boolean hasInput(boolean containSpace) {
        if (containSpace){
            return mSearchInputBox.getText().toString().length()>0;
        }else{
            return mSearchInputBox.getText().toString().trim().length()>0;
        }
    }

    @Override
    protected void initListener() {

        //发起搜索
        mSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hasInput(false)){

                }else{
                    KeyBoardUtil.hide(getContext(),v);
                }
            }
        });

        //清除输入框里的内容
        mSearchCleanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSearchInputBox.setText("");

            }
        });

        //监听输入框里的内容变化
        mSearchInputBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //变化的时候的通知
                //LogUtils.e(SearchFragment.this,"input text ===> " + s.toString().trim());
                //如果长度不为零，那么显示删除按钮
                //否则隐藏删除按钮
                mSearchCleanBtn.setVisibility(hasInput(true)?View.VISIBLE:View.GONE);
                mSearchBtn.setText(hasInput(false)?"搜索":"取消");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mSearchInputBox.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE
                    //&& mSearchPresenter != null
                ){
                    //判断拿到的内容时候付为空
                    String keyword = v.getText().toString().trim();
                    if (TextUtils.isEmpty(keyword)){
                        return false;
                    }else{
                        //发起搜索
                        //mSearchPresenter.doSearch(keyword);
                        //toSearch(keyword);
                    }
                }
                return false;
            }
        });

        //TODO:搜索的处理
//        mSearchResultAdapter.setOnListItemClickListener(new LinearItemContentAdapter.OnListItemClickListener() {
//            @Override
//            public void onItemClick(IBaseInfo item) {
//                //搜索列表的内容被点击了
//                TicketUtil.toTicketPage(getContext(),item);
//            }
//        });

//        mLooperPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                if (mLooperPagerAdapter.getDataSize()==0){
//                    return;
//                }
//                int targetPosition = position%mLooperPagerAdapter.getDataSize();
//                updateLooperIndicator(targetPosition);
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });

//        mBanner.setOnBannerListener(((data, position) -> {
//            TurnToPageUtil(getContext(),)
//        }))

        mExperimentAdapter.setOnExperimentItemClickListener(this);

    }

    @Override
    protected void initPresenter() {

    }

    @Override
    public void onError() {
        setUpstate(State.ERROR);
    }

    @Override
    public void onLoading() {
        setUpstate(State.LOADING);
    }

    @Override
    public void onEmpty() {
        setUpstate(State.EMPTY);
    }

    @Override
    protected View loadRootView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.base_fragment_layout,container,false);
    }


    private void initExperimentList() {
        experimentList.add(new RecommendExperiment(R.mipmap.chemical_1, "过氧化氢分解制取氧气", "人教教材", 5654));
        experimentList.add(new RecommendExperiment(R.mipmap.chemical_2, "铁丝在氧气中燃烧", "人教教材", 7615));
        experimentList.add(new RecommendExperiment(R.mipmap.chemical_3, "配置氯化钠溶液", "人教教材", 7321));
        experimentList.add(new RecommendExperiment(R.mipmap.chemical_4, "浓硫酸的稀释", "人教教材", 7832));
        experimentList.add(new RecommendExperiment(R.mipmap.chemical_5, "氢氧化钠，氯化钡与硫酸铜反应", "人教教材", 5214));
        experimentList.add(new RecommendExperiment(R.mipmap.experiment, "一氧化碳还原四氧化三铁", "人教教材", 3533));
        experimentList.add(new RecommendExperiment(R.mipmap.experiment, "苛性钠暴露在空气中变质", "人教教材", 10472));
        experimentList.add(new RecommendExperiment(R.mipmap.experiment, "碳酸钠与稀盐酸反应", "人教教材", 5738));
        experimentList.add(new RecommendExperiment(R.mipmap.experiment, "硫酸钠和氯化钡", "人教教材", 2432));
        experimentList.add(new RecommendExperiment(R.mipmap.experiment, "水在直流电的作用下分解", "人教教材", 5732));
        experimentList.add(new RecommendExperiment(R.mipmap.experiment, "一氧化碳在氧气中燃烧", "人教教材", 5734));
        experimentList.add(new RecommendExperiment(R.mipmap.experiment, "锌和硫酸铜溶液反应", "人教教材", 8615));
        experimentList.add(new RecommendExperiment(R.mipmap.experiment, "酒精在空气中燃烧", "人教教材", 7324));
    }

    @Override
    protected int getRootViewResId() {
        return R.layout.fragment_experiment;
    }


    @Override
    public void onExperimentLoaded(RecommendExperiment recommendExperiment) {
        setUpstate(State.SUCCESS);
        LogUtils.e(this,"onLessonLoaded.....");
        initExperimentList();
        mExperimentAdapter.setData(experimentList);
    }

    @Override
    public void onResume() {
        super.onResume();
        //可见的时候我们去调用开始轮播
    }

    @Override
    public void onStart() {
        super.onStart();
        //开始轮播
        mBanner.start();
    }

    @Override
    public void onPause() {
        super.onPause();
        //不可见的时候我们去暂停轮播
//        mLooperPager.stopLoop();
    }

    @Override
    public void onStop() {
        super.onStop();
        //停止轮播
        mBanner.stop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //销毁
        mBanner.destroy();
    }

    @Override
    public void onExperimentItemClick(RecommendExperiment item,int position) {
        TurnToPageUtil.ToExperimentPage(getContext(),item,position);
    }

//    /**
//     * 切换指示器
//     * @param targetPosition
//     */
//    private void updateLooperIndicator(int targetPosition) {
//        for (int i = 0; i < mLooperPointContainer.getChildCount(); i++) {
//            View point = mLooperPointContainer.getChildAt(i);
//            if ( i == targetPosition ){
//                point.setBackgroundResource(R.drawable.shape_indicator_point_selected);
//            }else{
//                point.setBackgroundResource(R.drawable.shape_indicator_point_normal);
//            }
//        }
//    }

//    @RequiresApi(api = Build.VERSION_CODES.M)
//    //@Override
//    public void onLooperListLoaded(@NotNull List<RecommendExperiment> contents) {
//        LogUtils.d(this,"looper size ---->" + contents.size());
//        mLooperPagerAdapter.setData(contents);
//        int dx = (Integer.MAX_VALUE / 2) % contents.size();
//        int targetCenterPosition = (Integer.MAX_VALUE / 2) - dx;
//        //设置到中间点
//        mLooperPager.setCurrentItem(targetCenterPosition);
//        mLooperPointContainer.removeAllViews();
//        //添加点
//        for (int i = 0; i < contents.size(); i++) {
//            View point = new View(getContext());
//            int size = SizeUtils.dip2px(getContext(), 8);
//            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(size,size);
//            layoutParams.leftMargin = SizeUtils.dip2px(getContext(),5);
//            layoutParams.rightMargin = SizeUtils.dip2px(getContext(),5);
//            point.setLayoutParams(layoutParams);
//            if ( i == 0 ){
//                point.setBackgroundResource(R.drawable.shape_indicator_point_selected);
//            }else{
//                point.setBackgroundResource(R.drawable.shape_indicator_point_normal);
//            }
//            mLooperPointContainer.addView(point);
//        }
//    }
}
