package tech.foodies.app.techfoodies.data.retrofit;


import retrofit2.Retrofit;
import tech.foodies.app.techfoodies.data.service.AuthService;
import tech.foodies.app.techfoodies.data.service.CheckUpdateApi;
import tech.foodies.app.techfoodies.data.service.CheckUpdateService;
import tech.foodies.app.techfoodies.data.service.FormDownloadService;
import tech.foodies.app.techfoodies.data.service.FormDownloadServiceAPI;
import tech.foodies.app.techfoodies.data.service.LoginServiceAPI;
import tech.foodies.app.techfoodies.data.service.RestoreRegistrationService;
import tech.foodies.app.techfoodies.data.service.RestoreRegistrationServiceAPI;
import tech.foodies.app.techfoodies.data.service.RestoreVisitsService;
import tech.foodies.app.techfoodies.data.service.RestoreVisitsServiceAPI;
import tech.foodies.app.techfoodies.data.service.SyncFormService;
import tech.foodies.app.techfoodies.data.service.SyncFormServiceApi;
import tech.foodies.app.techfoodies.data.service.SyncRegistrationService;
import tech.foodies.app.techfoodies.data.service.SyncRegistrationServiceApi;


/**
 * @author Aniket & Vivek  Created on 16/8/2018
 */

public class RemoteDataSource {
    private static RemoteDataSource mRemoteDataSource;
    private Retrofit mRestClient;

    private RemoteDataSource(Retrofit restClient) {
        mRestClient = restClient;
    }

    public static RemoteDataSource getInstance() {
        if (mRemoteDataSource == null) {
            mRemoteDataSource = new RemoteDataSource(RestClient.getClient());
        }
        return mRemoteDataSource;
    }

    public <T> T createApiService(Class<T> apiInterface) {
        return mRestClient.create(apiInterface);
    }

    public AuthService getAuthService() {
        return new AuthService(createApiService(LoginServiceAPI.class));
    }

    public FormDownloadService downloadFormService() {
        return new FormDownloadService(createApiService(FormDownloadServiceAPI.class));
    }

    public SyncFormService syncFormService() {
        return new SyncFormService(createApiService(SyncFormServiceApi.class));
    }

    public SyncRegistrationService syncRegistrationService() {
        return new SyncRegistrationService(createApiService(SyncRegistrationServiceApi.class));
    }

    public CheckUpdateService getCheckUpdateService() {
        return new CheckUpdateService(createApiService(CheckUpdateApi.class));
    }

    public RestoreRegistrationService restoreRegistrationService() {
        return new RestoreRegistrationService(createApiService(RestoreRegistrationServiceAPI.class));
    }

    public RestoreVisitsService restoreVisitsService() {
        return new RestoreVisitsService(createApiService(RestoreVisitsServiceAPI.class));
    }

  /*

    public HelpManualDownloadService helpManualDownloadService() {
        return new HelpManualDownloadService(createApiService(HelpManualDownloadServiceApi.class));
    }

    public SyncReferralService syncReferralService() {
        return new SyncReferralService(createApiService(SyncReferralServiceApi.class));
    }

    public SendChildGrowthDataService sendChildGrowthDataService() {
        return new SendChildGrowthDataService(createApiService(SendChildGrowthDataAPI.class));
    }

    public RestoreReferralService restoreReferralService() {
        return new RestoreReferralService(createApiService(RestoreReferralServiceAPI.class));
    }

    public SyncUpdatePhotoService syncUpdatePhotoService() {
        return new SyncUpdatePhotoService(createApiService(SyncUpdatePhotoServiceApi.class));
    }

    public RestoreChildGrowthService restoreChildGrowthService() {
        return new RestoreChildGrowthService(createApiService(RestoreChildGrowthServiceAPI.class));
    }*/
}
