package com.example.bmicalculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText heightEditText = (EditText) findViewById(R.id.heightEditText);
        final EditText weightEditText = (EditText) findViewById(R.id.weightEditText);

        findViewById(R.id.callculateButton).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (heightEditText.getText().toString().isEmpty()) {
                    heightEditText.setError("Enter height");
                    heightEditText.requestFocus();
                    return;
                }
                if (weightEditText.getText().toString().isEmpty()) {
                    weightEditText.setError("Enter weight");
                    weightEditText.requestFocus();
                    return;
                }
                float result = calculateBMI(Float.parseFloat(weightEditText.getText().toString()), Float.parseFloat(heightEditText.getText().toString()) / 100);
                resultDetails(result);
            }
        });
    }

    private void resultDetails(float result) {
        final TextView resultTextView = (TextView) findViewById(R.id.resultTextView);
        resultTextView.setText(String.format("%.2f", result));
        final TextView resultDetailsTextView = (TextView) findViewById(R.id.resultDetailsTextView);
        if (result < 18.5)
            resultDetailsTextView.setText(R.string.bmi_underweight);
        else if (result >= 18.5 && result <= 25)
            resultDetailsTextView.setText(R.string.bmi_normalweight);
        else if (result > 25 && result <= 30)
            resultDetailsTextView.setText(R.string.bmi_overweight);
        else
            resultDetailsTextView.setText(R.string.bmi_obese);
    }

    private float calculateBMI(float weight, float height) {
        return (float) (weight / (Math.pow(height, 2)));
    }
}
