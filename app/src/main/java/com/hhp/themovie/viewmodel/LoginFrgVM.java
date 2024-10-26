package com.hhp.themovie.viewmodel;

import android.util.Log;

import com.hhp.themovie.api.request.AccountReq;
import com.hhp.themovie.api.request.RequestTokenReq;
import com.hhp.themovie.api.response.AuthenRes;
import com.hhp.themovie.view.base.BaseViewModel;

public class LoginFrgVM extends BaseViewModel {
    public static final String TAG = LoginFrgVM.class.getName();
    public static final String KEY_API_AUTHEN = "KEY_API_AUTHEN";
    public static final String KEY_API_SESSION_ID = "KEY_API_SESSION_ID";
    public static final String KEY_API_CREATE_SESSION = "KEY_API_CREATE_SESSION ";
    private String username,password;

    public void getAuthen( String username, String password){
        this.username = username;
        this.password = password;
        getAPI().getAuthen().enqueue(handleResponse(KEY_API_AUTHEN));
    }

    private void createSession(String requestToken){
        getAPI().createSession(new AccountReq(username, password, requestToken)).enqueue(handleResponse(KEY_API_CREATE_SESSION));
    }

    private void createSessionID(String requestToken){
        getAPI().createSessionID(new RequestTokenReq(requestToken)).enqueue(handleResponse(KEY_API_SESSION_ID));
    }


    @Override
    protected void handleSuccess(String key, Object body) {
        Log.i(TAG, "handleSuccess: " + key);
        Log.i(TAG, "handleSuccess: " + body);
        switch (key) {
            case KEY_API_AUTHEN:
                createSession(((AuthenRes) body).requestToken);
                break;
            case KEY_API_CREATE_SESSION:
                createSessionID(((AuthenRes) body).requestToken);
                break;
            case KEY_API_SESSION_ID:
                apiCallback.apiSuccess(key,body);
                break;
        }
    }


}
