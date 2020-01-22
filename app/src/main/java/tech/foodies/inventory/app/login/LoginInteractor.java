package tech.foodies.inventory.app.login;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import org.json.JSONException;
import org.json.JSONObject;

import tech.foodies.inventory.app.data.model.UserDetails;
import tech.foodies.inventory.app.data.retrofit.RemoteDataSource;
import tech.foodies.inventory.app.data.service.AuthService;
import tech.foodies.inventory.app.database.DBHelper;
import tech.foodies.inventory.app.database.DatabaseContract;
import tech.foodies.inventory.app.utility.utility;

/**
 * @author Aniket & Vivek  Created on 15/8/2018
 */

public class LoginInteractor implements ILoginInteractor {

    private DBHelper dbHelper;
    private Context mcontext;

    LoginInteractor(Context context) {
        this.mcontext = context;
    }

    @Override
    public void login(UserDetails userDetails, OnLoginFinished onLoginFinished, Context context) {
        RemoteDataSource remoteDataSource = RemoteDataSource.getInstance();
        AuthService authService = remoteDataSource.getAuthService();
        authService.getAuthentication(userDetails, onLoginFinished, context);
    }

    @Override
    public void saveUserDetails(String username, String password, JSONObject jsonObject) throws JSONException {
        if (jsonObject.has("data")) {

            deleteUserDetails();

            JSONObject jsonObjectUser = jsonObject.getJSONObject("data");

            ContentValues values = new ContentValues();

            values.put(DatabaseContract.LoginTable.COLUMN_USER_ID, jsonObjectUser.optString("id"));
            values.put(DatabaseContract.LoginTable.COLUMN_NAME, jsonObjectUser.optString("first_name"));
            values.put(DatabaseContract.LoginTable.COLUMN_USERNAME, username);
            values.put(DatabaseContract.LoginTable.COLUMN_PASSWORD, password);
            values.put(DatabaseContract.LoginTable.COLUMN_PHONE_NO, jsonObjectUser.optString("contact"));
            values.put(DatabaseContract.LoginTable.COLUMN_CITY, jsonObjectUser.optString("city"));
            values.put(DatabaseContract.LoginTable.COLUMN_STATE, jsonObjectUser.optString("state"));
            values.put(DatabaseContract.LoginTable.COLUMN_USER_TYPE, jsonObjectUser.optString("user_type"));
            values.put(DatabaseContract.LoginTable.COLUMN_ZONE, jsonObjectUser.optString("zone"));

            utility.getDatabase().insert(DatabaseContract.LoginTable.TABLE_NAME, null, values);
        }
    }

    @Override
    public void deleteUserDetails() {
        utility.getDatabase().delete(DatabaseContract.LoginTable.TABLE_NAME, null, null);
    }

    @Override
    public boolean userAlreadyLoggedIn() {
        Cursor cursor = utility.getDatabase().rawQuery("SELECT * FROM "
                + DatabaseContract.LoginTable.TABLE_NAME, null);
        return cursor.moveToFirst();
    }
}
