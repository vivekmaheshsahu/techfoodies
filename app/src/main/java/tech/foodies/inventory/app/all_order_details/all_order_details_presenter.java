package tech.foodies.inventory.app.all_order_details;

import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import tech.foodies.inventory.app.data.model.all_order_model;

public class all_order_details_presenter implements Iall_order_details_presenter<Iall_order_details_view> {

    Iall_order_details_view iall_order_view;
    all_order_details_interactor all_order_inter;

    @Override
    public void attachView(Iall_order_details_view iall_orde) {
        this.iall_order_view = iall_orde;
        all_order_inter = new all_order_details_interactor(iall_orde.getContext());
    }

    @Override
    public void detch() {
        iall_order_view = null;
    }

    @Override
    public void getListCompleteForm(String unique_id, String createdOn) {
        List<all_order_model> womenList = new ArrayList<>();

        Cursor cursor = all_order_inter.fetchDetails(unique_id, createdOn);
        if (cursor != null && cursor.moveToFirst())
            do {
                womenList.add(new all_order_model(cursor.getString(cursor.getColumnIndex("question_keyword")), cursor.getString(cursor.getColumnIndex("answer_keyword")), ""));
            } while (cursor.moveToNext());

        iall_order_view.setAdapter(womenList);
    }
}
