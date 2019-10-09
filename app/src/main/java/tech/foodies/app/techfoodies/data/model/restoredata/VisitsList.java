package tech.foodies.app.techfoodies.data.model.restoredata;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import tech.foodies.app.techfoodies.data.model.syncing.QuestionAnswer;

/**
 * @author Aniket & Vivek  Created on 4/9/2018
 */

public class VisitsList {
    @SerializedName("quantity")
    private int formId;
    @SerializedName("product_name")
    private String createdOn;
//    @SerializedName("data")
//    private ArrayList<QuestionAnswer> questionAnswers;

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

//    public ArrayList<QuestionAnswer> getQuestionAnswers() {
//        return questionAnswers;
//    }
//
//    public void setQuestionAnswers(ArrayList<QuestionAnswer> questionAnswers) {
//        this.questionAnswers = questionAnswers;
//    }

    @Override
    public String toString() {
        return "VisitsList{" +
                "formId=" + formId +
                ", createdOn='" + createdOn + '\'' +
              //  ", questionAnswers=" + questionAnswers +
                '}';
    }
}
