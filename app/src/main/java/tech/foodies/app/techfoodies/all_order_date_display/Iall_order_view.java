package tech.foodies.app.techfoodies.all_order_date_display;


import android.content.Context;

import java.util.List;

import tech.foodies.app.techfoodies.data.model.all_order_model;

public interface Iall_order_view {

    Context getContext();

    void setAdapter(List<all_order_model> mWomenList);

}
