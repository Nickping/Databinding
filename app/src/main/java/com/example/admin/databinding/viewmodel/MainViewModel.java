package com.example.admin.databinding.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.view.View;

/**
 * Created by admin on 2017-08-25.
 */

public class MainViewModel extends ViewModel {
    @NonNull
    public ObservableField<String> mTitle = new ObservableField<>("initial");
    @NonNull
    public ObservableField<Integer> mProgressBarStatus = new ObservableField<>(View.GONE);


}
