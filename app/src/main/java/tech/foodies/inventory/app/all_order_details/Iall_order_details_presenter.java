package tech.foodies.inventory.app.all_order_details;

import tech.foodies.inventory.app.utility.IBasePresenter;

public interface Iall_order_details_presenter<v> extends IBasePresenter<v> {

    public void getListCompleteForm(String unique_id,String createdOn);

}
