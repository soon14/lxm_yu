package com.lxm.ss.sscai.base;

/**
 * Created by lxm on 2017/8/3.
 * UI update
 */

public interface IBaseView {

    void requestFailed(int code, String message);

    void showProgressDialogView();

    void hideProgressDialogView();
}
