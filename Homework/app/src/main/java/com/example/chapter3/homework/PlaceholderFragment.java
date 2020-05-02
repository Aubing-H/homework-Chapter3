package com.example.chapter3.homework;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.animation.AnimatorSet;

import com.airbnb.lottie.LottieAnimationView;

public class PlaceholderFragment extends Fragment {
    private static final String TAG = "hjinbing";

    private LottieAnimationView animationView;
    private AnimatorSet my_animatorSet;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private static final int duration = 2000;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "holderFragment onCreate start");
        // TODO ex3-3: 修改 fragment_placeholder，添加 loading 控件和列表视图控件
        return inflater.inflate(R.layout.fragment_placeholder, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "holderFragment onActivityCreate");
        getView().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 这里会在 5s 后执行
                // TODO ex3-4：实现动画，将 lottie 控件淡出，列表数据淡入
                viewPager = getView().findViewById(R.id.view_pager);
                viewPager.setAlpha(0.0f);
                viewPager.setVisibility(View.VISIBLE);
                tabLayout = getView().findViewById(R.id.tab_layout);
                tabLayout.setAlpha(0.0f);
                tabLayout.setVisibility(View.VISIBLE);
                animationView = getView().findViewById(R.id.place_holder_animation);

                // 设置淡出视图
                animationView.animate()
                        .alpha(0.0f)
                        .setDuration(duration)
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                animationView.setVisibility(View.GONE);
                            }
                        });

                // 设置淡入的viewPager视图
                viewPager.animate()
                        .alpha(1.0f)
                        .setDuration(duration);
                tabLayout.animate()
                        .alpha(1.0f)
                        .setDuration(duration);
            }
        }, 5000);
    }
}
