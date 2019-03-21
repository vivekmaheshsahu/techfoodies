package tech.foodies.app.techfoodies.Display_Product;

import android.content.Context;
import android.database.Cursor;

import tech.foodies.ins_armman.techfoodies.database.DBHelper;

public class showProductsInteractor implements IshowProductsInteractor {

    public Context context;
    DBHelper dbHelper;

    showProductsInteractor(Context context2)
    {
        this.context = context2;
        dbHelper = new DBHelper(context2);
    }

    @Override
    public Cursor fetchListcompleteForm() {
        return dbHelper.getcompleteFormListList();
    }
}
