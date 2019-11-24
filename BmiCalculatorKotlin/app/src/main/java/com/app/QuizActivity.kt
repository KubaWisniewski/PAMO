package com.app

import android.content.Intent
import android.os.Bundle

import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class QuizActivity : AppCompatActivity() {
    private var answer1: Button? = null
    private var answer2: Button? = null
    private var answer3: Button? = null
    private var answer4: Button? = null
    private var score: TextView? = null
    private var question: TextView? = null
    private var mAnswer: String? = null
    private var mScore = 0

    private var questionNumber: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        questionNumber = 0
        mScore = 0
        answer1 = findViewById(R.id.answer1Button)
        answer2 = findViewById(R.id.answer2Button)
        answer3 = findViewById(R.id.answer3Button)
        answer4 = findViewById(R.id.answer4Button)
        score = findViewById(R.id.scoreTextView)
        question = findViewById(R.id.questionTextView)
        score!!.text = getString(R.string.score) + mScore

        updateQuestion(questionNumber)

        answer1!!.setOnClickListener {
            if (answer1!!.text === mAnswer) {
                mScore++
                score!!.text = getString(R.string.score) + mScore
                questionNumber++
                updateQuestion(questionNumber)
            } else {
                gameOver()
            }
        }

        answer2!!.setOnClickListener {
            if (answer2!!.text === mAnswer) {
                mScore++
                score!!.text = getString(R.string.score) + mScore
                questionNumber++
                updateQuestion(questionNumber)
            } else {
                gameOver()
            }
        }

        answer3!!.setOnClickListener {
            if (answer3!!.text === mAnswer) {
                mScore++
                score!!.text = getString(R.string.score) + mScore
                questionNumber++
                updateQuestion(questionNumber)
            } else {
                gameOver()
            }
        }

        answer4!!.setOnClickListener {
            if (answer4!!.text === mAnswer) {
                mScore++
                score!!.text = getString(R.string.score + mScore)
                questionNumber++
                updateQuestion(questionNumber)
            } else {
                gameOver()
            }
        }
    }

    private fun updateQuestion(num: Int) {
        if (num >= 5) {
            endTheGame()
        } else {
            question!!.text = Questions.getQuestion(num)
            answer1!!.text = Questions.getChoice(num, 0)
            answer2!!.text = Questions.getChoice(num, 1)
            answer3!!.text = Questions.getChoice(num, 2)
            answer4!!.text = Questions.getChoice(num, 3)
            mAnswer = Questions.getCorrectAnwer(num)
        }
    }

    private fun gameOver() {
        val alertDialogBuilder = AlertDialog.Builder(this@QuizActivity)
        alertDialogBuilder
            .setMessage(getString(R.string.lose_quiz) + mScore)
            .setCancelable(false)
            .setPositiveButton(
                getString(R.string.rerun)
            ) { _, _ ->
                startActivity(Intent(applicationContext, QuizActivity::class.java))
                finish()
            }
            .setNegativeButton(
                getString(R.string.exit_quiz)
            ) { _, _ ->
                Toast.makeText(this@QuizActivity, "", Toast.LENGTH_LONG).show()
                finish()
            }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    private fun endTheGame() {
        val alertDialogBuilder = AlertDialog.Builder(this@QuizActivity)
        alertDialogBuilder
            .setMessage(getString(R.string.win_quiz))
            .setCancelable(false)
            .setPositiveButton(
                getString(R.string.rerun)
            ) { _, _ ->
                startActivity(Intent(applicationContext, QuizActivity::class.java))
                finish()
            }
            .setNegativeButton(
                getString(R.string.exit_quiz)
            ) { _, _ ->
                Toast.makeText(this@QuizActivity, "", Toast.LENGTH_LONG).show()
                finish()
            }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }
}
