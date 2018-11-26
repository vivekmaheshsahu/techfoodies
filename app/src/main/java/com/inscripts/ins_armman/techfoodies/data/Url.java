package com.inscripts.ins_armman.techfoodies.data;

/**
 * @author Aniket & Vivek  Created on 16/8/2018
 */

public interface Url {

    //API V2 added after data sync error message structure changed

    // String BASE_URL = "http://aww.armman.org/nutrition_API/";
    String BASE_URL = "http://www.vandanafoods.com/inventory/api/";

    String AUTHENTICATE = "login";
    String DOWNLOAD_FORMS = "newjson";
    String DOWNLOAD_HELP_MANUAL = "gethelp";
    String RELEASE = "release";
    String SYNC_REGISTRATION_DATA = "registration";
    String SYNC_UPDATE_PHOTO_DATA = "update_image";
    String SYNC_FORM_DATA = "visits";
    String SYNC_REFERRAL_DATA = "update_referral";
    String SYNC_CHILD_GROWTH = "childgrowth";
    String GET_REGISTRATIONS = "getregistrations";
    String GET_VISITS = "getvisits";
    String GET_REFERRALS = "getreferrals";
    String GET_CHILD_GRWOTH = "getchildgrowth";

}
