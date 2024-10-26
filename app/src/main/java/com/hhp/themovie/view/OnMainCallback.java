package com.hhp.themovie.view;

public interface OnMainCallback {
    default void callback(String key, Object data){};
    void showFragment(String tag, Object data, Boolean isBack);
    void addFragment(String tag, Object data, Boolean isBack);
}
