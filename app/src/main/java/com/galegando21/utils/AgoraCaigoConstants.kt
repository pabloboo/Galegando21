package com.galegando21.utils

import com.galegando21.model.QuestionAgoraCaigo

object AgoraCaigoConstants {

    const val SCORE = "correct_agora_caigo_answers"

    fun getQuestions() : MutableList<QuestionAgoraCaigo> {
        var questions : MutableList<QuestionAgoraCaigo> = mutableListOf()
        questions.add(QuestionAgoraCaigo(
            "Cargo que teñen os membros de un equipo de fútbol que levan un brazalete no brazo esquerdo?",
            "C___TÁN",
            "CAPITÁN"))
        return questions
    }
}