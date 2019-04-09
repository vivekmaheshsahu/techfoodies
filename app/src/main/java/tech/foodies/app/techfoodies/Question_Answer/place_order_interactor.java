package tech.foodies.app.techfoodies.Question_Answer;

import android.content.Context;
import android.database.Cursor;

import tech.foodies.app.techfoodies.database.DBHelper;

public class place_order_interactor implements Iplace_order_interactor {

    public Context context;
    DBHelper dbHelper;

    place_order_interactor(Context context1) {
        this.context = context1;
        dbHelper = new DBHelper(context1);
    }

    @Override
    public Cursor fetchCustDetails() {
        return dbHelper.getListOfCustomer();
    }

    @Override
    public Cursor fetchProductList() {
        return dbHelper.getListOfQuestion();
    }

}
