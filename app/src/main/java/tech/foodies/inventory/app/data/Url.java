package tech.foodies.inventory.app.data;

/**
 * @author Aniket & Vivek  Created on 16/8/2018
 */

public interface Url {

    //API V2 added after data sync error message structure changed

    String BASE_URL = "http://www.inventory.vandanafoods.com/api/";
   // String BASE_URL = "http://arogyasakhi.armman.org/API/V2/";
    String AUTHENTICATE = "login";
    String DOWNLOAD_FORMS = "newjson";
    String DOWNLOAD_HELP_MANUAL = "gethelp";
    String RELEASE = "release";
    String SYNC_REGISTRATION_DATA = "registration";
    String SYNC_UPDATE_PHOTO_DATA = "update_image";
    String SYNC_FORM_DATA = "store_answer";
    String SYNC_REFERRAL_DATA = "update_referral";
    String SYNC_CHILD_GROWTH = "childgrowth";
    String GET_REGISTRATIONS = "getCustomerDetail";
    String GET_VISITS = "getOrderDetails";
    String GET_REFERRALS = "getreferrals";
    String GET_CHILD_GRWOTH = "getchildgrowth";

}
