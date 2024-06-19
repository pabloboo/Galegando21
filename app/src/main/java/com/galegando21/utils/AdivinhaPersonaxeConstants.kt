package com.galegando21.utils

import com.galegando21.model.QuestionAdivinhaPersonaxe

object AdivinhaPersonaxeConstants {
    const val ADIVINHA_PERSONAXE_SCORE = "ADIVINHA_PERSONAXE_SCORE"

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
        questionsList.add(
            QuestionAdivinhaPersonaxe(
                "BERLUSCONI",
                listOf(
                    "Política",
                    "Naceu en 1936",
                    "Gran empresario",
                    "Polémico",
                    "Comprou un clube de fútbol",
                    "Fundou un partido político",
                    "Envolto en escándalos",
                    "Foi primeiro ministro",
                    "Italiano",
                    "Apodado 'Il Cavaliere'",
                    "Silvio",
                )
            )
        )
        questionsList.add(
            QuestionAdivinhaPersonaxe(
                "MALECÓN",
                listOf(
                    "Lugar",
                    "É do siglo XX",
                    "Contriuise por etapas",
                    "Mezcla estilos arquitectónicos",
                    "É unha avenida",
                    "Mide uns 8 km",
                    "'Hasta que se seque el...'",
                    "Xunto ao mar",
                    "É unha zona de pescadores",
                    "Está en Cuba"
                )
            )
        )
        questionsList.add(
            QuestionAdivinhaPersonaxe(
                "CAROLINA",
                listOf(
                    "MULLER",
                    "Estudou na Sorbona",
                    "Ten máis de 60 anos",
                    "Nai de catro fillos",
                    "É avoa",
                    "Icono de elegancia e beleza",
                    "Sale nas revistas",
                    "É princesa",
                    "Casouse con un playboy",
                    "Naceu en un palacio",
                    "Filla de Grace Kelly"
                )
            )
        )
        questionsList.add(
            QuestionAdivinhaPersonaxe(
                "THELMA E LOUISE",
                listOf(
                    "Pelicula",
                    "De 1991",
                    "Estadounidense",
                    "Road movie",
                    "Óscar ao mellor guión orixinal",
                    "Dirixida por Ridley Scott",
                    "Aparece Brad Pitt",
                    "Protagonizada por dúas mulleres",
                    "Son fuxitivas",
                    "Fuxen de vidas aburridas",
                    "Crítica ao machismo"
                )
            )
        )
        questionsList.add(
            QuestionAdivinhaPersonaxe(
                "ROSALÍA DE CASTRO",
                listOf(
                    "Literatura",
                    "Naceu en España",
                    "De 1837",
                    "Precursora do feminismo",
                    "Inspirou a Machado",
                    "Casouse con Manuel Murguía",
                    "Poetisa",
                    "Apareceu en billetes",
                    "Clave na recuperación do galego",
                    "'Cantares gallegos'",
                    "María Rosalía Rita"
                )
            )
        )
        questionsList.add(
            QuestionAdivinhaPersonaxe(
                "JAMES BOND",
                listOf(
                    "Personaxe de ficción",
                    "Espía",
                    "Creado por Ian Fleming",
                    "Traballa para MI6",
                    "Viste traxe",
                    "Ten licencia para matar",
                    "Conduce coches de luxo",
                    "Bebe vodka martini",
                    "Agente 007",
                    "Sean Connery",
                    "Roger Moore"
                )
            )
        )
        questionsList.add(
            QuestionAdivinhaPersonaxe(
                "TÁMESIS",
                listOf(
                    "Xeografía",
                    "É un río",
                    "Está en unha isla",
                    "Viven salmóns",
                    "Monet pintouno",
                    "Cruzano moitas pontes",
                    "Estivo contaminado",
                    "Desemboca no mar do Norte",
                    "O 'London Eye' está ao seu carón",
                    "Famoso pola súa regata",
                    "Pasa por Londres"
                )
            )
        )
        return questionsList
    }
}