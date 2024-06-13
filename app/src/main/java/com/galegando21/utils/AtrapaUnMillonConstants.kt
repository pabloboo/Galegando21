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
        questions.add(
            QuestionAtrapaUnMillon(
                "Por que Santa Claus vai de vermello?",
                "Pola marca 'Coca Cola'",
                "Pola marca Sprite sabor a fresa",
                "Porque o seu fillo se chama 'Rojo'",
                "Para que se lle vexa pola noite",
                1)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Cantas parellas de animais meteu Moisés na arca?",
                "0",
                "40",
                "42",
                "45",
                1)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Cal de estos nomes de xogadores de fútbol ten algo que ver con coser?",
                "Puyol",
                "Ronaldo",
                "Piqué",
                "Casillas",
                3)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Cal de estos nomes é un cereal?",
                "Azufre",
                "Centeno",
                "Remolacha",
                "Bifis",
                2)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Dos seguintes equipos cal é o que máis veces gañou o mundial?",
                "Italia",
                "Argentina",
                "Francia",
                "Brasil",
                4)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Cal ten sepia nos seus ingredientes?",
                "Chocos fritos",
                "Patatas bravas",
                "Arroz con costra",
                "Cañas",
                1)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Cal é unha canción de ABBA?",
                "Cuidadín",
                "Help!",
                "SOS",
                "Vigila!",
                3)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Que operaban os médicos musulmáns da Península Ibérica fai máis de 800 anos?",
                "Cataratas",
                "Peitos",
                "Juanetes",
                "Arrugas",
                1)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Que dixo Mickey Mouse a primeira vez que o oímos falar?",
                "Hot dogs",
                "Fast food",
                "Manteca colorá",
                "Apple pies",
                1)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Que animal nace sempre macho e pode volverse femia?",
                "Paxaro carpinteiro",
                "Araña lobo",
                "Pez paiaso",
                "Elefante africano",
                3)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Como se chama a noiva de Naranjito?",
                "Clementina",
                "Mandarina",
                "Nectarina",
                "Piña",
                1)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Quen lle poñía voz a Ruperta no concurso 'Un, dos, tres'?",
                "Irmáns Hurtado",
                "Paula Vázquez",
                "Chicho Ibáñez Serrador",
                "Mayra Gómez Kemp",
                3)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Como se chama a revista na que traballan os persoaxes da serie 'Yo soy Bea'?",
                "Bulevar 21",
                "Hola",
                "Gente",
                "AR",
                1)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Nome do cabalo de Don Quijote",
                "Rocinante",
                "Babieca",
                "Rucio",
                "Bucéfalo",
                1)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Con que se atrae a boa sorte?",
                "Casándose en martes 13",
                "Con unha ferradura",
                "Con un espello roto",
                "Con un gato negro",
                2)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Como se chama o protagonista da serie Smallville?",
                "Peter Parker",
                "Clark Kent",
                "Lobezno",
                "Hellboy",
                2)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Como se chama o heroe de Bichos?",
                "Flea",
                "Flik",
                "Francis",
                "Heimlich",
                2)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Quen foi o primeiro humano en pisar a lúa na misión do Apolo 11?",
                "Georgi Dobrovolski",
                "Neil Armstrong",
                "Laika",
                "Yuri Gagarin",
                2)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Quen escribiu 'Cien años de soledad'?",
                "Boris Izaguirre",
                "Camilo José Cela",
                "Gabriel García Márquez",
                "Arturo Pérez Reverte",
                3)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Donde está o estadio de fútbol Maracaná?",
                "Italia",
                "Colombia",
                "Portugal",
                "Brasil",
                4)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Como se chaman os protagonistas de 'La Celestina'?",
                "Calisto e Melibea",
                "Ana e Miguel",
                "Romeo e Julieta",
                "Adán e Eva",
                1)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Que é un 'cachopo'?",
                "Unha prenda de roupa",
                "Unha especie de peixe",
                "Unha comida",
                "Unha bebida",
                3)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Quen era o presidente de Bolivia en 2016?",
                "Álvaro Uribe Vélez",
                "Evo Morales",
                "Felipe Calderón",
                "Hugo Chávez",
                2)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Cal é a comida favorita de Winnie the Pooh?",
                "A mel",
                "As grosellas",
                "As mazás",
                "As moras",
                1)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Cal é a unidade básica de lonxitude según o Sistema Internacional de Unidades?",
                "Mol",
                "Candela",
                "Kevin",
                "Metro",
                4)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "En cantos tempos se divide un partido de fútbol?",
                "4",
                "5",
                "2",
                "1",
                3)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "A que xénero pertence El Quijote?",
                "Novela",
                "Poesía",
                "Cómic",
                "Teatro",
                1)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Quen tivo unha experiencia relixiosa nos 90?",
                "Ismael Serrano",
                "David Bustamante",
                "Miguel Poveda",
                "Enrique Iglesias",
                4)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Que tipo de serie é Bonanza?",
                "Bélica",
                "De colexiales",
                "De superheroes",
                "Wester",
                4)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "De que país son típicos os tacos?",
                "España",
                "México",
                "Ecuador",
                "Irlanda",
                2)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Quen é David Hasselhoff en 'Los vigilantes de la playa'?",
                "Hobie Buchannon",
                "Jill Riley",
                "Don Thorpe",
                "Mitch Buchannan",
                4)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Cantas horas ten unha semana?",
                "24",
                "52",
                "168",
                "1440",
                3)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Quen foi o marido de Juana la Loca?",
                "Juan de Trastamara",
                "Juan de Austria",
                "Felipe el Hermoso",
                "Duque de Alba",
                3)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Quen foi o dictador que seguiu ao goberno de Salvador Allende en Chile?",
                "Augusto Pinochet",
                "Anastasio Somoza García",
                "Jorge Rafael Videla",
                "Manuel Noriega",
                1)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Como se chama o satélite da Terra?",
                "Titán",
                "Júpiter",
                "Lúa",
                "Sol",
                3)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Que cadea de televisión está orientada a un público infantil e xuvenil?",
                "Disney Channel",
                "Fashion TV",
                "MTV",
                "Bloomberg TV",
                1)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "De que cor é o cinturón que leva un principiante en Karate?",
                "Laranxa",
                "Amarelo",
                "Branco",
                "Negro",
                3)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Como se chama o primeiro álbum de Kiko e Shara?",
                "Cántame",
                "Se me olvidó tu nombre",
                "Kiko y Shara",
                "Entre 2 y un mes",
                3)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Que instrumento toca o bardo da aldea Gala de Asterix?",
                "Trompeta",
                "Guitarra",
                "Tambor",
                "Lira",
                4)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Que tipo de coches fabrica Land Rover?",
                "Deportivos",
                "Descapotables",
                "Todoterreos",
                "Motocicletas",
                3)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "En que fecha chegou Cristóbal Colón a América por primeira vez?",
                "12 de outubro de 1429",
                "8 de setembro de 1492",
                "12 de outubro de 1492",
                "8 de setembro de 1429",
                3)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Quen formaba o dúo humorístico 'Tip y Coll' xunto con José Luis Coll?",
                "Emilio Aragón",
                "Luís Sánchez Polack",
                "Santiago Segura",
                "O Gran Wyoming",
                2)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Como se chama o boneco vaqueiro de Toy Story?",
                "Woody",
                "Stitch",
                "Buzz Lightyear",
                "Lilo",
                1)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Como se chama o druida da aldea gala na que viven Asterix e Obelix?",
                "Ordenalfabetix",
                "Asurancetúrix",
                "Abraracúrcix",
                "Panorámix",
                4)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "De que país é o grupo 'The Beatles'?",
                "Francia",
                "Reino Unido",
                "Australia",
                "Estados Unidos",
                2)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Quen é o protagonista masculino de 'Californication'?",
                "Ted Danson",
                "David Duchovny",
                "Michael Landon",
                "David Hasselhoff",
                2)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Como se chaman os pais dos 101 dálmatas?",
                "Roger e Anita",
                "Jafar e Jasmine",
                "Perdita e Pongo",
                "Jago e Faline",
                3)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Quen era o piloto e loco do equipo A ('Los Magníficos')?",
                "H.M. Murdock",
                "M.A. Baracus",
                "Templon Peck",
                "John 'Hanibal' Smith",
                1)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Como se chama a cazavampiros que ten a súa propia serie?",
                "Dana Scully",
                "Allison Dubois",
                "Buffy",
                "Angela Channing",
                3)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Como se chama o noivo de Barbie?",
                "Clark",
                "Elyon",
                "Peter Cook",
                "Ken",
                4)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Como se chama a proba final do concurso Pasapalabra?",
                "De par en par",
                "O pulsador",
                "O rosco",
                "A escaleira",
                3)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "En que estación do ano os días son máis longos?",
                "Inverno",
                "Outono",
                "Primavera",
                "Verán",
                4)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Quen cantou 'La raja de tu falda'?",
                "The Cure Spain",
                "Paulina Rubio",
                "Estopa",
                "Oasis",
                3)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "En que continente se encontra o río Niger?",
                "En África",
                "En Asia",
                "En América",
                "En Oceanía",
                1)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "De quen é a canción 'One'?",
                "Metallica",
                "Estopa",
                "Alaska",
                "Mecano",
                1)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Cal é o cantante de Reggaetón?",
                "Shakria",
                "Daddy Yankee",
                "Ricky Martin",
                "Enrique Iglesias",
                2)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Como se chama o único satélite natural da Terra?",
                "Landsat",
                "Meteosat",
                "RapidEye",
                "Lúa",
                4)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Que clase de animais eran Tom e Jerry?",
                "Un can e un gato",
                "Un castor e un oso",
                "Un zorro e un ganso",
                "Un gato e un rato",
                4)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "A quen unha boa árbore se arrima...",
                "asomase pola tarima",
                "boa sombra lle cobija",
                "ven un can e orinalle",
                "non lle ve a veciña",
                2)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "En que destacaba o protagonista de Kung fu?",
                "Na medicina",
                "Guerra de guerrillas",
                "Artes marciais",
                "No baile",
                3)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Como se chama o máis polémico xurado de Operación Triunfo - España?",
                "Alejandro Abad",
                "Risto Mejide",
                "Noemí Galera",
                "Javier Llano",
                2)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "De que cor é a bandeira de Xapón?",
                "Branca e vermella",
                "Fucsia e marrón",
                "Amarela e granate",
                "Azul e verde",
                1)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Actor protagonista da comedia 'La Máscara'?",
                "Daniel-Day Lewis",
                "Benny Hill",
                "Jim Carrey",
                "Viggo Mortensen",
                3)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Que tipo de modelismo se practica con avións?",
                "Modelismo ferroviario",
                "Automodelismo",
                "Doramas",
                "Aeromodelismo",
                4)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Segundo a novela de Bram Stoker de donde é Drácula?",
                "China",
                "Francia",
                "Transilvania",
                "Estados Unidos",
                3)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "A quen adopta Lilo como mascota?",
                "A Rayo McQueen",
                "A Valiant",
                "A Nemo",
                "A Stitch",
                4)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Cal é o símbolo do Astato?",
                "Tt",
                "Stt",
                "tt",
                "At",
                4)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "De que país foi colonia Australia?",
                "Inglaterra",
                "España",
                "Portugal",
                "Francia",
                1)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Quen foi o campeón mundial de fórmula 1 en 2005?",
                "Alain Prost",
                "Niki Lauda",
                "Fernando Alonso",
                "Ayton Senna",
                3)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "En que clube debutou o futbolista David Beckham?",
                "Real Madrid",
                "FC Barcelona",
                "Manchester United",
                "Los Angeles Galaxy",
                3)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Cantos tentáculos ten un pulpo?",
                "8",
                "10",
                "4",
                "5",
                1)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Cal é o símbolo do Hidróxeno?",
                "Hi",
                "Hdrg",
                "H",
                "Hd",
                3)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Que símbolo ten o ouro?",
                "Au",
                "Or",
                "Oo",
                "O",
                1)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Cal é o símbolo do Titanio?",
                "Talas",
                "Ti",
                "Ttn",
                "Tt",
                2)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Cantos días ten o mes de xaneiro en ano bisiesto?",
                "28",
                "30",
                "29",
                "31",
                4)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Que medida de volumen anglosaxona equivale a 4'546 litros?",
                "Galón",
                "Yarda",
                "Pé",
                "Libra",
                1)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Entre que outras cousas destacou Galileo Galilei?",
                "Xeoloxía",
                "Robótica",
                "Medicina",
                "Astronomía",
                4)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Quen inventou a bombilla?",
                "James Bowman",
                "Thomas Alva Edison",
                "Joseph Swan",
                "Alexander Graham Bell",
                2)
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
        questions.add(
            QuestionAtrapaUnMillon(
                "En que monumento tes que subir máis escalóns?",
                "London Eye",
                "Empire State",
                "Big Ben",
                "-",
                2)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "En que xogo de mesa es que conseguir a bandeira do enemigo?",
                "Estratego",
                "Risk",
                "Captura a bandeira",
                "-",
                1)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Que lle pasa aos astronautas que dormen no espazo?",
                "Roncan menos",
                "Dormen máis",
                "Soñan con Angelina Jolie",
                "-",
                1)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Para aforrar auga, canto recomenda a OMS que dure a ducha diaria?",
                "Entre 3 e 5 minutos",
                "Uns 13 minutos",
                "Media hora",
                "-",
                1)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Que afirmación sobre o banco de España é mentira?",
                "Foi unha cárcere",
                "En caso de roubo, inundase",
                "Alberga obras de arte",
                "-",
                1)
        )
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
        questions.add(
            QuestionAtrapaUnMillon(
                "Que existiu de verdade?",
                "Rita a Cantaora",
                "Perico dos palotes",
                "-",
                "-",
                1)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Según o record Guiness, que tivo un home durante 68 anos seguidos?",
                "Ataque de risa",
                "Ataque de hipo",
                "-",
                "-",
                2)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Cal foi antes unha característica distintiva dos autobuses de Londres?",
                "Ter dous pisos",
                "Ser vermellos",
                "-",
                "-",
                2)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Cal foi unha operación da segunda Guerra Mundial?",
                "Cobra",
                "Anaconda",
                "-",
                "-",
                1)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Cal é o nome do primeiro satélite radar español destinado a observar a Terra?",
                "GUERRA",
                "PAZ",
                "-",
                "-",
                2)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Cal é un afluente do río Ebro?",
                "Agujeta",
                "Tirón",
                "-",
                "-",
                2)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Quen deseñou unha baralla de Tarot?",
                "Dalí",
                "Picasso",
                "",
                "-",
                1)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Que alimento contén cianuro?",
                "Cebola",
                "Faba",
                "-",
                "-",
                2)
        )
        return questions
    }
}