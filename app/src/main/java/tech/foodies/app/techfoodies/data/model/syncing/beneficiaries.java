package tech.foodies.app.techfoodies.data.model.syncing;

import com.google.gson.annotations.SerializedName;

import tech.foodies.app.techfoodies.database.DatabaseContract.RegistrationTable;

/**
 * @author Aniket & Vivek  Created on 4/9/2018
 */

public class beneficiaries {

    @SerializedName(RegistrationTable.COLUMN_UNIQUE_ID)
    private String customer_id;
    @SerializedName(RegistrationTable.COLUMN_FIRST_NAME)
    private String customer_first_name;
    @SerializedName(RegistrationTable.COLUMN_ADDRESS)
    private String customer_shop_address;
    @SerializedName(RegistrationTable.COLUMN_MOBILE_NO)
    private String customer_shop_name;
    @SerializedName(RegistrationTable.COLUMN_ALT_CONTACT)
    private String customer_phone1;
    @SerializedName(RegistrationTable.COLUMN_CITY)
    private String customer_city;
    @SerializedName(RegistrationTable.COLUMN_STATE)
    private String customer_state;
    @SerializedName(RegistrationTable.COLUMN_SNAME)
    private String sname;
    @SerializedName(RegistrationTable.COLUMN_ZONE)
    private String zone;

    public String getUniqueId() {
        return customer_id;
    }

    public void setUniqueId(String uniqueId) {
        this.customer_id = uniqueId;
    }

    public String getName() {
        return customer_first_name;
    }

    public void setName(String firstName) {
        this.customer_first_name = firstName;
    }

    public String getAddress() {
        return customer_shop_address;
    }

    public void setAddress(String address) {
        this.customer_shop_address = address;
    }

    public String getMobNo() {
        return customer_shop_name;
    }

    public void setMobNo(String mobNo) {
        this.customer_shop_name = mobNo;
    }

    public String getAltNumber() {
        return customer_phone1;
    }

    public void setAltNumber(String altNumber) {
        this.customer_phone1 = altNumber;
    }

    public String getCity() {
        return customer_city;
    }

    public void setCity(String city) {
        this.customer_city = city;
    }

    public String getState() {
        return customer_state;
    }

    public void setState(String state) {
        this.customer_state = state;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }


    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    @Override

    public String toString() {
        return "beneficiaries{" +
                "uniqueId='" + customer_id + '\'' +
                ", Name='" + customer_city + '\'' +
                ", address='" + customer_first_name + '\'' +
                ", mobNo='" + customer_shop_address + '\'' +
                ", altNumber='" + customer_phone1 + '\'' +
                ", city='" + customer_city + '\'' +
                ", state='" + customer_state + '\'' +
                ", sname='" + sname + '\'' +
                '}';
    }
}
