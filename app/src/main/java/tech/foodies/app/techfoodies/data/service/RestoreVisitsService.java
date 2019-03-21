package tech.foodies.app.techfoodies.data.service;

import android.content.Context;

import tech.foodies.ins_armman.techfoodies.R;
import tech.foodies.ins_armman.techfoodies.data.model.restoredata.RestoreDataRequest;
import tech.foodies.ins_armman.techfoodies.data.model.restoredata.RestoreVisits;
import tech.foodies.ins_armman.techfoodies.settingActivity.ISettingInteractor;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author Aniket & Vivek  Created on 4/9/2018
 */

public class RestoreVisitsService {
    private RestoreVisitsServiceAPI mServiceAPI;

    public RestoreVisitsService(RestoreVisitsServiceAPI mServiceAPI) {
        this.mServiceAPI = mServiceAPI;
    }

    public void downloadVisitsData(final Context context, RestoreDataRequest request, final ISettingInteractor.OnVisitsDownloadFinished downloadFinished) {
        Call<RestoreVisits> call = mServiceAPI.restoreRegistrationData(request);
        call.enqueue(new Callback<RestoreVisits>() {
            @Override
            public void onResponse(Call<RestoreVisits> call, Response<RestoreVisits> response) {
                if (response.code() == 200) {
                    downloadFinished.onSuccessVisitsDownloading(response.body());
                } else {
                    try {
                        downloadFinished.onFailure(response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<RestoreVisits> call, Throwable t) {
                downloadFinished.onFailure(context.getString(R.string.oops_some_thing_happened_wrong));
            }
        });
    }
}
