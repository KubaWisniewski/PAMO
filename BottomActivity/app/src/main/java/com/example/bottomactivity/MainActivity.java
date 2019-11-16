package com.example.bottomactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private LinearLayout homeLayout;
    private LinearLayout bmiLayout;
    private LinearLayout dietLayout;
    private ConstraintLayout imageLayout;
    private int gender;
    private ArrayList<String> statistics = new ArrayList<>();
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    homeLayout.setVisibility(View.VISIBLE);
                    bmiLayout.setVisibility(View.INVISIBLE);
                    dietLayout.setVisibility(View.INVISIBLE);
                    imageLayout.setVisibility(View.INVISIBLE);
                    final TextView welcomeTextView = findViewById(R.id.welcomeTextView);
                    welcomeTextView.setText(R.string.welcome);
                    if (statistics.size() > 0) {
                        findViewById(R.id.chartButton).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                showChart();
                            }
                        });
                    }
                    return true;
                case R.id.navigation_dashboard:
                    homeLayout.setVisibility(View.INVISIBLE);
                    bmiLayout.setVisibility(View.VISIBLE);
                    dietLayout.setVisibility(View.INVISIBLE);
                    imageLayout.setVisibility(View.INVISIBLE);
                    final EditText heightEditText = (EditText) findViewById(R.id.heightEditText);
                    final EditText weightEditText = (EditText) findViewById(R.id.weightEditText);
                    final EditText ageEditText = (EditText) findViewById(R.id.ageEditText);
                    final RadioGroup genderRadioGroup = (RadioGroup) findViewById(R.id.genderRadioGroup);
                    genderRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(RadioGroup group, int checkedId) {
                            View radioButton = genderRadioGroup.findViewById(checkedId);
                            gender = genderRadioGroup.indexOfChild(radioButton);
                        }
                    });
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
                            if (ageEditText.getText().toString().isEmpty()) {
                                ageEditText.setError("Enter age");
                                ageEditText.requestFocus();
                                return;
                            }
                            float bmiResult = calculateBMI(Float.parseFloat(weightEditText.getText().toString()), Float.parseFloat(heightEditText.getText().toString()) / 100);
                            bmiResultDetails(bmiResult);
                            float ppmResult = calculatePPM(Float.parseFloat(weightEditText.getText().toString()), Float.parseFloat(heightEditText.getText().toString()), Integer.parseInt(ageEditText.getText().toString()));
                            ppmResultDetails(ppmResult);
                        }
                    });
                    return true;
                case R.id.navigation_notifications:
                    final TextView resultTextView = (TextView) findViewById(R.id.ppmResultTextView);
                    resultTextView.setVisibility(View.VISIBLE);
                    homeLayout.setVisibility(View.INVISIBLE);
                    bmiLayout.setVisibility(View.INVISIBLE);
                    dietLayout.setVisibility(View.VISIBLE);
                    imageLayout.setVisibility(View.VISIBLE);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        if (statistics.size() > 0) {
            findViewById(R.id.chartButton).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showChart();
                }
            });
        }
        findViewById(R.id.quizButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startQuiz();
            }
        });
        homeLayout = findViewById(R.id.homeLayout);
        bmiLayout = findViewById(R.id.bmiLayout);
        dietLayout = findViewById(R.id.dietLayout);
        imageLayout = findViewById(R.id.imageLayout);
        homeLayout.setVisibility(View.VISIBLE);
        bmiLayout.setVisibility(View.INVISIBLE);
        dietLayout.setVisibility(View.INVISIBLE);
        imageLayout.setVisibility(View.INVISIBLE);
    }

    private void bmiResultDetails(float bmiResult) {
        final TextView resultDetailsTextView = (TextView) findViewById(R.id.bmiResultDetailsTextView);
        final TextView bmiResultTextView = (TextView) findViewById(R.id.bmiResultTextView);
        bmiResultTextView.setText(String.format("%.2f", bmiResult));
        if (bmiResult < 18.5)
            resultDetailsTextView.setText(R.string.bmi_underweight);
        else if (bmiResult >= 18.5 && bmiResult <= 25)
            resultDetailsTextView.setText(R.string.bmi_normalweight);
        else if (bmiResult > 25 && bmiResult <= 30)
            resultDetailsTextView.setText(R.string.bmi_overweight);
        else
            resultDetailsTextView.setText(R.string.bmi_obese);
    }

    private void ppmResultDetails(float ppmResult) {
        final ImageView very_low = findViewById(R.id.veryLowImageView);
        final ImageView low = findViewById(R.id.lowImageView);
        final ImageView normal = findViewById(R.id.normalImageView);
        final ImageView high = findViewById(R.id.highImageView);
        final TextView ppmResultTextView = (TextView) findViewById(R.id.ppmResultTextView);
        ppmResultTextView.setText(String.format("%.2f", ppmResult));
        if (ppmResult < 1600) {
            very_low.findViewById(R.id.veryLowImageView).setVisibility(View.VISIBLE);
            low.findViewById(R.id.lowImageView).setVisibility(View.INVISIBLE);
            normal.findViewById(R.id.normalImageView).setVisibility(View.INVISIBLE);
            high.findViewById(R.id.highImageView).setVisibility(View.INVISIBLE);
        } else if (ppmResult >= 1600 && ppmResult < 1800) {
            very_low.findViewById(R.id.veryLowImageView).setVisibility(View.INVISIBLE);
            low.findViewById(R.id.lowImageView).setVisibility(View.VISIBLE);
            normal.findViewById(R.id.normalImageView).setVisibility(View.INVISIBLE);
            high.findViewById(R.id.highImageView).setVisibility(View.INVISIBLE);
        } else if (ppmResult >= 1800 && ppmResult < 2000) {
            very_low.findViewById(R.id.veryLowImageView).setVisibility(View.INVISIBLE);
            low.findViewById(R.id.lowImageView).setVisibility(View.INVISIBLE);
            normal.findViewById(R.id.normalImageView).setVisibility(View.VISIBLE);
            high.findViewById(R.id.highImageView).setVisibility(View.INVISIBLE);
        } else if (ppmResult >= 2000) {
            very_low.findViewById(R.id.veryLowImageView).setVisibility(View.INVISIBLE);
            low.findViewById(R.id.lowImageView).setVisibility(View.INVISIBLE);
            normal.findViewById(R.id.normalImageView).setVisibility(View.INVISIBLE);
            high.findViewById(R.id.highImageView).setVisibility(View.VISIBLE);
        }
    }

    private float calculateBMI(float weight, float height) {
        statistics.add(String.valueOf(weight));
        return (float) (weight / (Math.pow(height, 2)));
    }

    private float calculatePPM(float weight, float height, int age) {
        if (gender == 0) {
            return (float) ((10 * weight) + (height * 6.25) - (5 * age) - 161);
        } else {
            return (float) ((10 * weight) + (height * 6.25) - (5 * age) + 5);
        }
    }

    private void startQuiz() {
        Intent intent = new Intent(this, QuizActivity.class);
        startActivity(intent);
    }

    private void showChart() {
        Intent intent = new Intent(this, ChartActivity.class);
        intent.putStringArrayListExtra("statistics", statistics);
        startActivity(intent);
    }
}
