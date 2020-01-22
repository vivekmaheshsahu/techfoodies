package tech.foodies.inventory.app.all_order_date_display;


import android.content.Context;

import java.util.List;

import tech.foodies.inventory.app.data.model.all_order_model;

public interface Iall_order_view {

    Context getContext();

    void setAdapter(List<all_order_model> mWomenList);

}
