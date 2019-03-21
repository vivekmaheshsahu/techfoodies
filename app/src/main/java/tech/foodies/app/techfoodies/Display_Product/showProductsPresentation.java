package tech.foodies.app.techfoodies.Display_Product;

import android.database.Cursor;

import tech.foodies.ins_armman.techfoodies.data.model.completeFiledForm;

import java.util.ArrayList;
import java.util.List;

public class showProductsPresentation implements IshowProductsPresentation<IshowProductsView> {

    IshowProductsView ishowProductsView;
    showProductsInteractor showProductsInteractor;

    @Override
    public void attachView(IshowProductsView ishowProducts) {
        this.ishowProductsView = ishowProducts;
        showProductsInteractor = new showProductsInteractor(ishowProducts.getContext());
    }

    @Override
    public void detch() {
        ishowProductsView = null;
    }

    @Override
    public void getListCompleteForm() {
        List<completeFiledForm> womenList = new ArrayList<>();
        Cursor cursor = showProductsInteractor.fetchListcompleteForm();
        if (cursor != null && cursor.moveToFirst())
            do {
                womenList.add(new completeFiledForm(cursor.getString(cursor.getColumnIndex("keyword")),cursor.getString(cursor.getColumnIndex("question_id"))));
            } while (cursor.moveToNext());

        ishowProductsView.setAdapter(womenList);
    }
}
