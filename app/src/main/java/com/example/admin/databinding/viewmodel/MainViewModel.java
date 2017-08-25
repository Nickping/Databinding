package com.example.admin.databinding.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;
import android.os.Handler;
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

    public MainViewModel() {
        Handler handler = new Handler();
        mProgressBarStatus.set(View.VISIBLE);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mTitle.set("데이터 로딩 완료");
                mProgressBarStatus.set(View.GONE);
            }
        }, 3000);
    }
}
