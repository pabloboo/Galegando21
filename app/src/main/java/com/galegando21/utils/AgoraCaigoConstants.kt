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
        questions.add(QuestionAgoraCaigo(
            "A que periodo artístico pertence a Victoria de Samotracia?",
            "H____Í___CO",
            "HELENÍSTICO"))
        questions.add(QuestionAgoraCaigo(
            "Que famoso actor galego interpreta a un activista medioambiental na película 'Adú'?",
            "___S __S_R",
            "LUIS TOSAR"))
        questions.add(QuestionAgoraCaigo(
            "Flor que se considera emblema nacional en España",
            "___V_L",
            "CLAVEL"))
        questions.add(QuestionAgoraCaigo(
            "A cordilleira de Atlas vai desde o océano Atlántico ata este mar",
            "_E___E___N__",
            "MEDITERRÁNEO"))
        questions.add(QuestionAgoraCaigo(
            "A que personaxe de 'Cincuenta sombras de Grey' interpreta o actor Jamie Dornan?",
            "__R__T_AN __EY",
            "CHRISTIAN GREY"))
        questions.add(QuestionAgoraCaigo(
            "Do nome de qué deus exipcio deriva a palabra 'amoniaco'",
            "___N",
            "AMÓN"))
        questions.add(QuestionAgoraCaigo(
            "Que banda de rapaces triunfou nos 90 con temas como 'Everybody' ou 'As Long as You Love Me'?",
            "B___S__E_T __YS",
            "BACKSTREET BOYS"))
        questions.add(QuestionAgoraCaigo(
            "Que título real ostenta Isabel II de Inglaterra en Australia?",
            "R__N_",
            "RAÍÑA"))
        questions.add(QuestionAgoraCaigo(
            "Así se chamou o mercado negro en España durante o franquismo",
            "_STR____L_",
            "ESTRAPERLO"))
        questions.add(QuestionAgoraCaigo(
            "Que anglicismo usamos para falar de unha furgoneta acondicionada para elaborar e vender pratos de comida?",
            "___D ___CK",
            "FOOD TRUCK"))

        return questions
    }
}