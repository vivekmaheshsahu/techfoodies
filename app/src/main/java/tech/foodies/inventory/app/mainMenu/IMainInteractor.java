package tech.foodies.inventory.app.mainMenu;

import android.database.Cursor;

import org.json.JSONArray;
import org.json.JSONObject;

import tech.foodies.inventory.app.data.model.SyncRegistrationDetails;
import tech.foodies.inventory.app.data.model.syncing.FormDetails;

/**
 * @author Aniket & Vivek  Created on 15/8/2018
 */

public interface IMainInteractor {

    Integer fetchUnsentFormsCount();

    void resetFailureStatus();

    void fetchRegistrationDetails(int id);

    Cursor checkUnsentForms();

    Cursor fetchFormData(String referenceId);

    void sendForms(FormDetails formDetails, IMainInteractor.OnFormSync onFormSync);

    int fetchUnregisteredChildCount(String motherId);

    void updateFormFailureStatus(String uniqueId, String formId, String errorMsg);

    void sendRegistrationBasicDetails(SyncRegistrationDetails registrationDetails, IMainInteractor.OnDataSync onDataSync);

    void updateFormSyncStatus(String uniqueId, String formId);

    void updateRegistrationSyncStatus(JSONArray jsonArray);

    void fetchLoginDetails(int id);

    interface OnFormSync {
        void onSuccessfullySyncForm(JSONObject jsonObject);

        void onFailure(String message);
    }

    interface OnDataSync {
        void onSuccessfullySyncRegData(JSONObject jsonObjectResponse);

        void onFailure(String message);
    }
}
