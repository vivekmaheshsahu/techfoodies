package tech.foodies.app.techfoodies.mainMenu;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;

import tech.foodies.ins_armman.techfoodies.data.model.SyncRegistrationDetails;
import tech.foodies.ins_armman.techfoodies.data.model.syncing.FormDetails;
import tech.foodies.ins_armman.techfoodies.data.retrofit.RemoteDataSource;
import tech.foodies.ins_armman.techfoodies.data.service.SyncFormService;
import tech.foodies.ins_armman.techfoodies.data.service.SyncRegistrationService;
import tech.foodies.ins_armman.techfoodies.database.DBHelper;
import tech.foodies.ins_armman.techfoodies.database.DatabaseContract;
import tech.foodies.ins_armman.techfoodies.database.GenericCursorLoader;
import tech.foodies.ins_armman.techfoodies.database.LocalDataSource;
import tech.foodies.ins_armman.techfoodies.utility.Constants;
import tech.foodies.ins_armman.techfoodies.utility.utility;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static tech.foodies.ins_armman.techfoodies.utility.Constants.INVALID_DATA;
import static tech.foodies.ins_armman.techfoodies.utility.Constants.RESPONSE;
import static tech.foodies.ins_armman.techfoodies.utility.Constants.STATUS;
import static tech.foodies.ins_armman.techfoodies.utility.Constants.UNIQUE_ID;
import static tech.foodies.ins_armman.techfoodies.utility.utility.getDatabase;

/**
 * @author Aniket & Vivek  Created on 15/8/2018
 */

public class MainInteractor implements IMainInteractor, LoaderManager.LoaderCallbacks<Cursor> {

    private Context mContext;
    private DBHelper dbHelper;
    private MainPresenter.OnQueryFinished mOnQueryFinished;

    MainInteractor(Context context, IMainPresenter.OnQueryFinished mOnQueryFinished) {
        this.mContext = context;
        dbHelper = new DBHelper(context);
        this.mOnQueryFinished = mOnQueryFinished;
    }


    @Override
    public void resetFailureStatus() {
        SQLiteDatabase sqLiteDatabase = getDatabase();
        sqLiteDatabase.beginTransaction();

        ContentValues values = new ContentValues();
        //column name is same IN all table
        values.put(DatabaseContract.FilledFormStatusTable.COLUMN_FAILURE_STATUS, 0);

        sqLiteDatabase.update(DatabaseContract.RegistrationTable.TABLE_NAME,
                values,
                null,
                null);

        sqLiteDatabase.update(DatabaseContract.FilledFormStatusTable.TABLE_NAME,
                values,
                null,
                null);


        getDatabase().setTransactionSuccessful();
        getDatabase().endTransaction();
    }

    @Override
    public Integer fetchUnsentFormsCount() {
        String count = dbHelper.fetchCount();
        return Integer.valueOf(count);
    }

    @Override
    public void fetchRegistrationDetails(int id) {
        String query = "SELECT * FROM "
                + DatabaseContract.RegistrationTable.TABLE_NAME
                + " WHERE "
                + DatabaseContract.RegistrationTable.COLUMN_SYNC_STATUS + " = 0 "
                + " AND "
                + DatabaseContract.RegistrationTable.COLUMN_REGISTRATION_STATUS + " = 1 "
                + " AND "
                + DatabaseContract.RegistrationTable.COLUMN_FAILURE_STATUS + " = 0 "
                + " LIMIT 1 ";

        Bundle bundle = new Bundle();
        bundle.putString(Constants.RAW_QUERY, query);
        bundle.putString(Constants.QUERY_TYPE, LocalDataSource.QueryType.RAW.name());
        ((AppCompatActivity) mContext).getSupportLoaderManager().restartLoader(id, bundle, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle bundle) {
        SQLiteDatabase sqLiteDatabase = getDatabase();
        String queryType = bundle.getString(Constants.QUERY_TYPE);
        if (queryType != null && queryType.equalsIgnoreCase(LocalDataSource.QueryType.RAW.name())) {
            return new GenericCursorLoader(mContext, sqLiteDatabase, bundle, LocalDataSource.QueryType.RAW);
        } else {
            return new GenericCursorLoader(mContext, sqLiteDatabase, bundle, LocalDataSource.QueryType.FUNCTION);
        }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mOnQueryFinished.onSuccess(data, loader.getId());
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    @Override
    public Cursor checkUnsentForms() {
        String query = "SELECT f.* FROM "
                + DatabaseContract.FilledFormStatusTable.TABLE_NAME + " f"
                + " JOIN "
                + DatabaseContract.RegistrationTable.TABLE_NAME + " r"
                + " ON "
                + " f." + DatabaseContract.FilledFormStatusTable.COLUMN_UNIQUE_ID + " = "
                + " r." + DatabaseContract.RegistrationTable.COLUMN_UNIQUE_ID
                + " WHERE "
                + " f." + DatabaseContract.FilledFormStatusTable.COLUMN_FORM_SYNC_STATUS + " = 0 "
                + " AND "
                + " f." + DatabaseContract.FilledFormStatusTable.COLUMN_FORM_COMPLETION_STATUS + " = 1 "
                + " AND "
                + " f." + DatabaseContract.FilledFormStatusTable.COLUMN_FAILURE_STATUS + " = 0 "
                + " AND "
                + " r." + DatabaseContract.RegistrationTable.COLUMN_SYNC_STATUS + " = 1 "
                + " LIMIT 1 ";
        return utility.getDatabase().rawQuery(query, null);
    }

    @Override
    public void sendForms(FormDetails formDetails, OnFormSync onFormSync) {
        RemoteDataSource remoteDataSource = RemoteDataSource.getInstance();
        SyncFormService syncFormService = remoteDataSource.syncFormService();
        syncFormService.syncForms(formDetails, onFormSync, mContext);
    }

    @Override
    public Cursor fetchFormData(String referenceId) {
        String query = "SELECT * FROM "
                + DatabaseContract.QuestionAnswerTable.TABLE_NAME
                + " WHERE "
                + DatabaseContract.QuestionAnswerTable.COLUMN_REFERENCE_ID + " = ? ";
        return utility.getDatabase().rawQuery(query, new String[]{referenceId});
    }

    @Override
    public int fetchUnregisteredChildCount(String motherId) {
//        Cursor cursor = utility.getDatabase().query(DatabaseContract.RegistrationTable.TABLE_NAME,
//                new String[]{DatabaseContract.RegistrationTable.COLUMN_ID},
//                DatabaseContract.RegistrationTable.COLUMN_MOTHER_ID + " =? "
//                        + " AND "
//                        + DatabaseContract.RegistrationTable.COLUMN_REGISTRATION_STATUS + " = 0 ",
//                new String[]{motherId},
//                null,
//                null,
//                null);
//        if (cursor != null && cursor.moveToFirst()) {
//            int childCount = cursor.getCount();
//            cursor.close();
//            return childCount;
//        }

        return 0;
    }

    @Override
    public void updateFormFailureStatus(String uniqueId, String formId, String errorMsg) {
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.FilledFormStatusTable.COLUMN_FAILURE_STATUS, 1);
        values.put(DatabaseContract.FilledFormStatusTable.COLUMN_FAILURE_REASON, errorMsg);

        int result = utility.getDatabase().update(DatabaseContract.FilledFormStatusTable.TABLE_NAME,
                values,
                DatabaseContract.FilledFormStatusTable.COLUMN_UNIQUE_ID + "=? AND "
                        + DatabaseContract.FilledFormStatusTable.COLUMN_FORM_ID + " =? ",
                new String[]{uniqueId, formId});

        if (result < 1)
            throw new IllegalArgumentException("Invalid unique &/ formId");
    }


    @Override
    public void sendRegistrationBasicDetails(SyncRegistrationDetails registrationDetails, IMainInteractor.OnDataSync onDataSync) {
        RemoteDataSource remoteDataSource = RemoteDataSource.getInstance();
        SyncRegistrationService registrationService = remoteDataSource.syncRegistrationService();
        registrationService.syncRegistrationDetails(registrationDetails, onDataSync, mContext);
    }

    @Override
    public void updateFormSyncStatus(String uniqueId, String formId) {
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.FilledFormStatusTable.COLUMN_FORM_SYNC_STATUS, 1);
        values.put(DatabaseContract.FilledFormStatusTable.COLUMN_FAILURE_STATUS, 0);
        values.put(DatabaseContract.FilledFormStatusTable.COLUMN_FAILURE_REASON, "");

        utility.getDatabase().update(DatabaseContract.FilledFormStatusTable.TABLE_NAME
                , values
                , DatabaseContract.FilledFormStatusTable.COLUMN_UNIQUE_ID + " = ? "
                        + " AND "
                        + DatabaseContract.FilledFormStatusTable.COLUMN_FORM_ID + " = ? "
                , new String[]{uniqueId, formId});
    }

    @Override
    public void updateRegistrationSyncStatus(JSONArray jsonArray) {
        int length = jsonArray.length();
        ContentValues values = new ContentValues();
        for (int i = 0; i < length; i++) {
            try {
                JSONObject object = jsonArray.getJSONObject(i);
                if (object.optBoolean(STATUS)) {
                    values.put(DatabaseContract.RegistrationTable.COLUMN_SYNC_STATUS, 1);
                    values.put(DatabaseContract.RegistrationTable.COLUMN_FAILURE_STATUS, 0);
                    values.put(DatabaseContract.RegistrationTable.COLUMN_FAILURE_REASON, "");
                    utility.getDatabase().update(DatabaseContract.RegistrationTable.TABLE_NAME
                            , values
                            , DatabaseContract.RegistrationTable.COLUMN_UNIQUE_ID + " = ? "
                            , new String[]{object.optString(UNIQUE_ID)});
                    values.clear();
                } else if (object.has(UNIQUE_ID)) {
                    values.put(DatabaseContract.RegistrationTable.COLUMN_FAILURE_STATUS, 1);
                    values.put(DatabaseContract.RegistrationTable.COLUMN_FAILURE_REASON,
                            object.optString(RESPONSE, INVALID_DATA));

                    utility.getDatabase().update(DatabaseContract.RegistrationTable.TABLE_NAME
                            , values
                            , DatabaseContract.RegistrationTable.COLUMN_UNIQUE_ID + " = ? "
                            , new String[]{object.optString(UNIQUE_ID)});
                    values.clear();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void fetchLoginDetails(int id) {
        Bundle bundle = new Bundle();
        bundle.putString(Constants.QUERY_ARGS_TABLE_NAME, DatabaseContract.LoginTable.TABLE_NAME);
        bundle.putStringArray(Constants.QUERY_ARGS_PROJECTION, null);
        bundle.putString(Constants.QUERY_ARGS_SELECTION, null);
        bundle.putStringArray(Constants.QUERY_ARGS_SELECTION_ARGS, null);
        ((AppCompatActivity) mContext).getSupportLoaderManager().restartLoader(id, bundle, this);
    }
}

