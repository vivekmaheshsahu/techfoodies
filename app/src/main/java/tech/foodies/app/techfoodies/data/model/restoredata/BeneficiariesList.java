package tech.foodies.app.techfoodies.data.model.restoredata;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * @author Aniket & Vivek  Created on 4/9/2018
 */

public class BeneficiariesList {
    @SerializedName("unique_id")
    private String uniqueId;
    @SerializedName("OrderDetail")
    private ArrayList<VisitsList> visitsList;

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public ArrayList<VisitsList> getVisitsList() {
        return visitsList;
    }

    public void setVisitsList(ArrayList<VisitsList> visitsList) {
        this.visitsList = visitsList;
    }

    @Override
    public String toString() {
        return "BeneficiariesList{" +
                "uniqueId='" + uniqueId + '\'' +
                ", visitsList=" + visitsList +
                '}';
    }
}
