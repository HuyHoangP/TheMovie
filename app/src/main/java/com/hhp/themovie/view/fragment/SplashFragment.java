package com.hhp.themovie.view.fragment;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.hhp.themovie.R;
import com.hhp.themovie.databinding.FragmentSplashBinding;
import com.hhp.themovie.view.base.BaseFragment;
import com.hhp.themovie.viewmodel.SplashFrgVM;

public class SplashFragment extends BaseFragment<FragmentSplashBinding, SplashFrgVM> {
    public static final String TAG = SplashFragment.class.getName();
    @Override
    public void initView() {
        Animation anim = AnimationUtils.loadAnimation(context, R.anim.fade_in_translate_right);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                callback.showFragment(LoginFragment.TAG, null, false);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        binding.ivTmdb.startAnimation(anim);
    }

    @Override
    public Class<SplashFrgVM> initViewModel() {
        return SplashFrgVM.class;
    }

    @Override
    public FragmentSplashBinding initViewBinding() {
        return FragmentSplashBinding.inflate(getLayoutInflater());
    }
}
