package com.hzq.frame.ui.activity;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.hzq.frame.R;
import com.hzq.frame.adapter.SecondAdapter;
import com.hzq.frame.databinding.ActivityTestBindingBinding;
import com.hzq.frame.entity.User;
import com.hzq.frame.jetpack.NameViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nate on 3/9/20.
 */
public class TestDataBindingActivity extends AppCompatActivity {

    ActivityTestBindingBinding binding;
    NameViewModel mNameViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mNameViewModel = new ViewModelProvider(this).get(NameViewModel.class);
//        mNameViewModel.getCurrentName().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(String name) {
//                ToastUtil.toast(TestDataBindingActivity.this, name);
//            }
//        });

        //        ActivityTestBinding binding = ActivityTestBinding.inflate(getLayoutInflater());
        binding = DataBindingUtil.setContentView(this, R.layout.activity_test_binding);
        binding.setNameViewModel(mNameViewModel);
        binding.setLifecycleOwner(this);

        binding.btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNameViewModel.getCurrentName().setValue("李四");
            }
        });

        initRecyclerView();
    }


    private void initRecyclerView() {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            User user = new User("张三", i);
            users.add(user);
        }
        SecondAdapter adapter = new SecondAdapter(users, this);
        binding.setAdapter(adapter);
    }


}
