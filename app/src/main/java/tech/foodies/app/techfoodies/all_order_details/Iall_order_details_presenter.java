package tech.foodies.app.techfoodies.all_order_details;

import tech.foodies.app.techfoodies.utility.IBasePresenter;

public interface Iall_order_details_presenter<v> extends IBasePresenter<v> {

    public void getListCompleteForm(String unique_id,String createdOn);

}
