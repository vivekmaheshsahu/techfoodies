package tech.foodies.app.techfoodies.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import tech.foodies.app.techfoodies.data.model.syncing.beneficiaries;

import static tech.foodies.app.techfoodies.utility.Constants.BENEFICIARIES;

/**
 * @author Aniket & Vivek  Created on 4/9/2018
 */

public class SyncRegistrationDetails extends UserDetails {
    @SerializedName(BENEFICIARIES)
    private ArrayList<beneficiaries> regData;

    public void setRegData(ArrayList<beneficiaries> regData) {
        this.regData = regData;
    }

    @Override
    public String toString() {
        return "SyncRegistrationDetails{" +
                "regData=" + regData +
                '}';
    }
}
