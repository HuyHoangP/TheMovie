package com.hhp.themovie.view.fragment;

import android.view.View;
import android.widget.Toast;

import com.hhp.themovie.CommonUtils;
import com.hhp.themovie.api.response.SessionRes;
import com.hhp.themovie.databinding.FragmentLoginBinding;
import com.hhp.themovie.view.base.BaseFragment;
import com.hhp.themovie.viewmodel.LoginFrgVM;

public class LoginFragment extends BaseFragment<FragmentLoginBinding, LoginFrgVM> {
    public static final String TAG = LoginFragment.class.getName();
    @Override
    public void initView() {
        binding.btLogin.setOnClickListener(view -> viewModel.getAuthen(
                binding.edtUsername.getText().toString(),
                binding.edtPassword.getText().toString()
        ));
    }

    @Override
    public Class<LoginFrgVM> initViewModel() {
        return LoginFrgVM.class;
    }

    @Override
    public FragmentLoginBinding initViewBinding() {
        return FragmentLoginBinding.inflate(getLayoutInflater());
    }

    @Override
    public void apiSuccess(String key, Object data) {
        if(key.equals(LoginFrgVM.KEY_API_SESSION_ID)){
            SessionRes res = (SessionRes) data;
            CommonUtils.getInstance().savePref(LoginFrgVM.KEY_API_SESSION_ID, res.sessionID);
        }
        callback.showFragment(ListMoviesFragment.TAG, null, false);
    }
}
