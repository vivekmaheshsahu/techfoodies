package com.inscripts.ins_armman.techfoodies.data.model.syncing;

import com.google.gson.annotations.SerializedName;
import com.inscripts.ins_armman.techfoodies.database.DatabaseContract.RegistrationTable;

/**
 * @author Aniket & Vivek  Created on 4/9/2018
 */

public class beneficiaries {

    @SerializedName(RegistrationTable.COLUMN_UNIQUE_ID)
    private String uniqueId;
    @SerializedName(RegistrationTable.COLUMN_FIRST_NAME)
    private String Name;
    @SerializedName(RegistrationTable.COLUMN_ADDRESS)
    private String address;
    @SerializedName(RegistrationTable.COLUMN_MOBILE_NO)
    private String mobNo;
    @SerializedName(RegistrationTable.COLUMN_ALT_CONTACT)
    private String altNumber;
    @SerializedName(RegistrationTable.COLUMN_CITY)
    private String city;
    @SerializedName(RegistrationTable.COLUMN_STATE)
    private String state;
    @SerializedName(RegistrationTable.COLUMN_SNAME)
    private String sname;
    @SerializedName(RegistrationTable.COLUMN_ZONE)
    private String zone;

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String firstName) {
        this.Name = firstName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobNo() {
        return mobNo;
    }

    public void setMobNo(String mobNo) {
        this.mobNo = mobNo;
    }

    public String getAltNumber() {
        return altNumber;
    }

    public void setAltNumber(String altNumber) {
        this.altNumber = altNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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
                "uniqueId='" + uniqueId + '\'' +
                ", Name='" + Name + '\'' +
                ", address='" + address + '\'' +
                ", mobNo='" + mobNo + '\'' +
                ", altNumber='" + altNumber + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", sname='" + sname + '\'' +
                '}';
    }
}
