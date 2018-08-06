package com.dhgate.dhpartner.ui.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.dhgate.dhpartner.R;
import com.dhgate.dhpartner.base.BaseMvpActivity;
import com.dhgate.dhpartner.ui.contract.MainActivityContract;
import com.dhgate.dhpartner.ui.fragment.MainFragment;
import com.dhgate.dhpartner.ui.presenter.MainActivityPresenter;
import com.dhgate.dhpartner.widget.ViewPagerSlidingTabStrip;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseMvpActivity<MainActivityPresenter> implements MainActivityContract.MainView {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @BindView(R.id.view_pager)
    public ViewPager viewPager;

    @BindView(R.id.tabs)
    public ViewPagerSlidingTabStrip pagerSlidingTabStrip;

    private List<Fragment> fragmentList = new ArrayList<>();
    private String[] titles;

    @Override
    protected void onCreate() {
        super.onCreate();
    }

    @Override
    protected void initView() {
        super.initView();

        setSwipeBackEnable(false);

        setActionTitle("主页面");

        initViewPager();
    }

    @Override
    protected void initData() {
        super.initData();
    }


    @Override
    protected MainActivityPresenter createPresenter() {
        return new MainActivityPresenter(mActivity);
    }


    /**
     * 初始化viewpager
     */
    private void initViewPager() {
        fragmentList.add(MainFragment.newInstance());
        fragmentList.add(MainFragment.newInstance());
        fragmentList.add(MainFragment.newInstance());
        fragmentList.add(MainFragment.newInstance());
        fragmentList.add(MainFragment.newInstance());

        titles = new String[]{getResources().getString(R.string.main_chats)
                , getResources().getString(R.string.main_contacts)
                , getResources().getString(R.string.collection)
                , getResources().getString(R.string.main_shop)
                , getResources().getString(R.string.main_setting)};

        setActionTitle(titles[0]);

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setOffscreenPageLimit(4);
        pagerSlidingTabStrip = (ViewPagerSlidingTabStrip) findViewById(R.id.tabs);
        viewPager.setAdapter(new PagerAdapter(this.getSupportFragmentManager()));
        pagerSlidingTabStrip.setViewPager(viewPager);
        viewPager.setOnPageChangeListener(pagerSlidingTabStrip);
        viewPager.setCurrentItem(0);
        pagerSlidingTabStrip.setOnTabClickListener(new ViewPagerSlidingTabStrip.OnTabClickListener() {
            @Override
            public void onCurrentTabClicked(int position) {
                setActionTitle(titles[position]);
                viewPager.setCurrentItem(position, false);
                switch (position) {
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                }
            }
        });
        pagerSlidingTabStrip.setOnPagerTabChangeListener(new ViewPagerSlidingTabStrip.onPagerTabChangeListener() {
            @Override
            public void onPageSelected(int position) {
                setActionTitle(titles[position]);
                switch (position) {
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                }
            }
        });
    }

    /**
     * pager 适配器
     */
    class PagerAdapter extends FragmentStatePagerAdapter {

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //super.destroyItem(container, position, object);
        }
    }

}
