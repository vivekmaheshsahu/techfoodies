package tech.foodies.ins_armman.techfoodies.all_order;


import android.content.Context;

import tech.foodies.ins_armman.techfoodies.data.model.all_order_model;

import java.util.List;

public interface Iall_order_view {

   Context getContext();

   void setAdapter(List<all_order_model> mWomenList);

   }
