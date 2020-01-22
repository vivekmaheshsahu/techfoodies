package tech.foodies.inventory.app.data.service;

import tech.foodies.inventory.app.data.model.syncing.FormDetails;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import tech.foodies.inventory.app.data.Url;

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
