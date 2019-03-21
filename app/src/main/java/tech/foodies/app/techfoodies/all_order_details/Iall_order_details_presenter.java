package tech.foodies.ins_armman.techfoodies.all_order_details;

import tech.foodies.ins_armman.techfoodies.utility.IBasePresenter;

public interface Iall_order_details_presenter<v> extends IBasePresenter<v> {

    public void getListCompleteForm(String unique_id,String createdOn);

}
