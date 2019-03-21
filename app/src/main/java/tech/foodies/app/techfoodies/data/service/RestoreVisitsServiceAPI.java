package tech.foodies.ins_armman.techfoodies.data.service;

import tech.foodies.ins_armman.techfoodies.data.model.restoredata.RestoreDataRequest;
import tech.foodies.ins_armman.techfoodies.data.model.restoredata.RestoreVisits;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import tech.foodies.ins_armman.techfoodies.data.Url;

/**
 * @author Aniket & Vivek  Created on 4/9/2018
 */

public interface RestoreVisitsServiceAPI {
    @Headers({
            "Content-Type: application/json"
    })
    @POST(Url.GET_VISITS)
    Call<RestoreVisits> restoreRegistrationData(@Body RestoreDataRequest request);
}
