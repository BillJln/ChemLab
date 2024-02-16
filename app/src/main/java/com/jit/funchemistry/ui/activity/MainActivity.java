package com.jit.funchemistry.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.jit.funchemistry.R;
import com.jit.funchemistry.base.BaseActivity;
import com.jit.funchemistry.base.BaseFragment;
import com.jit.funchemistry.ui.fragment.ExperimentFragment;
import com.jit.funchemistry.ui.fragment.LessonFragment;
import com.jit.funchemistry.ui.fragment.MineFragment;
import com.jit.funchemistry.utils.KeyboardStateObserver;
import com.jit.funchemistry.utils.LogUtils;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements IMainActivity {
    @BindView(R.id.nav_view)
    BottomNavigationView mNavigationView;
    private MineFragment mMineFragment;
    private LessonFragment mLessonFragment;
    private ExperimentFragment mExperimentFragment;
    private FragmentManager mFragmentManager;
    private BaseFragment lastOneFragment = null;

    @Override
    protected void initPresenter() {
    }

    @Override
    protected void initEvent() {
        KeyboardStateObserver.getKeyboardStateObserver(this).
                setKeyboardVisibilityListener(new KeyboardStateObserver.OnKeyboardVisibilityListener() {
                    @Override
                    public void onKeyboardShow() {
                        mNavigationView.setVisibility(View.GONE);
                        //Toast.makeText(MainActivity.this,"键盘弹出",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onKeyboardHide() {
                        mNavigationView.setVisibility(View.VISIBLE);
                        //Toast.makeText(MainActivity.this,"键盘收回",Toast.LENGTH_SHORT).show();
                    }
                });

        mNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navigation_lesson:
                        LogUtils.e(MainActivity.this,"切换到课程");
                        switchFragment(mLessonFragment);
                        break;
                    case R.id.navigation_experiment:
                        LogUtils.e(MainActivity.this,"切换到实验");
                        switchFragment(mExperimentFragment);
                        break;
                    case R.id.navigation_mine:
                        LogUtils.e(MainActivity.this,"切换到我的");
                        switchFragment(mMineFragment);
                        break;
                }
                return true;
            }
        });
    }

    @Override
    protected void initView() {
        initFragment();
    }

    private void initFragment() {
        mMineFragment = new MineFragment();
        mLessonFragment = new LessonFragment();
        mExperimentFragment = new ExperimentFragment();
        mFragmentManager = getSupportFragmentManager();
        switchFragment(mLessonFragment);
    }

    private void switchFragment(BaseFragment targetFragment) {
        //如果上一个fragment跟当前要切换的fragment是同一个，那么不需要切换
        if(lastOneFragment == targetFragment) {
            return;
        }
        //修改成add和hide的方式来控制fragment的切换
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        if (!targetFragment.isAdded()) {
            fragmentTransaction.add(R.id.nav_host_fragment,targetFragment);
        }else{
            fragmentTransaction.show(targetFragment);
        }
        if (lastOneFragment != null) {
            fragmentTransaction.hide(lastOneFragment);
        }
        lastOneFragment = targetFragment;
        //fragmentTransaction.replace(R.id.main_page_container,targetFragment);
        fragmentTransaction.commit();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    public void switch2Mine() {
        mNavigationView.setSelectedItemId(R.id.navigation_mine);
    }
}