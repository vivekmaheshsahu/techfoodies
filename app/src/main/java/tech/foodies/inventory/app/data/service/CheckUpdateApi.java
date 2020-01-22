package tech.foodies.inventory.app.data.service;

import tech.foodies.inventory.app.data.model.UpdateModel;

import retrofit2.Call;
import retrofit2.http.GET;

import static tech.foodies.inventory.app.data.Url.RELEASE;

/**
 * @author Aniket & Vivek  Created on 4/9/2018
 */

public interface CheckUpdateApi {
    @GET(RELEASE)
    Call<UpdateModel> getUpdateData();
}
