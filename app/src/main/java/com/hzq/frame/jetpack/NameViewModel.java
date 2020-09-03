package com.hzq.frame.jetpack;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

/**
 * Created by Nate on 28/8/20.
 */
public class NameViewModel extends ViewModel {
    // Create a LiveData with a String
    private MutableLiveData<String> mCurrentName;
    // Create a LiveData with a String list
    private MutableLiveData<List<String>> mNameListData;

    public MutableLiveData<String> getCurrentName() {
        if (mCurrentName == null) {
            mCurrentName = new MutableLiveData<>();
        }
        return mCurrentName;
    }

    public MutableLiveData<List<String>> getNameList() {
        if (mNameListData == null) {
            mNameListData = new MutableLiveData<>();
        }
        return mNameListData;
    }
}
