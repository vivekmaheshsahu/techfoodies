package tech.foodies.app.techfoodies.login;

import tech.foodies.ins_armman.techfoodies.utility.IBasePresenter;
import java.util.List;

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
