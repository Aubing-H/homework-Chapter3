package com.example.chapter3.homework;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.Objects;

/**
 * 使用 ViewPager 和 Fragment 做一个简单版的好友列表界面
 * 1. 使用 ViewPager 和 Fragment 做个可滑动界面
 * 2. 使用 TabLayout 添加 Tab 支持
 * 3. 对于好友列表 Fragment，使用 Lottie 实现 Loading 效果，在 5s 后展示实际的列表，要求这里的动效是淡入淡出
 */

public class Ch3Ex3Activity extends AppCompatActivity {
    private static final String TAG = "hjinbing";

    ViewPager viewPager;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "activity start");
        setContentView(R.layout.activity_ch3ex3);
        Log.d(TAG, "activity xml setup successfully");
        // TODO: ex3-1. 添加 ViewPager 和 Fragment 做可滑动界面
        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tab_layout);

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            private static final int itemNum = 3;

            @Override
            public Fragment getItem(int i) {
                return new MyFragment();
            }

            @Override
            public int getCount() {
                return itemNum;
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return "friend list " + position;
            }
        });
        // TODO: ex3-2, 添加 TabLayout 支持 Tab
        tabLayout.setupWithViewPager(viewPager);

    }

    public static class MyFragment extends Fragment{
        RecyclerView recyclerView;
        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            return inflater.inflate(R.layout.my_fragment, container, false);
        }

        @Override
        public void onActivityCreated(@Nullable Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            recyclerView = getView().findViewById(R.id.recycle_view);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
            recyclerView.setAdapter(new MyAdapter());
        }
        private static class MyHolder extends RecyclerView.ViewHolder{
            public MyHolder(View itemView){
                super(itemView);
            }
        }

        private static class MyAdapter extends RecyclerView.Adapter<MyHolder>{
            private static final int friendNum = 30;
            @NonNull
            @Override
            public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                return new MyHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.my_linear_layout, viewGroup, false));
            }

            @Override
            public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
                TextView tv_friend = myHolder.itemView.findViewById(R.id.friend_text);
                TextView tv_name = myHolder.itemView.findViewById(R.id.name_text);
                tv_friend.setText("friend" + i);
                tv_name.setText("name" + i);
            }

            @Override
            public int getItemCount() {
                return friendNum;
            }
        }
    }
}
