package tech.foodies.app.techfoodies.data.service;

import tech.foodies.app.techfoodies.data.model.restoredata.RestoreDataRequest;
import tech.foodies.app.techfoodies.data.model.restoredata.RestoreRegistration;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

import static tech.foodies.app.techfoodies.data.Url.GET_REGISTRATIONS;

/**
 * @author Aniket & Vivek  Created on 4/9/2018
 */

public interface RestoreRegistrationServiceAPI {
    @Headers({
            "Content-Type: application/json"
    })
    @POST(GET_REGISTRATIONS)
    Call<RestoreRegistration> restoreRegistrationData(@Body RestoreDataRequest request);
}
