package tech.foodies.inventory.app.forms;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.widget.LinearLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import tech.foodies.inventory.app.R;
import tech.foodies.inventory.app.database.DatabaseContract;
import tech.foodies.inventory.app.utility.utility;

import static tech.foodies.inventory.app.utility.Keywords.AROGYASAKHI_MOB;
import static tech.foodies.inventory.app.utility.Keywords.AROGYASAKHI_NAME;

/**
 * @author Aniket & Vivek  Created on 4/9/2018
 */

public class QuestionInteractor {

    private Context mContext;

    public QuestionInteractor(Context mContext) {
        this.mContext = mContext;
    }

    public static String getCheckItemSelectedOption(String keyword) {
        Cursor cursor = utility.getDatabase().rawQuery("SELECT " + DatabaseContract.DependentQuestionsTable.COLUMN_FORM_ID + " FROM "
                + DatabaseContract.DependentQuestionsTable.TABLE_NAME
                + " WHERE "
                + DatabaseContract.DependentQuestionsTable.COLUMN_MAIN_QUESTION_OPTION_KEYWORD + " = ? ", new String[]{keyword});

        if (cursor.moveToFirst())
            return cursor.getString(cursor.getColumnIndex(DatabaseContract.QuestionOptionsTable.COLUMN_FORM_ID));
        else return null;
    }

    public String saveRegistrationDetails(String firstName, String address, String s_name,
                                          String mobileNo, String state, String city, String zone, int registrationStatus) {
        ContentValues values = new ContentValues();

        String woman_id = utility.generateUniqueId();

        values.put(DatabaseContract.RegistrationTable.COLUMN_UNIQUE_ID, woman_id);
        values.put(DatabaseContract.RegistrationTable.COLUMN_FIRST_NAME, firstName);
        values.put(DatabaseContract.RegistrationTable.COLUMN_ADDRESS, address);
        values.put(DatabaseContract.RegistrationTable.COLUMN_SNAME, s_name);
        values.put(DatabaseContract.RegistrationTable.COLUMN_MOBILE_NO, mobileNo);
        values.put(DatabaseContract.RegistrationTable.COLUMN_STATE, state);
        values.put(DatabaseContract.RegistrationTable.COLUMN_CITY, city);
        values.put(DatabaseContract.RegistrationTable.COLUMN_ZONE, zone);
        values.put(DatabaseContract.RegistrationTable.COLUMN_REGISTRATION_STATUS, registrationStatus);
        values.put(DatabaseContract.RegistrationTable.COLUMN_CREATED_ON, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).format(new Date()));

        utility.getDatabase().insert(DatabaseContract.RegistrationTable.TABLE_NAME, null, values);

        return woman_id;
    }

    public void saveReferralData(HashMap<String, String> highRiskList, String uniqueId, String formId) {
    /*    ContentValues vals = new ContentValues();
        Iterator<Map.Entry<String, String>> itr2 = highRiskList.entrySet().iterator();
        while (itr2.hasNext()) {
            Map.Entry<String, String> entry = itr2.next();
            entry.getKey();
            entry.getValue();
            String myString = entry.getValue();
            String[] a = myString.split(delimeter);

            vals.put(DatabaseContract.ReferralTable.COLUMN_UNIQUE_ID, uniqueId);
            vals.put(DatabaseContract.ReferralTable.COLUMN_NAME_FORM_ID, formId);
            vals.put(DatabaseContract.ReferralTable.COLUMN_NAME_HIGH_RISK_KEYWORD, a[2]);
            vals.put(DatabaseContract.ReferralTable.COLUMN_NAME_HIGH_RISK_ANSWER, a[3]);
            vals.put(DatabaseContract.ReferralTable.COLUMN_NAME_REFERRAL_TYPE, a[5]);
            vals.put(DatabaseContract.ReferralTable.COLUMN_NAME_STATUS, a[6]);

            Utility.getDatabase().insert(DatabaseContract.ReferralTable.TABLE_NAME, null, vals);

        }*/
    }

    public int saveFilledFormStatus(String uniqueId, int formId, int completionStatus, int syncStatus, String createdOn) {
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.FilledFormStatusTable.COLUMN_UNIQUE_ID, uniqueId);
        values.put(DatabaseContract.FilledFormStatusTable.COLUMN_FORM_ID, formId);
        values.put(DatabaseContract.FilledFormStatusTable.COLUMN_FORM_COMPLETION_STATUS, completionStatus);
        values.put(DatabaseContract.FilledFormStatusTable.COLUMN_FORM_SYNC_STATUS, syncStatus);
        values.put(DatabaseContract.FilledFormStatusTable.COLUMN_CREATED_ON, createdOn);

        return (int) utility.getDatabase().insert(DatabaseContract.FilledFormStatusTable.TABLE_NAME, null, values);
    }

    public int currentFormStatus(String uniqueId, int formId, int completionStatus, String createdOn) {
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.CurrentFormStatus.COLUMN_UNIQUE_ID, uniqueId);
        values.put(DatabaseContract.CurrentFormStatus.COLUMN_FORM_ID, formId);
        values.put(DatabaseContract.CurrentFormStatus.COLUMN_FORM_COMPLETION_STATUS, completionStatus);
        values.put(DatabaseContract.CurrentFormStatus.COLUMN_CREATED_ON, createdOn);

        return (int) utility.getDatabase().insert(DatabaseContract.CurrentFormStatus.TABLE_NAME, null, values);
    }

    public void currentFormUpdate(String uniqueID, int formID) {
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.CurrentFormStatus.COLUMN_FORM_ID, formID);

        utility.getDatabase().update(DatabaseContract.CurrentFormStatus.TABLE_NAME
                , values
                , DatabaseContract.CurrentFormStatus.COLUMN_UNIQUE_ID + " = ? "
                , new String[]{String.valueOf(uniqueID)});
    }

    public void updateFormCompletionStatus(int id) {
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.FilledFormStatusTable.COLUMN_FORM_COMPLETION_STATUS, 1);

        utility.getDatabase().update(DatabaseContract.FilledFormStatusTable.TABLE_NAME
                , values
                , DatabaseContract.FilledFormStatusTable.COLUMN_ID + " = ? "
                , new String[]{String.valueOf(id)});
    }

    public void saveQuestionAnswers(HashMap<String, String> answerTyped, int id, String womanId, int formId, String createdOn) {
        ContentValues values = new ContentValues();

        Iterator<Map.Entry<String, String>> itr2 = answerTyped.entrySet().iterator();
        while (itr2.hasNext()) {
            Map.Entry<String, String> entry = itr2.next();

            values.put(DatabaseContract.QuestionAnswerTable.COLUMN_REFERENCE_ID, id);
            values.put(DatabaseContract.QuestionAnswerTable.COLUMN_UNIQUE_ID, womanId);
            values.put(DatabaseContract.QuestionAnswerTable.COLUMN_FORM_ID, formId);
            values.put(DatabaseContract.QuestionAnswerTable.COLUMN_QUESTION_KEYWORD, entry.getKey());
            values.put(DatabaseContract.QuestionAnswerTable.COLUMN_ANSWER_KEYWORD, entry.getValue());
            values.put(DatabaseContract.QuestionAnswerTable.COLUMN_CREATED_ON, createdOn);

            int row = utility.getDatabase().update(DatabaseContract.QuestionAnswerTable.TABLE_NAME
                    , values
                    , DatabaseContract.QuestionAnswerTable.COLUMN_FORM_ID + " = ? "
                            + " AND "
                            + DatabaseContract.QuestionAnswerTable.COLUMN_UNIQUE_ID + " = ? "
                            + " AND "
                            + DatabaseContract.QuestionAnswerTable.COLUMN_QUESTION_KEYWORD + " = ? "
                    , new String[]{String.valueOf(formId), womanId, entry.getKey()});

            if (row == 0)
                utility.getDatabase().insert(DatabaseContract.QuestionAnswerTable.TABLE_NAME, null, values);


            values.clear();
        }
    }

    public List<Visit> getQuestionOptions(String questionId, String formId) {
        List<Visit> optionsList = new ArrayList<>();
        Visit defaultText = new Visit(mContext.getString(R.string.select));
        optionsList.add(defaultText);

        Cursor cursor = utility.getDatabase().rawQuery("SELECT DISTINCT * FROM "
                + DatabaseContract.QuestionOptionsTable.TABLE_NAME
                + " WHERE "
                + DatabaseContract.QuestionOptionsTable.COLUMN_FORM_ID
                + " = "
                + formId
                + " AND "
                + DatabaseContract.QuestionOptionsTable.COLUMN_QUESTION_ID
                + " = "
                + questionId, null);

        while (cursor.moveToNext()) {
            optionsList.add(new Visit("radio", ""
                    , cursor.getString(cursor.getColumnIndex(DatabaseContract.QuestionOptionsTable.COLUMN_KEYWORD))
                    , cursor.getString(cursor.getColumnIndex(DatabaseContract.QuestionOptionsTable.COLUMN_OPTION_LABEL))));
        }
        return optionsList;
    }

    public List<Visit> getDependantQuesList(String selectedOptionKeyword, String formId, LinearLayout ll_sub, String parentQstnKeyword, String pageScrollId) {

        List<Visit> dependentQuestionsList = new ArrayList<Visit>();

        Cursor cursor = utility.getDatabase().rawQuery("SELECT * FROM "
                        + DatabaseContract.DependentQuestionsTable.TABLE_NAME
                        + " WHERE "
                        + DatabaseContract.DependentQuestionsTable.COLUMN_FORM_ID
                        + " = "
                        + formId
                        + " AND "
                        + DatabaseContract.DependentQuestionsTable.COLUMN_MAIN_QUESTION_OPTION_KEYWORD
                        + " = ?"
                        + " GROUP BY "
                        + DatabaseContract.DependentQuestionsTable.COLUMN_KEYWORD
                        + " ORDER BY "
                        + DatabaseContract.DependentQuestionsTable.COLUMN_ID
                        + " DESC "
                , new String[]{selectedOptionKeyword});

        while (cursor.moveToNext()) {
            Visit visit = new Visit(ll_sub, parentQstnKeyword, pageScrollId
                    , cursor.getInt(cursor.getColumnIndex(DatabaseContract.DependentQuestionsTable.COLUMN_QUESTION_ID))
                    , cursor.getString(cursor.getColumnIndex(DatabaseContract.DependentQuestionsTable.COLUMN_FORM_ID))
                    , "0"
                    , cursor.getString(cursor.getColumnIndex(DatabaseContract.DependentQuestionsTable.COLUMN_KEYWORD))
                    , cursor.getString(cursor.getColumnIndex(DatabaseContract.DependentQuestionsTable.COLUMN_QUESTION_TYPE))
                    , cursor.getString(cursor.getColumnIndex(DatabaseContract.DependentQuestionsTable.COLUMN_QUESTION_LABEL))
                    , cursor.getString(cursor.getColumnIndex(DatabaseContract.DependentQuestionsTable.COLUMN_VALIDATIONS))
                    , cursor.getString(cursor.getColumnIndex(DatabaseContract.DependentQuestionsTable.COLUMN_MESSAGES))
                    , cursor.getInt(cursor.getColumnIndex(DatabaseContract.DependentQuestionsTable.COLUMN_ORIENTATION)));

            dependentQuestionsList.add(visit);
        }

        return dependentQuestionsList;
    }

    public List<Visit> removeDependantQuesList(String selectedOptionKeyword, String formId, LinearLayout ll_sub, String parentQstnKeyword, String pageScrollId) {

        List<Visit> removedependentQuestionsList = new ArrayList<Visit>();

        Cursor cursor = utility.getDatabase().rawQuery("SELECT * FROM "
                        + DatabaseContract.DependentQuestionsTable.TABLE_NAME
                        + " WHERE "
                        + DatabaseContract.DependentQuestionsTable.COLUMN_FORM_ID
                        + " = "
                        + formId
                        + " AND "
                        + DatabaseContract.DependentQuestionsTable.COLUMN_MAIN_QUESTION_OPTION_KEYWORD
                        + " = ?"
                        + " GROUP BY "
                        + DatabaseContract.DependentQuestionsTable.COLUMN_KEYWORD
                        + " ORDER BY "
                        + DatabaseContract.DependentQuestionsTable.COLUMN_ID
                        + " DESC "
                , new String[]{selectedOptionKeyword});

        while (cursor.moveToNext()) {
            Visit visit = new Visit(ll_sub, parentQstnKeyword, pageScrollId
                    , cursor.getInt(cursor.getColumnIndex(DatabaseContract.DependentQuestionsTable.COLUMN_QUESTION_ID))
                    , cursor.getString(cursor.getColumnIndex(DatabaseContract.DependentQuestionsTable.COLUMN_FORM_ID))
                    , "0"
                    , cursor.getString(cursor.getColumnIndex(DatabaseContract.DependentQuestionsTable.COLUMN_KEYWORD))
                    , cursor.getString(cursor.getColumnIndex(DatabaseContract.DependentQuestionsTable.COLUMN_QUESTION_TYPE))
                    , cursor.getString(cursor.getColumnIndex(DatabaseContract.DependentQuestionsTable.COLUMN_QUESTION_LABEL))
                    , cursor.getString(cursor.getColumnIndex(DatabaseContract.DependentQuestionsTable.COLUMN_VALIDATIONS))
                    , cursor.getString(cursor.getColumnIndex(DatabaseContract.DependentQuestionsTable.COLUMN_MESSAGES))
                    , cursor.getInt(cursor.getColumnIndex(DatabaseContract.DependentQuestionsTable.COLUMN_ORIENTATION)));

            removedependentQuestionsList.add(visit);
        }

        return removedependentQuestionsList;
    }

    public String getHighRiskCondition(String optionKeyword) {
        Cursor cursor = utility.getDatabase().rawQuery("SELECT "
                        + DatabaseContract.QuestionOptionsTable.COLUMN_MESSAGES
                        + " FROM "
                        + DatabaseContract.QuestionOptionsTable.TABLE_NAME
                        + " WHERE "
                        + DatabaseContract.QuestionOptionsTable.COLUMN_KEYWORD
                        + " = ? "
                , new String[]{optionKeyword});

        if (cursor.moveToFirst())
            return cursor.getString(cursor.getColumnIndex(DatabaseContract.QuestionOptionsTable.COLUMN_MESSAGES));
        else return null;
    }

    public String getDependentQuestionLabel(String optionKeyword) {
        Cursor cursor = utility.getDatabase().rawQuery("SELECT "
                        + DatabaseContract.QuestionOptionsTable.COLUMN_OPTION_LABEL
                        + " FROM "
                        + DatabaseContract.QuestionOptionsTable.TABLE_NAME
                        + " WHERE "
                        + DatabaseContract.QuestionOptionsTable.COLUMN_KEYWORD
                        + " = ? "
                , new String[]{optionKeyword});

        if (cursor.moveToFirst())
            return cursor.getString(cursor.getColumnIndex(DatabaseContract.QuestionOptionsTable.COLUMN_OPTION_LABEL));
        else return null;
    }

    public List<Visit> getMainQuestions(String formId) {
        List<Visit> questionList = new ArrayList<Visit>();

        Cursor cursor = utility.getDatabase().rawQuery("SELECT DISTINCT * "
                        + ", q."
                        + DatabaseContract.MainQuestionsTable.COLUMN_QUESTION_ID
                        + " AS qstn_id "
                        + " FROM "
                        + DatabaseContract.MainQuestionsTable.TABLE_NAME
                        + " q "
                        + " LEFT JOIN "
                        + DatabaseContract.ValidationsTable.TABLE_NAME
                        + " v "
                        + " ON "
                        + " q. "
                        + DatabaseContract.MainQuestionsTable.COLUMN_QUESTION_ID
                        + " = "
                        + " v. "
                        + DatabaseContract.ValidationsTable.COLUMN_QUESTION_ID
                        + " WHERE "
                        + " q. "
                        + DatabaseContract.MainQuestionsTable.COLUMN_FORM_ID
                        + " = ? "
                , new String[]{formId});

        while (cursor.moveToNext()) {

            Visit visit = new Visit(
                    cursor.getString(cursor.getColumnIndex(DatabaseContract.MainQuestionsTable.COLUMN_FORM_ID)),
                    cursor.getString(cursor.getColumnIndex("qstn_id")),
                    "0",
                    cursor.getString(cursor.getColumnIndex(DatabaseContract.MainQuestionsTable.COLUMN_KEYWORD)),
                    cursor.getString(cursor.getColumnIndex(DatabaseContract.MainQuestionsTable.COLUMN_QUESTION_TYPE)),
                    cursor.getString(cursor.getColumnIndex(DatabaseContract.MainQuestionsTable.COLUMN_QUESTION_LABEL)),
                    cursor.getString(cursor.getColumnIndex(DatabaseContract.ValidationsTable.COLUMN_COMPULSORY_QUESTIONS)),
                    cursor.getString(cursor.getColumnIndex(DatabaseContract.ValidationsTable.COLUMN_CUSTOM_VALIDATION_CON)),
                    cursor.getString(cursor.getColumnIndex(DatabaseContract.ValidationsTable.COLUMN_CUSTOM_VALIDATION_LANG)),
                    cursor.getString(cursor.getColumnIndex(DatabaseContract.ValidationsTable.COLUMN_MIN_LENGTH)),
                    cursor.getString(cursor.getColumnIndex(DatabaseContract.ValidationsTable.COLUMN_MAX_LENGTH)),
                    cursor.getString(cursor.getColumnIndex(DatabaseContract.ValidationsTable.COLUMN_LENGTH_ERROR_MESSAGE)),
                    cursor.getString(cursor.getColumnIndex(DatabaseContract.ValidationsTable.COLUMN_MIN_RANGE)),
                    cursor.getString(cursor.getColumnIndex(DatabaseContract.ValidationsTable.COLUMN_MAX_RANGE)),
                    cursor.getString(cursor.getColumnIndex(DatabaseContract.ValidationsTable.COLUMN_RANGE_ERROR_MESSAGE)),
                    cursor.getString(cursor.getColumnIndex(DatabaseContract.MainQuestionsTable.COLUMN_MESSAGES)),
                    cursor.getString(cursor.getColumnIndex(DatabaseContract.ValidationsTable.COLUMN_DISPLAY_CONDITION)),
                    cursor.getString(cursor.getColumnIndex(DatabaseContract.MainQuestionsTable.COLUMN_CALCULATION)),
                    cursor.getInt(cursor.getColumnIndex(DatabaseContract.MainQuestionsTable.COLUMN_ORIENTATION)),
                    cursor.getString(cursor.getColumnIndex(DatabaseContract.ValidationsTable.COLUMN_AVOID_REPETETIONS)));

            questionList.add(visit);
        }

        return questionList;
    }

    public HashMap<String, String> fetchUserDetails() {
        HashMap<String, String> hashMapUserDetails = new HashMap<>();
        Cursor cursor = utility.getDatabase().rawQuery("SELECT * FROM " + DatabaseContract.LoginTable.TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            hashMapUserDetails.put(AROGYASAKHI_NAME, cursor.getString(cursor.getColumnIndex(DatabaseContract.LoginTable.COLUMN_NAME)));
            hashMapUserDetails.put(AROGYASAKHI_MOB, cursor.getString(cursor.getColumnIndex(DatabaseContract.LoginTable.COLUMN_PHONE_NO)));
        }
        return hashMapUserDetails;
    }

    public void deleteAnswer(String uniqueId, String formId, String keyword) {

        utility.getDatabase().delete(DatabaseContract.QuestionAnswerTable.TABLE_NAME
                , DatabaseContract.QuestionAnswerTable.COLUMN_UNIQUE_ID + " = ? "
                        + " AND " + DatabaseContract.QuestionAnswerTable.COLUMN_FORM_ID + " = ? "
                        + " AND " + DatabaseContract.QuestionAnswerTable.COLUMN_QUESTION_KEYWORD + " = ? "
                , new String[]{uniqueId, formId, keyword});
    }

    public int getFilledFormReferenceId(String uniqueId, String formId) {
        Cursor cursor = utility.getDatabase().rawQuery("SELECT * FROM "
                + DatabaseContract.FilledFormStatusTable.TABLE_NAME
                + " WHERE "
                + DatabaseContract.FilledFormStatusTable.COLUMN_UNIQUE_ID + " = ? "
                + " AND "
                + DatabaseContract.FilledFormStatusTable.COLUMN_FORM_ID + " = ? ", new String[]{uniqueId, formId});

        if (cursor.moveToFirst())
            return cursor.getInt(cursor.getColumnIndex(DatabaseContract.FilledFormStatusTable.COLUMN_ID));
        else return -1;
    }

//    public CalculateVisit getNextVisitOf(String formId) {
//        switch (Integer.valueOf(formId)){
//            case ANC_SIX_VISIT_ID:
//            case DELIVERY_FORM_ID:
//            case WOMAN_CLOSURE_FORM_ID:
//            case CC_TWELTH_VISIT_ID:
//            case CHILD_CLOSURE_FORM_ID:
//                return null;
//            default:
//                Cursor nextVisitCursor = fetchNextVisitFormInfo(formId);
//                if (nextVisitCursor != null && nextVisitCursor.moveToFirst()){
//                    String visitName = nextVisitCursor.getString(
//                            nextVisitCursor.getColumnIndex(FormDetailsTable.COLUMN_VISIT_NAME));
//                    int fromWeek = nextVisitCursor.getInt(
//                            nextVisitCursor.getColumnIndex(FormDetailsTable.COLUMN_FROM_WEEKS));
//                    int toWeek = nextVisitCursor.getInt(
//                            nextVisitCursor.getColumnIndex(FormDetailsTable.COLUMN_TO_WEEKS));
//                    CalculateVisit calculateVisit = new CalculateVisit(fromWeek,toWeek, visitName);
//                    return calculateVisit;
//                }
//                else return null;
//
//        }
    //  }

//    public Cursor fetchNextVisitFormInfo(String  formId) {
//        return utility.getDatabase().rawQuery(" SELECT "
//                        + " * "
//                        + " FROM "
//                        + FormDetailsTable.TABLE_NAME
//                        + " WHERE "
//                        + " CAST ( " + FormDetailsTable.COLUMN_ORDER_ID + " AS INTEGER ) "
//                        + " > CAST ( ("
//                        + " SELECT " + FormDetailsTable.COLUMN_ORDER_ID
//                        + " FROM "
//                        + FormDetailsTable.TABLE_NAME
//                        + " WHERE " + FormDetailsTable.COLUMN_FORM_ID + " =? )"
//                        + " AS INTEGER )"
//                        + " LIMIT 1"
//                , new String[]{formId});
//    }

    public HashMap<String, String> getFormFilledData(String uniqueId, int formId) {
        HashMap<String, String> hashMap = new HashMap<>();
        Cursor cursor = utility.getDatabase().rawQuery("SELECT * FROM "
                + DatabaseContract.QuestionAnswerTable.TABLE_NAME
                + " WHERE "
                + DatabaseContract.QuestionAnswerTable.COLUMN_UNIQUE_ID + " = ? "
                + " AND "
                + DatabaseContract.QuestionAnswerTable.COLUMN_FORM_ID + " = ? ", new String[]{uniqueId, String.valueOf(formId)});

        while (cursor.moveToNext()) {
            hashMap.put(cursor.getString(cursor.getColumnIndex(DatabaseContract.QuestionAnswerTable.COLUMN_QUESTION_KEYWORD))
                    , cursor.getString(cursor.getColumnIndex(DatabaseContract.QuestionAnswerTable.COLUMN_ANSWER_KEYWORD)));
        }
        return hashMap;
    }

    public String getSelectedOptionText(String keyword) {
        Cursor cursor = utility.getDatabase().rawQuery("SELECT * FROM "
                + DatabaseContract.QuestionOptionsTable.TABLE_NAME
                + " WHERE "
                + DatabaseContract.QuestionOptionsTable.COLUMN_KEYWORD + " = ? ", new String[]{keyword});

        if (cursor.moveToFirst())
            return cursor.getString(cursor.getColumnIndex(DatabaseContract.QuestionOptionsTable.COLUMN_OPTION_LABEL));
        else return null;
    }

    public HashMap<String, String> getOptionsLabel(String formId) {
        HashMap<String, String> wordList = new HashMap<>();
        Cursor cursor = utility.getDatabase().rawQuery("SELECT DISTINCT * FROM "
                + DatabaseContract.QuestionOptionsTable.TABLE_NAME
                + " WHERE "
                + DatabaseContract.QuestionOptionsTable.COLUMN_FORM_ID + " = ? ", new String[]{formId});

        while (cursor.moveToNext()) {
            wordList.put(cursor.getString(cursor.getColumnIndex(DatabaseContract.QuestionOptionsTable.COLUMN_KEYWORD))
                    , cursor.getString(cursor.getColumnIndex(DatabaseContract.QuestionOptionsTable.COLUMN_OPTION_LABEL)));
        }

        return wordList;
    }

//    public void updateChildRegistrationDetails(String uniqueId, String firstName, String middleName, String lastName, String gender) {
//        ContentValues values = new ContentValues();
//        values.put(DatabaseContract.RegistrationTable.COLUMN_FIRST_NAME, firstName);
//        values.put(DatabaseContract.RegistrationTable.COLUMN_GENDER, gender);
//        values.put(DatabaseContract.RegistrationTable.COLUMN_REGISTRATION_STATUS, 1);
//
//        utility.getDatabase().update(DatabaseContract.RegistrationTable.TABLE_NAME
//                , values
//                , DatabaseContract.RegistrationTable.COLUMN_UNIQUE_ID + " = ? "
//                , new String[]{uniqueId});
//
//    }

    public void updateClosureDetails(String uniqueId, String closeDate, String closeReason, String deathDate, String deathReason) {
        ContentValues values = new ContentValues();
//        values.put(RegistrationTable.COLUMN_CLOSE_STATUS, 1);
//
//        utility.getDatabase().update(RegistrationTable.TABLE_NAME
//                , values
//                , RegistrationTable.COLUMN_UNIQUE_ID + " = ? "
//                , new String[]{uniqueId});

    }

    public String getDob(String uniqueId) {
        Cursor cursor = utility.getDatabase().rawQuery("SELECT * "
                + " FROM "
                + DatabaseContract.RegistrationTable.TABLE_NAME
                + " WHERE "
                + DatabaseContract.RegistrationTable.COLUMN_UNIQUE_ID
                + " = ? ", new String[]{uniqueId});

        return cursor.moveToFirst() ? cursor.getString(cursor.getColumnIndex(DatabaseContract.RegistrationTable.COLUMN_UNIQUE_ID)) : "";
    }

    public ArrayList<String> getChildrenUniqueID(String uniqueId) {
        ArrayList<String> uniqueIds = new ArrayList<>();
        Cursor cursor = utility.getDatabase().query(DatabaseContract.RegistrationTable.TABLE_NAME,
                new String[]{DatabaseContract.RegistrationTable.COLUMN_UNIQUE_ID},
                DatabaseContract.RegistrationTable.COLUMN_UNIQUE_ID + " = ? ",
                new String[]{uniqueId}, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                uniqueIds.add(cursor.getString(cursor.getColumnIndex(DatabaseContract.RegistrationTable.COLUMN_UNIQUE_ID)));
            } while (cursor.moveToNext());

            return uniqueIds;
        }

        return null;
    }

    public void updateChildRegistration(String childName, String uniqueId) {
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.RegistrationTable.COLUMN_FIRST_NAME, childName);
        values.put(DatabaseContract.RegistrationTable.COLUMN_REGISTRATION_STATUS, 1);

        utility.getDatabase().update(DatabaseContract.RegistrationTable.TABLE_NAME,
                values,
                DatabaseContract.RegistrationTable.COLUMN_UNIQUE_ID + " = ?",
                new String[]{uniqueId});

    }

    public String getFormNameFromId(int formId) {
        Cursor cursor = utility.getDatabase().rawQuery("SELECT visit_name FROM form_details WHERE form_id = '" + formId + "' group by form_id", null);

        if (cursor.moveToFirst())
            return cursor.getString(cursor.getColumnIndex(DatabaseContract.FormDetailsTable.COLUMN_VISIT_NAME));
        else return null;
    }

    public void deleteExisitingChild(String uniqueId) {
        Cursor cursor = utility.getDatabase().rawQuery("DELETE FROM registration WHERE mother_id = '" + uniqueId + "'", null);
        cursor.getCount();
    }

}
