package tech.foodies.app.techfoodies.settingActivity;

import android.content.Context;

import tech.foodies.app.techfoodies.data.model.RequestFormModel;
import tech.foodies.app.techfoodies.data.model.UpdateModel;
import tech.foodies.app.techfoodies.data.model.restoredata.BeneficiariesList;
import tech.foodies.app.techfoodies.data.model.restoredata.RestoreDataRequest;
import tech.foodies.app.techfoodies.data.model.restoredata.RestoreRegistration;
import tech.foodies.app.techfoodies.data.model.restoredata.RestoreVisits;
import tech.foodies.app.techfoodies.data.model.syncing.beneficiaries;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * @author Aniket & Vivek  Created on 21/8/2018
 */

public interface ISettingInteractor {

    void changeLocale(Context context, String language);

    void deleteLoginDetails();

    void downloadAndSaveApk(String apkLink);

    void downloadRegistrationData(RestoreDataRequest request, OnRegistrationsDownloadFinished downloadFinished);

    void downloadVisitsData(RestoreDataRequest request, OnVisitsDownloadFinished downloadFinished);

    void saveFormData(JSONObject formJsonObject);

    void downloadForms(RequestFormModel requestFormModel, OnFormDownloadFinished onFormDownloadFinished);

    String getHash(String type);

    void addOrUpdateFormHash(String item, String hash);

    void fetchLoginDetails(int id);

    void fetchFormJsonHash(int id);

    void checkReleaseUpdate(onCheckUpdateFinished onCheckUpdateFinished);

    void saveDownloadedData(ArrayList<beneficiaries> listRegistrations, ArrayList<BeneficiariesList> listVisits);

    interface OnFormDownloadFinished {
        void onSuccessFormDownloading(JSONObject jsonObject, String hash);

        void onFailure(String message);
    }

    interface onCheckUpdateFinished {
        void onUpdateCheckSuccess(UpdateModel updateModel);

        void onFailure(String message);
    }

    interface OnRegistrationsDownloadFinished {
        void onSuccessRegistrationsDownloading(RestoreRegistration registration);

        void onFailure(String message);
    }

    interface OnVisitsDownloadFinished {
        void onSuccessVisitsDownloading(RestoreVisits visits);

        void onFailure(String message);
    }

}
