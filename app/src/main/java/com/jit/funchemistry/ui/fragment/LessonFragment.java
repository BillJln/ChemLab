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
import android.view.ViewTreeObserver;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.jit.funchemistry.R;
import com.jit.funchemistry.base.BaseFragment;
import com.jit.funchemistry.model.domain.DataBean;
import com.jit.funchemistry.model.domain.RecommendLesson;
import com.jit.funchemistry.ui.adapter.ImageLessonAdapter;
import com.jit.funchemistry.ui.adapter.LessonAdapter;
import com.jit.funchemistry.utils.KeyBoardUtil;
import com.jit.funchemistry.utils.KeyboardStateObserver;
import com.jit.funchemistry.utils.LogUtils;
import com.jit.funchemistry.utils.TurnToPageUtil;
import com.jit.funchemistry.view.ILessonCallback;
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
public class LessonFragment extends BaseFragment implements ILessonCallback, LessonAdapter.OnLessonItemClickListener, ImageLessonAdapter.OnLessonItemClickListener {
    @BindView(R.id.search_container)
    RelativeLayout mSearchContainer;
    @BindView(R.id.search_input_box)
    EditText mSearchInputBox;
    @BindView(R.id.search_btn)
    TextView mSearchBtn;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
//    @BindView(R.id.looper_pager)
//    ImageView mLooperPager;
//    @BindView(R.id.looper_point_container)
//    LinearLayout mLooperPointContainer;
    @BindView(R.id.recommend_experiment_container)
    RecyclerView mRecommendLessonContainer;
    @BindView(R.id.search_clean_btn)
    ImageView mSearchCleanBtn;
    @BindView(R.id.banner)
    Banner mBanner;
//    @BindView(R.id.nav_view)
//    BottomNavigationView bottomNavigationView;

    private ArrayList<RecommendLesson> lessonList = new ArrayList<>();
    private boolean isFirstEnter = true;
    private LessonAdapter mLessonAdapter;
    private ImageLessonAdapter mImageLessonAdapter;
    //public static final int DEFAULT_SPAN_COUNT = 1;


    //仅作为界面加载的测试使用
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initLessonList();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected void initView(View rootView) {
        mLessonAdapter = new LessonAdapter();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecommendLessonContainer.setLayoutManager(layoutManager);
        mRecommendLessonContainer.setAdapter(mLessonAdapter);
        mRecommendLessonContainer.addItemDecoration(new RecyclerView.ItemDecoration(){
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
        mLessonAdapter.setData(lessonList);

        mImageLessonAdapter = new ImageLessonAdapter(DataBean.getTestData2());
        mBanner.addBannerLifecycleObserver(this)
                .setAdapter(mImageLessonAdapter)
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
                //bottomNavigationView.setVisibility(View.GONE);
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

        mLessonAdapter.setOnLessonItemClickListener(this);

        mImageLessonAdapter.setOnLessonItemClickListener(this);
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

    @Override
    protected int getRootViewResId() {
        return R.layout.fragment_lesson;
    }

    private void initLessonList() {
        lessonList.add(new RecommendLesson(R.mipmap.experiment_1, "过氧化氢分解制取氧气", "人教教材", 5654,"http://2cwstudio.com/wp-content/uploads/2020/09/U2_H2O2MnO2.mp4",R.string.H2O2_To_O2));
        lessonList.add(new RecommendLesson(R.mipmap.experiment_2, "铁丝在氧气中燃烧", "人教教材", 7615,"http://2cwstudio.com/wp-content/uploads/2020/Video/Video02.mp4",R.string.Fe_O2));
        lessonList.add(new RecommendLesson(R.mipmap.experiment_3, "配置氯化钠溶液", "人教教材", 7321,"http://2cwstudio.com/wp-content/uploads/2020/Video/Video03.mp4",R.string.NaCl));
        lessonList.add(new RecommendLesson(R.mipmap.experiment_4, "浓硫酸的稀释", "人教教材", 7832,"http://2cwstudio.com/wp-content/uploads/2020/Video/Video04.mp4",R.string.H2SO4));
        lessonList.add(new RecommendLesson(R.mipmap.experiment_5, "氢氧化钠，氯化钡与硫酸铜反应", "人教教材", 5214,"http://2cwstudio.com/wp-content/uploads/2020/Video/Video05.mp4",R.string.NaOH_BaCl));
        lessonList.add(new RecommendLesson(R.mipmap.icon, "一氧化碳还原四氧化三铁", "人教教材", 3533,"http://2cwstudio.com/wp-content/uploads/2020/09/U2_H2O2MnO2.mp4"));
        lessonList.add(new RecommendLesson(R.mipmap.icon, "苛性钠暴露在空气中变质", "人教教材", 10472,"http://2cwstudio.com/wp-content/uploads/2020/09/U2_H2O2MnO2.mp4"));
        lessonList.add(new RecommendLesson(R.mipmap.icon, "碳酸钠与稀盐酸反应", "人教教材", 5738,"http://2cwstudio.com/wp-content/uploads/2020/09/U2_H2O2MnO2.mp4"));
        lessonList.add(new RecommendLesson(R.mipmap.icon, "硫酸钠和氯化钡", "人教教材", 2432,"http://2cwstudio.com/wp-content/uploads/2020/09/U2_H2O2MnO2.mp4"));
        lessonList.add(new RecommendLesson(R.mipmap.icon, "水在直流电的作用下分解", "人教教材", 5732,"http://2cwstudio.com/wp-content/uploads/2020/09/U2_H2O2MnO2.mp4"));
        lessonList.add(new RecommendLesson(R.mipmap.icon, "一氧化碳在氧气中燃烧", "人教教材", 5734,"http://2cwstudio.com/wp-content/uploads/2020/09/U2_H2O2MnO2.mp4"));
        lessonList.add(new RecommendLesson(R.mipmap.icon, "锌和硫酸铜溶液反应", "人教教材", 8615,"http://2cwstudio.com/wp-content/uploads/2020/09/U2_H2O2MnO2.mp4"));
        lessonList.add(new RecommendLesson(R.mipmap.icon, "酒精在空气中燃烧", "人教教材", 7324,"http://2cwstudio.com/wp-content/uploads/2020/09/U2_H2O2MnO2.mp4"));
    }

    @Override
    public void onLessonLoaded(RecommendLesson recommendLesson) {
        setUpstate(State.SUCCESS);
        LogUtils.e(this,"onLessonLoaded.....");
        initLessonList();
        mLessonAdapter.setData(lessonList);
    }

    @Override
    public void onLessonItemClick(RecommendLesson item) {
        TurnToPageUtil.toVideoPage(getContext(),item);
    }

    @Override
    public void onLessonItemClick(DataBean item) {
        TurnToPageUtil.toVideoPage2(getContext(),item);
    }
}
