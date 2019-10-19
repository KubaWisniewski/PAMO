package com.example.myapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    private EditText weightEditText;
    private EditText heightEditText;
    private TextView resultTextView;
    private TextView resultDetailsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        heightEditText = (EditText) findViewById(R.id.heightEditText);
        weightEditText = (EditText) findViewById(R.id.weightEditText);
        resultTextView = (TextView) findViewById(R.id.resultTextView);
        resultDetailsTextView = (TextView) findViewById(R.id.resultDetailsTextView);

        findViewById(R.id.callculateButton).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (weightEditText.getText().toString().isEmpty()) {
                    weightEditText.setError("Enter weight");
                    weightEditText.requestFocus();
                    return;
                }
                if (heightEditText.getText().toString().isEmpty()) {
                    heightEditText.setError("Enter height");
                    heightEditText.requestFocus();
                    return;
                }
                double result = calculateBMI(Double.parseDouble(weightEditText.getText().toString()), Double.parseDouble(heightEditText.getText().toString()) / 100);

                resultTextView.setText(String.valueOf(result));
                resultDetailsTextView.setText(resultDetails(result));
            }
        });
    }

    private String resultDetails(double result) {
        if (result < 18.5)
            return "Underwieght";
        else if (result >= 18.5 && result <= 25)
            return "Normalweight";
        else if (result > 25 && result <= 30)
            return "Overweight";
        else if (result > 30)
            return "Obese";
        else return "Error";
    }

    private double calculateBMI(double weight, double height) {
        return (double) weight / (Math.pow(height, 2));
    }
}
