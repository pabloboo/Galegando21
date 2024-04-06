package com.galegando21.utils

import com.galegando21.model.QuestionAtrapameSePodes

object AtrapameSePodesConstants {

    const val SCORE = "correct_atrapame_se_podes_answers"

    fun getAtrapameSePodesQuestions(level: Int) : MutableList<QuestionAtrapameSePodes> {
        var questions : MutableList<QuestionAtrapameSePodes> = mutableListOf()
        when (level) {
            0 -> questions = getAtrapameSePodesQuestionsLevel0()
            1 -> questions = getAtrapameSePodesQuestionsLevel1()
            2 -> questions = getAtrapameSePodesQuestionsLevel2()
            3 -> questions = getAtrapameSePodesQuestionsLevel3()
            4 -> questions = getAtrapameSePodesQuestionsLevel4()
        }
        return questions
    }

    private fun getAtrapameSePodesQuestionsLevel0() : MutableList<QuestionAtrapameSePodes> {
        var questions : MutableList<QuestionAtrapameSePodes> = mutableListOf()
        questions.add(QuestionAtrapameSePodes("En que programa da TVG se fixeron famosos os Tonechos", "LUAR"))
        questions.add(QuestionAtrapameSePodes("O estadio Verónica Boquete de Santiago leva tamén o nome do barrio compostelán no que está. Cal é?", "SAN LAZARO"))
        return questions
    }

    private fun getAtrapameSePodesQuestionsLevel1() : MutableList<QuestionAtrapameSePodes> {
        var questions : MutableList<QuestionAtrapameSePodes> = mutableListOf()
        questions.add(QuestionAtrapameSePodes( "Que cineasta dirixiu a película 'Mujeres al borde de un ataque de nervios'?", "PEDRO ALMODOVAR"))
        questions.add(QuestionAtrapameSePodes( "Que película converteu en celebridade a Julia Roberts no papel dunha prostituta que cambia de vida xunto a Richard Gere?", "PRETTY WOMAN"))
        questions.add(QuestionAtrapameSePodes( "Para que un bebé sexa muller, que letra se repite dúas veces nos cromosomas sexuais?", "X"))
        questions.add(QuestionAtrapameSePodes("En que cidade galega podes visitar o centro comercial Camelias?", "VIGO"))
        questions.add(QuestionAtrapameSePodes("En que cidade galega se atopa a catedral de San Martiño?", "OURENSE"))
        return questions
    }

    private fun getAtrapameSePodesQuestionsLevel2() : MutableList<QuestionAtrapameSePodes> {
        var questions : MutableList<QuestionAtrapameSePodes> = mutableListOf()
        questions.add(QuestionAtrapameSePodes("Que magnitude se mide co higrómetro?", "HUMIDADE"))
        questions.add(QuestionAtrapameSePodes("A que cidade pertencen os barrios de Covent Garden, Litte Venice ou Soho", "LONDRES"))
        questions.add(QuestionAtrapameSePodes("De que grupo de cumbia arxentino, autor de 'Mentirosa', era vocalista principal Rodrigo Tapari?", "RAFAGA"))
        questions.add(QuestionAtrapameSePodes( "De que cantante é o disco do ano 2000 'El alma al aire'?", "ALEJANDRO SANZ"))
        return questions
    }

    private fun getAtrapameSePodesQuestionsLevel3() : MutableList<QuestionAtrapameSePodes> {
        var questions : MutableList<QuestionAtrapameSePodes> = mutableListOf()
        questions.add(QuestionAtrapameSePodes("En que ría desemboca o río Lérez?", "PONTEVEDRA"))
        questions.add(QuestionAtrapameSePodes("Que cantante arxentino adquiriu sona mundia grazas á canción 'Color Esperanza'?", "DIEGO TORRES"))
        questions.add(QuestionAtrapameSePodes("Cal é o nome do protagonista zoupón e miope da serie de animación 'Padre de Familia'", "PETER"))
        return questions
    }

    private fun getAtrapameSePodesQuestionsLevel4() : MutableList<QuestionAtrapameSePodes> {
        var questions : MutableList<QuestionAtrapameSePodes> = mutableListOf()
        questions.add(QuestionAtrapameSePodes( "Segundo o calendario relixioso, que día é a festividade da Virxe das Dores?", "15 DE SETEMBRO"))
        questions.add(QuestionAtrapameSePodes("Cal é a nacionalidade da cantante e compositora Enya, que triunfou no ano 2000 co álbum 'A day without rain'", "IRLANDESA"))
        return questions
    }
}