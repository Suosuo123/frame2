package com.hzq.myframe2.utils;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.hzq.myframe2.R;

/**
 */
public class ListViewUtil {
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    public static void setEmptyView(Context context, View listView, BaseAdapter adapter, int imgRes, String tip) {
        View emptyView = View.inflate(context, R.layout.layout_empty, null);
        ((ViewGroup) listView.getParent()).addView(emptyView, -1, -1);
        emptyView.setId(R.id.empty_listview);
        ViewGroup parent = (ViewGroup) listView.getParent();
        View emptyListView = parent.findViewById(R.id.empty_listview);
        if (emptyListView == null) {
            emptyListView = emptyView;
            (parent).addView(emptyView, -1, -1);
            ImageView iv_tip = (ImageView) emptyView.findViewById(R.id.iv_tip);
            TextView tv_tip = (TextView) emptyView.findViewById(R.id.tv_tip);
            iv_tip.setImageResource(imgRes);
            tv_tip.setText(tip);
        }
        if (adapter.isEmpty()) {
            listView.setVisibility(View.GONE);
            emptyListView.setVisibility(View.VISIBLE);
        } else {
            listView.setVisibility(View.VISIBLE);
            emptyListView.setVisibility(View.GONE);
        }
    }
}
