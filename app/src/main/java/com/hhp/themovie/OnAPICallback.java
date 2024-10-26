package com.hhp.themovie;

public interface OnAPICallback {
    void apiSuccess(String key, Object data);
    void apiError(String key, Object data, int code);
}
