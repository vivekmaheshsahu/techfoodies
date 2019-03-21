package tech.foodies.app.techfoodies.data.service;

import tech.foodies.app.techfoodies.data.model.syncing.FormDetails;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import tech.foodies.app.techfoodies.data.Url;

/**
 * @author Aniket & Vivek  Created on 4/9/2018
 */

public interface SyncFormServiceApi {
    @Headers({
            "Content-Type: application/json"
    })
    @POST(Url.SYNC_FORM_DATA)
    Call<ResponseBody> syncFormDetails(@Body FormDetails formDetails);
}
