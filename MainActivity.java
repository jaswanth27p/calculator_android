package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Stack;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void buttonPress(View v) {
        String x,temp;
        char y;
        TextView a = findViewById(R.id.atv);
        TextView t = findViewById(R.id.itv);
        switch (v.getId()) {
            case R.id.n1:
                t.append("1");
                break;
            case R.id.n2:
                t.append("2");
                break;
            case R.id.n3:
                t.append("3");
                break;
            case R.id.plus:
                x = t.getText().toString();
                if(x.equals("")){
                    break;
                }else{
                    y=x.charAt(x.length()-1);
                    temp = String.valueOf(y);
                    if (temp.equals("*") || temp.equals("/") || temp.equals("+") || temp.equals("-")){
                        t.setText(x.substring(0,x.length()-1)+"+");
                    }else{
                        t.append("+");
                    }
                }
                break;


            case R.id.n4:
                t.append("4");
                break;
            case R.id.n5:
                t.append("5");
                break;
            case R.id.n6:
                t.append("6");
                break;
            case R.id.minus:
                x = t.getText().toString();
                if(x.equals("")){
                    break;
                }else{
                    y=x.charAt(x.length()-1);
                    temp = String.valueOf(y);
                    if (temp.equals("*") || temp.equals("/") || temp.equals("+") || temp.equals("-")){
                        t.setText(x.substring(0,x.length()-1)+"-");
                    }else{
                        t.append("-");
                    }
                }
                break;
            case R.id.n7:
                t.append("7");
                break;
            case R.id.n8:
                t.append("8");
                break;
            case R.id.n9:
                t.append("9");
                break;
            case R.id.mul:
                x = t.getText().toString();
                if(x.equals("")){
                    break;
                }else{
                    y=x.charAt(x.length()-1);
                    temp = String.valueOf(y);
                    if (temp.equals("*") || temp.equals("/") || temp.equals("+") || temp.equals("-")){
                        t.setText(x.substring(0,x.length()-1)+"*");
                    }else{
                        t.append("*");
                    }
                }
                break;
            case R.id.clear:
                x = t.getText().toString();
                if(x.equals("")){
                    t.setText("");
                }else{
                    t.setText(x.substring(0,x.length()-1));
                }
                break;
            case R.id.n0:
                t.append("0");
                break;
            case R.id.eq:
                x = t.getText().toString();
                boolean nozerodiv=true;

                for (int i=0;i<x.length()-1;i++){
                    if (x.charAt(i)=='/' && x.charAt(i+1)=='0'){
                        System.out.println("if");
                        a.setText("Can't divide by zero");
                        a.setTextSize(20);
                        nozerodiv=false;
                    }
                }
                if (nozerodiv){
                    try{
                        x = t.getText().toString();
                        if(x.equals("")){
                            t.setText("");
                        }else{
                            float ans= string_to_ans(t.getText().toString());
                            a.setText("ans : "+ ans);
                        }
                        //Try to do something on here
                    }catch(Exception e) {
                        x = t.getText().toString();
                        temp=x.substring(0,x.length()-1);
                        if(x.equals("")){
                            t.setText("");
                        }else{
                            float ans= string_to_ans(temp);
                            a.setText("ans : "+ ans);
                        }
                    }
                }
                break;
            case R.id.div:
                x = t.getText().toString();
                if(x.equals("")){
                    break;
                }else{
                    y=x.charAt(x.length()-1);
                    temp = String.valueOf(y);
                    if (temp.equals("*") || temp.equals("/") || temp.equals("+") || temp.equals("-")){
                        t.setText(x.substring(0,x.length()-1)+"/");
                    }else{
                        t.append("/");
                    }
                }
                break;
        }
    }



    public static float string_to_ans(String expression)
    {
        char[] tokens = expression.toCharArray();

        Stack<Float> values = new
                Stack<Float>();

        Stack<Character> ops = new
                Stack<Character>();

        for (int i = 0; i < tokens.length; i++)
        {
            if (tokens[i] == ' ')
                continue;
            if (tokens[i] >= '0' &&
                    tokens[i] <= '9')
            {
                StringBuffer sbuf = new
                        StringBuffer();
                while (i < tokens.length &&
                        tokens[i] >= '0' &&
                        tokens[i] <= '9')
                    sbuf.append(tokens[i++]);
                values.push(Float.parseFloat(sbuf.
                        toString()));

                i--;
            }
            else if (tokens[i] == '(')
                ops.push(tokens[i]);
            else if (tokens[i] == ')')
            {
                while (ops.peek() != '(')
                    values.push(applyOp(ops.pop(),
                            values.pop(),
                            values.pop()));
                ops.pop();
            }
            else if (tokens[i] == '+' ||
                    tokens[i] == '-' ||
                    tokens[i] == '*' ||
                    tokens[i] == '/')
            {
                while (!ops.empty() &&
                        hasPrecedence(tokens[i],
                                ops.peek()))
                    values.push(applyOp(ops.pop(),
                            values.pop(),
                            values.pop()));
                ops.push(tokens[i]);
            }
        }
        while (!ops.empty())
            values.push(applyOp(ops.pop(),
                    values.pop(),
                    values.pop()));
        return values.pop();
    }
    public static boolean hasPrecedence(
            char op1, char op2)
    {
        if (op2 == '(' || op2 == ')')
            return false;
        if ((op1 == '*' || op1 == '/') &&
                (op2 == '+' || op2 == '-'))
            return false;
        else
            return true;
    }
    public static float applyOp(char op,
                                float b, float a) {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0){
                    return 0;
                }
                else {
                    return (float)a/b;
                }



        }
        return 0;
    }
}
