package com.hhp.themovie.api.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AuthenRes {
    @SerializedName("success")
    public boolean success;
    @SerializedName("expires_at")
    public String expiresAt;
    @SerializedName("request_token")
    public String requestToken;

    @Override
    public String toString() {
        return "AuthenRes{" +
                "success=" + success +
                ", expiresAt='" + expiresAt + '\'' +
                ", requestToken='" + requestToken + '\'' +
                '}';
    }
}
