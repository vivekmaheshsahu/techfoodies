package tech.foodies.app.techfoodies.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * This is model class of UserDetails used to create json and pass parameter in retrofit api request
 *
 * @author Aniket & Vivek  Created on 4/9/2018
 */

public class UserDetails {
    @SerializedName("username")
    private String userName;
    @SerializedName("password")
    private String password;
    @SerializedName("user_id")
    private String imei;
    @SerializedName("showdata")
    private String showdata;

    public void setShowdata(String showdata) {
        this.showdata = showdata;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserDetails{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", imei=" + imei +
                ", showdata='" + showdata + '\'' +
                '}';
    }

}
