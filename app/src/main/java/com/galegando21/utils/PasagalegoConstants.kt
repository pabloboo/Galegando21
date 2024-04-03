package com.galegando21.utils

import com.galegando21.model.QuestionPasagalego

object PasagalegoConstants {

    const val SCORE = "correct_pasagalego_answers"
    const val ALFABETO = "ABCDEFGHILMNÑOPQRSTUVXZ"

    fun getPasagalegoQuestions(letter: Char) : MutableList<QuestionPasagalego> {
        var questions : MutableList<QuestionPasagalego> = mutableListOf()
        when (letter) {
            'A' -> questions = getPasagalegoQuestionsA()
            'B' -> questions = getPasagalegoQuestionsB()
            'C' -> questions = getPasagalegoQuestionsC()
            'D' -> questions = getPasagalegoQuestionsD()
            'E' -> questions = getPasagalegoQuestionsE()
            'F' -> questions = getPasagalegoQuestionsF()
            'G' -> questions = getPasagalegoQuestionsG()
            'H' -> questions = getPasagalegoQuestionsH()
            'I' -> questions = getPasagalegoQuestionsI()
            'L' -> questions = getPasagalegoQuestionsL()
            'M' -> questions = getPasagalegoQuestionsM()
            'N' -> questions = getPasagalegoQuestionsN()
            'Ñ' -> questions = getPasagalegoQuestionsÑ()
            'O' -> questions = getPasagalegoQuestionsO()
            'P' -> questions = getPasagalegoQuestionsP()
            'Q' -> questions = getPasagalegoQuestionsQ()
            'R' -> questions = getPasagalegoQuestionsR()
            'S' -> questions = getPasagalegoQuestionsS()
            'T' -> questions = getPasagalegoQuestionsT()
            'U' -> questions = getPasagalegoQuestionsU()
            'V' -> questions = getPasagalegoQuestionsV()
            'X' -> questions = getPasagalegoQuestionsX()
            'Z' -> questions = getPasagalegoQuestionsZ()
        }
        return questions
    }

    private fun getPasagalegoQuestionsA() : MutableList<QuestionPasagalego> {
        var questions : MutableList<QuestionPasagalego> = mutableListOf()
        questions.add(QuestionPasagalego(1,"Armazón con forma de X que, nos molinos de vento, sostén as telas nas que fai presión o vento", "ASPA"))

        return questions
    }

    private fun getPasagalegoQuestionsB() : MutableList<QuestionPasagalego> {
        var questions : MutableList<QuestionPasagalego> = mutableListOf()
        questions.add(QuestionPasagalego(1,"Pertencente ou relativo á guerra", "BELICO"))

        return questions
    }

    private fun getPasagalegoQuestionsC() : MutableList<QuestionPasagalego> {
        var questions : MutableList<QuestionPasagalego> = mutableListOf()
        questions.add(QuestionPasagalego(1,"Total privación do sentido da vista", "CEGUEIRA"))

        return questions
    }

    private fun getPasagalegoQuestionsD() : MutableList<QuestionPasagalego> {
        var questions : MutableList<QuestionPasagalego> = mutableListOf()
        questions.add(QuestionPasagalego(1,"Comercialización e reparto de un produto desde a súa saída de produción ata que chega ao vendedor", "DISTRIBUCION"))

        return questions
    }

    private fun getPasagalegoQuestionsE() : MutableList<QuestionPasagalego> {
        var questions : MutableList<QuestionPasagalego> = mutableListOf()
        questions.add(QuestionPasagalego(1,"Superficie brillante que reflexa imaxes", "ESPELLO"))

        return questions
    }

    private fun getPasagalegoQuestionsF() : MutableList<QuestionPasagalego> {
        var questions : MutableList<QuestionPasagalego> = mutableListOf()
        questions.add(QuestionPasagalego(1,"Podar unha árbore moi cerca da parte na que termina o tronco e empezan as ramas", "FRADAR"))

        return questions
    }

    private fun getPasagalegoQuestionsG() : MutableList<QuestionPasagalego> {
        var questions : MutableList<QuestionPasagalego> = mutableListOf()
        questions.add(QuestionPasagalego(1,"Ave doméstica da familia dos fasiánidos, femia do galo, con crista pequena e vermella e peteiro curto, que se cría para aproveitar os seus ovos e a súa carne para a alimentación.", "GALIÑA"))

        return questions
    }

    private fun getPasagalegoQuestionsH() : MutableList<QuestionPasagalego> {
        var questions : MutableList<QuestionPasagalego> = mutableListOf()
        questions.add(QuestionPasagalego(1,"Conxunto de bens que se reciben tras o falecemento do seu posedor, por disposición testamentaria ou legal", "HERDANZA"))

        return questions
    }

    private fun getPasagalegoQuestionsI() : MutableList<QuestionPasagalego> {
        var questions : MutableList<QuestionPasagalego> = mutableListOf()
        questions.add(QuestionPasagalego(1,"Irritable, propenso á ira", "IRASCIBLE"))

        return questions
    }

    private fun getPasagalegoQuestionsL() : MutableList<QuestionPasagalego> {
        var questions : MutableList<QuestionPasagalego> = mutableListOf()
        questions.add(QuestionPasagalego(1,"Pedra plana situada a pouca altura do chan, onde se fai o lume para cociñar, nas casas tradicionais, e sobre a que adoita ir colocada unha cambota.", "LAREIRA"))

        return questions
    }

    private fun getPasagalegoQuestionsM() : MutableList<QuestionPasagalego> {
        var questions : MutableList<QuestionPasagalego> = mutableListOf()
        questions.add(QuestionPasagalego(1,"Ensalada de froitas", "MACEDONIA"))

        return questions
    }

    private fun getPasagalegoQuestionsN() : MutableList<QuestionPasagalego> {
        var questions : MutableList<QuestionPasagalego> = mutableListOf()
        questions.add(QuestionPasagalego(1,"Natural da comunidade autónoma con capital en Pamplona", "NAVARRO"))

        return questions
    }

    private fun getPasagalegoQuestionsÑ() : MutableList<QuestionPasagalego> {
        var questions : MutableList<QuestionPasagalego> = mutableListOf()
        questions.add(QuestionPasagalego(1,"Ave corredora de América do Sur parecida á avestruz africana, aínda que algo máis pequena ca esta e de plumaxe gris menos fina, da que existen distintas especies.", "ÑANDU"))

        return questions
    }

    private fun getPasagalegoQuestionsO() : MutableList<QuestionPasagalego> {
        var questions : MutableList<QuestionPasagalego> = mutableListOf()
        questions.add(QuestionPasagalego(1,"Estofado de carne de vacún cortada do xarrete, co seu oso e caña incluídos", "OSOBUCO"))

        return questions
    }

    private fun getPasagalegoQuestionsP() : MutableList<QuestionPasagalego> {
        var questions : MutableList<QuestionPasagalego> = mutableListOf()
        questions.add(QuestionPasagalego(1,"Cartel ou edicto que se fixaba nas esquinas para coñecemento da xente", "PLACARTE"))

        return questions
    }

    private fun getPasagalegoQuestionsQ() : MutableList<QuestionPasagalego> {
        var questions : MutableList<QuestionPasagalego> = mutableListOf()
        questions.add(QuestionPasagalego(1,"Ter cariño ou amor a [alguén].", "QUERER"))

        return questions
    }

    private fun getPasagalegoQuestionsR() : MutableList<QuestionPasagalego> {
        var questions : MutableList<QuestionPasagalego> = mutableListOf()
        questions.add(QuestionPasagalego(1,"Actitude da persoa que garda as consideracións debidas ás persoas ou cousas", "RESPETO"))

        return questions
    }

    private fun getPasagalegoQuestionsS() : MutableList<QuestionPasagalego> {
        var questions : MutableList<QuestionPasagalego> = mutableListOf()
        questions.add(QuestionPasagalego(1,"Título da película de 1969, dirixida por Pierre Grimblat e protagonizada por Serge Gainsbourg e Jane Birkin", "SLOGAN"))

        return questions
    }

    private fun getPasagalegoQuestionsT() : MutableList<QuestionPasagalego> {
        var questions : MutableList<QuestionPasagalego> = mutableListOf()
        questions.add(QuestionPasagalego(1,"Mechón de pelo que se leva levantado sobre a frente", "TUPE"))

        return questions
    }

    private fun getPasagalegoQuestionsU() : MutableList<QuestionPasagalego> {
        var questions : MutableList<QuestionPasagalego> = mutableListOf()
        questions.add(QuestionPasagalego(1,"Dicese do fillo que carece de irmáns", "UNICO"))

        return questions
    }

    private fun getPasagalegoQuestionsV() : MutableList<QuestionPasagalego> {
        var questions : MutableList<QuestionPasagalego> = mutableListOf()
        questions.add(QuestionPasagalego(1,"Traspasar a alguén polo precio convido a propiedade do que se posee", "VENDER"))

        return questions
    }

    private fun getPasagalegoQuestionsX() : MutableList<QuestionPasagalego> {
        var questions : MutableList<QuestionPasagalego> = mutableListOf()
        questions.add(QuestionPasagalego(1,"Microorganismo que pode causar ou propagar enfermidades", "XERMEN"))

        return questions
    }

    private fun getPasagalegoQuestionsZ() : MutableList<QuestionPasagalego> {
        var questions : MutableList<QuestionPasagalego> = mutableListOf()
        questions.add(QuestionPasagalego(1,"Ignorante, torpe e moi tardo en aprender", "ZOQUETE"))

        return questions
    }

}