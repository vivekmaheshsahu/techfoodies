package tech.foodies.ins_armman.techfoodies.all_order;

import android.database.Cursor;

import tech.foodies.ins_armman.techfoodies.data.model.all_order_model;

import java.util.ArrayList;
import java.util.List;

public class all_order_presenter implements Iall_order_presenter<Iall_order_view> {

    Iall_order_view iall_order_view;
    all_order_interactor all_order_inter;

    @Override
    public void attachView(Iall_order_view iall_order_view1) {
        this.iall_order_view = iall_order_view1;
        all_order_inter = new all_order_interactor(iall_order_view1.getContext());
    }

    @Override
    public void detch() {
        iall_order_view = null;
    }

    @Override
    public void getListCompleteForm() {
        List<all_order_model> womenList = new ArrayList<>();
        Cursor cursor = all_order_inter.fetchDetails();
        if (cursor != null && cursor.moveToFirst())
            do {
                womenList.add(new all_order_model(cursor.getString(cursor.getColumnIndex("sname")),cursor.getString(cursor.getColumnIndex("name")),cursor.getString(cursor.getColumnIndex("unique_id"))));
            } while (cursor.moveToNext());

        iall_order_view.setAdapter(womenList);
    }


}

