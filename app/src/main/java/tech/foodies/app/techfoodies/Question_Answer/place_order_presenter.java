package tech.foodies.app.techfoodies.Question_Answer;

import android.database.Cursor;

import java.util.ArrayList;

import tech.foodies.app.techfoodies.data.model.syncing.QuestionAnswer;
import tech.foodies.app.techfoodies.data.model.syncing.beneficiaries;

public class place_order_presenter implements Iplace_order_presenter<Iplace_order_view> {

    Iplace_order_view iplace_order_view;
    place_order_interactor place_order;
    ArrayList<QuestionAnswer> arrayList;
    ArrayList<beneficiaries> arrayList_cust;

    @Override
    public void attachView(Iplace_order_view iplace_order_view1) {
        this.iplace_order_view = iplace_order_view1;
        place_order = new place_order_interactor(iplace_order_view1.getContext());
    }

    @Override
    public void detch() {
        iplace_order_view = null;
    }


    @Override
    public ArrayList<beneficiaries> fetchUserDetails() {
        arrayList_cust = new ArrayList<>();
        QuestionAnswer details = new QuestionAnswer();
        Cursor cursor1 = place_order.fetchCustDetails();
        if (cursor1.moveToFirst() && cursor1 != null) {
            for (int i = 0; i < cursor1.getCount(); i++) {
                beneficiaries answer = new beneficiaries();
                answer.setUniqueId(cursor1.getString(cursor1.getColumnIndex("customer_id")));
                answer.setSname(cursor1.getString(cursor1.getColumnIndex("customer_shop_name")));
                answer.setName(cursor1.getString(cursor1.getColumnIndex("customer_shop_address")));
                arrayList_cust.add(answer);
                cursor1.moveToNext();
            }
            cursor1.close();
        } else {
            details.setKeyword("ERROR");
            details.setAnswer("error");
        }
        return arrayList_cust;
    }

    @Override
    public ArrayList<QuestionAnswer> fetchQuestionDetails() {
        arrayList = new ArrayList<>();
        QuestionAnswer details = new QuestionAnswer();
        Cursor cursor1 = place_order.fetchProductList();
        if (cursor1.moveToFirst() && cursor1 != null) {
            for (int i = 0; i < cursor1.getCount(); i++) {
                QuestionAnswer answer = new QuestionAnswer();
                answer.setKeyword(cursor1.getString(cursor1.getColumnIndex("keyword")));
                answer.setAnswer(cursor1.getString(cursor1.getColumnIndex("question_label")));
                arrayList.add(answer);
                cursor1.moveToNext();
            }
            cursor1.close();
        } else {
            details.setKeyword("ERROR");
            details.setAnswer("error");
        }
        return arrayList;
    }
}
