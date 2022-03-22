package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.calculator.R;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView txt_calculate, txt_log, txt_history;
    Button btn_0, btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9;
    Button btn_plus, btn_minus, btn_multi, btn_divide, btn_rest, btn_equal;
    String strA, strB, strResult;
    String strTxtCal, strOp, strTxtLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_calculate = findViewById(R.id.txt_calculate);
        txt_log = findViewById(R.id.txt_log);
        txt_history = findViewById(R.id.txt_history);
        btn_0 = findViewById(R.id.btn_0);
        btn_1 = findViewById(R.id.btn_1);
        btn_2 = findViewById(R.id.btn_2);
        btn_3 = findViewById(R.id.btn_3);
        btn_4 = findViewById(R.id.btn_4);
        btn_5 = findViewById(R.id.btn_5);
        btn_6 = findViewById(R.id.btn_6);
        btn_7 = findViewById(R.id.btn_7);
        btn_8 = findViewById(R.id.btn_8);
        btn_9 = findViewById(R.id.btn_9);
        btn_plus = findViewById(R.id.btn_plus);
        btn_minus = findViewById(R.id.btn_minus);
        btn_multi = findViewById(R.id.btn_multi);
        btn_divide = findViewById(R.id.btn_divide);
        btn_rest = findViewById(R.id.btn_rest);
        btn_equal = findViewById(R.id.btn_equal);

        btn_0.setOnClickListener(this);
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);
        btn_9.setOnClickListener(this);
        btn_plus.setOnClickListener(this);
        btn_minus.setOnClickListener(this);
        btn_multi.setOnClickListener(this);
        btn_divide.setOnClickListener(this);
        btn_rest.setOnClickListener(this);
        btn_equal.setOnClickListener(this);

        initValue();
    }

    private void initValue() {
        strA = "";
        strB = "";
        strOp = "";
        strResult = "";
        strTxtCal = "";
        strTxtLog = "";
    }

    @Override
    public void onClick(View v) {
        ArrayList<String> arrNumber = new ArrayList<>(
                Arrays.asList(
                        "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"
                )
        );
        ArrayList<String> arrOperator = new ArrayList<>(
                Arrays.asList(
                        "+", "-", "x", "/", "%"
                )
        );
        Button btn = (Button) v;
        String btnTxt = btn.getText().toString();
        if (btnTxt.equals("0") && txt_calculate.getText().toString().equals("")) return;
        if (arrNumber.contains(btnTxt)) {
            if (true) {
                strTxtCal += btnTxt;
                update();
            }
        } else if (arrOperator.contains(btnTxt)) {
            if (strA.equals("") && strB.equals("") && strOp.equals("")) {
                if (!strResult.equals("")) {
                    strOp = btnTxt;
                    strA = strResult;
                    strResult = "";
                    strTxtCal = "";
                    update();
                } else if (txt_calculate.getText().toString().equals("")) {
                    return;
                } else {
                    strOp = btnTxt;
                    strA = strTxtCal;
                    strTxtCal = "";
                    update();
                }
            }
        } else if (btnTxt.equals("=")) {
            if (txt_calculate.getText().toString().equals("")) return;
            if (!strA.equals("") && strB.equals("") && !strOp.equals("")) {
                strB = strTxtCal;
                strResult = "";
                strTxtCal = "";
                update();
            }
        }
    }

    public void update() {
        txt_calculate.setText(strTxtCal);
        if (!strA.equals("") && strB.equals("") && strOp.equals("")) {
            strTxtLog = strA;
            txt_log.setText(strTxtLog);
        } else if (!strA.equals("") && strB.equals("") && !strOp.equals("")) {
            strTxtLog = strA + " " + strOp;
            txt_log.setText(strTxtLog);
        } else if (!strA.equals("") && !strB.equals("") && !strOp.equals("")) {
            strResult = getCalculate(strA, strB, strOp);
            strTxtLog = strA + " " + strOp + " " + strB + " = " + strResult;
            String strHistory = txt_history.getText().toString();
            strHistory = strTxtLog + "\n" + strHistory;
            strA = "";
            strB = "";
            strOp = "";

            txt_log.setText(strTxtLog);
            txt_history.setText(strHistory);
        }
    }

    public String getCalculate(String a, String b, String o) {
        String result = "";
        double dA = (double) Double.parseDouble(a);
        double dB = (double) Double.parseDouble(b);
        double dR = 0.0;
        switch (o) {
            case "+":
                dR = dA + dB;
                break;
            case "-":
                dR = dA - dB;
                break;
            case "x":
                dR = dA * dB;
                break;
            case "/":
                dR = dA / dB;
                break;
            case "%":
                dR = dA % dB;
                break;
        }
        result = Double.toString(dR);
        return result;
    }
}