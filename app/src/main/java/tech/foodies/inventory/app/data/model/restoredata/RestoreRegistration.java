package tech.foodies.inventory.app.data.model.restoredata;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import tech.foodies.inventory.app.data.model.syncing.beneficiaries;

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
