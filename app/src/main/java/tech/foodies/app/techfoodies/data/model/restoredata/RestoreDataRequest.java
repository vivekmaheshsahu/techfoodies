package tech.foodies.ins_armman.techfoodies.data.model.restoredata;

import com.google.gson.annotations.SerializedName;
import tech.foodies.ins_armman.techfoodies.data.model.UserDetails;

/**
 * @author Aniket & Vivek  Created on 4/9/2018
 */

public class RestoreDataRequest extends UserDetails {
    @SerializedName("limit")
    private int limit;

    @SerializedName("pg")
    private int pageNumber;

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }
}
