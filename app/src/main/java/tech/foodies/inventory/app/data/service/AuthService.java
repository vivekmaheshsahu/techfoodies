package tech.foodies.inventory.app.data.service;


import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tech.foodies.inventory.app.R;
import tech.foodies.inventory.app.data.model.UserDetails;
import tech.foodies.inventory.app.login.LoginInteractor;

/**
 * @author Aniket & Vivek  Created on 16/8/2018
 */

public class AuthService {
    private static final String TAG = "AuthService";
    private LoginServiceAPI loginServiceAPI;

    public AuthService(LoginServiceAPI loginServiceAPI) {
        this.loginServiceAPI = loginServiceAPI;
    }

    public void getAuthentication(UserDetails userDetails, final LoginInteractor.OnLoginFinished onLoginFinished, final Context context) {
        if (userDetails != null) {
            Call<ResponseBody> responseBodyCall = loginServiceAPI.getAuthentication(userDetails);
            responseBodyCall.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    try {
                        String loginJsonResponse = null;
                        if (response.body() != null) {
                            loginJsonResponse = response.body().string();
                        } else if (response.errorBody() != null) {
                            loginJsonResponse = response.errorBody().string();
                        }
                        JSONObject loginJsonObject = new JSONObject(loginJsonResponse);
                        onLoginFinished.onSuccess(loginJsonObject);
                    } catch (IOException e) {
                        e.printStackTrace();
                        onLoginFinished.onFailure(context.getString(R.string.input_output_error_occured));
                    } catch (JSONException e) {
                        e.printStackTrace();
                        onLoginFinished.onFailure(context.getString(R.string.invalid_data_frm_server));
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    onLoginFinished.onFailure(context.getString(R.string.oops_some_thing_happened_wrong));
                }
            });
        }
    }
}
