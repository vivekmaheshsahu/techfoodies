package tech.foodies.inventory.app.settingActivity;

import tech.foodies.inventory.app.utility.IMvpView;


/**
 * @author Aniket & Vivek  Created on 21/8/2018
 */

public interface ISettingView extends IMvpView {

    void showProgressBar(String label);

    void hideProgressBar();

    void showDialog(String title, String message);

    void updateAvailable(final String url);

    void showApkDownloadProgress();

    void updateApkDownloadProgress(int progress);

    void dismissApkDownloadProgress();

    void showSnackBar(String message);

}
