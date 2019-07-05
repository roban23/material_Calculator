package com.example.mat_calculator;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tView, tv1;
    EditText edit1;
    Button btnSummit;
    ImageView imgPrice;

    long total = 0;
    boolean changing = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initInstance();
    }

    private void initInstance() {

        tView = (TextView) findViewById(R.id.tv);
        tv1 = (TextView) findViewById(R.id.tv11);

        edit1 = (EditText) findViewById(R.id.edt1);
        imgPrice = (ImageView) findViewById(R.id.img);
        btnSummit = (Button) findViewById(R.id.btn);
        btnSummit.setOnClickListener(this);
//        edit1.setText(String.valueOf(total));

        edit1.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
                if (!changing && edit1.getText().toString().startsWith("0")){
                    changing = true;
                    edit1.setText(edit1.getText().toString().replace("0", ""));
                }
                changing = false;
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

        });
    }

    private long calculate(long weight) {
        long b = 0;
        if ((0 <= weight) && (weight <= 100)) {
            total = total + weight * 2000;
        } else if ((101 <= weight) && (weight <= 200)) {
            b = weight % 100;
            if (b == 0) {
                b = 100;
            }
            weight = weight - b;
            total = total + (b * 2100);
            calculate(weight);
        } else if ((201 <= weight) && (weight <= 300)) {
            b = weight % 100;
            if (b == 0) {
                b = 100;
            }
            weight = weight - b;
            total = total + (b * 2250);
            calculate(weight);
        } else if ((301 <= weight) && (weight <= 400)) {
            b = weight % 100;
            if (b == 0) {
                b = 100;
            }
            weight = weight - b;
            total = total + (b * 2450);
            calculate(weight);
        } else if ((weight >= 401)) {
            weight = weight - 400;
            calculate(400);
            total = total + (weight * 2700);
        }
        return total;
    }

    @Override
    public void onClick(View view) {
        edit1.onEditorAction(EditorInfo.IME_ACTION_DONE);
        if (view == btnSummit) {
            long ton;
            String str = "";
            try {
                ton = Integer.parseInt(str);

            } catch (NumberFormatException e) {

            }
            ton = 0;
            try {
                ton = Integer.parseInt(edit1.getText().toString());

            } catch (NumberFormatException e) {

            }

            tView.setText(String.valueOf(calculate(ton)));
            total = 0;
            Log.d("test1", "val = " + ton);
        }
    }

}
