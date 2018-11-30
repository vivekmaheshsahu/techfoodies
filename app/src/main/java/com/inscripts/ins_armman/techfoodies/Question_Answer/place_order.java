package com.inscripts.ins_armman.techfoodies.Question_Answer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;

import com.inscripts.ins_armman.techfoodies.R;
import android.widget.Spinner;
import android.widget.*;
import java.util.ArrayList;
import java.util.HashMap;

public class place_order extends AppCompatActivity implements Iplace_order_view {

    private Spinner spinner1;
    private CardView place_order;
    LinearLayout constraintLayout;
    ArrayList<String> spinnerArray;
    Spinner spinner;
    EditText editTextquantity;
    int counter = 1;
    HashMap<String,String> hashMap;
    String[] items1;
    String key ="1";
    String check_counter = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);
        hashMap = new HashMap<>();
        spinner1 = (Spinner) findViewById(R.id.spinner_cust_name);

        String[] items = new String[]{"Select customer name","Sunny", "Hemant", "Sarvesh"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        spinner1.setSelection(1);
        spinner1.setAdapter(adapter);
        place_order = (CardView) findViewById(R.id.cardview_add_product);
        items1 = new String[]{"Select Product name","product1", "product2", "product3"};
        constraintLayout = (LinearLayout) findViewById(R.id.constraintLayout_questio);

        place_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                create_layout();
            }
        });


    }
    public void create_layout() {
        spinner = new Spinner(getApplicationContext());
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, items1);
        spinner.setAdapter(spinnerArrayAdapter);
        constraintLayout.addView(spinner);

        TextView textView = new TextView(getApplicationContext());
        textView.setText("Select product name");

        TextView textView1 = new TextView(getApplicationContext());
        textView1.setText("Enter the quantity");

        editTextquantity = new EditText(getApplicationContext());
        constraintLayout.addView(textView);
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
                            hashMap.put(key, item.toString());
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

}
