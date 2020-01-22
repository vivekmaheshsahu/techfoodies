package tech.foodies.inventory.app.all_order_date_display;


import android.content.Context;
import android.database.Cursor;

import tech.foodies.inventory.app.database.DBHelper;

public class all_order_interactor_date_display implements Iall_order_interactor {

    DBHelper dbHelper;
    Context context;

    all_order_interactor_date_display(Context context1) {
        dbHelper = new DBHelper(context1);
        this.context = context1;
    }

    @Override
    public Cursor fetchDetails(String id) {
        return dbHelper.getOrderDateListList(id);
    }

}
