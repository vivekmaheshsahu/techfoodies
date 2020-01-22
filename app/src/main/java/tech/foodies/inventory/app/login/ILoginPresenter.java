package tech.foodies.inventory.app.login;

import java.util.List;

import tech.foodies.inventory.app.utility.IBasePresenter;

/**
 * @author Aniket & Vivek  Created on 15/8/2018
 */

public interface ILoginPresenter<V> extends IBasePresenter<V> {

    void initializeDBHelper();

    boolean checkPermissions();

    void getPermissions(List<String> listPermissionsNeeded);

    void validateCredentials(String username, String password);

    void loginUser(String username, String password);

    void createRequestBody(String username, String password);

    void checkIfUserAlreadyLoggedIn();
}
