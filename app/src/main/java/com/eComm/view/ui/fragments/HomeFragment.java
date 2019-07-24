package com.eComm.view.ui.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eComm.R;
import com.eComm.databinding.FragmentHomeBinding;
import com.eComm.view.ui.adapter.DealOfTheDayAdapter;
import com.eComm.view.ui.adapter.ItemInCartAdapter;
import com.eComm.view.ui.adapter.MainCategoryHorizontalAdapter;
import com.eComm.view.ui.adapter.NewArrivalPageAdapter;
import com.eComm.view.ui.adapter.TrendingAndRecomAdapter;

import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class HomeFragment extends Fragment {
    FragmentHomeBinding mBinding;
    MainCategoryHorizontalAdapter adapter;
    TrendingAndRecomAdapter trendingAndRecomAdapter;
    ItemInCartAdapter itemInCartAdapter;
    DealOfTheDayAdapter dealOfTheDayAdapter;
    int[] img = new int[]{R.drawable.img123, R.drawable.img123,
            R.drawable.img123};
    NewArrivalPageAdapter arrivalPageAdapter;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpRecyclerView();
        setViewPagerAdapter();
        setPageChangeListener();
        setTimeForViewPager();
        setTrendingItems();
        setRecommendItemRecyclerView();
        setDealOfTheDayRecyclerView();
        showItemInCart();
    }

    private void showItemInCart() {
        itemInCartAdapter = new ItemInCartAdapter(getContext());
        mBinding.itemInCart.setLayoutManager(new GridLayoutManager(getContext(), 1));
        mBinding.itemInCart.setAdapter(itemInCartAdapter);
    }

    private void setDealOfTheDayRecyclerView() {
        dealOfTheDayAdapter = new DealOfTheDayAdapter(getContext());
        mBinding.dealOfDayRecycler.setLayoutManager(new GridLayoutManager(getContext(), 2));
        mBinding.dealOfDayRecycler.setAdapter(dealOfTheDayAdapter);
    }

    private void setRecommendItemRecyclerView() {
        trendingAndRecomAdapter = new TrendingAndRecomAdapter(getContext());
        mBinding.idRecomItem.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mBinding.idRecomItem.setAdapter(trendingAndRecomAdapter);
    }

    private void setTrendingItems() {
        trendingAndRecomAdapter = new TrendingAndRecomAdapter(getContext());
        mBinding.idTrendingRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mBinding.idTrendingRecycler.setAdapter(trendingAndRecomAdapter);
    }

    private void setTimeForViewPager() {
        final Handler handler = new Handler();
        final Runnable update = new Runnable() {
            @Override
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mBinding.viewPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {

            @Override
            public void run() {
                handler.post(update);
            }
        }, 1000, 1000);
    }

    private void setPageChangeListener() {
        mBinding.viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == ViewPager.SCROLL_STATE_IDLE) {
                    int pageCount = img.length;
                    if (currentPage == 0) {
                        mBinding.viewPager.setCurrentItem(pageCount - 1, false);
                    } else if (currentPage == pageCount - 1) {
                        mBinding.viewPager.setCurrentItem(0, false);

                    }
                }
            }
        });
    }

    private void setViewPagerAdapter() {
        arrivalPageAdapter = new NewArrivalPageAdapter(getContext());
        mBinding.viewPager.setAdapter(arrivalPageAdapter);
        mBinding.circleIndicator.setViewPager(mBinding.viewPager);

    }


    private void setUpRecyclerView() {
        adapter = new MainCategoryHorizontalAdapter(getContext());
        mBinding.horizontalRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mBinding.horizontalRecyclerView.setAdapter(adapter);

    }

}
