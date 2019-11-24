package com.app

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.annotation.NonNull
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

import java.util.ArrayList
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    private var homeLayout: LinearLayout? = null
    private var bmiLayout: LinearLayout? = null
    private var dietLayout: LinearLayout? = null
    private var imageLayout: ConstraintLayout? = null
    private var gender: Int = 0
    private val statistics = ArrayList<String>()
    private val mOnNavigationItemSelectedListener =
        object : BottomNavigationView.OnNavigationItemSelectedListener {

            override fun onNavigationItemSelected(@NonNull item: MenuItem): Boolean {
                when (item.itemId) {
                    R.id.navigation_home -> {
                        homeLayout?.visibility = View.VISIBLE
                        bmiLayout?.visibility = View.INVISIBLE
                        dietLayout?.visibility = View.INVISIBLE
                        imageLayout?.visibility = View.INVISIBLE
                        val welcomeTextView: TextView = findViewById(R.id.welcomeTextView)
                        welcomeTextView.setText(R.string.welcome)
                        if (statistics.size > 0) {
                            val chartButton: Button = findViewById(R.id.chartButton)
                            chartButton.setOnClickListener { showChart() }
                        }
                        return true
                    }
                    R.id.navigation_dashboard -> {
                        homeLayout?.visibility = View.INVISIBLE
                        bmiLayout?.visibility = View.VISIBLE
                        dietLayout?.visibility = View.INVISIBLE
                        imageLayout?.visibility = View.INVISIBLE
                        val heightEditText = findViewById<EditText>(R.id.heightEditText)
                        val weightEditText = findViewById<EditText>(R.id.weightEditText)
                        val ageEditText = findViewById<EditText>(R.id.ageEditText)
                        val genderRadioGroup = findViewById<RadioGroup>(R.id.genderRadioGroup)
                        genderRadioGroup.setOnCheckedChangeListener { _, checkedId ->
                            val radioButton = genderRadioGroup.findViewById<View>(checkedId)
                            gender = genderRadioGroup.indexOfChild(radioButton)
                        }
                        val callculateButton: Button = findViewById(R.id.callculateButton)
                        callculateButton.setOnClickListener(View.OnClickListener {
                            if (heightEditText.text.toString().isEmpty()) {
                                heightEditText.error = "Enter height"
                                heightEditText.requestFocus()
                                return@OnClickListener
                            }
                            if (weightEditText.text.toString().isEmpty()) {
                                weightEditText.error = "Enter weight"
                                weightEditText.requestFocus()
                                return@OnClickListener
                            }
                            if (ageEditText.text.toString().isEmpty()) {
                                ageEditText.error = "Enter age"
                                ageEditText.requestFocus()
                                return@OnClickListener
                            }
                            val bmiResult = calculateBMI(
                                java.lang.Float.parseFloat(weightEditText.text.toString()),
                                java.lang.Float.parseFloat(heightEditText.text.toString()) / 100
                            )
                            bmiResultDetails(bmiResult)
                            val ppmResult = calculatePPM(
                                java.lang.Float.parseFloat(weightEditText.text.toString()),
                                java.lang.Float.parseFloat(heightEditText.text.toString()),
                                Integer.parseInt(ageEditText.text.toString())
                            )
                            ppmResultDetails(ppmResult)
                        })
                        return true
                    }
                    R.id.navigation_notifications -> {
                        val resultTextView = findViewById<TextView>(R.id.ppmResultTextView)
                        resultTextView.visibility = View.VISIBLE
                        homeLayout?.visibility = View.INVISIBLE
                        bmiLayout?.visibility = View.INVISIBLE
                        dietLayout?.visibility = View.VISIBLE
                        imageLayout?.visibility = View.VISIBLE
                        return true
                    }
                }
                return false
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        if (statistics.size > 0) {
            val chartButton: Button = findViewById(R.id.chartButton)
            chartButton.setOnClickListener { showChart() }
        }
        val quizButton: Button = findViewById(R.id.quizButton)
        quizButton.setOnClickListener { startQuiz() }
        homeLayout = findViewById(R.id.homeLayout)
        bmiLayout = findViewById(R.id.bmiLayout)
        dietLayout = findViewById(R.id.dietLayout)
        imageLayout = findViewById(R.id.imageLayout)
        homeLayout?.visibility = View.VISIBLE
        bmiLayout?.visibility = View.INVISIBLE
        dietLayout?.visibility = View.INVISIBLE
        imageLayout?.visibility = View.INVISIBLE
    }

    private fun bmiResultDetails(bmiResult: Float) {
        val resultDetailsTextView = findViewById<TextView>(R.id.bmiResultDetailsTextView)
        val bmiResultTextView = findViewById<TextView>(R.id.bmiResultTextView)
        bmiResultTextView.text = String.format("%.2f", bmiResult)
        if (bmiResult < 18.5)
            resultDetailsTextView.setText(R.string.bmi_underweight)
        else if (bmiResult in 18.5..25.0)
            resultDetailsTextView.setText(R.string.bmi_normalweight)
        else if (bmiResult > 25 && bmiResult <= 30)
            resultDetailsTextView.setText(R.string.bmi_overweight)
        else
            resultDetailsTextView.setText(R.string.bmi_obese)
    }

    private fun ppmResultDetails(ppmResult: Float) {
        val veryLow: ImageView = findViewById(R.id.veryLowImageView)
        val low: ImageView = findViewById(R.id.lowImageView)
        val normal: ImageView = findViewById(R.id.normalImageView)
        val high: ImageView = findViewById(R.id.highImageView)
        val ppmResultTextView = findViewById<TextView>(R.id.ppmResultTextView)
        ppmResultTextView.text = String.format("%.2f", ppmResult)
        if (ppmResult < 1600) {
            veryLow.visibility = View.VISIBLE
            low.visibility = View.INVISIBLE
            normal.visibility = View.INVISIBLE
            high.visibility = View.INVISIBLE
        } else if (ppmResult >= 1600 && ppmResult < 1800) {
            veryLow.visibility = View.INVISIBLE
            low.visibility = View.VISIBLE
            normal.visibility = View.INVISIBLE
            high.visibility = View.INVISIBLE
        } else if (ppmResult >= 1800 && ppmResult < 2000) {
            veryLow.visibility = View.INVISIBLE
            low.visibility = View.INVISIBLE
            normal.visibility = View.VISIBLE
            high.visibility = View.INVISIBLE
        } else if (ppmResult >= 2000) {
            veryLow.visibility = View.INVISIBLE
            low.visibility = View.INVISIBLE
            normal.visibility = View.INVISIBLE
            high.visibility = View.VISIBLE
        }
    }

    private fun calculateBMI(weight: Float, height: Float): Float {
        statistics.add(weight.toString())
        return (weight / height.toDouble().pow(2.0)).toFloat()
    }

    private fun calculatePPM(weight: Float, height: Float, age: Int): Float {
        return if (gender == 0) {
            (10 * weight + height * 6.25 - (5 * age).toDouble() - 161.0).toFloat()
        } else {
            (10 * weight + height * 6.25 - 5 * age + 5).toFloat()
        }
    }

    private fun startQuiz() {
        val intent = Intent(this, QuizActivity::class.java)
        startActivity(intent)
    }

    private fun showChart() {
        val intent = Intent(this, ChartActivity::class.java)
        intent.putStringArrayListExtra("statistics", statistics)
        startActivity(intent)
    }
}
