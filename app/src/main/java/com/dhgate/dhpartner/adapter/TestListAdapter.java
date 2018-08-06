package com.dhgate.dhpartner.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dhgate.dhpartner.R;
import com.dhgate.dhpartner.requestApi.ResultTest;
import com.dhgate.dhpartner.widget.glideImageView.GlideImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class TestListAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private Context mContext;

    private List<ResultTest> mList = new ArrayList<>();


    public TestListAdapter(Context context) {
        this.mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }

    public void bindData(List<ResultTest> list) {
        this.mList.addAll(list);
        notifyDataSetChanged();
    }


    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public int getCount() {
        return mList != null ? mList.size() : 0;
    }

    @Override
    public Object getItem(int arg0) {
        return mList.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    public class ViewHolder {

        @BindView(R.id.iv_icon)
        public GlideImageView iv_icon;

        @BindView(R.id.tv_name)
        public TextView tv_name;

        @BindView(R.id.tv_title)
        public TextView tv_title;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup arg2) {
        ResultTest resultTest = mList.get(position);

        View view = convertView;
        if (view == null) {
            ViewHolder vh = new ViewHolder();
            view = mInflater.inflate(R.layout.item_test_list, arg2, false);
            ButterKnife.bind(vh, view);
            view.setTag(vh);
        }
        ViewHolder vh = (ViewHolder) view.getTag();
        vh.iv_icon.loadImage("http://img1.imgtn.bdimg.com/it/u=4027212837,1228313366&fm=23&gp=0.jpg", R.mipmap.ic_launcher);
        vh.tv_name.setText(resultTest.getName());
        vh.tv_title.setText(resultTest.getTitle());

        return view;
    }

}
