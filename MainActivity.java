package com.example.democalculator;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class MainActivity extends AppCompatActivity {

    StringBuilder expression = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView screen = findViewById(R.id.screen);

        Button num0 = findViewById(R.id.num0);
        Button num00 = findViewById(R.id.num00);
        Button num1 = findViewById(R.id.num1);
        Button num2 = findViewById(R.id.num2);
        Button num3 = findViewById(R.id.num3);
        Button num4 = findViewById(R.id.num4);
        Button num5 = findViewById(R.id.num5);
        Button num6 = findViewById(R.id.num6);
        Button num7 = findViewById(R.id.num7);
        Button num8 = findViewById(R.id.num8);
        Button num9 = findViewById(R.id.num9);

        Button ac = findViewById(R.id.all_clear);
        Button del = findViewById(R.id.del);
        Button add = findViewById(R.id.add);
        Button subtract = findViewById(R.id.subtract);
        Button multiply = findViewById(R.id.multiply);
        Button divide = findViewById(R.id.divide);
        Button percent = findViewById(R.id.percent);
        Button equal = findViewById(R.id.equal);
        Button dot = findViewById(R.id.decimal);
        Button openParen = findViewById(R.id.open_paren);
        Button closeParen = findViewById(R.id.close_paren);
        Button plusMinus = findViewById(R.id.plus_minus);

        ac.setOnClickListener(view -> {
            expression.setLength(0);
            screen.setText("0");
        });



        ArrayList<Button> buttons = new ArrayList<>();
        buttons.add(num0);
        buttons.add(num00);
        buttons.add(num1);
        buttons.add(num2);
        buttons.add(num3);
        buttons.add(num4);
        buttons.add(num5);
        buttons.add(num6);
        buttons.add(num7);
        buttons.add(num8);
        buttons.add(num9);

        for (Button b : buttons) {
            b.setOnClickListener(view -> {
                if (!screen.getText().toString().equals("0")) {
                    expression.append(b.getText().toString());
                    screen.setText(expression.toString());
                } else {
                    expression.append(b.getText().toString());
                    screen.setText(expression.toString());
                }
            });
        }

        ArrayList<Button> operates = new ArrayList<>();
        operates.add(add);
        operates.add(subtract);
        operates.add(multiply);
        operates.add(divide);
        for (Button b : operates) {
            b.setOnClickListener(view -> {
                expression.append(b.getText().toString());
                screen.setText(expression.toString());
            });
        }

        del.setOnClickListener(view -> {
            if (expression.length() > 0) {
                expression.deleteCharAt(expression.length() - 1);
                if (expression.length() == 0) {
                    screen.setText("0");
                } else {
                    screen.setText(expression.toString());
                }
            }
        });

        dot.setOnClickListener(view -> {
            if (!expression.toString().contains(".")) {
                expression.append(".");
                screen.setText(expression.toString());
            }
        });

        percent.setOnClickListener(view -> {
            expression.append("%");
            screen.setText(expression.toString());
        });


        plusMinus.setOnClickListener(view -> {
            if (expression.length() > 0) {
                char lastChar = expression.charAt(expression.length() - 1);
                if (lastChar == '-') {
                    expression.deleteCharAt(expression.length() - 1);
                } else {
                    expression.insert(0, "-");
                }
                screen.setText(expression.toString());
            }
        });

        openParen.setOnClickListener(view -> {
            expression.append("(");
            screen.setText(expression.toString());
        });


        closeParen.setOnClickListener(view -> {
            expression.append(")");
            screen.setText(expression.toString());
        });


        equal.setOnClickListener(view -> {
            try {
                double result = evaluateExpression(expression.toString());
                screen.setText(String.valueOf(result));
                expression.setLength(0);
                expression.append(result);
            } catch (Exception e) {
                screen.setText("Error");
                expression.setLength(0);
            }
        });
    }

    private double evaluateExpression(String expr) {
        Expression expression = new ExpressionBuilder(expr).build();
        return expression.evaluate();
    }
}
