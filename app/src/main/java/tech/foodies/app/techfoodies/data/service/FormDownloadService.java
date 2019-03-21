package tech.foodies.ins_armman.techfoodies.data.service;

import android.content.Context;

import tech.foodies.ins_armman.techfoodies.R;
import tech.foodies.ins_armman.techfoodies.data.model.RequestFormModel;
import tech.foodies.ins_armman.techfoodies.settingActivity.ISettingInteractor;
import tech.foodies.ins_armman.techfoodies.utility.utility;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author Aniket & Vivek  Created on 4/9/2018
 */

public class FormDownloadService {

    private FormDownloadServiceAPI formDownloadServiceAPI;

    public FormDownloadService(FormDownloadServiceAPI formDownloadServiceAPI) {
        this.formDownloadServiceAPI = formDownloadServiceAPI;
    }

    public void downloadForms(RequestFormModel requestFormModel, final ISettingInteractor.OnFormDownloadFinished onFormDownloadFinished, final Context context) {
        if (requestFormModel != null) {
            Call<ResponseBody> responseBodyCall = formDownloadServiceAPI.downloadFormJson(requestFormModel);
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
                        onFormDownloadFinished.onSuccessFormDownloading(loginJsonObject, utility.mdFive(loginJsonResponse));
                    } catch (IOException e) {
                        e.printStackTrace();
                        onFormDownloadFinished.onFailure(context.getString(R.string.input_output_error_occured));
                    } catch (JSONException e) {
                        e.printStackTrace();
                        onFormDownloadFinished.onFailure(context.getString(R.string.invalid_data_frm_server));
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    onFormDownloadFinished.onFailure(context.getString(R.string.oops_some_thing_happened_wrong));
                }
            });
        }
    }
}
