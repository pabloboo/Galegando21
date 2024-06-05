package com.galegando21.utils

import com.galegando21.model.QuestionAdivinhaPersonaxe

object AdivinhaPersonaxeConstants {

    fun getQuestions() : MutableList<QuestionAdivinhaPersonaxe> {
        var questionsList: MutableList<QuestionAdivinhaPersonaxe> = mutableListOf()
        questionsList.add(
            QuestionAdivinhaPersonaxe(
                "HOMER SIMPSON",
                listOf(
                    "Personaxe de serie de animación",
                    "Personaxe de Matt Groening",
                    "Amarelo",
                    "Cabeza de familia",
                    "Traballa nunha central nuclear",
                    "Vive en Springfield",
                    "Casado con Marge",
                    "Personaxe de The Simpsons",
                )
            )
        )
        questionsList.add(
            QuestionAdivinhaPersonaxe(
                "MICKEY MOUSE",
                listOf(
                    "Personaxe de debuxos animados",
                    "Rato",
                    "Creado por Walt Disney",
                    "Viste pantalóns vermellos",
                    "Ten unha noiva chamada Minnie",
                )
            )
        )
        questionsList.add(
            QuestionAdivinhaPersonaxe(
                "SPIDERMAN",
                listOf(
                    "Personaxe de cómic",
                    "Vive en Nova York",
                    "Creado por Stan Lee",
                    "Viste de azul e vermello",
                    "Lanza teas de araña",
                    "Ten unha tía chamada May",
                )
            )
        )
        questionsList.add(
            QuestionAdivinhaPersonaxe(
                "VOLDEMORT",
                listOf(
                    "Personaxe de unha saga",
                    "Ten unha variña",
                    "Alimentase de sangue de unicornio",
                    "Malvado",
                    "Non ten nariz",
                    "Tom Ryddle",
                    "O que non debe ser nomeado",
                    "É Lord",
                    "J.K. Rowling",
                    "Harry Potter",
                )
            )
        )
        return questionsList
    }
}