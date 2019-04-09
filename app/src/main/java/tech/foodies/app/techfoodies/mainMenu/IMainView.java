package tech.foodies.app.techfoodies.mainMenu;

import tech.foodies.app.techfoodies.utility.IMvpView;

/**
 * @author Aniket & Vivek  Created on 15/8/2018
 */

public interface IMainView extends IMvpView {

    void setUnsentFormsCount(int count);

    void showProgressBar(String label);

    void hideProgressBar();

    void showSnackBar(String message);
}
