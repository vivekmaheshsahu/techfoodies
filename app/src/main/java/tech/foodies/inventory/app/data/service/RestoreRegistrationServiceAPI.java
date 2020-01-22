package tech.foodies.inventory.app.data.service;

import tech.foodies.inventory.app.data.model.restoredata.RestoreDataRequest;
import tech.foodies.inventory.app.data.model.restoredata.RestoreRegistration;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

import static tech.foodies.inventory.app.data.Url.GET_REGISTRATIONS;

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
