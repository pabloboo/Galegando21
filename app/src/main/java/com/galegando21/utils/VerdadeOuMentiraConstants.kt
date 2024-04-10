package com.galegando21.utils

import com.galegando21.model.QuestionVerdadeOuMentira

object VerdadeOuMentiraConstants {

    const val SCORE = "correct_answers_verdade_ou_mentira"

    fun getQuestions(): MutableList<QuestionVerdadeOuMentira> {
        val questions = mutableListOf<QuestionVerdadeOuMentira>()
        questions.add(QuestionVerdadeOuMentira("Ibai Llanos ofreceulle a Marcial Mouzo retransmitir o seu programa nocturno co nome 'Pensando en Twitch'", 0))
        questions.add(QuestionVerdadeOuMentira("Elon Musk é o fundador da empresa SpaceX", 1))
        questions.add(QuestionVerdadeOuMentira("A Grande Muralha da China é visível dende o espazo", 0))
        questions.add(QuestionVerdadeOuMentira("A Mona Lisa é unha das pinturas máis famosas de Leonardo da Vinci", 1))
        questions.add(QuestionVerdadeOuMentira("A temperatura de conxelación da auga é de 100 graos Celsius", 0))
        questions.add(QuestionVerdadeOuMentira("A cidade de Nova York é a capital dos Estados Unidos da América", 0))
        questions.add(QuestionVerdadeOuMentira("O Sol é unha estrela", 1))
        questions.add(QuestionVerdadeOuMentira("A película \"Forrest Gump\" protagonizada por Tom Hanks gañou seis premios Óscar.", 1))
        questions.add(QuestionVerdadeOuMentira("A lúa orbita arredor da Terra.", 1))
        questions.add(QuestionVerdadeOuMentira("A pirámide de Khufu, tamén coñecida como a Gran Pirámide, é a máis grande das pirámides de Exipto.", 1))
        questions.add(QuestionVerdadeOuMentira("O río Amazonas é o máis longo do mundo.", 1))
        questions.add(QuestionVerdadeOuMentira("O chocolate é tóxico para os cans.", 1))
        questions.add(QuestionVerdadeOuMentira("O planeta máis grande do sistema solar é Xúpiter.", 1))
        questions.add(QuestionVerdadeOuMentira("O oso panda é nativo de China.", 1))
        questions.add(QuestionVerdadeOuMentira("O Titanic afundiu no ano 1912.", 1))
        questions.add(QuestionVerdadeOuMentira("A Mona Lisa foi pintada por Michelangelo.", 0))
        questions.add(QuestionVerdadeOuMentira("O fútbol é o deporte máis popular do mundo.", 1))
        questions.add(QuestionVerdadeOuMentira("A linguaxe de programación Python foi nomeada en honra á serie de televisión \"Monty Python's Flying Circus\".", 1))
        questions.add(QuestionVerdadeOuMentira("O volcán máis alto do mundo é o Ojos del Salado, situado en Chile.", 0))
        questions.add(QuestionVerdadeOuMentira("O 1 de abril é o Día dos Inocentes.", 0))
        questions.add(QuestionVerdadeOuMentira("A Coca-Cola foi creada accidentalmente por un farmacéutico.", 1))
        return questions
    }
}