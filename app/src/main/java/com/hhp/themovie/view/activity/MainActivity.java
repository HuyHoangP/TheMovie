package com.hhp.themovie.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.hhp.themovie.R;
import com.hhp.themovie.databinding.ActivityMainBinding;
import com.hhp.themovie.view.base.BaseActivity;
import com.hhp.themovie.view.fragment.SplashFragment;
import com.hhp.themovie.viewmodel.MainActVM;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainActVM> {

    @Override
    public void initView() {
        showFragment(SplashFragment.TAG, null, false);
    }

    @Override
    public Class<MainActVM> initViewModel() {
        return MainActVM.class;
    }

    @Override
    public ActivityMainBinding initViewBinding() {
        return ActivityMainBinding.inflate(getLayoutInflater());
    }
}