package com.galegando21.utils

import com.galegando21.model.QuestionAtrapameSePodes

object AtrapameSePodesConstants {

    const val SCORE = "correct_atrapame_se_podes_answers"

    fun getAtrapameSePodesQuestions() : MutableList<QuestionAtrapameSePodes> {
        var questions : MutableList<QuestionAtrapameSePodes> = mutableListOf()
        questions.add(QuestionAtrapameSePodes(1, "Para que un bebé sexa muller, que letra se repite dúas veces nos cromosomas sexuais?", "X"))
        questions.add(QuestionAtrapameSePodes(2, "De que grupo de cumbia arxentino, autor de 'Mentirosa', era vocalista principal Rodrigo Tapari?", "RAFAGA"))
        questions.add(QuestionAtrapameSePodes(3, "Que cantante arxentino adquiriu sona mundia grazas á canción 'Color Esperanza'?", "DIEGO TORRES"))
        questions.add(QuestionAtrapameSePodes(4, "Segundo o calendario relixioso, que día é a festividade da Virxe das Dores?", "15 DE SETEMBRO"))
        questions.add(QuestionAtrapameSePodes(5, "Que cineasta dirixiu a película 'Mujeres al borde de un ataque de nervios'?", "PEDRO ALMODOVAR"))
        questions.add(QuestionAtrapameSePodes(6, "Que película converteu en celebridade a Julia Roberts no papel dunha prostituta que cambia de vida xunto a Richard Gere?", "PRETTY WOMAN"))
        questions.add(QuestionAtrapameSePodes(7, "De que cantante é o disco do ano 2000 'El alma al aire'?", "ALEJANDRO SANZ"))
        return questions
    }
}