package tech.foodies.inventory.app.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import tech.foodies.inventory.app.data.model.syncing.beneficiaries;

import static tech.foodies.inventory.app.utility.Constants.BENEFICIARIES;

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
