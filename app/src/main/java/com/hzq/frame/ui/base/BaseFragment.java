package com.hzq.frame.ui.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hzq.frame.utils.ToastUtil;

import butterknife.ButterKnife;

public abstract class BaseFragment extends Fragment {

	protected View mainLayout;
	protected Activity mActivity;

	protected abstract int setLayout();

	protected void onCreate() {
	}

	protected void initView() {
	}

	protected void initData() {
	}

	protected void onFragmentDestroy() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mActivity = getActivity();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mainLayout = inflater.inflate(setLayout(), null);
		ButterKnife.bind(this, mainLayout);
		return mainLayout;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		onCreate();
		initView();
		initData();
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		onFragmentDestroy();
	}

	public ProgressDialog progressDialog;

	public ProgressDialog showProgressDialog() {
		progressDialog = new ProgressDialog(mActivity);
		progressDialog.setMessage("加载中");
		progressDialog.show();
		return progressDialog;
	}

	public ProgressDialog showProgressDialog(CharSequence message) {
		progressDialog = new ProgressDialog(mActivity);
		progressDialog.setMessage(message);
		progressDialog.show();
		return progressDialog;
	}

	public void dismissProgressDialog() {
		if (progressDialog != null && progressDialog.isShowing()) {
			// progressDialog.hide();会导致android.view.WindowLeaked
			progressDialog.dismiss();
		}
	}

	public void showMessage(CharSequence message) {
		ToastUtil.toast(mActivity, message);
	}
}
