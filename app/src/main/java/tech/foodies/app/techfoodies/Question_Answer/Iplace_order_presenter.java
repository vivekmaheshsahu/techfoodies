package tech.foodies.app.techfoodies.Question_Answer;

import tech.foodies.ins_armman.techfoodies.data.model.syncing.QuestionAnswer;
import tech.foodies.ins_armman.techfoodies.data.model.syncing.beneficiaries;
import tech.foodies.ins_armman.techfoodies.utility.IBasePresenter;

import java.util.ArrayList;

public interface Iplace_order_presenter<v> extends IBasePresenter<v> {

    public ArrayList<beneficiaries> fetchUserDetails();

    public ArrayList<QuestionAnswer> fetchQuestionDetails();

}
