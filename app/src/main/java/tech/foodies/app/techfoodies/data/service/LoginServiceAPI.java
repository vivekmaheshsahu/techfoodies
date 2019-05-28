package tech.foodies.app.techfoodies.data.service;

import tech.foodies.app.techfoodies.data.model.UserDetails;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

import static tech.foodies.app.techfoodies.data.Url.AUTHENTICATE;

/**
 * @author Aniket & Vivek  Created on 16/8/2018
 */

public interface LoginServiceAPI {

    @Headers({
            "Content-Type: application/json"
    })
    @POST(AUTHENTICATE)
    Call<ResponseBody> getAuthentication(@Body UserDetails userDetails);

}
