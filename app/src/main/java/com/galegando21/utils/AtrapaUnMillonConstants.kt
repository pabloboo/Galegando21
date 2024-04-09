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
        return questions
    }
}