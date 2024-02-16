package com.jit.funchemistry.ui.activity;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.jit.funchemistry.R;
import com.jit.funchemistry.base.BaseActivity;
import com.jit.funchemistry.ui.adapter.LearningAdapter;
import com.jit.funchemistry.ui.fragment.LearningStationFragment;
import com.jit.funchemistry.ui.fragment.ThinkMapFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author created by XiaNingIf
 * @data 2021/3/23
 */
public class MyLearningActivity extends BaseActivity {
    @BindView(R.id.ivNavigateBefore)
    ImageView mIvNavigateBefore;

    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;

    @BindView(R.id.view_pager)
    ViewPager2 mViewPager;

    List<String> titles=new ArrayList<>();
    List<Fragment> fragments=new ArrayList<>();

    @Override
    protected void initEvent() {
        mIvNavigateBefore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView() {
        titles.add("思维导图");
        titles.add("学习汇报");

        fragments.add(new ThinkMapFragment());
        fragments.add(new LearningStationFragment());

        LearningAdapter learningAdapter = new LearningAdapter(getSupportFragmentManager(),getLifecycle(),fragments);

        mViewPager.setAdapter(learningAdapter);
        new TabLayoutMediator(mTabLayout, mViewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(titles.get(position));
            }
        }).attach();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_think_map_activity;
    }
}
