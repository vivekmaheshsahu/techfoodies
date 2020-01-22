package tech.foodies.inventory.app.Question_Answer;

import java.util.ArrayList;

import tech.foodies.inventory.app.data.model.syncing.QuestionAnswer;
import tech.foodies.inventory.app.data.model.syncing.beneficiaries;
import tech.foodies.inventory.app.utility.IBasePresenter;

public interface Iplace_order_presenter<v> extends IBasePresenter<v> {

    public ArrayList<beneficiaries> fetchUserDetails();

    public ArrayList<QuestionAnswer> fetchQuestionDetails();

}
