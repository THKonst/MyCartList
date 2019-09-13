package com.thvkonst.mycartlist;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Add_ItemActivity extends AppCompatActivity {

    private static final String TAG = "Add_ItemActivity";
    private EditText name;
    private EditText price;
    private TextView smbRbl;
    private Button addBtn;
    private String msg_name="", msg_price="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__item);

        setTitle(R.string.add_item_title);

        name = findViewById(R.id.name);
        price = findViewById(R.id.price);
        addBtn = findViewById(R.id.add_btn);
        smbRbl = findViewById(R.id.smb_rbl);

        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence c, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence c, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable c) {
                Log.i(TAG, "afterNameChanged: " + c);
                addBtn.setEnabled(!TextUtils.isEmpty(c));
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String itemName = name.getText().toString();
                String itemPrice = price.getText().toString();

            }
        });


    }
}
