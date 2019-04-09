package tech.foodies.app.techfoodies.Display_Product;

import android.content.Context;

import java.util.List;

import tech.foodies.app.techfoodies.data.model.completeFiledForm;

public interface IshowProductsView {

    public Context getContext();

    void setAdapter(List<completeFiledForm> mWomenList);

}
