package tech.foodies.inventory.app.Question_Answer;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import tech.foodies.inventory.app.R;
import tech.foodies.inventory.app.data.model.syncing.QuestionAnswer;
import tech.foodies.inventory.app.data.model.syncing.beneficiaries;
import tech.foodies.inventory.app.database.DatabaseContract;
import tech.foodies.inventory.app.mainMenu.MainActivity;
import tech.foodies.inventory.app.utility.utility;

public class place_order extends AppCompatActivity implements Iplace_order_view {

    LinearLayout constraintLayout;
    Spinner spinner;
    EditText editTextquantity;
    HashMap<String, String> hashMap;
    String key = "1";
    String check_counter = "0";
    place_order_presenter place_order_presenter;
    ArrayList<String> Client_list;
    ArrayList<QuestionAnswer> questionAnswer;
    ArrayList<QuestionAnswer> filledQuestionAnswer;
    ArrayList<beneficiaries> cust_details;
    String keywor, custID;
    private Spinner spinner1;
    private CardView place_order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);
        place_order_presenter = new place_order_presenter();
        hashMap = new HashMap<>();
        spinner1 = (Spinner) findViewById(R.id.spinner_cust_name);
        place_order_presenter = new place_order_presenter();
        place_order_presenter.attachView(this);
        cust_details = place_order_presenter.fetchUserDetails();
        questionAnswer = place_order_presenter.fetchQuestionDetails();
        Client_list = new ArrayList<>();
        Client_list.add("Select customer name");
        for (int i = 0; i < cust_details.size(); i++) {
            Client_list.add("Shop Name " + cust_details.get(i).getSname() + " Owner Name " + cust_details.get(i).getName() + " Id:" + cust_details.get(i).getUniqueId());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, Client_list);
        spinner1.setSelection(1);
        spinner1.setAdapter(adapter);

        place_order = (CardView) findViewById(R.id.cardview_add_product);
        constraintLayout = (LinearLayout) findViewById(R.id.constraintLayout_questio);

        place_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                create_layout();
            }
        });
        filledQuestionAnswer = new ArrayList<>();
    }

    public void create_layout() {
        spinner = new Spinner(getApplicationContext());
        Client_list = new ArrayList<>();
        Client_list.add("Select Product name");
        for (int i = 0; i < questionAnswer.size(); i++) {
            Client_list.add("Product Name" + questionAnswer.get(i).getAnswer() + " keyword:" + questionAnswer.get(i).getKeyword());
        }

        TextView textView = new TextView(getApplicationContext());
        textView.setText("Select product name");
        textView.setPadding(10, 10, 10, 4);

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, Client_list);
        spinner.setAdapter(spinnerArrayAdapter);
        spinner.setPadding(7, 4, 10, 4);
        constraintLayout.addView(textView);
        constraintLayout.addView(spinner);

        TextView textView1 = new TextView(getApplicationContext());
        textView1.setText("Enter the quantity");
        textView1.setPadding(10, 4, 10, 4);

        editTextquantity = new EditText(getApplicationContext());
        editTextquantity.setInputType(InputType.TYPE_CLASS_NUMBER);
        editTextquantity.setPadding(10, 4, 10, 10);

        constraintLayout.addView(textView1);
        constraintLayout.addView(editTextquantity);

        spinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

                        Object item = parent.getItemAtPosition(pos);
                        System.out.println(item.toString());     //prints the text in spinner item.
                        if ((item.toString()).equalsIgnoreCase("Select Product name")) {
                            hashMap.put(key, item.toString());
                            hashMap.put("quantity" + key, editTextquantity.getText().toString());
                            check_counter = "0";
                        } else {
                            String selectedItem = spinner1.getSelectedItem().toString();
                            hashMap.put("Customer_name", selectedItem);
                            hashMap.put("product" + key, item.toString());
                            key = String.valueOf(Integer.valueOf(key) + 1);
                            check_counter = "1";
                        }
                    }

                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });

        editTextquantity.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {

                if (check_counter.equalsIgnoreCase("1")) {
                    int storeIndex = Integer.valueOf(key);
                    storeIndex = storeIndex - 1;
                    hashMap.put("quantity" + storeIndex, editTextquantity.getText().toString());
                } else {
                    hashMap.put("quantity" + key, editTextquantity.getText().toString());
                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
            }
        });

    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        place_order_presenter.detch();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(place_order.this, MainActivity.class);
        startActivity(intent);
    }


    public void submit(View view) {
        int hashsize = hashMap.size();
        int size = (hashsize - 1) / 2;
        for (int i = 1; i < size; i++) {
            String Quantity = hashMap.get("quantity" + i);
            String product_keyword = hashMap.get("product" + i);
            String custName = hashMap.get("Customer_name");

            if (product_keyword != null && custName != null) {
                String cust[] = custName.split(":");
                String parts[] = product_keyword.split(":");

                // Display result parts.
                for (String part : parts) {
                    System.out.println(part);
                    keywor = parts[1];
                }

                // Display result parts.
                for (String part : cust) {
                    custID = cust[1];
                }
            }
            QuestionAnswer answer = new QuestionAnswer();
            answer.setAnswer(Quantity);
            answer.setKeyword(keywor);
            answer.setCreatedOn(custID);
            filledQuestionAnswer.add(answer);
        }

        ContentValues values = new ContentValues();
        values.put(DatabaseContract.FilledFormStatusTable.COLUMN_UNIQUE_ID, custID);
        values.put(DatabaseContract.FilledFormStatusTable.COLUMN_FORM_ID, 2);
        values.put(DatabaseContract.FilledFormStatusTable.COLUMN_FORM_COMPLETION_STATUS, 1);
        values.put(DatabaseContract.FilledFormStatusTable.COLUMN_FORM_SYNC_STATUS, 0);
        values.put(DatabaseContract.FilledFormStatusTable.COLUMN_CREATED_ON, utility.getCurrentDateTime());

        int referenceId = (int) utility.getDatabase().insert(DatabaseContract.FilledFormStatusTable.TABLE_NAME, null, values);
        saveQuestionAnswers(filledQuestionAnswer, referenceId, custID, 2, utility.getCurrentDateTime());

        Intent intent = new Intent(place_order.this, MainActivity.class);
        startActivity(intent);
    }


    public void saveQuestionAnswers(ArrayList<QuestionAnswer> filled, int id, String womanId, int formId, String createdOn) {
        ContentValues values = new ContentValues();

        for (int i = 0; i < filled.size(); i++) {

            values.put(DatabaseContract.QuestionAnswerTable.COLUMN_REFERENCE_ID, id);
            values.put(DatabaseContract.QuestionAnswerTable.COLUMN_UNIQUE_ID, womanId);
            values.put(DatabaseContract.QuestionAnswerTable.COLUMN_FORM_ID, formId);
            values.put(DatabaseContract.QuestionAnswerTable.COLUMN_QUESTION_KEYWORD, filled.get(i).getKeyword());
            values.put(DatabaseContract.QuestionAnswerTable.COLUMN_ANSWER_KEYWORD, filled.get(i).getAnswer());
            values.put(DatabaseContract.QuestionAnswerTable.COLUMN_CREATED_ON, createdOn);
//
//            int row = utility.getDatabase().update(DatabaseContract.QuestionAnswerTable.TABLE_NAME
//                    , values
//                    , DatabaseContract.QuestionAnswerTable.COLUMN_FORM_ID + " = ? "
//                            + " AND "
//                            + DatabaseContract.QuestionAnswerTable.COLUMN_UNIQUE_ID + " = ? "
//                            + " AND "
//                            + DatabaseContract.QuestionAnswerTable.COLUMN_QUESTION_KEYWORD + " = ? "
//                    , new String[]{String.valueOf(formId), womanId, filled.get(i).getKeyword()});
//
//            if (row == 0)
            utility.getDatabase().insert(DatabaseContract.QuestionAnswerTable.TABLE_NAME, null, values);


            values.clear();
        }
    }
}
