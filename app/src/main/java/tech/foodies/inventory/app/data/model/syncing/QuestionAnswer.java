package tech.foodies.inventory.app.data.model.syncing;

import com.google.gson.annotations.SerializedName;

import static tech.foodies.inventory.app.utility.Constants.ANSWER;
import static tech.foodies.inventory.app.utility.Constants.CREATED_ON;
import static tech.foodies.inventory.app.utility.Constants.QUESTION_KEYWORD;

/**
 * @author Aniket & Vivek  Created on 4/9/2018
 */

public class QuestionAnswer {
    @SerializedName(QUESTION_KEYWORD)
    private String keyword;
    @SerializedName(ANSWER)
    private String answer;
    @SerializedName(CREATED_ON)
    private String createdOn;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    @Override
    public String toString() {
        return "QuestionAnswer{" +
                "keyword='" + keyword + '\'' +
                ", answer='" + answer + '\'' +
                ", createdOn='" + createdOn + '\'' +
                '}';
    }
}
