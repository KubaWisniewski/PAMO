package com.example.bottomactivity;

class Questions {
    private static String questions[] = {
            "Jaki posiłek w ciągu dnia jest najważniejsszy?",
            "O czym mówi współczynik BMI?",
            "Co należy ograniczać w posiłkach?",
            "Co sprzyja tyciu?",
            "Najzdrowszy zestaw na drugie śniadanie to?"

    };

    private static String choices[][] = {
            {"Sniadanie", "Obiad", "Podwieczorek", "Kolacja"},
            {"Prawdiłowa waga", "Prawdiłowa postawa ciała", "Pomiar tkanki tłuszczowej", "Prawdiłowy poziom chlesterolu"},
            {"Warzywa i owoce", "Produkty mleczne", "Słodycze i sól", "Mięso i ryby"},
            {"Spożywanie produktów mlecznych", "Spożywanie owoców", "Spożywanie słodyczy i tłuszczy", "Spożywanie warzyw"},
            {"Chipsy i cola", "Grahamka i jogurt", "Pączek i kefir", "Batonik czekoladowy i sok owocowy"}
    };

    private static String correctAnswer[] = {"Sniadanie", "Prawdiłowa waga", "Słodycze i sól", "Spożywanie słodyczy i tłuszczy", "Grahamka i jogurt"};

    static String getQuestion(int questionNumber) {
        return questions[questionNumber];
    }

    static String getChoice(int questionNumber, int choiceNumber) {
        return choices[questionNumber][choiceNumber];
    }

    static String getCorrectAnwer(int questionNumber) {
        return correctAnswer[questionNumber];
    }
}
