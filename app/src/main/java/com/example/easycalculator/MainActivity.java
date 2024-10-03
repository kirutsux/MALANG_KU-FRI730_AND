package com.example.easycalculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.google.android.material.button.MaterialButton;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private static final char ADDITION = '+';
    private static final char SUBTRACTION = '-';
    private static final char MULTIPLICATION = '*';
    private static final char DIVISION = '/';
    private static final char PERCENT = '%';
    private char currentSymbol;
    private double firstValue = Double.NaN;
    private double secondValue;
    private TextView inputDisplay, outputDisplay;
    private DecimalFormat decimalFormat;
    private MaterialButton button0, button1, button2, button3, button4, button5, button6, button7, button8, button9,
            buttonDot, buttonAdd, buttonSub, buttonMultiply, buttonDivide, buttonPercent, buttonClear, buttonOFF, buttonEqual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        decimalFormat = new DecimalFormat("#.00");
        inputDisplay = findViewById(R.id.input);
        outputDisplay = findViewById(R.id.output);
        button0 = findViewById(R.id.btn0);
        button1 = findViewById(R.id.btn1);
        button2 = findViewById(R.id.btn2);
        button3 = findViewById(R.id.btn3);
        button4 = findViewById(R.id.btn4);
        button5 = findViewById(R.id.btn5);
        button6 = findViewById(R.id.btn6);
        button7 = findViewById(R.id.btn7);
        button8 = findViewById(R.id.btn8);
        button9 = findViewById(R.id.btn9);
        buttonAdd = findViewById(R.id.add);
        buttonSub = findViewById(R.id.subtract);
        buttonDivide = findViewById(R.id.division);
        buttonDot = findViewById(R.id.btnPoint);
        buttonMultiply = findViewById(R.id.multiply);
        buttonClear = findViewById(R.id.clear);
        buttonOFF = findViewById(R.id.off);
        buttonEqual = findViewById(R.id.equal);
        buttonPercent = findViewById(R.id.percent);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MaterialButton button = (MaterialButton) view;
                inputDisplay.setText(inputDisplay.getText() + button.getText().toString());
            }
        };

        button0.setOnClickListener(listener);
        button1.setOnClickListener(listener);
        button2.setOnClickListener(listener);
        button3.setOnClickListener(listener);
        button4.setOnClickListener(listener);
        button5.setOnClickListener(listener);
        button6.setOnClickListener(listener);
        button7.setOnClickListener(listener);
        button8.setOnClickListener(listener);
        button9.setOnClickListener(listener);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processOperator(ADDITION);
            }
        });

        buttonSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processOperator(SUBTRACTION);
            }
        });

        buttonMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processOperator(MULTIPLICATION);
            }
        });

        buttonDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processOperator(DIVISION);
            }
        });

        buttonPercent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processOperator(PERCENT);
            }
        });

        buttonDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputDisplay.setText(inputDisplay.getText() + ".");
            }
        });

        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearInput();
            }
        });

        buttonOFF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        buttonEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateResult();
            }
        });
    }

    private void processOperator(char operator) {
        // Check if there's already a value in firstValue or a new number input
        if (!Double.isNaN(firstValue)) {
            allCalculations(); // Continue with the result from the last calculation
        } else if (inputDisplay.getText().length() > 0) {
            // If the firstValue is NaN, try to parse the number input
            firstValue = Double.parseDouble(inputDisplay.getText().toString());
        }

        currentSymbol = operator;
        outputDisplay.setText(decimalFormat.format(firstValue) + String.valueOf(operator));
        inputDisplay.setText(null); // Ready for the next number input
    }


    private void allCalculations() {
        // Only parse the input if there is valid input
        if (!Double.isNaN(firstValue)) {
            if (inputDisplay.getText().length() > 0) {
                secondValue = Double.parseDouble(inputDisplay.getText().toString());
                if (currentSymbol == ADDITION)
                    firstValue += secondValue;
                else if (currentSymbol == SUBTRACTION)
                    firstValue -= secondValue;
                else if (currentSymbol == MULTIPLICATION)
                    firstValue *= secondValue;
                else if (currentSymbol == DIVISION)
                    firstValue /= secondValue;
                else if (currentSymbol == PERCENT)
                    firstValue %= secondValue;
            }
        } else {
            try {
                firstValue = Double.parseDouble(inputDisplay.getText().toString());
            } catch (Exception e) {
                // Handle the case where the input is not a valid number
            }
        }
    }

    private void calculateResult() {
        if (inputDisplay.getText().length() > 0 || !Double.isNaN(firstValue)) {
            allCalculations(); // Calculate based on the last operation
            // Show the result
            outputDisplay.setText(decimalFormat.format(firstValue));
            inputDisplay.setText(null); // Clear the input display for the next number
            // Do not reset firstValue so it can be used in the next operation
            currentSymbol = '0'; // Reset the operator only
        }
    }


    private void clearInput() {
        inputDisplay.setText("");
        outputDisplay.setText("");
        firstValue = Double.NaN;
        secondValue = Double.NaN;
    }
}
