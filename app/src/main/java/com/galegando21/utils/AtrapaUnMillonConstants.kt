package com.galegando21.utils

import com.galegando21.model.QuestionAtrapaUnMillon

object AtrapaUnMillonConstants {

    const val EASY_LEVEL = "preguntas_faciles"
    const val MEDIUM_LEVEL = "preguntas_normales"
    const val HARD_LEVEL = "preguntas_dificiles"
    const val SCORE = "final_atrapa_un_millon_cash"

    fun getAtrapaUnMillonQuestions(difficult: String): MutableList<QuestionAtrapaUnMillon> {
        var questions = mutableListOf<QuestionAtrapaUnMillon>()
        when (difficult) {
            EASY_LEVEL -> questions = getEasyQuestions()
            MEDIUM_LEVEL -> questions = getMediumQuestions()
            HARD_LEVEL -> questions = getHardQuestions()
        }
        return questions
    }

    fun getEasyQuestions() : MutableList<QuestionAtrapaUnMillon> {
        val questions = mutableListOf<QuestionAtrapaUnMillon>()
        questions.add(
            QuestionAtrapaUnMillon(
            "Que producimos no noso cerebro?",
            "Choiva de meteoritos",
            "Torrentes de aire",
            "Correntes eléctricas",
            "Ríos de lava",
            3)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Por que Santa Claus vai de vermello?",
                "Pola marca 'Coca Cola'",
                "Pola marca Sprite sabor a fresa",
                "Porque o seu fillo se chama 'Rojo'",
                "Para que se lle vexa pola noite",
                1)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Cantas parellas de animais meteu Moisés na arca?",
                "0",
                "40",
                "42",
                "45",
                1)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Cal de estos nomes de xogadores de fútbol ten algo que ver con coser?",
                "Puyol",
                "Ronaldo",
                "Piqué",
                "Casillas",
                3)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Cal de estos nomes é un cereal?",
                "Azufre",
                "Centeno",
                "Remolacha",
                "Bifis",
                2)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Dos seguintes equipos cal é o que máis veces gañou o mundial?",
                "Italia",
                "Argentina",
                "Francia",
                "Brasil",
                4)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Cal ten sepia nos seus ingredientes?",
                "Chocos fritos",
                "Patatas bravas",
                "Arroz con costra",
                "Cañas",
                1)
        )
        return questions
    }

    fun getMediumQuestions() : MutableList<QuestionAtrapaUnMillon> {
        val questions = mutableListOf<QuestionAtrapaUnMillon>()
        questions.add(QuestionAtrapaUnMillon(
            "Que usaban para momificar os cuerpos no Antigo Exipto?",
            "Un tipo de sal",
            "Un tipo de azúcar",
            "Remedios Cervantes",
            "-",
            1))
        questions.add(
            QuestionAtrapaUnMillon(
                "En que monumento tes que subir máis escalóns?",
                "London Eye",
                "Empire State",
                "Big Ben",
                "-",
                2)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "En que xogo de mesa es que conseguir a bandeira do enemigo?",
                "Estratego",
                "Risk",
                "Captura a bandeira",
                "-",
                1)
        )
        return questions
    }

    fun getHardQuestions() : MutableList<QuestionAtrapaUnMillon> {
        val questions = mutableListOf<QuestionAtrapaUnMillon>()
        questions.add(QuestionAtrapaUnMillon(
            "Quen besa a quen no famoso cadro 'El beso' de Gustav Klimt",
            "A muller ao home",
            "O home á muller",
            "-",
            "-",
            2))
        questions.add(
            QuestionAtrapaUnMillon(
                "Que existiu de verdade?",
                "Rita a Cantaora",
                "Perico dos palotes",
                "-",
                "-",
                1)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Según o record Guiness, que tivo un home durante 68 anos seguidos?",
                "Ataque de risa",
                "Ataque de hipo",
                "-",
                "-",
                2)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Cal foi antes unha característica distintiva dos autobuses de Londres?",
                "Ter dous pisos",
                "Ser vermellos",
                "-",
                "-",
                2)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Cal foi unha operación da segunda Guerra Mundial?",
                "Cobra",
                "Anaconda",
                "-",
                "-",
                1)
        )
        return questions
    }
}