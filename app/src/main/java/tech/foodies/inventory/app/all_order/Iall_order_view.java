package tech.foodies.inventory.app.all_order;

import android.content.Context;

import tech.foodies.inventory.app.data.model.all_order_model;

import java.util.List;

public interface Iall_order_view {

   Context getContext();

   void setAdapter(List<all_order_model> mWomenList);

   }
