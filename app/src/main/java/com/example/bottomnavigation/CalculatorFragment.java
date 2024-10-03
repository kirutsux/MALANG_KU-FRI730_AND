package com.example.bottomnavigation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;

import java.text.DecimalFormat;

public class CalculatorFragment extends Fragment {

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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the fragment layout
        View view = inflater.inflate(R.layout.calculator_fragment, container, false);

        // Initialize the UI elements here using view.findViewById
        decimalFormat = new DecimalFormat("#.00");
        inputDisplay = view.findViewById(R.id.input);
        outputDisplay = view.findViewById(R.id.output);
        button0 = view.findViewById(R.id.btn0);
        button1 = view.findViewById(R.id.btn1);
        button2 = view.findViewById(R.id.btn2);
        button3 = view.findViewById(R.id.btn3);
        button4 = view.findViewById(R.id.btn4);
        button5 = view.findViewById(R.id.btn5);
        button6 = view.findViewById(R.id.btn6);
        button7 = view.findViewById(R.id.btn7);
        button8 = view.findViewById(R.id.btn8);
        button9 = view.findViewById(R.id.btn9);
        buttonAdd = view.findViewById(R.id.add);
        buttonSub = view.findViewById(R.id.subtract);
        buttonDivide = view.findViewById(R.id.division);
        buttonDot = view.findViewById(R.id.btnPoint);
        buttonMultiply = view.findViewById(R.id.multiply);
        buttonClear = view.findViewById(R.id.clear);
        buttonOFF = view.findViewById(R.id.off);
        buttonEqual = view.findViewById(R.id.equal);
        buttonPercent = view.findViewById(R.id.percent);

        setupButtonListeners();

        return view; // Return the view for this fragment
    }

    private void setupButtonListeners() {
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
                getActivity().finish();
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
        if (!Double.isNaN(firstValue)) {
            allCalculations();
        } else if (inputDisplay.getText().length() > 0) {
            firstValue = Double.parseDouble(inputDisplay.getText().toString());
        }

        currentSymbol = operator;
        outputDisplay.setText(decimalFormat.format(firstValue) + String.valueOf(operator));
        inputDisplay.setText(null);
    }

    private void allCalculations() {
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
                // Handle invalid input
            }
        }
    }

    private void calculateResult() {
        if (inputDisplay.getText().length() > 0 || !Double.isNaN(firstValue)) {
            allCalculations();
            outputDisplay.setText(decimalFormat.format(firstValue));
            inputDisplay.setText(null);
            currentSymbol = '0';
        }
    }

    private void clearInput() {
        inputDisplay.setText("");
        outputDisplay.setText("");
        firstValue = Double.NaN;
        secondValue = Double.NaN;
    }
}
