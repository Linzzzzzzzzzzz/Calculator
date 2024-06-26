package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class CalculatorActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_result;
    private String firstNum = "";
    private String operator = "";
    private String secondNum = "";
    private String result = ""; // 计算结果
    private String showText = ""; // 显示的文本内容

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        // resulting text
        tv_result = findViewById(R.id.tv_result);
        // buttons
        findViewById(R.id.btn_cancel).setOnClickListener(this);
        findViewById(R.id.btn_clear).setOnClickListener(this);
        findViewById(R.id.btn_equal).setOnClickListener(this);
        findViewById(R.id.btn_plus).setOnClickListener(this);
        findViewById(R.id.btn_minus).setOnClickListener(this);
        findViewById(R.id.btn_multiply).setOnClickListener(this);
        findViewById(R.id.btn_divide).setOnClickListener(this);
        findViewById(R.id.btn_dot).setOnClickListener(this);
        findViewById(R.id.btn_reciprocal).setOnClickListener(this);
        findViewById(R.id.ib_sqrt).setOnClickListener(this);

        findViewById(R.id.btn_zero).setOnClickListener(this);
        findViewById(R.id.btn_one).setOnClickListener(this);
        findViewById(R.id.btn_two).setOnClickListener(this);
        findViewById(R.id.btn_three).setOnClickListener(this);
        findViewById(R.id.btn_four).setOnClickListener(this);
        findViewById(R.id.btn_five).setOnClickListener(this);
        findViewById(R.id.btn_six).setOnClickListener(this);
        findViewById(R.id.btn_seven).setOnClickListener(this);
        findViewById(R.id.btn_eight).setOnClickListener(this);
        findViewById(R.id.btn_nine).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String inputText;
        // if square root
        if(v.getId() == R.id.ib_sqrt) {
            inputText = "√";
        }
        else {
            // all buttons except square root
            inputText = ((TextView)v).getText().toString();
        }
        if (v.getId() == R.id.btn_clear) {// clear
            clear();
            return;
        }
        if (v.getId() == R.id.btn_cancel) {// cancel
            refreshText(showText.substring(0, showText.length() - 1));
            return;
        }
        if (v.getId() == R.id.btn_plus || v.getId() == R.id.btn_minus||
                v.getId() == R.id.btn_multiply||v.getId() ==R.id.btn_divide) {// operators
            operator = inputText;
            refreshText(showText + operator);
            return;
        }
        if (v.getId() ==R.id.btn_equal) {// equal

            double calculate_result = calculatorFour();
            refreshOperator(String.valueOf(calculate_result));
            refreshText(showText + "=" + result);
            return;
        }
        if (v.getId() ==R.id.ib_sqrt) {// square root

            double sqrt_result = Math.sqrt(Double.parseDouble(firstNum));
            refreshOperator(String.valueOf(sqrt_result));
            refreshText(showText + "√=" + result);
            return;
        }
        if (v.getId() ==R.id.btn_reciprocal) {// reciprocal
            double reciprocal_result = 1.0 / Double.parseDouble(firstNum);
            refreshOperator(String.valueOf(reciprocal_result));
            refreshText(showText + "/=" + result);
            return;
        }
        // numbers and dot
        // no operator, start new
        if(result.length() > 0 && operator.equals("")) {
            clear();
        }
        if(operator.equals("")) {
            firstNum = firstNum + inputText;
        }
        else {
            secondNum = secondNum + inputText;
        }
        if(showText.equals("0") && !inputText.equals(".")) {
            refreshText(inputText);
        }
        else {
            refreshText(showText + inputText);
        }
    }

    private double calculatorFour() {
        switch(operator) {
            case "+":
                return Double.parseDouble(firstNum) + Double.parseDouble(secondNum);
            case "-":
                return Double.parseDouble(firstNum) - Double.parseDouble(secondNum);
            case "×":
                return Double.parseDouble(firstNum) * Double.parseDouble(secondNum);
            default:
                return Double.parseDouble(firstNum) / Double.parseDouble(secondNum);
        }
    }

    private void clear() {
        refreshOperator("");
        refreshText("");
    }

    // refresh result
    private void refreshOperator(String new_result) {
        result = new_result;
        firstNum = result;
        secondNum = "";
        operator = "";
    }

    // refresh text
    private void refreshText(String text) {
        showText = text;
        tv_result.setText(showText);
    }
}