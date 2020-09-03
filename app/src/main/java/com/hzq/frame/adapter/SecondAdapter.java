package com.hzq.frame.adapter;

/**
 * Created by Nate on 3/9/20.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.hzq.frame.R;
import com.hzq.frame.databinding.LayoutItemBindingBinding;
import com.hzq.frame.entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by $wu on 2017-09-09 上午 10:40.
 */

public class SecondAdapter extends RecyclerView.Adapter {

    private List<User> users;
    private Context context;

    public SecondAdapter(List<User> users, Context context) {
        if(this.users == null){
            this.users = new ArrayList<>();
        }
        this.users = users;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        LayoutItemBindingBinding binding = DataBindingUtil.inflate(inflater, R.layout.layout_item_binding, parent, false);
        return new MyViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        LayoutItemBindingBinding binding = DataBindingUtil.getBinding(holder.itemView);
        binding.setUser(users.get(position));
        binding.executePendingBindings();
    }


    @Override
    public int getItemCount() {
        return users.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }

}
