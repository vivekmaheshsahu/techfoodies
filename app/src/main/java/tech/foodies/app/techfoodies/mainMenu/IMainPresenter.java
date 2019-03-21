package tech.foodies.app.techfoodies.mainMenu;

import android.database.Cursor;

import tech.foodies.ins_armman.techfoodies.utility.IBasePresenter;

/**
 * @author Aniket & Vivek  Created on 15/8/2018
 */

public interface IMainPresenter<V> extends IBasePresenter<V> {

    void fetchUnsentFormsCount();

    void fetchRegistrationData();

    void onFetchedRegistrationData(Cursor cursor);

    void syncUnsentForms();

    interface OnQueryFinished {

        void onSuccess(Cursor cursor, int id);

        void onSuccess();

        void onFailure();
    }

    interface OnResetTaskCompleted {

        void onResetCompleted();
    }
}
