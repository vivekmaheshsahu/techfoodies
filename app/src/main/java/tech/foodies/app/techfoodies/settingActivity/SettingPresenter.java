package tech.foodies.app.techfoodies.settingActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import tech.foodies.app.techfoodies.R;
import tech.foodies.app.techfoodies.data.model.RequestFormModel;
import tech.foodies.app.techfoodies.data.model.UpdateModel;
import tech.foodies.app.techfoodies.data.model.restoredata.BeneficiariesList;
import tech.foodies.app.techfoodies.data.model.restoredata.RestoreDataRequest;
import tech.foodies.app.techfoodies.data.model.restoredata.RestoreRegistration;
import tech.foodies.app.techfoodies.data.model.restoredata.RestoreVisits;
import tech.foodies.app.techfoodies.data.model.syncing.beneficiaries;
import tech.foodies.app.techfoodies.database.DatabaseContract;
import tech.foodies.app.techfoodies.login.Login;
import tech.foodies.app.techfoodies.utility.Constants;
import tech.foodies.app.techfoodies.utility.utility;

/**
 * @author Aniket & Vivek  Created on 21/8/2018
 */

public class SettingPresenter implements ISettingPresenter<ISettingView>
        , ISettingInteractor.OnFormDownloadFinished, ISettingInteractor.onCheckUpdateFinished,
        ISettingInteractor.OnRegistrationsDownloadFinished, ISettingInteractor.OnVisitsDownloadFinished {

    private static final int FETCH_USER_DATA = 101;
    private static final int FETCH_FORM_HASH = 102;
    SettingInteractor settingInteractor;
    private ISettingView mSettingsView;
    private String mUsername, mPassword, mFormHash;
    OnQueryFinished onQueryFinished = new OnQueryFinished() {

        @Override
        public void onSuccess(Cursor cursor, int id) {
            switch (id) {
                case FETCH_USER_DATA:
                    if (cursor.moveToFirst()) {
                        mUsername = cursor.getString(cursor.getColumnIndex(DatabaseContract.LoginTable.COLUMN_USERNAME));
                        mPassword = cursor.getString(cursor.getColumnIndex(DatabaseContract.LoginTable.COLUMN_PASSWORD));
                    }
                    break;

                case FETCH_FORM_HASH:
                    if (cursor.moveToFirst()) {
                        mFormHash = cursor.getString(cursor.getColumnIndex(DatabaseContract.HashTable.COLUMN_HASH));
                    }
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
    private RestoreDataRequest mRequest;
    private boolean totalPagesCalculated;
    private int totalPages;
    private int pageCounter;
    private ArrayList<beneficiaries> listRegistrations = new ArrayList<>();
    private ArrayList<BeneficiariesList> listVisits = new ArrayList<>();

    @Override
    public void attachView(ISettingView view) {
        mSettingsView = view;
        settingInteractor = new SettingInteractor(mSettingsView.getContext(), this, onQueryFinished);
        settingInteractor.fetchLoginDetails(FETCH_USER_DATA);
        settingInteractor.fetchFormJsonHash(FETCH_FORM_HASH);
    }

    @Override
    public void detch() {
        mSettingsView = null;
    }

    @Override
    public void changeLanguage(Context context, String language) {
        settingInteractor.changeLocale(context, language);
    }

    @Override
    public void logout() {
        settingInteractor.deleteLoginDetails();
        Intent intent = new Intent(mSettingsView.getContext(), Login.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        mSettingsView.getContext().startActivity(intent);
    }

    @Override
    public void downloadForms() {
        if (utility.hasInternetConnectivity(mSettingsView.getContext())) {
            mSettingsView.showProgressBar(mSettingsView.getContext().getString(R.string.downloading_data));
            RequestFormModel details = new RequestFormModel();
            details.setUserName(mUsername);
            details.setPassword(mPassword);
            //     details.setImei(utility.getDeviceImeiNumber(mSettingsView.getContext()));
            details.setHash(settingInteractor.getHash(Constants.HASH_ITEM_FORM));
            details.setShowdata("true");

            settingInteractor.downloadForms(details, this);
        } else
            mSettingsView.showSnackBar(mSettingsView.getContext().getString(R.string.no_internet_connection));
    }

    @Override
    public void onSuccessFormDownloading(JSONObject jsonObject, String hash) {
        mSettingsView.hideProgressBar();

        if (jsonObject.has("response")) {
            mSettingsView.showSnackBar(mSettingsView.getContext().getString(R.string.forms_already_updated));
        } else {
            try {
                jsonObject.put("hash", hash);
                settingInteractor.saveFormData(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
                mSettingsView.showSnackBar(mSettingsView.getContext().getString(R.string.invalid_data_frm_server));
            }

        }
    }

    @Override
    public void onFailure(String message) {
        mSettingsView.hideProgressBar();
        String errorString = "";
        try {
            JSONObject errorObject = new JSONObject(message);
            if (errorObject.get("response").equals("NO_RECORDS")) {
                errorString = mSettingsView.getContext().getString(R.string.no_records_error_msg);
            } else
                errorString = message;
        } catch (JSONException e) {
            errorString = message;
            e.printStackTrace();
        }
        mSettingsView.showSnackBar(errorString);
    }

    @Override
    public void checkUpdate() {
        if (utility.hasInternetConnectivity(mSettingsView.getContext())) {
            mSettingsView.showProgressBar(mSettingsView.getContext().getString(R.string.looking_for_update));
            settingInteractor.checkReleaseUpdate(this);
        } else
            mSettingsView.showSnackBar(mSettingsView.getContext().getString(R.string.no_internet_connection));
    }

    @Override
    public void onUpdateCheckSuccess(UpdateModel updateModel) {
        mSettingsView.hideProgressBar();
        if (!updateModel.getStatus()) {
            mSettingsView.showSnackBar(mSettingsView.getContext().getString(R.string.dialog_app_updated_text));
            return;
        }
        Context mContext = mSettingsView.getContext();
        try {
            PackageInfo pInfo = mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0);
            int versionCode = pInfo.versionCode;
            int newVersionCode = Integer.parseInt(updateModel.getData().getVersionCode());

            if (newVersionCode > versionCode)
                mSettingsView.updateAvailable(updateModel.getData().getLink());
            else
                mSettingsView.showSnackBar(mSettingsView.getContext().getString(R.string.dialog_app_updated_text));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void downloadApk(String apkLink) {
        mSettingsView.showApkDownloadProgress();
        settingInteractor.downloadAndSaveApk(apkLink);
    }

    @Override
    public void setApkDownloadProgress(int progress) {
        mSettingsView.updateApkDownloadProgress(progress);
    }

    @Override
    public void onApkDownloaded() {
        mSettingsView.dismissApkDownloadProgress();
    }

    @Override
    public void restoreData() {
        if (utility.hasInternetConnectivity(mSettingsView.getContext())) {
            mSettingsView.showProgressBar(mSettingsView.getContext().getString(R.string.downloading_data));
            resetDataMemberValues();
            restoreRegistrations(pageCounter);
        } else
            mSettingsView.showSnackBar(mSettingsView.getContext().getString(R.string.no_internet_connection));

    }

    @Override
    public void resetDataMemberValues() {
        mRequest = new RestoreDataRequest();
        mRequest.setUserName(mUsername);
        mRequest.setPassword(mPassword);
        // mRequest.setImei(utility.getDeviceImeiNumber(mSettingsView.getContext()));
        mRequest.setLimit(Constants.FORM_DOWNLOAD_LIMIT);

        pageCounter = 1;
        totalPages = 0;
        totalPagesCalculated = false;
        listRegistrations.clear();
        listVisits.clear();
    }

    @Override
    public void restoreRegistrations(int pageNumber) {
        mRequest.setPageNumber(pageNumber);
        settingInteractor.downloadRegistrationData(mRequest, this);
    }

    @Override
    public void onSuccessRegistrationsDownloading(RestoreRegistration registration) {
        if (registration.getTotal() > 0) {
            listRegistrations.addAll(registration.getRegistrationData());

            if (!totalPagesCalculated) {
                totalPagesCalculated = true;
                totalPages = (int) Math.ceil((double) registration.getTotal() / (double) Constants.FORM_DOWNLOAD_LIMIT);
            }
        }

        if (pageCounter < totalPages) {
            restoreRegistrations(++pageCounter);
        } else {
            pageCounter = 1;
            totalPages = 0;
            totalPagesCalculated = false;
            mSettingsView.hideProgressBar();
            settingInteractor.saveDownloadedData(listRegistrations, listVisits);
            //restoreVisits(pageCounter);
        }
    }

    @Override
    public void restoreVisits(int pageNumber) {
        mRequest.setPageNumber(pageNumber);
        settingInteractor.downloadVisitsData(mRequest, this);
    }

    @Override
    public void onSuccessVisitsDownloading(RestoreVisits visits) {

        if (visits.getTotal() > 0) {
            listVisits.addAll(visits.getBeneficiariesLists());

            if (!totalPagesCalculated) {
                totalPagesCalculated = true;
                totalPages = (int) Math.ceil((double) visits.getTotal() / (double) Constants.FORM_DOWNLOAD_LIMIT);
            }
        }

        if (pageCounter < totalPages) {
            restoreVisits(++pageCounter);
        } else {
            mSettingsView.hideProgressBar();
            settingInteractor.saveDownloadedData(listRegistrations, listVisits);
        }
    }
}
