package tech.foodies.inventory.app.login;

import android.content.Context;

import tech.foodies.inventory.app.data.model.UserDetails;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Aniket & Vivek  Created on 15/8/2018
 */

public interface ILoginInteractor {

    void saveUserDetails(String username, String password, JSONObject jsonObject) throws JSONException;

    void login(UserDetails userDetails, OnLoginFinished onLoginFinished, Context context);

    void deleteUserDetails();

    boolean userAlreadyLoggedIn();

    interface OnLoginFinished {

        void onSuccess(JSONObject jsonObject) throws JSONException;

        void onFailure(String message);
    }
}
