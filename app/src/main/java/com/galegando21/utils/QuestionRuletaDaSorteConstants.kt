package com.galegando21.utils

import com.galegando21.model.QuestionRuletaDaSorte

object QuestionRuletaDaSorteConstants {

    const val PROBA_VELOCIDADE_QUESTIONS_NUMBER = 3
    const val SCORE_PROBA_VELOCIDADE = "correct_answers_proba_velocidade"

    fun getQuestions(): MutableList<QuestionRuletaDaSorte> {
        var questions : MutableList<QuestionRuletaDaSorte> = mutableListOf()
        questions.add(
            QuestionRuletaDaSorte(
                "ENTRENADORA",
                "PERSOA"
            )
        )
        questions.add(
            QuestionRuletaDaSorte(
                "SEMPRE TEÑEN CALZADO DO TEU NÚMERO",
                "NA ZAPATERÍA"
            )
        )
        questions.add(
            QuestionRuletaDaSorte(
                "DERROTAR, VENCER E GAÑAR",
                "TRES SINÓNIMOS"
            )
        )
        questions.add(
            QuestionRuletaDaSorte(
                "VEXO ALGO QUE NON SE PODE TOCAR E TOCOO",
                "SOBRE AS PROHIBICIÓNS"
            )
        )
        questions.add(
            QuestionRuletaDaSorte(
                "MERMELADA DE FRUTOS VERMELLOS SEN AZUCRE",
                "FAINA TI MESMO"
            )
        )
        questions.add(
            QuestionRuletaDaSorte(
                "O ESPECTÁCULO DEBE CONTINUAR",
                "'TU CARA ME SUENA'"
            )
        )
        questions.add(
            QuestionRuletaDaSorte(
                "CONFIARIAMOS MÁIS NO NOSO INSTINTO",
                "EN UN MUNDO PERFECTO"
            )
        )
        questions.add(
            QuestionRuletaDaSorte(
                "QUE A SÁBANA BAIXEIRA NON SE AXUSTE AO COLCHÓN",
                "A MIN MOLESTAME"
            )
        )
        questions.add(
            QuestionRuletaDaSorte(
                "O QUE CHE COBRAN POR FACER TI MESMO UN PAGO",
                "GASTOS DE XESTIÓN"
            )
        )
        questions.add(
            QuestionRuletaDaSorte(
                "DAME IGUAL O QUE DIGA FULANITO OU MENGANITO",
                "ISO NON É VERDADE..."
            )
        )
        questions.add(
            QuestionRuletaDaSorte(
                "EU SIMPLEMENTE FUN ONDE ME ENVIARON",
                "NEIL AMSTRONG"
            )
        )
        questions.add(
            QuestionRuletaDaSorte(
                "TOSTADA FRANCESA CON QUEIXO E MERMELADA",
                "PREPAROCHE O ALMORZO?"
            )
        )
        questions.add(
            QuestionRuletaDaSorte(
                "TARDE PARA A IRA DE RAÚL ARÉVALO",
                "PELÍCULA E DIRECTOR"
            )
        )
        questions.add(
            QuestionRuletaDaSorte(
                "E ACABEIME SUBINDO EN UNHA MESA A BAILAR",
                "HOXE NON TEÑO GANAS DE FESTA"
            )
        )
        questions.add(
            QuestionRuletaDaSorte(
                "CUMPLIR DEZAOITO ANOS RODEADOS DE AMIGOS",
                "O NOSO SOÑO FEITO REALIDADE"
            )
        )
        questions.add(
            QuestionRuletaDaSorte(
                "O PEQUENO PLUTÓN DEIXOU DE SER UN PLANETA",
                "EN 2006"
            )
        )
        questions.add(
            QuestionRuletaDaSorte(
                "DIS QUE É O TEU CUMPLEANOS E DANCHE O DÍA LIBRE",
                "NA OFICINA"
            )
        )
        questions.add(
            QuestionRuletaDaSorte(
                "CANCIÓN QUE CHE GUSTA E ABOCHORNA POR IGUAL",
                "CUMPLEANOS FELIZ"
            )
        )
        questions.add(
            QuestionRuletaDaSorte(
                "TARTA DE NATA CON FRESAS E GROSELLAS",
                "E QUE CUMPLAS MOITOS MÁIS!"
            )
        )
        questions.add(
            QuestionRuletaDaSorte(
                "CERILLA, FÓSFORO E MECHEIRO",
                "TRES PARA ENCENDER AS VELAS"
            )
        )

        return questions
    }
}