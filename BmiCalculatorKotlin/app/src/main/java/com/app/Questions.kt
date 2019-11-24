package com.app

internal object Questions {
    private val questions = arrayOf(
        "Jaki posiłek w ciągu dnia jest najważniejsszy?",
        "O czym mówi współczynik BMI?",
        "Co należy ograniczać w posiłkach?",
        "Co sprzyja tyciu?",
        "Najzdrowszy zestaw na drugie śniadanie to?"
    )

    private val choices = arrayOf(
        arrayOf("Sniadanie", "Obiad", "Podwieczorek", "Kolacja"),
        arrayOf(
            "Prawdiłowa waga",
            "Prawdiłowa postawa ciała",
            "Pomiar tkanki tłuszczowej",
            "Prawdiłowy poziom chlesterolu"
        ),
        arrayOf("Warzywa i owoce", "Produkty mleczne", "Słodycze i sól", "Mięso i ryby"),
        arrayOf(
            "Spożywanie produktów mlecznych",
            "Spożywanie owoców",
            "Spożywanie słodyczy i tłuszczy",
            "Spożywanie warzyw"
        ),
        arrayOf(
            "Chipsy i cola",
            "Grahamka i jogurt",
            "Pączek i kefir",
            "Batonik czekoladowy i sok owocowy"
        )
    )

    private val correctAnswer = arrayOf(
        "Sniadanie",
        "Prawdiłowa waga",
        "Słodycze i sól",
        "Spożywanie słodyczy i tłuszczy",
        "Grahamka i jogurt"
    )

    fun getQuestion(questionNumber: Int): String {
        return questions[questionNumber]
    }

    fun getChoice(questionNumber: Int, choiceNumber: Int): String {
        return choices[questionNumber][choiceNumber]
    }

    fun getCorrectAnwer(questionNumber: Int): String {
        return correctAnswer[questionNumber]
    }
}
