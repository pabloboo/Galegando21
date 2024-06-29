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
        questions.add(
            QuestionAtrapaUnMillon(
                "Cal é o país máis grande do mundo?",
                "China",
                "Rusia",
                "Canadá",
                "-",
                2)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Que tipo de animal é o delfín?",
                "Peixe",
                "Mamífero",
                "Réptil",
                "-",
                2)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Cal é o planeta máis próximo ao sol?",
                "Venus",
                "Marte",
                "Mercurio",
                "-",
                3)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Que órgano do corpo humano produce insulina?",
                "Fígado",
                "Páncreas",
                "Riles",
                "-",
                2)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Que inventor é coñecido pola súa contribución á electricidade?",
                "Albert Einstein",
                "Isaac Newton",
                "Nikola Tesla",
                "-",
                3)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Cal foi a primeira película animada de Disney?",
                "Brancaneves",
                "Pinocho",
                "Cenicienta",
                "-",
                1)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Cal é a capital de Australia?",
                "Sydney",
                "Melbourne",
                "Canberra",
                "-",
                3)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "En que continente se atopa o deserto do Sahara?",
                "América do Norte",
                "África",
                "Asia",
                "-",
                2)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Quen foi o primeiro presidente dos Estados Unidos?",
                "Thomas Jefferson",
                "Abraham Lincoln",
                "George Washington",
                "-",
                3)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Cal é o metal máis lixeiro?",
                "Aluminio",
                "Litio",
                "Plata",
                "-",
                2)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Que país é coñecido como a terra do sol nacente?",
                "China",
                "Corea do Sur",
                "Xapón",
                "-",
                3)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Quen escribiu 'Romeo e Xulieta'?",
                "Charles Dickens",
                "William Shakespeare",
                "Mark Twain",
                "-",
                2)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Cal é o órgano máis grande do corpo humano?",
                "Fígado",
                "Pel",
                "Pulmóns",
                "-",
                2)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "En que país se celebra o Oktoberfest?",
                "Suíza",
                "Bélxica",
                "Alemaña",
                "-",
                3)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Cal é o río máis longo do mundo?",
                "Río Amazonas",
                "Río Nilo",
                "Río Yangtsé",
                "-",
                2)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Que planeta é coñecido como o planeta vermello?",
                "Xúpiter",
                "Saturno",
                "Marte",
                "-",
                3)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Que gas é esencial para a respiración humana?",
                "Nitróxeno",
                "Osíxeno",
                "Dióxido de carbono",
                "-",
                2)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Cal é a moeda oficial do Reino Unido?",
                "Euro",
                "Libra esterlina",
                "Dólar",
                "-",
                2)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "En que cidade está a Estatua da Liberdade?",
                "Washington D.C.",
                "Boston",
                "Nova York",
                "-",
                3)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Que inventor creou a bombilla?",
                "Alexander Graham Bell",
                "Nikola Tesla",
                "Thomas Edison",
                "-",
                3)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Cal é o animal máis grande do mundo?",
                "Elefante africano",
                "Balea azul",
                "Tiburón branco",
                "-",
                2)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Que país é famoso polos seus canguros?",
                "Nova Zelandia",
                "Sudáfrica",
                "Australia",
                "-",
                3)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Cal é a capital de Canadá?",
                "Toronto",
                "Ottawa",
                "Vancouver",
                "-",
                2)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "En que ano comezou a Primeira Guerra Mundial?",
                "1914",
                "1918",
                "1939",
                "-",
                1)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Cal é a capital de Arxentina?",
                "Rosario",
                "Córdoba",
                "Buenos Aires",
                "-",
                3)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Cal é o país máis poboado do mundo?",
                "India",
                "China",
                "Estados Unidos",
                "-",
                1)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Quen pintou 'A Última Cea'?",
                "Claude Monet",
                "Leonardo da Vinci",
                "Pablo Picasso",
                "-",
                2)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Cal é a lingua máis falada no mundo?",
                "Inglés",
                "Español",
                "Mandarín",
                "-",
                3)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Que país gañou a Copa do Mundo de fútbol en 2018?",
                "Brasil",
                "Alemaña",
                "Francia",
                "-",
                3)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Cal é o pico máis alto do mundo?",
                "K2",
                "Monte Everest",
                "Kangchenjunga",
                "-",
                2)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Quen foi o primeiro home en chegar á Lúa?",
                "Buzz Aldrin",
                "Yuri Gagarin",
                "Neil Armstrong",
                "-",
                3)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Cal é o país coñecido como a terra dos mil lagos?",
                "Suecia",
                "Noruega",
                "Finlandia",
                "-",
                3)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Que país é o maior produtor de café do mundo?",
                "Colombia",
                "Brasil",
                "Vietnam",
                "-",
                2)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Cal é o segundo planeta máis próximo ao sol?",
                "Terra",
                "Venus",
                "Marte",
                "-",
                2)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Que instrumento musical ten teclas brancas e negras?",
                "Violín",
                "Piano",
                "Flauta",
                "-",
                2)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Cal é a cor do rubí?",
                "Azul",
                "Verde",
                "Vermello",
                "-",
                3)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Que país é coñecido pola Torre Inclinada de Pisa?",
                "Francia",
                "España",
                "Italia",
                "-",
                3)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Cal é o autor do libro 'Don Quixote'?",
                "Gabriel García Márquez",
                "Miguel de Cervantes",
                "Mario Vargas Llosa",
                "-",
                2)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "En que cidade se atopa a Sagrada Familia?",
                "Madrid",
                "Barcelona",
                "Sevilla",
                "-",
                2)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Cal é o principal ingrediente do guacamole?",
                "Tomate",
                "Cebola",
                "Aguacate",
                "-",
                3)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Que gran muralla é visible desde o espazo?",
                "Muralla de Adriano",
                "Muralla China",
                "Muralla de Berlín",
                "-",
                2)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Cal é a capital de Grecia?",
                "Atenas",
                "Esparta",
                "Salónica",
                "-",
                1)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "En que cidade se atopa o Coliseo?",
                "Atenas",
                "París",
                "Roma",
                "-",
                3)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Cal é o compoñente principal do vidro?",
                "Silicio",
                "Carbono",
                "Osíxeno",
                "-",
                1)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Quen é coñecido como o 'Rei do Rock and Roll'?",
                "Elvis Presley",
                "Michael Jackson",
                "Chuck Berry",
                "-",
                1)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Cal é a capital de Xapón?",
                "Osaka",
                "Tokio",
                "Kioto",
                "-",
                2)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Que animal é coñecido como o 'Rei da Selva'?",
                "Tigre",
                "Elefante",
                "León",
                "-",
                3)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Cal é o metal precioso máis blando?",
                "Ouro",
                "Plata",
                "Platino",
                "-",
                1)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "En que cidade se atopa a Torre de Londres?",
                "Londres",
                "Edimburgo",
                "Dublín",
                "-",
                1)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Quen foi o primeiro presidente de Sudáfrica tras o apartheid?",
                "Desmond Tutu",
                "Nelson Mandela",
                "Jacob Zuma",
                "-",
                2)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "En que cidade se atopa o famoso reloxo Big Ben?",
                "París",
                "Londres",
                "Berlín",
                "-",
                2)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Que gas se atopa en maior proporción na atmosfera terrestre?",
                "Osíxeno",
                "Nitróxeno",
                "Dióxido de carbono",
                "-",
                2)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Cal é a lingua oficial de Brasil?",
                "Español",
                "Portugués",
                "Inglés",
                "-",
                2)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Que molécula é a principal fonte de enerxía para as células?",
                "ADN",
                "ARN",
                "ATP",
                "-",
                3)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Que país ten a maior cantidade de pirámides do mundo?",
                "México",
                "Exipto",
                "Sudán",
                "-",
                3)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Cal é o compoñente principal da auga?",
                "Osíxeno",
                "Hidróxeno",
                "Nitróxeno",
                "-",
                2)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Que inventor é coñecido pola creación da teoría da relatividade?",
                "Isaac Newton",
                "Albert Einstein",
                "Nikola Tesla",
                "-",
                2)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Cal é a moeda oficial de Xapón?",
                "Yuan",
                "Won",
                "Ien",
                "-",
                3)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "En que país se atopa Machu Picchu?",
                "Chile",
                "Perú",
                "Bolivia",
                "-",
                2)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Que ciencia estuda os fósiles?",
                "Xeoloxía",
                "Bioloxía",
                "Paleontoloxía",
                "-",
                3)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Que río pasa por París?",
                "Río Támesis",
                "Río Sena",
                "Río Danubio",
                "-",
                2)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Cal é o instrumento que se utiliza para medir a temperatura?",
                "Termómetro",
                "Barómetro",
                "Higrómetro",
                "-",
                1)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Quen escribiu a novela 'Moby Dick'?",
                "Charles Dickens",
                "Herman Melville",
                "Mark Twain",
                "-",
                2)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Cal é a flor nacional de España?",
                "Rosa",
                "Clavel",
                "Lirio",
                "-",
                2)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "En que ano rematou a Segunda Guerra Mundial?",
                "1945",
                "1939",
                "1950",
                "-",
                1)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Que país é coñecido polo tango?",
                "Brasil",
                "Uruguai",
                "Arxentina",
                "-",
                3)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Quen escribiu a obra 'A Metamorfose'?",
                "Franz Kafka",
                "Fiodor Dostoyevski",
                "Gabriel García Márquez",
                "-",
                1)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Cal é o metal máis caro do mundo?",
                "Ouro",
                "Rodio",
                "Platino",
                "-",
                2)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Que filósofo grego é coñecido polo seu método socrático?",
                "Platón",
                "Aristóteles",
                "Sócrates",
                "-",
                3)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Cal é o compoñente principal da mazá?",
                "Frutosa",
                "Sacarosa",
                "Glucosa",
                "-",
                1)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "En que ano se inaugurou a Torre Eiffel?",
                "1889",
                "1901",
                "1923",
                "-",
                1)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Cal é a montaña máis alta de América do Sur?",
                "Cerro Aconcagua",
                "Monte Fitz Roy",
                "Cerro Torre",
                "-",
                1)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Quen é coñecido como o 'Padre da Computación'?",
                "Alan Turing",
                "Charles Babbage",
                "Steve Jobs",
                "-",
                2)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Que científico enunciou as leis do movemento planetario?",
                "Isaac Newton",
                "Galileo Galilei",
                "Johannes Kepler",
                "-",
                3)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Cal é o autor da pintura 'A noite estrelada'?",
                "Pablo Picasso",
                "Vincent van Gogh",
                "Claude Monet",
                "-",
                2)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Quen é coñecido polo seu teorema sobre triángulos?",
                "Euclides",
                "Pitágoras",
                "Arquímedes",
                "-",
                2)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Cal é o compoñente principal do diamante?",
                "Carbón",
                "Ouro",
                "Platino",
                "-",
                1)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Quen descubriu a penicilina?",
                "Alexander Fleming",
                "Louis Pasteur",
                "Robert Koch",
                "-",
                1)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Cal é o segundo elemento máis abundante no universo?",
                "Helio",
                "Osíxeno",
                "Carbono",
                "-",
                1)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Quen escribiu a obra 'Odissea'?",
                "Homero",
                "Sófocles",
                "Eurípides",
                "-",
                1)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Cal é o número de Euler, un número irracional importante?",
                "π",
                "e",
                "φ",
                "-",
                2)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Quen foi o primeiro ser humano no espazo exterior?",
                "Yuri Gagarin",
                "Neil Armstrong",
                "Buzz Aldrin",
                "-",
                1)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Cal é o nome da estrela máis próxima ao sistema solar?",
                "Sirio",
                "Proxima Centauri",
                "Alpha Centauri",
                "-",
                2)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Quen é coñecido polo seu teorema sobre a conservación da enerxía?",
                "Albert Einstein",
                "Isaac Newton",
                "James Prescott Joule",
                "-",
                3)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Que elemento químico é coñecido como 'vitamina C'?",
                "Ácido ascórbico",
                "Ácido fólico",
                "Ácido cítrico",
                "-",
                1)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Quen descubriu a estrutura do ADN?",
                "Francis Crick e James Watson",
                "Gregor Mendel",
                "Charles Darwin",
                "-",
                1)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Cal é o compoñente principal do petróleo?",
                "Metano",
                "Octano",
                "Hidrocarburos",
                "-",
                3)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Quen foi o primeiro emperador de Roma?",
                "Júlio César",
                "Augusto",
                "Nerón",
                "-",
                2)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Cal é a estrela máis brillante no ceo nocturno?",
                "Sirio",
                "Canopus",
                "Betelgeuse",
                "-",
                1)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Quen inventou o teléfono?",
                "Alexander Graham Bell",
                "Thomas Edison",
                "Nikola Tesla",
                "-",
                1)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Cal é o segundo planeta máis quente do noso sistema solar?",
                "Venus",
                "Mercurio",
                "Marte",
                "-",
                1)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Cal é o metal que ten a temperatura de fusión máis baixa?",
                "Plomo",
                "Aluminio",
                "Mercurio",
                "-",
                3)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Quen foi o primeiro emperador da China unificada?",
                "Sun Tzu",
                "Qin Shi Huang",
                "Mao Zedong",
                "-",
                2)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Cal é a montaña máis alta de África?",
                "Monte Kilimanjaro",
                "Monte Everest",
                "Monte Elbrus",
                "-",
                1)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Quen descubriu a lei da gravitación universal?",
                "Isaac Newton",
                "Galileo Galilei",
                "Johannes Kepler",
                "-",
                1)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Cal é o compoñente principal do ácido clorhídrico?",
                "Cloro",
                "Hidróxeno",
                "Sodio",
                "-",
                1)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Quen escribiu 'A Guerra dos Mundos'?",
                "Jules Verne",
                "H.G. Wells",
                "Arthur C. Clarke",
                "-",
                2)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Quen descubriu o electrón?",
                "Ernest Rutherford",
                "Niels Bohr",
                "J.J. Thomson",
                "-",
                3)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Quen é coñecido como o 'pai da medicina'?",
                "Hippocrates",
                "Galeno",
                "Avicenna",
                "-",
                1)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Quen inventou o primeiro microscopio composto?",
                "Antonie van Leeuwenhoek",
                "Robert Hooke",
                "Zacharias Janssen",
                "-",
                3)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Cal é o compoñente principal do vidro?",
                "Sílice",
                "Carbono",
                "Osíxeno",
                "-",
                1)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Quen é coñecido pola teoría da selección natural?",
                "Gregor Mendel",
                "Charles Darwin",
                "Louis Pasteur",
                "-",
                2)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Quen foi o primeiro científico en demostrar a circulación da sangue no corpo humano?",
                "William Harvey",
                "Andreas Vesalius",
                "Galen",
                "-",
                1)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Cal é o compoñente principal do gas natural?",
                "Metano",
                "Etano",
                "Propano",
                "-",
                1)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Quen descubriu a radioactividade?",
                "Marie Curie",
                "Ernest Rutherford",
                "Henri Becquerel",
                "-",
                3)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Quen inventou o primeiro telégrafo eléctrico?",
                "Samuel Morse",
                "Alexander Graham Bell",
                "Nikola Tesla",
                "-",
                1)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Cal é a unidade fundamental da hereditariedade?",
                "Xene",
                "Cromosoma",
                "Ácido desoxirribonucleico (ADN)",
                "-",
                3)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Quen descubriu a lei dos gases ideais?",
                "Robert Boyle",
                "Joseph Louis Gay-Lussac",
                "Daniel Bernoulli",
                "-",
                2)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Quen é o máximo goleador de todos os tempos na historia da Copa do Mundo de Fútbol?",
                "Pele",
                "Lionel Messi",
                "Miroslav Klose",
                "-",
                1)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Cal é o único equipo en gañar a Copa do Mundo de Fútbol en cinco ocasións?",
                "Italia",
                "Brasil",
                "Alemaña",
                "-",
                2)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Quen foi o primeiro nadador en romper a barreira dos 50 segundos nos 100 metros libres?",
                "Michael Phelps",
                "Ian Thorpe",
                "César Cielo",
                "-",
                3)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Quen é o xogador máis novo en gañar un torneo de tenis do Grand Slam na era aberta?",
                "Boris Becker",
                "Rafael Nadal",
                "Michael Chang",
                "-",
                3)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Cal é o único xogador en gañar a Bota de Ouro en seis ocasións non consecutivas?",
                "Lionel Messi",
                "Cristiano Ronaldo",
                "Thierry Henry",
                "-",
                1)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Quen é o único xogador de esta lista en gañar o premio MVP da NBA en cinco ocasións non consecutivas?",
                "LeBron James",
                "Michael Jordan",
                "Wilt Chamberlain",
                "-",
                2)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Cal é o nadador máis condecorado nos Xogos Olímpicos?",
                "Mark Spitz",
                "Michael Phelps",
                "Ian Thorpe",
                "-",
                2)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Quen foi o primeiro xogador de golf en gañar os catro grandes torneos na mesma temporada?",
                "Jack Nicklaus",
                "Tiger Woods",
                "Bobby Jones",
                "-",
                3)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Cal é o único xogador en gañar o premio Balón de Ouro en oito ocasións?",
                "Lionel Messi",
                "Cristiano Ronaldo",
                "Johan Cruyff",
                "-",
                1)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Quen dirixiu a película '2001: A Space Odyssey'?",
                "Stanley Kubrick",
                "Steven Spielberg",
                "George Lucas",
                "-",
                1)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Quen gañou o premio ao mellor actor no Oscar por interpretar a Joker en 'Joker'?",
                "Joaquin Phoenix",
                "Heath Ledger",
                "Jared Leto",
                "-",
                1)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Cal foi a primeira película de Pixar Animation Studios?",
                "Toy Story",
                "Finding Nemo",
                "Monsters, Inc.",
                "-",
                1)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Quen é o director da triloxía 'The Lord of the Rings'?",
                "Peter Jackson",
                "Christopher Nolan",
                "James Cameron",
                "-",
                1)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Quen gañou o premio ao mellor director no Oscar por 'Gravity'?",
                "Alfonso Cuarón",
                "Guillermo del Toro",
                "Alejandro González Iñárritu",
                "-",
                1)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Quen gañou o premio ao mellor actor no Oscar por 'The Revenant'?",
                "Leonardo DiCaprio",
                "Brad Pitt",
                "Matthew McConaughey",
                "-",
                1)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Quen gañou o premio ao mellor director no Oscar por 'The Shape of Water'?",
                "Guillermo del Toro",
                "Damien Chazelle",
                "Martin McDonagh",
                "-",
                1)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Quen dirixiu a triloxía 'The Dark Knight'?",
                "Christopher Nolan",
                "Tim Burton",
                "Zack Snyder",
                "-",
                1)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Quen gañou o premio ao mellor actor no Oscar por 'Forrest Gump'?",
                "Tom Hanks",
                "Robert De Niro",
                "Denzel Washington",
                "-",
                1)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Quen gañou o premio ao mellor director no Oscar por 'Schindler's List'?",
                "Steven Spielberg",
                "Quentin Tarantino",
                "Martin Scorsese",
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
        questions.add(
            QuestionAtrapaUnMillon(
                "Que é máis grande, un átomo ou unha molécula?",
                "Átomo",
                "Molécula",
                "-",
                "-",
                2)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Quen escribiu 'Os Lusíadas'?",
                "Camões",
                "Saramago",
                "-",
                "-",
                1)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Que famoso pintor surrealista era de Figueres?",
                "Dalí",
                "Picasso",
                "-",
                "-",
                1)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Quen escribiu 'O señor dos aneis'?",
                "Tolkien",
                "Rowling",
                "-",
                "-",
                1)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Cal é o punto máis profundo do oceano Atlántico?",
                "Fosa das Marianas",
                "Fosa das Sandwich",
                "-",
                "-",
                1)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Cal é o compositor da 'Quinta Sinfonía'?",
                "Beethoven",
                "Mozart",
                "-",
                "-",
                1)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Quen foi o primeiro presidente da República de Portugal?",
                "Manuel de Arriaga",
                "Marcelo Rebelo de Sousa",
                "-",
                "-",
                1)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Quen descubriu a estrutura do ADN xunto con Watson?",
                "Crick",
                "Franklin",
                "-",
                "-",
                1)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Quen foi o primeiro explorador en dar a volta ao mundo?",
                "Fernão de Magalhães",
                "Cristóbal Colón",
                "-",
                "-",
                1)
        )

        questions.add(
            QuestionAtrapaUnMillon(
                "Cal é o nome do primeiro satélite artificial da Terra?",
                "Sputnik 1",
                "Explorer 1",
                "-",
                "-",
                1)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Cal é o nome da primeira muller en gañar dous premios Nobel en campos diferentes?",
                "Marie Curie",
                "Rosalind Franklin",
                "-",
                "-",
                1)
        )

        questions.add(
            QuestionAtrapaUnMillon(
                "Quen escribiu 'Ulises'?",
                "James Joyce",
                "Virginia Woolf",
                "-",
                "-",
                1)
        )

        questions.add(
            QuestionAtrapaUnMillon(
                "Cal é o nome do exoplaneta máis próximo á Terra?",
                "Próxima Centauri b",
                "Kepler-186f",
                "-",
                "-",
                1)
        )

        questions.add(
            QuestionAtrapaUnMillon(
                "Quen pintou 'Guernica'?",
                "Pablo Picasso",
                "Salvador Dalí",
                "-",
                "-",
                1)
        )

        questions.add(
            QuestionAtrapaUnMillon(
                "Cal é o libro máis vendido da historia, despois da Biblia?",
                "Don Quijote de la Mancha",
                "El Señor de los Anillos",
                "-",
                "-",
                1)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Quen escribiu 'A Divina Comedia'?",
                "Petrarca",
                "Dante Alighieri",
                "-",
                "-",
                2)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Quen foi o primeiro presidente da democracia española?",
                "Felipe González",
                "Adolfo Suárez",
                "-",
                "-",
                2)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Quen foi o primeiro explorador en chegar ao Polo Sur?",
                "Robert Scott",
                "Roald Amundsen",
                "-",
                "-",
                2)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Quen foi o primeiro emperador romano en converterse ao cristianismo?",
                "Constantino I",
                "Nerón",
                "-",
                "-",
                1)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Cal é o pintor do famoso cuadro 'O nacemento de Venus'?",
                "Leonardo da Vinci",
                "Sandro Botticelli",
                "-",
                "-",
                2)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Cal é o único mamífero que pode voar?",
                "Golondrina",
                "Murciélago",
                "-",
                "-",
                2)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Quen foi o primeiro presidente dos Estados Unidos en ser asasinado no cargo?",
                "Abraham Lincoln",
                "John F. Kennedy",
                "-",
                "-",
                1)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Cal é o nome da estrela máis brillante da constelación de Can Mayor?",
                "Polaris",
                "Sirio",
                "-",
                "-",
                2)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Quen descubriu a radioactividade?",
                "Albert Einstein",
                "Marie Curie",
                "-",
                "-",
                2)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Quen é o creador do universo 'Star Wars'?",
                "George Lucas",
                "Steven Spielberg",
                "-",
                "-",
                1)
        )

        questions.add(
            QuestionAtrapaUnMillon(
                "Cal é a cidade orixinaria dos Rolling Stones?",
                "Londres",
                "Liverpool",
                "-",
                "-",
                1)
        )

        questions.add(
            QuestionAtrapaUnMillon(
                "Quen escribiu a novela 'Juegos del hambre'?",
                "J.K. Rowling",
                "Suzanne Collins",
                "-",
                "-",
                2)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Cal é o nome do famoso videoxogo de conducción con personaxes de Nintendo?",
                "Mario Kart",
                "Gran Turismo",
                "-",
                "-",
                1)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Quen é a autora da saga de libros 'Harry Potter'?",
                "J.K. Rowling",
                "Stephenie Meyer",
                "-",
                "-",
                1)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Quen é o protagonista do xogo 'The Legend of Zelda'?",
                "Mario",
                "Link",
                "-",
                "-",
                2)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Cal é o nome do primeiro filme de Pixar?",
                "Toy Story",
                "Buscando a Nemo",
                "-",
                "-",
                1)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Cal é o equipo de fútbol con máis Ligas de Campións da UEFA?",
                "Barcelona",
                "Real Madrid",
                "-",
                "-",
                2)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Quen é o campión da NBA no ano 2023?",
                "Denver Nuggets",
                "Los Angeles Lakers",
                "-",
                "-",
                1)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Cal é o país que gañou máis medallas de ouro nos Xogos Olímpicos de Tokio 2020?",
                "China",
                "Estados Unidos",
                "-",
                "-",
                2)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Cal é o nome do torneo de tenis que se xoga nos Estados Unidos e é o último Grand Slam do ano?",
                "US Open",
                "Wimbledon",
                "-",
                "-",
                1)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Quen é o xogador con máis puntos anotados na historia da NBA?",
                "Kareem Abdul-Jabbar",
                "LeBron James",
                "-",
                "-",
                2)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Quen gañou a Copa do Mundo de rugby masculino en 2023?",
                "Suráfrica",
                "Inglaterra",
                "-",
                "-",
                1)
        )
        questions.add(
            QuestionAtrapaUnMillon(
                "Cal é o nome do evento deportivo internacional que se celebra cada catro anos, similar aos Xogos Olímpicos, pero para deportes universitarios?",
                "Universiadas",
                "Panamericanos",
                "-",
                "-",
                1)
        )
        return questions
    }
}