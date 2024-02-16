package com.jit.funchemistry.ui.activity;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;

import androidx.viewpager.widget.ViewPager;

import com.jit.funchemistry.R;
import com.jit.funchemistry.base.BaseActivity;
import com.jit.funchemistry.ui.adapter.FavouriteAdapter;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

/**
 * @author XiaNingIf
 * @date 2021/3/24
 */
public class FavouriteActivity extends BaseActivity {
    private static final String[] CHANNELS = new String[]{"视频收藏","实验收藏"};
    private List<String> mDataList = Arrays.asList(CHANNELS);
    private FavouriteAdapter mFavouriteAdapter = new FavouriteAdapter(mDataList);

    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.magic_indicator)
    MagicIndicator mMagicIndicator;


    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView() {
        mViewPager.setAdapter(mFavouriteAdapter);
        initMagicIndicator();
    }

    private void initMagicIndicator() {
        mMagicIndicator.setBackgroundColor(Color.parseColor("#66CCFF"));
        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setLeftPadding(220);
        commonNavigator.setRightPadding(220);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {

            @Override
            public int getCount() {
                return mDataList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ColorTransitionPagerTitleView(context);
                simplePagerTitleView.setNormalColor(Color.GRAY);
                simplePagerTitleView.setSelectedColor(Color.WHITE);
                simplePagerTitleView.setText(mDataList.get(index));
                simplePagerTitleView.setTextSize(24f);
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mViewPager.setCurrentItem(index);
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator linePagerIndicator = new LinePagerIndicator(context);
                linePagerIndicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
                linePagerIndicator.setColors(Color.WHITE);
                return linePagerIndicator;
            }
        });
        mMagicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(mMagicIndicator, mViewPager);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activtiy_favourite;
    }
}
