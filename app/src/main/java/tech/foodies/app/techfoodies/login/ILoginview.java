package tech.foodies.ins_armman.techfoodies.login;


import tech.foodies.ins_armman.techfoodies.utility.IMvpView;

/**
 * @author Aniket & Vivek  Created on 15/8/2018
 */

public interface ILoginview extends IMvpView {

    void setUsernameError();

    void setPasswordError();

    void resetErrorMsg();

    void showDialog(String title, String message);

    void showProgressBar();

    void hideProgressBar();

    void openHomeActivity();

    void setAuthenticationFailedError();
}
