package tech.foodies.inventory.app.data.service;


import tech.foodies.inventory.app.data.model.RequestFormModel;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

import static tech.foodies.inventory.app.data.Url.DOWNLOAD_FORMS;

/**
 * @author Aniket & Vivek  Created on 4/9/2018
 */

public interface FormDownloadServiceAPI {
    @Headers({
            "Content-Type: application/json"
    })
    @POST(DOWNLOAD_FORMS)
    Call<ResponseBody> downloadFormJson(@Body RequestFormModel requestFormModel);
}
