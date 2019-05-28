package tech.foodies.app.techfoodies.data.service;

import tech.foodies.app.techfoodies.data.model.UpdateModel;

import retrofit2.Call;
import retrofit2.http.GET;

import static tech.foodies.app.techfoodies.data.Url.RELEASE;

/**
 * @author Aniket & Vivek  Created on 4/9/2018
 */

public interface CheckUpdateApi {
    @GET(RELEASE)
    Call<UpdateModel> getUpdateData();
}
