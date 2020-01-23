package tech.foodies.inventory.app.data.model.restoredata;

import com.google.gson.annotations.SerializedName;

/**
 * @author Aniket & Vivek  Created on 4/9/2018
 */

public class VisitsList {
    @SerializedName("quantity")
    private int formId;
    @SerializedName("product_name")
    private String createdOn;

    public int getFormId() {
        return formId;
    }

    public void setFormId(int formId) {
        this.formId = formId;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    @Override
    public String toString() {
        return "VisitsList{" +
                "formId=" + formId +
                ", createdOn='" + createdOn + '\'' +
              //  ", questionAnswers=" + questionAnswers +
                '}';
    }
}
