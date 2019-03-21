package tech.foodies.app.techfoodies.Display_Product;

import android.content.Context;

import tech.foodies.ins_armman.techfoodies.data.model.completeFiledForm;

import java.util.List;

public interface IshowProductsView {

    public Context getContext();

    void setAdapter(List<completeFiledForm> mWomenList);

}
