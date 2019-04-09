package tech.foodies.app.techfoodies.Question_Answer;

import java.util.ArrayList;

import tech.foodies.app.techfoodies.data.model.syncing.QuestionAnswer;
import tech.foodies.app.techfoodies.data.model.syncing.beneficiaries;
import tech.foodies.app.techfoodies.utility.IBasePresenter;

public interface Iplace_order_presenter<v> extends IBasePresenter<v> {

    public ArrayList<beneficiaries> fetchUserDetails();

    public ArrayList<QuestionAnswer> fetchQuestionDetails();

}
