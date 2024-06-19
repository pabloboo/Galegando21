package com.galegando21.utils

import com.galegando21.model.QuestionPasagalego

object PasagalegoConstants {

    const val SCORE = "correct_pasagalego_answers"
    const val ERRORS = "error_pasagalego_answers"
    const val TIME = "chronometer_final_time"

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
        questions.add(QuestionPasagalego("Armazón con forma de X que, nos molinos de vento, sostén as telas nas que fai presión o vento", "ASPA"))
        questions.add(QuestionPasagalego("Ciencia que trata dos astros, do seu movemento e das leis que as rexen", "ASTRONOMIA"))
        questions.add(QuestionPasagalego("Interpretar un papel en unha obra teatral ou cinematográfica", "ACTUAR"))
        questions.add(QuestionPasagalego("Cada unha das partes en que se divide unha obra literaria, unha película, unha serie de televisión, etc.", "ACTO"))
        questions.add(QuestionPasagalego("Arte ou oficio de facer vasillas ou outros obxectos de barro cocido", "ALFARERIA"))
        questions.add(QuestionPasagalego("Disco ou conxunto de discos sonoros de longa duración", "ALBUM"))
        questions.add(QuestionPasagalego("Dar palmadas en señal de aprobación, admiración ou apoio", "APLAUDIR"))
        questions.add(QuestionPasagalego("Mecanismo para esparcir un líquido a presión, como a auga para o rego", "ASPERSOR"))
        questions.add(QuestionPasagalego("Gañas de comer", "APETITO"))
        questions.add(QuestionPasagalego("Gran masa de neve que se derrumba dos montes con violencia e estrépito", "AVALANCHA"))
        questions.add(QuestionPasagalego("Aire que se expulsa ao respirar", "ALENTO"))
        questions.add(QuestionPasagalego("Elemento químico metálico, de cor similar á prata e símbolo AL", "ALUMINIO"))
        questions.add(QuestionPasagalego("Natural do país asíatico con capital en Kabul", "AFGANO"))
        questions.add(QuestionPasagalego("Voz inglesa que designa a crema que se utiliza para hidratar a pel despois de tomar o sol", "AFTERSUN"))
        questions.add(QuestionPasagalego("Perfume, olor agradable", "AROMA"))
        questions.add(QuestionPasagalego("Vehículo automóvil de transporte público e traxecto fixo que se emplea habitualmente no servizo urbano", "AUTOBUS"))
        questions.add(QuestionPasagalego("Tomar legalmente como o fillo que non o é biolóxicamente", "ADOPTAR"))
        questions.add(QuestionPasagalego("Soporte en donde se coloca un libro ou unha partitura para poder ler con comodidade", "ATRIL"))
        return questions
    }

    private fun getPasagalegoQuestionsB() : MutableList<QuestionPasagalego> {
        var questions : MutableList<QuestionPasagalego> = mutableListOf()
        questions.add(QuestionPasagalego("Pertencente ou relativo á guerra", "BELICO"))
        questions.add(QuestionPasagalego("Garda civil española", "BENEMERITA"))
        questions.add(QuestionPasagalego("Estudo ou despacho de avogado", "BUFETE"))
        questions.add(QuestionPasagalego("Respirar con ansiedade como resultado dun traballo ou exercicio vigoroso", "BOQUEAR"))
        questions.add(QuestionPasagalego("Persoa que ten por oficio extinguir incendios e realiz outras labores de salvamento", "BOMBEIRO"))
        questions.add(QuestionPasagalego("Molusco mariño comestible, de conchas estriadas casi circulares, que vive enterrado na area", "BERBERECHO"))
        questions.add(QuestionPasagalego("Alteración atmosférica, especialmente no mar, con vento, choiva e relámpagos", "BORRASCA"))
        questions.add(QuestionPasagalego("Agradable ao paladar", "BO"))
        questions.add(QuestionPasagalego("Límite ou extremo da superficie de algo", "BORDE"))
        questions.add(QuestionPasagalego("Voz inglesa de comida de media mañán que se toma para sustituir o almorzo e a comida de mediodía", "BRUNCH"))
        questions.add(QuestionPasagalego("Máquina ou artefacto para impulsar auga ou outro líquido en unha dirección determinada", "BOMBA"))
        questions.add(QuestionPasagalego("Amontonamento de toda clase de cousas, que se fai para resgardarse tras él de unha loita", "BARRICADA"))
        questions.add(QuestionPasagalego("Gorra sen visera, redonda e chata, de lá e xeralmente de unha soa peza", "BOINA"))
        questions.add(QuestionPasagalego("Mover o corpo e os membros con ritmo, seguindo o compás de unha música", "BAILAR"))
        questions.add(QuestionPasagalego("Peza de pan de forma alargada", "BARRA"))
        questions.add(QuestionPasagalego("Almacén de viños", "BODEGA"))
        questions.add(QuestionPasagalego("Gota de líquido acuoso, segregada por unha glándula do ollo, debido a unha irritación de este, ou a unha dor ou unha emoción", "BAGOA"))
        questions.add(QuestionPasagalego("Cunca que se pon aos paxaros na xaula para que beban", "BEBEDEIRO"))
        questions.add(QuestionPasagalego("Conxunto completo de cartas empleado para xogos de azar", "BARALLA"))
        return questions
    }

    private fun getPasagalegoQuestionsC() : MutableList<QuestionPasagalego> {
        var questions : MutableList<QuestionPasagalego> = mutableListOf()
        questions.add(QuestionPasagalego("Total privación do sentido da vista", "CEGUEIRA"))
        questions.add(QuestionPasagalego("Cama para bebés, con bordos altos ou varandas laterais, dispostas ás veces para que se poida balancear", "CUNA"))
        questions.add(QuestionPasagalego("Interrupción dunha emisión de radio ou televisión para publicidade", "CORTE"))
        questions.add(QuestionPasagalego("Décimo signo do zodiaco cuxo símbolo é unha cabra", "CAPRICORNIO"))
        questions.add(QuestionPasagalego("Gastar enerxía ou un producto enerxético", "CONSUMIR"))
        questions.add(QuestionPasagalego("Persoa que leva a hombre a ombros un paso de unha procesión", "COSTALEIRO"))
        questions.add(QuestionPasagalego("Parte do intestino groso situada entre o cego e o recto", "COLON"))
        questions.add(QuestionPasagalego("Parte de un edificio que está máix baixa que o chan e serve para darlle solidez", "CIMENTO"))
        questions.add(QuestionPasagalego("Presentarse unha persoa ante un órgano público para un acto legal, especialmente ante un xuíz ou un tribunal", "COMPARECER"))
        questions.add(QuestionPasagalego("Soltería, especialmente de quen dou o seu voto de castidade", "CELIBATO"))
        questions.add(QuestionPasagalego("Músculo situado na parte anterior do muslo, que intervén na extensión da perna", "CUADRICEPS"))
        questions.add(QuestionPasagalego("En algúns tipos de loita, como o judo, movemento para inmovilizar ou derribar a un contrario", "CHAVE"))
        questions.add(QuestionPasagalego("Coloquialmente, sono corto e lixeiro", "CABEZADA"))
        questions.add(QuestionPasagalego("Cada un dos osos largos e arqueados que parten da columna vertebral", "COSTILLA"))
        questions.add(QuestionPasagalego("Área ou superficie plana contida dentro de unha circunferencia", "CIRCULO"))
        questions.add(QuestionPasagalego("Artefacto que se move por propulsión a chorro empleado en vehículos espaciais ou como arma de guerra", "COHETE"))
        questions.add(QuestionPasagalego("En algúns países europeos, como Alemaña, xefe do goberno", "CANCILLER"))
        questions.add(QuestionPasagalego("Plato composto de lonchas moi finas de carne ou pescado crudo, condimentadas con especias", "CARPACHO"))
        questions.add(QuestionPasagalego("Instrumento de debuxo que se emplea para trazar arcos de circunferencia", "COMPAS"))
        questions.add(QuestionPasagalego("Porción de unha masa feita con bechamel e un alimento picado que se come rebozada en ovo e pan raiado e frita", "CROQUETA"))
        return questions
    }

    private fun getPasagalegoQuestionsD() : MutableList<QuestionPasagalego> {
        var questions : MutableList<QuestionPasagalego> = mutableListOf()
        questions.add(QuestionPasagalego("Comercialización e reparto de un produto desde a súa saída de produción ata que chega ao vendedor", "DISTRIBUCION"))
        questions.add(QuestionPasagalego("Conxunto de descendentes dunha persoa ou animal", "DESCENDENCIA"))
        questions.add(QuestionPasagalego("Documento expedido por un centro educativo e que acredite un título ou título académico", "DIPLOMA"))
        questions.add(QuestionPasagalego("Ocultar algo para que non se vexa ou non se note algo material ou cousas como intencións, accións, calidades, defectos", "DISIMULAR"))
        questions.add(QuestionPasagalego("Mezcla explosiva de nitroglicerina con outras substancias", "DINAMITA"))
        questions.add(QuestionPasagalego("Pañal desbotable de celulosa", "DODOTIS"))
        questions.add(QuestionPasagalego("Unidade de lonxitude que equivale a unha décima parte de un metro", "DECIMETRO"))
        questions.add(QuestionPasagalego("Explicación do significado de unha palabra en un diccionario", "DEFINICION"))
        questions.add(QuestionPasagalego("Vencer a un  amigo ou a un rival", "DERROTAR"))
        questions.add(QuestionPasagalego("Calidade especial ou habilidade para facer algo", "DESTREZA"))
        questions.add(QuestionPasagalego("Modalidade de esquí alpino con unha baixxada en velocidade por un recorrido que pasa por unha serie de portas", "DESCENSO"))
        questions.add(QuestionPasagalego("Punto central de un blanco de tiro", "DIANA"))
        questions.add(QuestionPasagalego("Capa inferior e máis grosa da pel", "DERMIS"))
        questions.add(QuestionPasagalego("Persoa comisionada por outra para actuar no seu nome", "DELEGADO"))
        questions.add(QuestionPasagalego("Desviación de unha nave do seu rumbo por efecto do vento, mar ou da corrente", "DERIVA"))
        questions.add(QuestionPasagalego("Nome do personaxe bíblico que suscitou o amor de Sansón e lle arrebatou a forza tras cortarlle a cabeleira", "DALILA"))
        questions.add(QuestionPasagalego("Unidade de lonxitude equivalente a dez metros", "DECAMETRO"))
        return questions
    }

    private fun getPasagalegoQuestionsE() : MutableList<QuestionPasagalego> {
        var questions : MutableList<QuestionPasagalego> = mutableListOf()
        questions.add(QuestionPasagalego("Superficie brillante que reflexa imaxes", "ESPELLO"))
        questions.add(QuestionPasagalego("Mesa de despacho", "ESCRITORIO"))
        questions.add(QuestionPasagalego("Prato que se come ao comezo dunha comida", "ENTRANTE"))
        questions.add(QuestionPasagalego("Programa cultural da Televisión de Galicia que se emitiu en 1988 dirixido e presentado por Luis Álvarez Pousa dedicado ás novas correntes artísticas e de pensamento en Galicia e no mundo", "EXTRAMUROS"))
        questions.add(QuestionPasagalego("Príncipe ou caudillo árabe", "EMIR"))
        questions.add(QuestionPasagalego("Mecanismo para subir e baixar os cristales das ventanillas dos automóviles", "ELEVALUNAS"))
        questions.add(QuestionPasagalego("Fantasma ou figura irreal, normalmente terrorífica, que alguén ve ou se imaxina como se fose real", "ESPECTRO"))
        questions.add(QuestionPasagalego("Cantidade de diñeiro que se entrega como primeiro pago de unha compra a prazos", "ENTRADA"))
        questions.add(QuestionPasagalego("Apelido do científico que foi director xeral do Organismo Internacional de Enerxía Atómica de 1961 a 1981", "EKLUND"))
        questions.add(QuestionPasagalego("Trozo de árbore ou de madeira podrido ou carcomido", "ESCARZO"))
        questions.add(QuestionPasagalego("Parte da filosofía que trata o ben e o mal nos actos humanos", "ETICA"))
        questions.add(QuestionPasagalego("Pez mariño de gran tamaño, que remonta os ríos para desovar e con cuxas ovas se prepara o caviar", "ESTURION"))
        questions.add(QuestionPasagalego("Periodo de tempo que se distingue polos feitos históricos nel acaecidos e polas súas formas de vida", "EPOCA"))
        questions.add(QuestionPasagalego("Persoa que desempeña un traballo a cambio de un soldo", "EMPREGADO"))
        questions.add(QuestionPasagalego("Conmemoración dun feito notable que se fai no seu aniversario", "EFEMERIDES"))
        questions.add(QuestionPasagalego("Manifestación dos afectos e das emocións por medio da xesticulacion", "EXPRESION"))
        questions.add(QuestionPasagalego("Seducir a alguén con adulacións e mentiras", "ENGANAR"))
        questions.add(QuestionPasagalego("Raíz ou tronco de unha familia ou linaxe", "ESTIRPE"))
        questions.add(QuestionPasagalego("Na filosofía grega, cada un dos catro principios que compoñen o universo: terra, auga, aire e foto", "ELEMENTO"))
        return questions
    }

    private fun getPasagalegoQuestionsF() : MutableList<QuestionPasagalego> {
        var questions : MutableList<QuestionPasagalego> = mutableListOf()
        questions.add(QuestionPasagalego("Podar unha árbore moi cerca da parte na que termina o tronco e empezan as ramas", "FRADAR"))
        questions.add(QuestionPasagalego("Terminación e finalización de algo", "FINAL"))
        questions.add(QuestionPasagalego("Movemento no que algo se curva ou dobra", "FLEXION"))
        questions.add(QuestionPasagalego("Título da película de 1967, dirixida por Jack Gold, sobre o estado de Bihar", "FAMINE"))
        questions.add(QuestionPasagalego("Con fame", "FAMENTO"))
        questions.add(QuestionPasagalego("Mecha de pelo que cae sobre a fronte", "FLEQUILLO"))
        questions.add(QuestionPasagalego("Axuda ou servizo que se presta a alguén gratuitamente", "FAVOR"))
        questions.add(QuestionPasagalego("Golfo estreito e profundo, entre montañas de ladeiras abruptas, habitual nas costas de Noruega", "FIORDO"))
        questions.add(QuestionPasagalego("Relación de películas de un mesmo cineasta, actor, director ou guionista", "FILMOGRAFIA"))
        questions.add(QuestionPasagalego("Nos xogos de cartas, envite falso que se fai para desorientar ou atemorizar ao contrario", "FAROL"))
        questions.add(QuestionPasagalego("Instrumento musical de vento en forma de tubo con buratos que se tapan con dedos ou chaves", "FRAUTA"))
        questions.add(QuestionPasagalego("Cantidade de diñeiro ou ben material que se entrega como garantía do cumprimento de unha obrigación", "FIANZA"))
        questions.add(QuestionPasagalego("Apelido do clérigo que, en 1836, fundou unha pioneira escola de enfermería en Alemania", "FLIEDNER"))
        questions.add(QuestionPasagalego("Apelido do arquitecto que deseñou a Villa Wenhold na cidade alemana de Bremen", "FAHRENKAMP"))
        questions.add(QuestionPasagalego("Acróbata que realiza exercicios sobra a corda frouxa ou o alambra", "FUNAMBULISTA"))
        questions.add(QuestionPasagalego("En química, introducir ácido fosfórico en unha molécula", "FOSFORILAR"))
        questions.add(QuestionPasagalego("Tratamento das lesións por medios naturais como a auga ou mecánicos como o masaxe", "FISIOTERAPIA"))
        questions.add(QuestionPasagalego("Establecemento donde se preparan e se venden medicamentos", "FARMACIA"))
        questions.add(QuestionPasagalego("Vivenda na que reside unha persoa ou un familiar", "FOGAR"))
        questions.add(QuestionPasagalego("Que sucede de forma casual e imprevista", "FORTUITO"))
        questions.add(QuestionPasagalego("Persoa apostada en certo lugar que dispara con armas de fogo", "FRANCOTIRADOR"))
        return questions
    }

    private fun getPasagalegoQuestionsG() : MutableList<QuestionPasagalego> {
        var questions : MutableList<QuestionPasagalego> = mutableListOf()
        questions.add(QuestionPasagalego("Ave doméstica da familia dos fasiánidos, femia do galo, con crista pequena e vermella e peteiro curto, que se cría para aproveitar os seus ovos e a súa carne para a alimentación", "GALIÑA"))
        questions.add(QuestionPasagalego("Instrumento consistente nun arame forte dobrado nun dos extremos, que serve para abrir fechos en substitución das chaves", "GANZUA"))
        questions.add(QuestionPasagalego("Río de Etiopía que desemboca no lago Tana, en cuxa orilla están os manantiais de auga termal de Wanzaye", "GUMARA"))
        questions.add(QuestionPasagalego("En profesións, servicio que asegura a continuidade das prestacións básicas fora do seu horario habitual", "GARDA"))
        questions.add(QuestionPasagalego("Abundancia de carnes e graxas nas persoas e animais", "GORDURA"))
        questions.add(QuestionPasagalego("Provincia insular de Filipinas situada entre as islas de Panay e Negros", "GUIMARAS"))
        questions.add(QuestionPasagalego("Nome da dinastía do norte da India á que pertenceu o rei Chandradeva", "GAHADAVALA"))
        questions.add(QuestionPasagalego("Dicese da cor vermella oscura", "GRANATE"))
        questions.add(QuestionPasagalego("Ácaro diminuto, de forma ovalada, que se agarra ao corpo de certos mamíferos para chuparlles o sangue", "GARRAPATA"))
        questions.add(QuestionPasagalego("Inscripción ou debuxo callejero, xeralmente anónimo", "GRAFITI"))
        questions.add(QuestionPasagalego("Home que remaba forzado nas galeiras", "GALEOTE"))
        questions.add(QuestionPasagalego("Título da novela do escritor cubano Juan Abreu, publicada en 2001 e ambientada en un tempo futuro", "GARBAGELAND"))
        questions.add(QuestionPasagalego("Parte da lingüística que estuda os elementos en unha lingua, así como a forma na que estos se combinan", "GRAMATICA"))
        questions.add(QuestionPasagalego("Dicese do canto propio de culto cristiano de notas uniformes no tono e na duración", "GREGORIANO"))
        questions.add(QuestionPasagalego("Apelido do obispo que apoiou a Enrique 8º de Inglaterra contra o papa e foi lord canciller con María Tudor de 1553 a 1555", "GARDINER"))
        questions.add(QuestionPasagalego("Pillo, sinvergonza, folgazán", "GOLFO"))
        questions.add(QuestionPasagalego("Salsa espesa que se prepara con aguacate molido e picado ao que se lle agrega cebola, tomate e chile verde", "GUACAMOLE"))
        return questions
    }

    private fun getPasagalegoQuestionsH() : MutableList<QuestionPasagalego> {
        var questions : MutableList<QuestionPasagalego> = mutableListOf()
        questions.add(QuestionPasagalego("Conxunto de bens que se reciben tras o falecemento do seu posedor, por disposición testamentaria ou legal", "HERDANZA"))
        questions.add(QuestionPasagalego("Apelido do matemático que, en 1976, resolveu, xunto con Kenneth Appel, o teorema das catro cores", "HAKEN"))
        questions.add(QuestionPasagalego("Corno dun animal", "HASTA"))
        questions.add(QuestionPasagalego("Aceptar a autoridade oficial un determinado producto despois de comprobar que cumple certas reglas", "HOMOLOGAR"))
        questions.add(QuestionPasagalego("Animal fantástico composto de macho cabrío e cervo", "HIRCOCERVO"))
        questions.add(QuestionPasagalego("Aparato donde se mezcla o hormigón", "HORMIGONEIRA"))
        questions.add(QuestionPasagalego("Mecanismo formado por varias palas que xiran ao redor de un eixo e que se utiliza para propulsar barcos e aeronaves", "HELICE"))
        questions.add(QuestionPasagalego("Gravamen que pesa sobre unha finca, polo cal está suxeita a responder a unha deuda", "HIPOTECA"))
        questions.add(QuestionPasagalego("Preocupación obsesiva pola propia saúde, particularmente por contraer enfermidades", "HIPOCONDRIA"))
        questions.add(QuestionPasagalego("Varón que chegou á idade adulta", "HOME"))
        questions.add(QuestionPasagalego("Polígono de sete lados", "HEPTAGONO"))
        questions.add(QuestionPasagalego("Cidade estadounidense que é capital do estado de Montana", "HELENA"))
        return questions
    }

    private fun getPasagalegoQuestionsI() : MutableList<QuestionPasagalego> {
        var questions : MutableList<QuestionPasagalego> = mutableListOf()
        questions.add(QuestionPasagalego("Irritable, propenso á ira", "IRASCIBLE"))
        questions.add(QuestionPasagalego("Vivenda hemisférica, feita con bloques de xeo, que os esquimós constrúen no inverno", "IGLU"))
        questions.add(QuestionPasagalego("Falta de deseño avanzado a favor ou en contra de alguén, o que permite proceder coa xustiza", "IMPARCIALIDADE"))
        questions.add(QuestionPasagalego("Conxunto de luces que hai en un lugar para alumbralo ou adornalo", "ILUMINACION"))
        questions.add(QuestionPasagalego("Insignificante por pequeno", "IRRISORIO"))
        questions.add(QuestionPasagalego("Que executa algo a exemplo ou semellanza de outra cousa", "IMITADOR"))
        questions.add(QuestionPasagalego("Dicese da persoa que fala con tono burlón", "IRONICO"))
        questions.add(QuestionPasagalego("Pertencente ou relativo ao emperador", "IMPERIAL"))
        questions.add(QuestionPasagalego("País europeo con capital en Dublín", "IRLANDA"))
        questions.add(QuestionPasagalego("Inxección que se pon en unha dona do corpo afectada por unha dolencia", "INFILTRACION"))
        questions.add(QuestionPasagalego("Falta de coidado e dilixencia en chegar a un lugar ou partir de el á hora convida", "IMPUNTUALIDADE"))
        questions.add(QuestionPasagalego("Observación dos propios estados de ánimo ou de conciencia", "INTROSPECCION"))
        questions.add(QuestionPasagalego("Alteración patolóxica en unha parte do organismo que produce calor, enverllecemento, hinchazón e dor", "INFLAMACION"))
        questions.add(QuestionPasagalego("Parte do corpo na que se xunta o muslo co ventre", "INGLE"))
        questions.add(QuestionPasagalego("Natural do país asiático con capital en Nova Delhi", "INDIO"))
        questions.add(QuestionPasagalego("Emblema distintivo de unha institución que se usa prendido á roupa como mostra de vinculación ou simpatía", "INSIGNIA"))
        questions.add(QuestionPasagalego("Que non se pode percibir cos sentidos ou coa atención", "IMPERCEPTIBLE"))
        return questions
    }

    private fun getPasagalegoQuestionsL() : MutableList<QuestionPasagalego> {
        var questions : MutableList<QuestionPasagalego> = mutableListOf()
        questions.add(QuestionPasagalego("Pedra plana situada a pouca altura do chan, onde se fai o lume para cociñar, nas casas tradicionais, e sobre a que adoita ir colocada unha cambota", "LAREIRA"))
        questions.add(QuestionPasagalego("Símbolo gráfico que serve como emblema dunha empresa ou entidade, dunha marca ou dun produto", "LOGOTIPO"))
        questions.add(QuestionPasagalego("Propiedade rural de grande extensión", "LATIFUNDIO"))
        questions.add(QuestionPasagalego("Institución cultural ou recreativa", "LICEO"))
        questions.add(QuestionPasagalego("Coloquialmente, aposento desarreglado e revolto", "LEONEIRA"))
        questions.add(QuestionPasagalego("Carrito de man para recoller basuras", "LUTOCAR"))
        questions.add(QuestionPasagalego("Autorización que se concede para explotar con fins industriais ou comerciais unha patente ou marca", "LICENZA"))
        questions.add(QuestionPasagalego("Ruiseñor, paxaro coñecido pola veleza do seu canto", "LUCINA"))
        questions.add(QuestionPasagalego("Película de 1934, dirixida por Fritz Lang, que adapta unha obra de teatro do mesmo título de Ferenc Molnár", "LILIOM"))
        questions.add(QuestionPasagalego("Lindar dous territorios ou dous terreos", "LIMITAR"))
        questions.add(QuestionPasagalego("Mate cubano de cor amarela", "LECUSA"))
        questions.add(QuestionPasagalego("Parte inclinada de un monte ou de outra altura do terreo", "LADEIRA"))
        questions.add(QuestionPasagalego("Conxunto de obxectos similares entre sí que se agrupan con un fin determinado", "LOTE"))
        questions.add(QuestionPasagalego("Operación de ciruxía estética que consiste en estirar a pel para eliminar as arrugas", "LIFTING"))
        questions.add(QuestionPasagalego("Executar sen proceso e tumultariamente a un sospeitoso ou a un reo", "LINCHAR"))
        return questions
    }

    private fun getPasagalegoQuestionsM() : MutableList<QuestionPasagalego> {
        var questions : MutableList<QuestionPasagalego> = mutableListOf()
        questions.add(QuestionPasagalego("Ensalada de froitas", "MACEDONIA"))
        questions.add(QuestionPasagalego("Reunión pública na que un ou varios oradores pronuncian discursos de carácter político", "MITIN"))
        questions.add(QuestionPasagalego("Animal híbrido, en femenino, fillo de burro e euga que casi sempre é estéril", "MULA"))
        questions.add(QuestionPasagalego("Grupo de edificios, generalmente cuadrangular, delimitado por calles en todos os seus lados", "MAZA"))
        questions.add(QuestionPasagalego("Cartílago que forma parte da articulación do xeonllo e serve para facilitar o xogo de esta", "MENISCO"))
        questions.add(QuestionPasagalego("Humedecer algo con auga ou outro líquido", "MOLLAR"))
        questions.add(QuestionPasagalego("Representante de un artista ou deportista, ou de unha actividade artística ou deportiva", "MANAGER"))
        questions.add(QuestionPasagalego("Parte interior e máis blanda do pan, rodeada e cuberta por corteza", "MIGA"))
        questions.add(QuestionPasagalego("Gañador en un concurso de beleza masculina", "MISTER"))
        questions.add(QuestionPasagalego("Posto no que se venden retazos de diferentes telas", "MAULERIA"))
        questions.add(QuestionPasagalego("Poñer unha cousa dentro de outra ou no interior de algún sitio", "METER"))
        questions.add(QuestionPasagalego("Árbore da India, tambén chamado 'belérico', cuxos froitos se usan na medicina e tintorería", "MIROBALANO"))
        questions.add(QuestionPasagalego("Substancia doce elaborada polas abellas", "MEL"))
        questions.add(QuestionPasagalego("Dente posterior aos caninos que serve para triturar os alimentos", "MOLAR"))
        questions.add(QuestionPasagalego("Persoa que ten por oficio o manexo de unha locomotora", "MAQUINISTA"))
        questions.add(QuestionPasagalego("Esperar sen facer nada", "MUSAR"))
        questions.add(QuestionPasagalego("Telar do vano de unha porta ou ventá", "MOCHETA"))
        return questions
    }

    private fun getPasagalegoQuestionsN() : MutableList<QuestionPasagalego> {
        var questions : MutableList<QuestionPasagalego> = mutableListOf()
        questions.add(QuestionPasagalego("Natural da comunidade autónoma con capital en Pamplona", "NAVARRO"))
        questions.add(QuestionPasagalego("Información sobre algo que se considera interesante divulgar", "NOTICIA"))
        questions.add(QuestionPasagalego("Dicese da sustancia que produce sopor, relaxación muscular e disminución da sensibilidades, como o opio", "NARCOTICA"))
        questions.add(QuestionPasagalego("Mineral que é un silicato de sodio e aluminio", "NEFELINA"))
        questions.add(QuestionPasagalego("Oficina donde despacha o notario", "NOTARIA"))
        questions.add(QuestionPasagalego("Apelido do xogador de baloncesto que gañou o concurso de mates da ACB na temporada 98-99", "NICKERSON"))
        questions.add(QuestionPasagalego("Sigla de número de identificación fiscal", "NIF"))
        questions.add(QuestionPasagalego("Médico especialista nas enfermidades dos pulmóns ou das vías respiratorias", "NEUMOLOGO"))
        questions.add(QuestionPasagalego("Lazo que se estreita e cerra de modo que, con dificultade, podese soltar por sí só", "NO"))
        questions.add(QuestionPasagalego("Preferencia hacia familiares e amigos á hora de otorgar un empleo ou cargo públicos", "NEPOTISMO"))
        questions.add(QuestionPasagalego("Pertencente ou relativo ás bodas", "NUPCIAL"))
        questions.add(QuestionPasagalego("Espazo de almacenamento de datos en internet, ao que pode acceder o usuario desde calquera dispositivo", "NUBE"))
        questions.add(QuestionPasagalego("Sustancia branca e dura, que reviste o interior de algunhas conchas de moluscos, como a madreperla", "NACAR"))
        questions.add(QuestionPasagalego("Árbore de tronco robusto e madeira dura, cuxo fruto é a noz", "NOGAL"))
        questions.add(QuestionPasagalego("Doctrina filosófica que nega calquer creencia", "NIHILISMO"))
        questions.add(QuestionPasagalego("Xugo azucarado que hai no fondo da corola ou o cáliz das flores que chupan os insectos", "NECTAR"))
        questions.add(QuestionPasagalego("Coloquialmente, interior da garganta", "GAÑOTE"))
        return questions
    }

    private fun getPasagalegoQuestionsÑ() : MutableList<QuestionPasagalego> {
        var questions : MutableList<QuestionPasagalego> = mutableListOf()
        questions.add(QuestionPasagalego("Parque Nacional de España, situado na serra de Toledo, na comunidade autónoma de Castela-A Mancha", "CABAÑEROS"))
        questions.add(QuestionPasagalego("Cóctel feito con augardente de caña, azúcar, xeo picado e zumo de limón", "CAIPIRIÑA"))
        questions.add(QuestionPasagalego("Natural do norte de un país", "NORTEÑO"))
        questions.add(QuestionPasagalego("Sentenza dada en un pleito", "FAZAÑA"))
        questions.add(QuestionPasagalego("Índole ou natureza de alguén, xeralmente negativa", "CALAÑA"))
        questions.add(QuestionPasagalego("Emitir a súa propia voz o porco", "GRUÑIR"))
        questions.add(QuestionPasagalego("Dicese da persoa que rí con facilidade ou ten carácter alegre", "RISUEÑA"))
        questions.add(QuestionPasagalego("Agrupación de actores ou bailaríns unidos para representar espectáculos escénicos", "COMPAÑIA"))
        questions.add(QuestionPasagalego("Acción de chocar os dentas de unha mandíbula cos de outra con temblor producido por medo ou por frío", "CASTAÑETEO"))
        questions.add(QuestionPasagalego("Prenda, xeralmente de unha peza, usada para bañarse en praias ou piscinas", "BAÑADOR"))
        questions.add(QuestionPasagalego("Prenda de vestir que cobre o corpo ata a cintura, axustada e sen mangas", "CORPIÑO"))
        questions.add(QuestionPasagalego("Individuo do primeiro grupo de humanos modernos, que apareceu en Europa no Paleolítico superior", "CROMAÑON"))
        return questions
    }

    private fun getPasagalegoQuestionsO() : MutableList<QuestionPasagalego> {
        var questions : MutableList<QuestionPasagalego> = mutableListOf()
        questions.add(QuestionPasagalego("Estofado de carne de vacún cortada do xarrete, co seu oso e caña incluídos", "OSOBUCO"))
        questions.add(QuestionPasagalego("Parte frontal dun proxectil", "OXIVA"))
        questions.add(QuestionPasagalego("Tema ou cuestión que trata unha ciencia ou estudo", "OBXECTO"))
        questions.add(QuestionPasagalego("Realizar unha operación quirúrxica", "OPERAR"))
        questions.add(QuestionPasagalego("Nome do satélite artificial lanzado por Irán en 2009, que foi o primeiro producido íntegramente en ese país", "OMID"))
        questions.add(QuestionPasagalego("Número cardinal equivalente a sete máis un", "OITO"))
        questions.add(QuestionPasagalego("Médico especialista no estudo dos dentes e o tratamento das súas enfermidades", "ODONTOLOGO"))
        questions.add(QuestionPasagalego("Territorio baixo a xurisdicción de un obispo", "OBISPADO"))
        questions.add(QuestionPasagalego("Gran que se forma no borde de unha pálpebra", "ORZUELO"))
        questions.add(QuestionPasagalego("Correo despachado polos medios e nos prazos habituais, a diferencia do aéreo ou do urxente", "ORDINARIO"))
        questions.add(QuestionPasagalego("Instrumento para explorar o oído", "OTOSCOPIO"))
        questions.add(QuestionPasagalego("Órgano da vista no ser humano e nos animais", "OLLO"))
        questions.add(QuestionPasagalego("Nos contos e lendas, monstruo de figura humana que devora ás persoas", "OGRO"))
        questions.add(QuestionPasagalego("Situación da persoa que disfruta do seu tempo libre", "OCIO"))
        questions.add(QuestionPasagalego("Substancia anarcótica e moi adictiva que se extrae da adormidera", "OPIO"))
        questions.add(QuestionPasagalego("Dicese do animal que pon ovos nos que se desarrollan os embrións", "OVIPARO"))
        questions.add(QuestionPasagalego("Arte de facer obxetos artísticos con metales preciosos", "ORFEBRERIA"))
        questions.add(QuestionPasagalego("Asio de órfanos", "ORFANATO"))
        return questions
    }

    private fun getPasagalegoQuestionsP() : MutableList<QuestionPasagalego> {
        var questions : MutableList<QuestionPasagalego> = mutableListOf()
        questions.add(QuestionPasagalego("Cartel ou edicto que se fixaba nas esquinas para coñecemento da xente", "PLACARTE"))
        questions.add(QuestionPasagalego("Pé de un moble", "PATA"))
        questions.add(QuestionPasagalego("Licor orixinario de Navarra que se obtén macerando endrinas en anís", "PACHARAN"))
        questions.add(QuestionPasagalego("Parte máis alta e sobresaínte dunha rocha ou dunha montaña", "PENACHO"))
        questions.add(QuestionPasagalego("Sustancia, xeralmente líquida, que se utiliza para dar bo olor", "PERFUME"))
        questions.add(QuestionPasagalego("Espazo pechado con paredes ou galerías que en casas e outros edificios se soe deixar ao descuberto", "PATIO"))
        questions.add(QuestionPasagalego("Masa preparada con fariña coa que se fan os fideos, tallaríns ou macarróns", "PASTA"))
        questions.add(QuestionPasagalego("Oso e prominencia de cada unha das meixelas", "POMULO"))
        questions.add(QuestionPasagalego("Cría de unha ave, particularmente da galiña", "PITO"))
        questions.add(QuestionPasagalego("Documento no que oficialmente se lle recoñece a alguén unha invención e os dereitos que se derivan de ela", "PATENTE"))
        questions.add(QuestionPasagalego("Faja ou tira de esparto, pita ou palma, que cosida con outras serve para facer obxectos, como sombreiros ou zapatos", "PLEITA"))
        questions.add(QuestionPasagalego("Estandarte das iglesias e cofradías rematado en dúas puntas, que se leva nas procesións", "PENDON"))
        questions.add(QuestionPasagalego("Tranquilo, sosegado, que non provoca loitas ou discordias", "PACIFICO"))
        questions.add(QuestionPasagalego("Apelido do político secretario xeral da Asociación de Nacións do Sudeste Asiático de 2008 a 2012", "PITSUWAN"))
        questions.add(QuestionPasagalego("Que pode ser penetrado ou traspasado pola auga ou outro fluído", "PERMEABLE"))
        questions.add(QuestionPasagalego("Nome do filósofo grego da escola ecleática cuxa única obra coñecida é o poema titulado 'Sobre a natureza'", "PARMENIDES"))
        questions.add(QuestionPasagalego("Tubo delgado, xeralmente de plástico, que se utiliza para sorber refrescos ou outros líquidos", "PALLA"))
        questions.add(QuestionPasagalego("En certos aparatos electrónicos, superficie donde aparecen imaxes", "PANTALLA"))
        return questions
    }

    private fun getPasagalegoQuestionsQ() : MutableList<QuestionPasagalego> {
        var questions : MutableList<QuestionPasagalego> = mutableListOf()
        questions.add(QuestionPasagalego("Ter cariño ou amor a [alguén].", "QUERER"))
        questions.add(QuestionPasagalego("De Qatar, estado de Asia meridional, ou dos seus habitantes", "QATARI"))
        questions.add(QuestionPasagalego("Terreo con moitos e pronunciados desniveis", "QUEBRADO"))
        questions.add(QuestionPasagalego("Participio do verbo quebrar", "QUEBRADO"))
        questions.add(QuestionPasagalego("Situación da empresa que non pode afrontar as débeda co capital do que dispón, e efecto que produce esta situación", "QUEBRA"))
        questions.add(QuestionPasagalego("Acción e efecto de crebar ou crebarse algo", "QUEBRA"))
        questions.add(QuestionPasagalego("Que pode crebar con facilidade", "QUEBRADIZO"))
        questions.add(QuestionPasagalego("Buque especialmente construído para que poida romper a capa de xeo e navegar por augas xeadas", "QUEBRAXEOS"))
        return questions
    }

    private fun getPasagalegoQuestionsR() : MutableList<QuestionPasagalego> {
        var questions : MutableList<QuestionPasagalego> = mutableListOf()
        questions.add(QuestionPasagalego("Actitude ou sentimento que fai que sexa tratada con consideración unha persoa ou cousa.", "RESPECTO"))
        questions.add(QuestionPasagalego("Peza de tenreira, que se corta en forma case cilíndrica, dende a parte inmediata ata a contracuberta", "REDONDO"))
        questions.add(QuestionPasagalego("Camiño establecido ou planificado para unha expedición ou viaxe", "RUTA"))
        questions.add(QuestionPasagalego("Cada unha das continxencias que poden ser obxecto de un contrato de seguro", "RISCO"))
        questions.add(QuestionPasagalego("Bebida alcohólica obtida por fermentación de caña de azúcar", "RON"))
        questions.add(QuestionPasagalego("Apelido do atleta que gañou a maratón de Nova York en 2004, na categoría masculina", "RAMAALA"))
        questions.add(QuestionPasagalego("Armario ou cuarto donde se gardan as prendas de vestir", "ROPEIRO"))
        questions.add(QuestionPasagalego("Voz inglesa que designa cada un dos periodos nos que se divide un combate de boxeo", "ROUND"))
        questions.add(QuestionPasagalego("Persoa que ten por oficio levar ao domicilio algunha cousa, como o leite, o pan ou o periódico", "REPARTIDOR"))
        questions.add(QuestionPasagalego("Apelido do escultor autor da estatua do Cristo Redentor saída da localidade arxentina de La Cumbre", "RAMACCIOTI"))
        questions.add(QuestionPasagalego("Sustancia que se emplea para eliminar ratas ou ratóns", "RATICIDA"))
        questions.add(QuestionPasagalego("Cobrar ou percibir cantidades de diñeiro por calquera concepto, como cuotas, donativos ou contribucións", "RECAUDAR"))
        questions.add(QuestionPasagalego("Resentimento arraigado e tenaz", "RENCOR"))
        questions.add(QuestionPasagalego("En unha comida, volver a servirse un prato que xa se tomou", "REPETIR"))
        questions.add(QuestionPasagalego("Encargo ou xestión que normalmente implica desprazamento", "RECADO"))
        questions.add(QuestionPasagalego("Conxunto de pequenas gotas de auga formadas pola condensación de vapor da atmósfera nas noites frías", "ROCIO"))
        questions.add(QuestionPasagalego("Apelido da escritora autora da novela 'Myriam de Magdala', de 1983", "RINSER"))
        return questions
    }

    private fun getPasagalegoQuestionsS() : MutableList<QuestionPasagalego> {
        var questions : MutableList<QuestionPasagalego> = mutableListOf()
        questions.add(QuestionPasagalego("Título da película de 1969, dirixida por Pierre Grimblat e protagonizada por Serge Gainsbourg e Jane Birkin", "SLOGAN"))
        questions.add(QuestionPasagalego("En tenis e outros deportes similares, saque", "SERVICIO"))
        questions.add(QuestionPasagalego("Dise do monumento clásico adornado con columnatas cuxos espazos entre columnas son catro módulos.", "SISTILO"))
        questions.add(QuestionPasagalego("Adverbio que significa 'únicamente, soamente'", "SO"))
        questions.add(QuestionPasagalego("Fotografía de unha ou máis persoas, feita por unha delas, con un teléfono e para compartila", "SELFI"))
        questions.add(QuestionPasagalego("Tempo inmediatamente posterior a unha comida durante o cal os comensais seguen reunidos", "SOBREMESA"))
        questions.add(QuestionPasagalego("Coloquialmente, vixilante con uniforme, empleado de unha empresa privada de seguridade", "SEGURATA"))
        questions.add(QuestionPasagalego("Respecto de unha persoa, fillo do seu irmán", "SOBRIÑO"))
        questions.add(QuestionPasagalego("Dar diñeiro ou regalos a alguén para conseguir algo de forma ilícita", "SOBORNAR"))
        questions.add(QuestionPasagalego("Que ocupa en unha serie o lugar número dous", "SEGUNDO"))
        questions.add(QuestionPasagalego("Nome da rama do jainismo, unha das dúas principales de esta relixión e cuxos menjes visten de blanco", "SVETAMBARA"))
        questions.add(QuestionPasagalego("Provincia do Ecuador con capital na cidade de Nueva Loja", "SUCUMBIOS"))
        questions.add(QuestionPasagalego("Adestramento para ler e entonar a notación musical", "SOLFEO"))
        questions.add(QuestionPasagalego("Que é moi nombrado, coñecido ou famoso", "SOADO"))
        questions.add(QuestionPasagalego("Soporte de tela metálica ou láminas de madeira, sobre o que se coloca o colchón da cama", "SOMIER"))
        questions.add(QuestionPasagalego("Ir ou moverse hacia arriba", "SUBIR"))
        questions.add(QuestionPasagalego("Poñer a alguén ou algo en lugar de outra persoa ou cousa", "SUBSTITUIR"))
        return questions
    }

    private fun getPasagalegoQuestionsT() : MutableList<QuestionPasagalego> {
        var questions : MutableList<QuestionPasagalego> = mutableListOf()
        questions.add(QuestionPasagalego("Mechón de pelo que se leva levantado sobre a frente", "TUPE"))
        questions.add(QuestionPasagalego("Corpo xeométrico limitado por catro caras que son triángulos", "TETRAEDRO"))
        questions.add(QuestionPasagalego("Río de Asia que, xunto co Éufrates, definía os límites da antiga rexión de Mesopotamia", "TIGRIS"))
        questions.add(QuestionPasagalego("Apelido do escultor que realizou, no Teatro Colón de Buenos Aires, os bustos de compositores como Verdi", "TRINCHERO"))
        questions.add(QuestionPasagalego("Apelido do economista e político autor do libre 'La otra campana'", "TOMBOLINI"))
        questions.add(QuestionPasagalego("Vestidura longa, xeralmente negra, que usan xuíces, letrados e catedráticos en determinados actos", "TOGA"))
        questions.add(QuestionPasagalego("Cada unha das partes, con paxinación propia, nas que se divide unha obra impresa de certa extensión", "TOMO"))
        questions.add(QuestionPasagalego("Mediar para poñer de acordo ou reconciliar a dúas persoas", "TERCIAR"))
        questions.add(QuestionPasagalego("Vehículo de motor, potente e de gran adherencia, que serve para tirar de máquinas agrícolas", "TRACTOR"))
        questions.add(QuestionPasagalego("Tela grosa colgada da parede con debuxos de figuras e esceas realizadas combinando fíos de diferentes cores", "TAPIZ"))
        questions.add(QuestionPasagalego("Provincia española á que pertencen os municipios de Alcañiz e Alba", "TERUEL"))
        questions.add(QuestionPasagalego("Medida convencional usada na fabricación e venta de prendas de vestir", "TALLA"))
        questions.add(QuestionPasagalego("Parte de algo que se considera por separado do resto", "TROZO"))
        questions.add(QuestionPasagalego("Habilidade ou aptitude para unha cousa determinada", "TALENTO"))
        questions.add(QuestionPasagalego("Pastel grande, xeralmente redondo, elaborado a base de fariña e con recheo de doce, froitas ou crema", "TARTA"))
        questions.add(QuestionPasagalego("Potro que non chegou aos dous anos", "TUSON"))
        questions.add(QuestionPasagalego("Cetro en forma de arpón de tres puntas, que teñen na man as estatuas de Neptuno", "TRIDENTE"))
        questions.add(QuestionPasagalego("Cabo gaditano xunto ao que tivo lugar a batalla naval na que perdeu a vida o almirante británico Horatio Nelson", "TRAFALGAR"))
        return questions
    }

    private fun getPasagalegoQuestionsU() : MutableList<QuestionPasagalego> {
        var questions : MutableList<QuestionPasagalego> = mutableListOf()
        questions.add(QuestionPasagalego("Dicese do fillo que carece de irmáns", "UNICO"))
        questions.add(QuestionPasagalego("O que hai máis aló ou ao outro lado das montañas", "ULTRAMONTANO"))
        questions.add(QuestionPasagalego("Interxección usada para indicar cansanzo, molestia ou asfixia", "UF"))
        questions.add(QuestionPasagalego("Especialista no estudo dos fenómenos asociaos aos ovnis", "UFOLOGO"))
        questions.add(QuestionPasagalego("Xerundio do verbo untar", "UNTANDO"))
        questions.add(QuestionPasagalego("Dicese da persoa, especialmente un monarca ou un sacerdote, que foi signado co óleo de un santo", "UNXIDO"))
        questions.add(QuestionPasagalego("Primeira persoa do plural do presente de subxuntivo do verbo unir", "UNAMOS"))
        questions.add(QuestionPasagalego("Só é sen outro da súa especie", "UNICO"))
        questions.add(QuestionPasagalego("Cidade de Brasil, no estado Minas Gerais, na que se encontra o estado de fútbol Parque do Sabiá", "UBERLANDIA"))
        questions.add(QuestionPasagalego("Cidade, especialmente a moi populosa", "URBE"))
        questions.add(QuestionPasagalego("Conxunto de coñecementos relacionados coa planificación e desenvolvemento das cidades", "URBANISMO"))
        questions.add(QuestionPasagalego("Estar situado en determinado espacio ou lugar", "UBICARSE"))
        questions.add(QuestionPasagalego("Apelido do navegante, que con Alvise Cadamosto, foi o primeiro europeo en alcanzar as islas de Cabo Verde", "USODIMARE"))
        questions.add(QuestionPasagalego("Polígono de once lados", "UNDECAGONO"))
        questions.add(QuestionPasagalego("Acondicionar un terreo dotandoo de luz, calles, alcantarillado e outros servizos", "URBANIZAR"))
        questions.add(QuestionPasagalego("Adverbio que significa 'soamente'", "UNICAMENTE"))
        questions.add(QuestionPasagalego("Primeiro dos números", "UN"))
        questions.add(QuestionPasagalego("No xénero humano, conducto por onde se expulsa a orina desde a vexiga ao exterior", "URETRA"))
        return questions
    }

    private fun getPasagalegoQuestionsV() : MutableList<QuestionPasagalego> {
        var questions : MutableList<QuestionPasagalego> = mutableListOf()
        questions.add(QuestionPasagalego("Traspasar a alguén polo precio convido a propiedade do que se posee", "VENDER"))
        questions.add(QuestionPasagalego("Calidade da persoa que exerce unha profesión durante moito tempo ou ten experiencia nunha actividade", "VETERANIA"))
        questions.add(QuestionPasagalego("Ventre dos pescados", "VENTRESCA"))
        questions.add(QuestionPasagalego("Primeira persoa do plural do futuro de indicativo do verbo vencer", "VENCEREMOS"))
        questions.add(QuestionPasagalego("Vapor que despiden os corpos de determinadas condicións de temperatura e humidade", "VAHO"))
        questions.add(QuestionPasagalego("Obsesión patolóxica por desenvolver a musculatura", "VIGOREXIA"))
        questions.add(QuestionPasagalego("Apelido do escritor da obra 'Balada de Caín'", "VICENT"))
        questions.add(QuestionPasagalego("Cada un dos paus ou estacas dispostos para construir unha empalizada", "VARGANO"))
        questions.add(QuestionPasagalego("Banda, xeralmente de gasa, que se emplea para cubrir unha parte do corpo danada ou ferida", "VENDA"))
        questions.add(QuestionPasagalego("Pegajoso, glutinoso", "VISCOSO"))
        questions.add(QuestionPasagalego("Bebida alcohólica que se fai do zumo das uvas exprimido e fermentado naturalmente", "VIÑO"))
        questions.add(QuestionPasagalego("Tempo breve no que, en España, soe facer calor durante o outono", "VERANILLO"))
        questions.add(QuestionPasagalego("Chegar unha persoa ao lugar donde está quen fala", "VIR"))
        questions.add(QuestionPasagalego("Serpente venenosa de cabeza triangular e aplastada e dous dentes ocos na mandíbula superior", "VIBORA"))
        questions.add(QuestionPasagalego("Fillo descendente de unha familia", "VASTAGO"))
        questions.add(QuestionPasagalego("Utensilio que se fai con ramas de distintas plantas, atadas e suxeitas a un pau, que se emplea para barrer", "VASOIRA"))
        questions.add(QuestionPasagalego("Rendir culto a Deus, aos santos ou ás cousas sagradas", "VENERAR"))
        questions.add(QuestionPasagalego("Apelido do autor do cadro titulado 'Luis Francisco de la Cerda, 9º duque de Medinaceli'", "VOET"))
        questions.add(QuestionPasagalego("Hábito de facer mal certa cousa ou de facer certa cousa mala", "VICIO"))
        return questions
    }

    private fun getPasagalegoQuestionsX() : MutableList<QuestionPasagalego> {
        var questions : MutableList<QuestionPasagalego> = mutableListOf()
        questions.add(QuestionPasagalego("Microorganismo que pode causar ou propagar enfermidades", "XERMEN"))
        questions.add(QuestionPasagalego("Persoa que coida e cultiva un xardín de oficio", "XARDINEIRO"))
        questions.add(QuestionPasagalego("Recipiente groso no medio e estreito na boca e na base, con asa e bico, que se emprega para conter líquidos e para servilos", "XERRA"))
        questions.add(QuestionPasagalego("Membro de un xurado ou tribunal", "XUIZ"))
        questions.add(QuestionPasagalego("Sexto mes do ano", "XUÑO"))
        questions.add(QuestionPasagalego("Adxectivo substantivo que expresa o lugar de orixen ou a nacionalidade como americano ou español", "XENTILICIO"))
        return questions
    }

    private fun getPasagalegoQuestionsZ() : MutableList<QuestionPasagalego> {
        var questions : MutableList<QuestionPasagalego> = mutableListOf()
        questions.add(QuestionPasagalego("Ignorante, torpe e moi tardo en aprender", "ZOQUETE"))
        questions.add(QuestionPasagalego("Coser [unha peza de roupa] por onde está rachada ou moi gastada, imitando o tecido e de maneira que se note o menos posible", "ZURCIR"))
        questions.add(QuestionPasagalego("Elemento químico metálico, de símbolo ZR", "ZIRCONIO"))
        questions.add(QuestionPasagalego("Persoa que fabrica, repara ou vende zapatos", "ZAPATEIRO"))
        questions.add(QuestionPasagalego("Pertencente ou relativo ao zorro", "ZORRUNO"))
        questions.add(QuestionPasagalego("Festa buliciosa con baile que se facía entre os moriscos", "ZAMBRA"))
        questions.add(QuestionPasagalego("Vaso pequeno de viño ou outra bebida", "ZURITO"))
        questions.add(QuestionPasagalego("Acción e efecto de dividir un terreo en zonas", "ZONIFICACION"))
        questions.add(QuestionPasagalego("Moble destinado a gardar o calzado", "ZAPATEIRO"))
        questions.add(QuestionPasagalego("Dicese de algo que é de cor azul claro, especialmente ollos", "ZARZO"))
        questions.add(QuestionPasagalego("Dicese da persoa que ten tendencia natural a utilizar preferentemente a man e os pés esquerdos", "ZURDA"))
        questions.add(QuestionPasagalego("Soldado de un corpo destinado aos traballos de excavación", "ZAPADOR"))
        questions.add(QuestionPasagalego("Arbusto co que as súas follas se prepara un refresco chamado de igual modo", "ZARZAPARRILLA"))
        questions.add(QuestionPasagalego("Participio do verbo zampar", "ZAMPADO"))
        return questions
    }

}