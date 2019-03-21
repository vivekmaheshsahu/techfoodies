package tech.foodies.ins_armman.techfoodies.mainMenu;

import android.database.Cursor;

import tech.foodies.ins_armman.techfoodies.R;
import tech.foodies.ins_armman.techfoodies.data.model.SyncRegistrationDetails;
import tech.foodies.ins_armman.techfoodies.data.model.syncing.FormDetails;
import tech.foodies.ins_armman.techfoodies.data.model.syncing.QuestionAnswer;
import tech.foodies.ins_armman.techfoodies.data.model.syncing.beneficiaries;
import tech.foodies.ins_armman.techfoodies.database.DatabaseContract;
import tech.foodies.ins_armman.techfoodies.utility.utility;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import tech.foodies.ins_armman.techfoodies.utility.Constants;

/**
 * @author Aniket & Vivek  Created on 15/8/2018
 */

public class MainPresenter implements IMainPresenter<IMainView>, IMainInteractor.OnDataSync
        , IMainInteractor.OnFormSync {

    private static final int FETCH_REGISTRATION_DATA = 102;
    private static final int FETCH_USER_DATA = 101;
    private static final int FETCH_UNSENT_FORM_COUNT = 104;
    private static final int FETCH_GROWTH_MONITORING_DATA = 105;
    private static final int FETCH_AROGYASAKHI_INFO = 108;
    IMainView iMainView;
    MainInteractor mainInteractor;
    private String mUsername, mPassword,mUserId;
    private ArrayList<String> mImei;
    private IMainPresenter.OnQueryFinished mOnQueryFinished = new OnQueryFinished() {
        @Override
        public void onSuccess(Cursor cursor, int id) {
            switch (id) {
                case FETCH_USER_DATA:
                    if (cursor.moveToFirst()) {
                        mUsername = cursor.getString(cursor.getColumnIndex(DatabaseContract.LoginTable.COLUMN_USERNAME));
                        mPassword = cursor.getString(cursor.getColumnIndex(DatabaseContract.LoginTable.COLUMN_PASSWORD));
                        mUserId = cursor.getString(cursor.getColumnIndex(DatabaseContract.LoginTable.COLUMN_USER_ID));
                        mImei = utility.getDeviceImeiNumber(iMainView.getContext());
                        String arogyasakhiName =
                                cursor.getString(cursor.getColumnIndex(DatabaseContract.LoginTable.COLUMN_NAME));
                    }
                    break;

                case FETCH_UNSENT_FORM_COUNT:
                    if (cursor.moveToFirst())
                        iMainView.setUnsentFormsCount(cursor.getInt(0));
                    break;

                case FETCH_REGISTRATION_DATA:
                    if (cursor.getCount() > 0) {
                        onFetchedRegistrationData(cursor);
                    } else syncUnsentForms();
                    break;
                case FETCH_GROWTH_MONITORING_DATA:
                    iMainView.hideProgressBar();
                    fetchUnsentFormsCount();
                    break;


            }
        }

        @Override
        public void onSuccess() {

        }

        @Override
        public void onFailure() {

        }
    };

    @Override
    public void attachView(IMainView iMainVIEW) {
        this.iMainView = iMainVIEW;
        mainInteractor = new MainInteractor(iMainView.getContext(), mOnQueryFinished);
        mainInteractor.fetchLoginDetails(FETCH_USER_DATA);
    }

    @Override
    public void detch() {
        iMainView = null;
    }

    @Override
    public void fetchUnsentFormsCount() {
        int count = mainInteractor.fetchUnsentFormsCount();
        iMainView.setUnsentFormsCount(count);
    }

    @Override
    public void fetchRegistrationData() {
        iMainView.showProgressBar(iMainView.getContext().getString(R.string.uploading_forms));

        new ResetFailureTask(new OnResetTaskCompleted() {
            @Override
            public void onResetCompleted() {
                mainInteractor.fetchRegistrationDetails(FETCH_REGISTRATION_DATA);
            }
        },
                mainInteractor)
                .execute();
    }

    @Override
    public void onFetchedRegistrationData(Cursor cursor) {
        SyncRegistrationDetails regDetails = new SyncRegistrationDetails();
        regDetails.setUserName(mUsername);
        regDetails.setPassword(mPassword);
       // regDetails.setImei(mImei);

        ArrayList<beneficiaries> regData = new ArrayList<>();
        while (cursor.moveToNext()) {
            beneficiaries details = new beneficiaries();

            String uniqueId = cursor.getString(cursor.getColumnIndex(DatabaseContract.RegistrationTable.COLUMN_UNIQUE_ID));
            details.setUniqueId(uniqueId);
             details.setName(cursor.getString(cursor.getColumnIndex(DatabaseContract.RegistrationTable.COLUMN_FIRST_NAME)));
            details.setAddress(cursor.getString(cursor.getColumnIndex(DatabaseContract.RegistrationTable.COLUMN_ADDRESS)));
            details.setMobNo(cursor.getString(cursor.getColumnIndex(DatabaseContract.RegistrationTable.COLUMN_MOBILE_NO)));
            details.setCity(cursor.getString(cursor.getColumnIndex(DatabaseContract.RegistrationTable.COLUMN_CITY)));
            details.setState(cursor.getString(cursor.getColumnIndex(DatabaseContract.RegistrationTable.COLUMN_STATE)));
            details.setSname(cursor.getString(cursor.getColumnIndex(DatabaseContract.RegistrationTable.COLUMN_SNAME)));
            details.setAltNumber(cursor.getString(cursor.getColumnIndex(DatabaseContract.RegistrationTable.COLUMN_ALT_CONTACT)));
            details.setZone(cursor.getString(cursor.getColumnIndex(DatabaseContract.RegistrationTable.COLUMN_ZONE)));
            regData.add(details);
        }

        if (regData.size() == 0)
            return;

        regDetails.setRegData(regData);

        mainInteractor.sendRegistrationBasicDetails(regDetails, this);
    }

    @Override
    public void syncUnsentForms() {
        Cursor cursor = mainInteractor.checkUnsentForms();

        if (cursor.moveToFirst()) {

            FormDetails details = new FormDetails();
            ArrayList<QuestionAnswer> answerList = new ArrayList<>();

            details.setUserName(mUsername);
            details.setPassword(mPassword);
            details.setImei(mUserId);
            String uniqueId = cursor.getString(cursor.getColumnIndex(DatabaseContract.FilledFormStatusTable.COLUMN_UNIQUE_ID));
            details.setUniqueId(uniqueId);
            String formId = cursor.getString(cursor.getColumnIndex(DatabaseContract.FilledFormStatusTable.COLUMN_FORM_ID));
            details.setFormId(formId);


            if (String.valueOf(Constants.DELIVERY_FORM_ID).equals(formId)) {
                int unregisteredChildCount = mainInteractor.fetchUnregisteredChildCount(uniqueId);
                if (unregisteredChildCount > 0) {
                    markImproperVisitToSync(uniqueId, formId, Constants.CHILD_UNREGISTERED_ERROR);
                    return;
                }
            }

            Cursor cursorForm = mainInteractor.fetchFormData(cursor.getString(cursor.getColumnIndex(DatabaseContract.FilledFormStatusTable.COLUMN_ID)));
            while (cursorForm.moveToNext()) {
                QuestionAnswer answer = new QuestionAnswer();
                answer.setKeyword(cursorForm.getString(cursorForm.getColumnIndex(DatabaseContract.QuestionAnswerTable.COLUMN_QUESTION_KEYWORD)));
                answer.setAnswer(cursorForm.getString(cursorForm.getColumnIndex(DatabaseContract.QuestionAnswerTable.COLUMN_ANSWER_KEYWORD)));
                answer.setCreatedOn(cursorForm.getString(cursorForm.getColumnIndex(DatabaseContract.QuestionAnswerTable.COLUMN_CREATED_ON)));
                answerList.add(answer);
            }

            details.setData(answerList);

            mainInteractor.sendForms(details, this);

        } else {

            iMainView.hideProgressBar();
            fetchUnsentFormsCount();
        }
    }

    private void markImproperVisitToSync(String uniqueId, String formId, String errorMsg) {
        mainInteractor.updateFormFailureStatus(uniqueId, formId, errorMsg);
        syncUnsentForms();
    }

    @Override
    public void onSuccessfullySyncForm(JSONObject jsonObject) {
        if (jsonObject.optBoolean(Constants.STATUS)) {
            mainInteractor.updateFormSyncStatus(jsonObject.optString(Constants.UNIQUE_ID), jsonObject.optString(Constants.FORM_ID));
            syncUnsentForms();
        } else {
            if (jsonObject.optString(Constants.RESPONSE).equals(Constants.AUTHENTICATION_FAILED)
                    || jsonObject.optString(Constants.RESPONSE).equals(Constants.INVALID_IMEI))
                onFailure(iMainView.getContext().getString(R.string.authentication_error_msg));
            else {
                mainInteractor.updateFormFailureStatus(
                        jsonObject.optString(Constants.UNIQUE_ID),
                        jsonObject.optString(Constants.FORM_ID),
                        jsonObject.optString(Constants.RESPONSE, Constants.INVALID_DATA)
                );
                syncUnsentForms();
            }

        }
    }

    @Override
    public void onSuccessfullySyncRegData(JSONObject jsonObjectResponse) {
        if (!jsonObjectResponse.optBoolean(Constants.STATUS)
                && (jsonObjectResponse.optString(Constants.RESPONSE).equals(Constants.AUTHENTICATION_FAILED)
                || jsonObjectResponse.optString(Constants.RESPONSE).equals(Constants.INVALID_IMEI)))
            onFailure(iMainView.getContext().getString(R.string.authentication_error_msg));
        else {
            try {
                JSONArray jsonArrayResponse = jsonObjectResponse.getJSONArray(Constants.DATA);
                JSONObject object = jsonArrayResponse.getJSONObject(0);
                mainInteractor.updateFormSyncStatus(object.optString(Constants.UNIQUE_ID), object.optString(Constants.FORM_ID));
                mainInteractor.updateRegistrationSyncStatus(jsonArrayResponse);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            mainInteractor.fetchRegistrationDetails(FETCH_REGISTRATION_DATA);
        }
    }

    @Override
    public void onFailure(String message) {
        iMainView.hideProgressBar();
        iMainView.showSnackBar(message);
        fetchUnsentFormsCount();
    }
}
