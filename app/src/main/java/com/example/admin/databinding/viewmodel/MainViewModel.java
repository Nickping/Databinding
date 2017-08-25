package com.example.admin.databinding.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;

import com.example.admin.databinding.databinding.ActivityMainBinding;
import com.jakewharton.rxbinding2.widget.RxAdapterView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

/**
 * Created by admin on 2017-08-25.
 */

public class MainViewModel extends ViewModel {
    private static final String TAG = "MainViewModel";
    @NonNull
    public ObservableField<String> mTitle = new ObservableField<>("initial");
    @NonNull
    public ObservableField<Integer> mProgressBarStatus = new ObservableField<>(View.GONE);
    @NonNull
    public ObservableField<Integer> mProgressBarStatusForProducts = new ObservableField<>(View.GONE);

    @NonNull
    public ObservableField<Integer> mSpinnerVisiblity = new ObservableField<>(View.VISIBLE);
    @NonNull
    public ObservableField<List<String>> mCompanies = new ObservableField<>();
    @NonNull
    public ObservableField<Integer> mSpinnerProductsVisiblity = new ObservableField<>(View.GONE);

    @NonNull
    public ObservableField<List<String>> mProducts = new ObservableField<>();

    public MainViewModel() {
        Handler handler = new Handler();
        mProgressBarStatus.set(View.VISIBLE);
        ArrayList<String> companies = new ArrayList<>();
        companies.add("smArts");
        companies.add("samsung");
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mTitle.set("데이터 로딩 완료");
                mProgressBarStatus.set(View.GONE);
                mCompanies.set(companies);
                mSpinnerProductsVisiblity.set(View.VISIBLE);
            }
        }, 3000);
    }

    public void attachViews(ActivityMainBinding binding) {
        Observable<Integer> observable = RxAdapterView.itemSelections(binding.spinnerCompanies);
        Handler handler = new Handler();
        observable.subscribe(
                (position) -> {

                    if (position != -1) {
                        Log.d(TAG, "attachViews: position : " + position);
                        mTitle.set(Integer.toString(position));
                        mSpinnerProductsVisiblity.set(View.VISIBLE);
                        mProgressBarStatusForProducts.set(View.VISIBLE);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if(position == 0){
                                    ArrayList<String> list = new ArrayList<String>();
                                    list.add("전구");
                                    list.add("에어컨");
                                    mProducts.set(list);

                                    mProgressBarStatusForProducts.set(View.GONE);
                                    mSpinnerProductsVisiblity.set((View.VISIBLE));

                                }
                                else if(position == 1){
                                    ArrayList<String> list = new ArrayList<String>();
                                    list.add("냉장고");
                                    list.add("로봇 청소기");
                                    mProducts.set(list);
                                    mProgressBarStatusForProducts.set(View.GONE);
                                    mSpinnerProductsVisiblity.set(View.VISIBLE);
                                }
                            }
                        }, 3000);



                    }
                }
        );
    }
}
