package tech.foodies.inventory.app.all_order_details;

import android.database.Cursor;

public interface Iall_order_details_interactor {

    Cursor fetchDetails(String uniqueId,String createdOn);

}
