package tech.foodies.ins_armman.techfoodies.data.model;

import com.google.gson.annotations.SerializedName;
import tech.foodies.ins_armman.techfoodies.data.model.syncing.beneficiaries;

import java.util.ArrayList;

import static tech.foodies.ins_armman.techfoodies.utility.Constants.BENEFICIARIES;

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
