package tech.foodies.inventory.app.data.service;


import tech.foodies.inventory.app.data.model.SyncRegistrationDetails;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

import static tech.foodies.inventory.app.data.Url.SYNC_REGISTRATION_DATA;

/**
 * @author Aniket & Vivek  Created on 4/9/2018
 */

public interface SyncRegistrationServiceApi {
    @Headers({
            "Content-Type: application/json"
    })
    @POST(SYNC_REGISTRATION_DATA)
    Call<ResponseBody> SyncRegistrationDetails(@Body SyncRegistrationDetails registrationDetails);
}
