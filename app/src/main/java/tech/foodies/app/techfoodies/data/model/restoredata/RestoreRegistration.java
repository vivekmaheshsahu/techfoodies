package tech.foodies.app.techfoodies.data.model.restoredata;

import com.google.gson.annotations.SerializedName;
import tech.foodies.ins_armman.techfoodies.data.model.syncing.beneficiaries;

import java.util.ArrayList;

/**
 * @author Aniket & Vivek  Created on 4/9/2018
 */

public class RestoreRegistration {
    @SerializedName("total")
    private int total;
    @SerializedName("data")
    private ArrayList<beneficiaries> registrationData;

    public int getTotal() {
        return total;
    }

    public ArrayList<beneficiaries> getRegistrationData() {
        return registrationData;
    }
}
