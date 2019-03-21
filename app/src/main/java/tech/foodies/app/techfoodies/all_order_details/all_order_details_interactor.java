package tech.foodies.app.techfoodies.all_order_details;

import android.content.Context;
import android.database.Cursor;

import tech.foodies.ins_armman.techfoodies.database.DBHelper;

public class all_order_details_interactor implements Iall_order_details_interactor {

    DBHelper dbHelper;
    Context context;

    public all_order_details_interactor(Context context1)
    {
        dbHelper = new DBHelper(context1);
        this.context = context1;
    }

    @Override
    public Cursor fetchDetails(String uniqueId,String createdOn) {
        return dbHelper.OrderListForm(uniqueId,createdOn);
    }
}

