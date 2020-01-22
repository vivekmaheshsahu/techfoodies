package tech.foodies.inventory.app.Display_Product;

import android.content.Context;

import java.util.List;

import tech.foodies.inventory.app.data.model.completeFiledForm;

public interface IshowProductsView {

    public Context getContext();

    void setAdapter(List<completeFiledForm> mWomenList);

}
