package com.hhp.themovie.api.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SessionRes implements Serializable {
    @SerializedName("success")
    public boolean success;
    @SerializedName("session_id")
    public String sessionID;

    @Override
    public String toString() {
        return "SessionRes{" +
                "success=" + success +
                ", sessionID='" + sessionID + '\'' +
                '}';
    }
}
