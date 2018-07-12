package com.hzq.myframe2.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hzq.myframe2.R;
import com.nineoldandroids.view.ViewHelper;

/**
 * <p/>
 * 主页指示器
 */
public class ViewPagerSlidingTabStrip extends HorizontalScrollView implements ViewPager.OnPageChangeListener {

    private Context mContext;

    private LinearLayout.LayoutParams tabViewLayoutParams;

    private LinearLayout tabsContainer;

    private int tabCount;//选择器数量

    private int tabPadding = 0;

    public String[] titles = new String[]{getStringById(R.string.main_chats), getStringById(R.string.main_contacts), getStringById(R.string.collection), getStringById(R.string.main_shop), getStringById(R.string.main_setting)};

    public String getStringById(int id) {
        return getResources().getString(id);
    }

    public ViewPagerSlidingTabStrip(Context context) {
        this(context, null);
        this.mContext = context;
    }

    public ViewPagerSlidingTabStrip(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        this.mContext = context;
    }

    public ViewPagerSlidingTabStrip(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        this.mContext = context;

        setFillViewport(true);
        setWillNotDraw(false);

        tabsContainer = new LinearLayout(context);
        tabsContainer.setOrientation(LinearLayout.HORIZONTAL);
        tabsContainer.setBackgroundResource(R.color.main_bottom_color);
        tabsContainer.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        addView(tabsContainer);

//        DisplayMetrics dm = getResources().getDisplayMetrics();
//        tabPadding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, tabPadding, dm);
//        tabViewLayoutParams = new LinearLayout.LayoutParams(0, LayoutParams.MATCH_PARENT, 2.0f);
        tabViewLayoutParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 2.0f);

        notifyDataSetChanged();
    }

    /**
     * 切换器
     */
    private ViewPager pager;

    public void setViewPager(ViewPager pager) {
        this.pager = pager;

        if (pager.getAdapter() == null) {
            throw new IllegalStateException("ViewPager does not have adapter instance.");
        }
    }

    /**
     * 通知，更新
     */
    public void notifyDataSetChanged() {
        tabsContainer.removeAllViews();//删除父容器里面的所有子view，清空

        tabCount = titles.length;

        for (int i = 0; i < tabCount; i++) {
            addTabView(i, titles[i]);
        }

    }

    public void addTabView(final int position, String title) {
        final LayoutInflater inflater = LayoutInflater.from(getContext());

        View tabView = inflater.inflate(R.layout.layout_main_tab, null);

        ImageView icon = tabView.findViewById(R.id.icon);
        ImageView backIcon = tabView.findViewById(R.id.background);
        TextView titleText = tabView.findViewById(R.id.title);

        titleText.setText(title);
        switch (position) {
            case 0:
                titleText.setTextColor(getResources().getColor(R.color.main_text_pressed));
                icon.setBackgroundResource(R.mipmap.main_chats_pressed);
                backIcon.setBackgroundResource(R.mipmap.main_chats_normal);
                break;
            case 1:
                icon.setBackgroundResource(R.mipmap.main_contacts_pressed);
                backIcon.setBackgroundResource(R.mipmap.main_contacts_normal);
                break;
            case 2:
                icon.setBackgroundResource(R.mipmap.main_collection_pressed);
                backIcon.setBackgroundResource(R.mipmap.main_collection_normal);
                break;
            case 3:
                icon.setBackgroundResource(R.mipmap.main_shop_pressed);
                backIcon.setBackgroundResource(R.mipmap.main_shop_normal);
                break;
            case 4:
                icon.setBackgroundResource(R.mipmap.main_setting_pressed);
                backIcon.setBackgroundResource(R.mipmap.main_setting_normal);
                break;
        }
        ViewHelper.setAlpha(icon, 0.0f);
        addTab(position, tabView);
    }

    private OnTabClickListener onTabClickListener;

    public void setOnTabClickListener(OnTabClickListener onTabClickListener) {
        this.onTabClickListener = onTabClickListener;
    }

    private onPagerTabChangeListener onPagerTabChangeListener;

    public void setOnPagerTabChangeListener(onPagerTabChangeListener o) {
        this.onPagerTabChangeListener = o;
    }

//    public void switchTab(int position) {
//        RelativeLayout tabView;
//        TextView textView;
//        ImageView icon;
//
////        tabView = (RelativeLayout) tabsContainer.getChildAt(position);
////        textView = (TextView) tabView.findViewById(R.id.title);
////        icon = (ImageView) tabView.findViewById(R.id.icon);
////
////        textView.setTextColor(getResources().getColor(R.color.main_text_pressed));
////
////        switch (position){
////            case 0:
////                icon.setBackgroundResource(R.mipmap.main_chats_pressed);
////                break;
////            case 1:
////                icon.setBackgroundResource(R.mipmap.main_contacts_pressed);
////                break;
////            case 2:
////                icon.setBackgroundResource(R.mipmap.main_shop_pressed);
////                break;
////            case 3:
////                icon.setBackgroundResource(R.mipmap.main_setting_pressed);
////                break;
////        }
//
//
////        this.pager.setCurrentItem(position,true);
//
//        for (int i = 0; i < tabCount; ++i) {
//            tabView = (RelativeLayout) tabsContainer.getChildAt(i);
//            textView = (TextView) tabView.findViewById(R.id.title);
//            icon = (ImageView) tabView.findViewById(R.id.icon);
//            if (i == position) {
//                textView.setTextColor(getResources().getColor(R.color.main_text_pressed));
//                switch (i) {
//                    case 0:
//                        icon.setBackgroundResource(R.mipmap.main_chats_pressed);
//                        break;
//                    case 1:
//                        icon.setBackgroundResource(R.mipmap.main_contacts_pressed);
//                        break;
//                    case 2:
//                        icon.setBackgroundResource(R.mipmap.main_contacts_pressed);
//                        break;
//                    case 3:
//                        icon.setBackgroundResource(R.mipmap.main_shop_pressed);
//                        break;
//                    case 4:
//                        icon.setBackgroundResource(R.mipmap.main_setting_pressed);
//                        break;
//                }
//            } else {
//                textView.setTextColor(getResources().getColor(R.color.main_text_normal));
//                switch (i) {
//                    case 0:
//                        icon.setBackgroundResource(R.mipmap.main_chats_normal);
//                        break;
//                    case 1:
//                        icon.setBackgroundResource(R.mipmap.main_contacts_normal);
//                        break;
//                    case 2:
//                        icon.setBackgroundResource(R.mipmap.main_contacts_normal);
//                        break;
//                    case 3:
//                        icon.setBackgroundResource(R.mipmap.main_shop_normal);
//                        break;
//                    case 4:
//                        icon.setBackgroundResource(R.mipmap.main_setting_normal);
//                        break;
//                }
//            }
////            else {
////                textView.setTextColor(getResources().getColor(R.color.main_text_normal));
////                switch (i) {
////                    case 0:
////                        icon.setBackgroundResource(R.mipmap.main_chats_normal);
////                        break;
////                    case 1:
////                        icon.setBackgroundResource(R.mipmap.main_contacts_normal);
////                        break;
////                    case 2:
////                        icon.setBackgroundResource(R.mipmap.main_shop_normal);
////                        break;
////                    case 3:
////                        icon.setBackgroundResource(R.mipmap.main_setting_normal);
////                        break;
////                }
////            }
//        }
//
//    }


    private void addTab(final int position, View tab) {
        tab.setFocusable(true);
        tab.setOnClickListener(v -> {
            if (onTabClickListener != null)
                onTabClickListener.onCurrentTabClicked(position);
        });
//        addTabDoubleTapListener(position, tab);
        tab.setPadding(tabPadding, 0, tabPadding, 0);
        tabsContainer.addView(tab, position, tabViewLayoutParams);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (position == 0) {//1->0||1->0
            ViewHelper.setAlpha(tabsContainer.getChildAt(0).findViewById(R.id.icon), 1 - positionOffset);
            ViewHelper.setAlpha(tabsContainer.getChildAt(1).findViewById(R.id.icon), positionOffset);
        } else if (position == 1) {//1->2||2->1
            ViewHelper.setAlpha(tabsContainer.getChildAt(1).findViewById(R.id.icon), 1 - positionOffset);
            ViewHelper.setAlpha(tabsContainer.getChildAt(2).findViewById(R.id.icon), positionOffset);
        } else if (position == 2) {//2->3||3->2
            ViewHelper.setAlpha(tabsContainer.getChildAt(2).findViewById(R.id.icon), 1 - positionOffset);
            ViewHelper.setAlpha(tabsContainer.getChildAt(3).findViewById(R.id.icon), positionOffset);
        } else if (position == 3) {//2->3||3->2
            ViewHelper.setAlpha(tabsContainer.getChildAt(3).findViewById(R.id.icon), 1 - positionOffset);
            ViewHelper.setAlpha(tabsContainer.getChildAt(4).findViewById(R.id.icon), positionOffset);
        }
    }

    @Override
    public void onPageSelected(int position) {
        if (this.onPagerTabChangeListener != null) {
            this.onPagerTabChangeListener.onPageSelected(position);
        }
        for (int i = 0; i < tabCount; ++i) {
            if (position == i) {//被选中的
                ((TextView) tabsContainer.getChildAt(i).findViewById(R.id.title)).setTextColor(getResources().getColor(R.color.main_text_pressed));
                tabsContainer.getChildAt(i).findViewById(R.id.background).setVisibility(View.GONE);
                ViewHelper.setAlpha(tabsContainer.getChildAt(i).findViewById(R.id.icon), 1.0f);
                ViewHelper.setAlpha(tabsContainer.getChildAt(i).findViewById(R.id.title), 1.0f);
            } else {//其他的
                ((TextView) tabsContainer.getChildAt(i).findViewById(R.id.title)).setTextColor(getResources().getColor(R.color.main_text_normal));
                tabsContainer.getChildAt(i).findViewById(R.id.background).setVisibility(View.VISIBLE);
                ViewHelper.setAlpha(tabsContainer.getChildAt(i).findViewById(R.id.icon), 0.0f);
                ViewHelper.setAlpha(tabsContainer.getChildAt(i).findViewById(R.id.title), 1.0f);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    /**
     * 滑动监听
     */
    public interface onPagerTabChangeListener {
        void onPageSelected(int position);
    }

    /**
     * TAB 的点击监听
     */
    public interface OnTabClickListener {

        void onCurrentTabClicked(int position);
    }


}































