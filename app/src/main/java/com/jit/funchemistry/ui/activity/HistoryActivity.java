package com.jit.funchemistry.ui.activity;

import android.content.Intent;
import android.graphics.Rect;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jit.funchemistry.R;
import com.jit.funchemistry.base.BaseActivity;
import com.jit.funchemistry.model.domain.DataBean;
import com.jit.funchemistry.model.domain.RecommendLesson;
import com.jit.funchemistry.ui.adapter.HistoryLessonAdapter;
import com.jit.funchemistry.utils.TurnToPageUtil;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;

import java.util.ArrayList;

import butterknife.BindView;

import static com.jit.funchemistry.base.BaseApplication.isLogin;
import static com.jit.funchemistry.model.domain.DataBean.getTestData3;
import static com.jit.funchemistry.utils.PUtil.dip2px;

/**
 * @author XiaNingIf
 * @date 2021/3/24
 */
public class HistoryActivity extends BaseActivity implements HistoryLessonAdapter.OnHistoryLessonItemClickListener {
    @BindView(R.id.ivNavigateBefore6)
    ImageView mIvNavigateBefore;
    @BindView(R.id.recycle_view_2)
    RecyclerView recyclerView2;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;

    private ArrayList<DataBean> historyList = new ArrayList<>();
    private HistoryLessonAdapter mHistoryLessonAdapter;
    private boolean isFirstEnter = true;

    @Override
    protected void initEvent() {
        mIvNavigateBefore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mHistoryLessonAdapter.setOnHistoryLessonItemClickListener(this);
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView() {
        mHistoryLessonAdapter = new HistoryLessonAdapter();
        historyList = DataBean.getTestData3();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView2.setLayoutManager(layoutManager);
        recyclerView2.setAdapter(mHistoryLessonAdapter);
        recyclerView2.addItemDecoration(new RecyclerView.ItemDecoration(){
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
        mHistoryLessonAdapter.setData(historyList);
    }

    @Override
    protected int getLayoutResId() {
        if (isLogin){
            return R.layout.activity_history;
        }else{
            startActivity(new Intent(this,NoLoginActivity.class));
            finish();
        }
        return R.layout.activity_history;
    }

    @Override
    public void onLessonItemClick(DataBean item) {
        TurnToPageUtil.toVideoPage2(this,item);
        finish();
    }
}
