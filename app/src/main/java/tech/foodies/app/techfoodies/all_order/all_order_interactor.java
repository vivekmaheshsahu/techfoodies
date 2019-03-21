package tech.foodies.ins_armman.techfoodies.all_order;


import android.content.Context;
import android.database.Cursor;

import tech.foodies.ins_armman.techfoodies.database.DBHelper;

public class all_order_interactor implements Iall_order_interactor {

    DBHelper dbHelper;
    Context context;

    all_order_interactor(Context context1)
    {
        dbHelper = new DBHelper(context1);
        this.context = context1;
    }

    @Override
    public Cursor fetchDetails() {
        return dbHelper.getRegistrationFormListList();
    }

}
