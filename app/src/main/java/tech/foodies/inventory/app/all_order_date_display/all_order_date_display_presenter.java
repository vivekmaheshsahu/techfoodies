package tech.foodies.inventory.app.all_order_date_display;

import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import tech.foodies.inventory.app.data.model.all_order_model;

public class all_order_date_display_presenter implements Iall_order_presenter<Iall_order_view> {

    Iall_order_view iall_order_view;
    all_order_interactor_date_display all_order_inter;

    @Override
    public void attachView(Iall_order_view iall_order_view1) {
        this.iall_order_view = iall_order_view1;
        all_order_inter = new all_order_interactor_date_display(iall_order_view1.getContext());
    }

    @Override
    public void detch() {
        iall_order_view = null;
    }

    @Override
    public void getListCompleteForm(String unique_id) {
        List<all_order_model> womenList = new ArrayList<>();
        Cursor cursor = all_order_inter.fetchDetails(unique_id);
        if (cursor != null && cursor.moveToFirst())
            do {
                womenList.add(new all_order_model(cursor.getString(cursor.getColumnIndex("created_on")), cursor.getString(cursor.getColumnIndex("unique_id")), ""));
            } while (cursor.moveToNext());

        iall_order_view.setAdapter(womenList);
    }


}

