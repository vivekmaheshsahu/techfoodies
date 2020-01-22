package tech.foodies.inventory.app.login;


import tech.foodies.inventory.app.utility.IMvpView;

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
