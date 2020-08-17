package com.hzq.frame.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.baselib.view.glideImageView.GlideImageView;
import com.hzq.frame.R;
import com.hzq.frame.entity.ResponseTest;


/**
 * Created by Nate on 2020-04-14.
 */
public class TestAdapter extends BaseQuickAdapter<ResponseTest, BaseViewHolder> {

    private Context mContext;

    public TestAdapter(Context context) {
        super(R.layout.item_test_list);
        mContext = context;
    }


    @Override
    protected void convert(BaseViewHolder helper, final ResponseTest listBean) {

        ((GlideImageView) helper.getView(R.id.iv_icon)).loadImage("https://img1.imgtn.bdimg.com/it/u=4027212837,1228313366&fm=23&gp=0.jpg", R.mipmap.ic_launcher);
        helper.setText(R.id.tv_name, listBean.getName());
        helper.setText(R.id.tv_title, listBean.getTitle());
    }
}
