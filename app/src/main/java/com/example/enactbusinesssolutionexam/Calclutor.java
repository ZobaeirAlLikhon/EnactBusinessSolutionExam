package com.example.enactbusinesssolutionexam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Calclutor extends AppCompatActivity {
    EditText editText,editText1;
    boolean operator=true;
    String op="";
    String oldNum="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Calclutor");
        setContentView(R.layout.activity_calclutor);
        editText=findViewById(R.id.editText);
        editText1=findViewById(R.id.editText1);
    }

    public void buttonClickEvent(View view) {
        if(operator)
            editText.setText("");
        operator=false;
        String number=editText.getText().toString();
        switch (view.getId())
        {
            case R.id.zero:
                number+="0";
                break;
             case R.id.one:
                number+="1";
                break;
             case R.id.two:
                number+="2";
                break;
             case R.id.three:
                number+="3";
                break;
             case R.id.four:
                number+="4";
                break;
             case R.id.five:
                number+="5";
                break;
             case R.id.six:
                number+="6";
                break;
             case R.id.buttonSeven:
                number+="7";
                break;
             case R.id.buttonEight:
                number+="8";
                break;
             case R.id.buttonNine:
                number+="9";
                break;
             case R.id.flot:
                number+=".";
                break;
             case R.id.clear:
                editText.clearComposingText();
                break;
        }
        editText.setText(number);
    }

    public void opClickEvent(View view) {
        operator=true;
        oldNum=editText.getText().toString();
        switch (view.getId())
        {
            case R.id.plus:
                op="+";
                editText1.setText(op);
                break;
            case R.id.minus:
                op="-";
                editText1.setText(op);
                break;
            case R.id.devide:
                op="/";
                editText1.setText(op);
                break;
            case R.id.multiple:
                op="*";
                editText1.setText(op);
                break;
        }
    }

    public void equalEvent(View view) {
        String newNum=editText.getText().toString();
        double result=0.0;
        switch (op)
        {
            case "+":
                result=Double.parseDouble(oldNum)+Double.parseDouble(newNum);
                break;
            case "-":
                result=Double.parseDouble(oldNum)-Double.parseDouble(newNum);
                break;
            case "*":
                result=Double.parseDouble(oldNum)*Double.parseDouble(newNum);
                break;
            case "/":
                result=Double.parseDouble(oldNum)/Double.parseDouble(newNum);
                break;
        }
        editText.setText(result+"");
//        editText1.setText(oldNum+"op"+newNum+"="+result);
    }

    public void clearEvent(View view) {
        editText.setText("");
        editText1.setText("");
        operator=true;
    }
}