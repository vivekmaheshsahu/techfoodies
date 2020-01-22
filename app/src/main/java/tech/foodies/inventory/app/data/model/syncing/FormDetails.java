package tech.foodies.inventory.app.data.model.syncing;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import tech.foodies.inventory.app.data.model.UserDetails;

import static tech.foodies.inventory.app.utility.Constants.DATA;
import static tech.foodies.inventory.app.utility.Constants.FORM_ID;
import static tech.foodies.inventory.app.utility.Constants.UNIQUE_ID;

/**
 * @author Aniket & Vivek  Created on 4/9/2018
 */

public class FormDetails extends UserDetails {
    @SerializedName(UNIQUE_ID)
    private String uniqueId;
    @SerializedName(FORM_ID)
    private String formId;
    @SerializedName(DATA)
    private ArrayList<QuestionAnswer> data;

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }

    public void setData(ArrayList<QuestionAnswer> data) {
        this.data = data;
    }

}
