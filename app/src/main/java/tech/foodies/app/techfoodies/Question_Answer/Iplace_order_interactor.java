package tech.foodies.ins_armman.techfoodies.Question_Answer;

import android.database.Cursor;

public interface Iplace_order_interactor {

    public Cursor fetchCustDetails();
    public Cursor fetchProductList();

}