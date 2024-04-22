package com.galegando21.utils

import com.galegando21.model.QuestionPasagalego

object PasagalegoConstants {

    const val SCORE = "correct_pasagalego_answers"
    const val ERRORS = "error_pasagalego_answers"
    const val TIME = "chronometer_final_time"
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
        questions.add(QuestionPasagalego("Armazón con forma de X que, nos molinos de vento, sostén as telas nas que fai presión o vento", "ASPA"))
        questions.add(QuestionPasagalego("Ciencia que trata dos astros, do seu movemento e das leis que as rexen", "ASTRONOMIA"))
        questions.add(QuestionPasagalego("Interpretar un papel en unha obra teatral ou cinematográfica", "ACTUAR"))
        questions.add(QuestionPasagalego("Cada unha das partes en que se divide unha obra literaria, unha película, unha serie de televisión, etc.", "ACTO"))
        questions.add(QuestionPasagalego("Arte ou oficio de facer vasillas ou outros obxectos de barro cocido", "ALFARERIA"))
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
        return questions
    }

    private fun getPasagalegoQuestionsC() : MutableList<QuestionPasagalego> {
        var questions : MutableList<QuestionPasagalego> = mutableListOf()
        questions.add(QuestionPasagalego("Total privación do sentido da vista", "CEGUEIRA"))
        questions.add(QuestionPasagalego("Cama para bebés, con bordos altos ou varandas laterais, dispostas ás veces para que se poida balancear", "CUNA"))
        questions.add(QuestionPasagalego("Interrupción dunha emisión de radio ou televisión para publicidade", "CORTE"))
        questions.add(QuestionPasagalego("Décimo signo do zodiaco cuxo símbolo é unha cabra", "CAPRICORNIO"))
        questions.add(QuestionPasagalego("Gastar enerxía ou un producto enerxético", "CONSUMIR"))
        return questions
    }

    private fun getPasagalegoQuestionsD() : MutableList<QuestionPasagalego> {
        var questions : MutableList<QuestionPasagalego> = mutableListOf()
        questions.add(QuestionPasagalego("Comercialización e reparto de un produto desde a súa saída de produción ata que chega ao vendedor", "DISTRIBUCION"))
        questions.add(QuestionPasagalego("Conxunto de descendentes dunha persoa ou animal", "DESCENDENCIA"))
        questions.add(QuestionPasagalego("Documento expedido por un centro educativo e que acredite un título ou título académico", "DIPLOMA"))
        questions.add(QuestionPasagalego("Ocultar algo para que non se vexa ou non se note algo material ou cousas como intencións, accións, calidades, defectos", "DISIMULAR"))
        questions.add(QuestionPasagalego("Mezcla explosiva de nitroglicerina con outras substancias", "DINAMITA"))
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
        return questions
    }

    private fun getPasagalegoQuestionsG() : MutableList<QuestionPasagalego> {
        var questions : MutableList<QuestionPasagalego> = mutableListOf()
        questions.add(QuestionPasagalego("Ave doméstica da familia dos fasiánidos, femia do galo, con crista pequena e vermella e peteiro curto, que se cría para aproveitar os seus ovos e a súa carne para a alimentación", "GALIÑA"))
        questions.add(QuestionPasagalego("Instrumento consistente nun arame forte dobrado nun dos extremos, que serve para abrir fechos en substitución das chaves", "GANZUA"))
        questions.add(QuestionPasagalego("Río de Etiopía que desemboca no lago Tana, en cuxa orilla están os manantiais de auga termal de Wanzaye", "GUMARA"))
        questions.add(QuestionPasagalego("En profesións, servicio que asegura a continuidade das prestacións básicas fora do seu horario habitual", "GARDA"))
        return questions
    }

    private fun getPasagalegoQuestionsH() : MutableList<QuestionPasagalego> {
        var questions : MutableList<QuestionPasagalego> = mutableListOf()
        questions.add(QuestionPasagalego("Conxunto de bens que se reciben tras o falecemento do seu posedor, por disposición testamentaria ou legal", "HERDANZA"))
        questions.add(QuestionPasagalego("Apelido do matemático que, en 1976, resolveu, xunto con Kenneth Appel, o teorema das catro cores", "HAKEN"))
        questions.add(QuestionPasagalego("Corno dun animal", "HASTA"))
        questions.add(QuestionPasagalego("Aceptar a autoridade oficial un determinado producto despois de comprobar que cumple certas reglas", "HOMOLOGAR"))
        questions.add(QuestionPasagalego("Animal fantástico composto de macho cabrío e cervo", "HIRCOCERVO"))
        return questions
    }

    private fun getPasagalegoQuestionsI() : MutableList<QuestionPasagalego> {
        var questions : MutableList<QuestionPasagalego> = mutableListOf()
        questions.add(QuestionPasagalego("Irritable, propenso á ira", "IRASCIBLE"))
        questions.add(QuestionPasagalego("Vivenda hemisférica, feita con bloques de xeo, que os esquimós constrúen no inverno", "IGLU"))
        questions.add(QuestionPasagalego("Falta de deseño avanzado a favor ou en contra de alguén, o que permite proceder coa xustiza", "IMPARCIALIDADE"))
        questions.add(QuestionPasagalego("Conxunto de luces que hai en un lugar para alumbralo ou adornalo", "ILUMINACION"))
        questions.add(QuestionPasagalego("Insignificante por pequeno", "IRRISORIO"))
        return questions
    }

    private fun getPasagalegoQuestionsL() : MutableList<QuestionPasagalego> {
        var questions : MutableList<QuestionPasagalego> = mutableListOf()
        questions.add(QuestionPasagalego("Pedra plana situada a pouca altura do chan, onde se fai o lume para cociñar, nas casas tradicionais, e sobre a que adoita ir colocada unha cambota", "LAREIRA"))
        questions.add(QuestionPasagalego("Símbolo gráfico que serve como emblema dunha empresa ou entidade, dunha marca ou dun produto", "LOGOTIPO"))
        questions.add(QuestionPasagalego("Propiedade rural de grande extensión", "LATIFUNDIO"))
        questions.add(QuestionPasagalego("Institución cultural ou recreativa", "LICEO"))
        questions.add(QuestionPasagalego("Coloquialmente, aposento desarreglado e revolto", "LEONEIRA"))
        return questions
    }

    private fun getPasagalegoQuestionsM() : MutableList<QuestionPasagalego> {
        var questions : MutableList<QuestionPasagalego> = mutableListOf()
        questions.add(QuestionPasagalego("Ensalada de froitas", "MACEDONIA"))
        questions.add(QuestionPasagalego("Reunión pública na que un ou varios oradores pronuncian discursos de carácter político", "MITIN"))
        questions.add(QuestionPasagalego("Animal híbrido, en femenino, fillo de burro e euga que casi sempre é estéril", "MULA"))
        questions.add(QuestionPasagalego("Grupo de edificios, generalmente cuadrangular, delimitado por calles en todos os seus lados", "MAZA"))
        return questions
    }

    private fun getPasagalegoQuestionsN() : MutableList<QuestionPasagalego> {
        var questions : MutableList<QuestionPasagalego> = mutableListOf()
        questions.add(QuestionPasagalego("Natural da comunidade autónoma con capital en Pamplona", "NAVARRO"))
        questions.add(QuestionPasagalego("Información sobre algo que se considera interesante divulgar", "NOTICIA"))
        questions.add(QuestionPasagalego("Dicese da sustancia que produce sopor, relaxación muscular e disminución da sensibilidades, como o opio", "NARCOTICA"))
        questions.add(QuestionPasagalego("Mineral que é un silicato de sodio e aluminio", "NEFELINA"))
        return questions
    }

    private fun getPasagalegoQuestionsÑ() : MutableList<QuestionPasagalego> {
        var questions : MutableList<QuestionPasagalego> = mutableListOf()
        questions.add(QuestionPasagalego("Parque Nacional de España, situado na serra de Toledo, na comunidade autónoma de Castela-A Mancha", "CABAÑEROS"))
        questions.add(QuestionPasagalego("Cóctel feito con augardente de caña, azúcar, xeo picado e zumo de limón", "CAIPIRIÑA"))
        questions.add(QuestionPasagalego("Natural do norte de un país", "NORTEÑO"))
        return questions
    }

    private fun getPasagalegoQuestionsO() : MutableList<QuestionPasagalego> {
        var questions : MutableList<QuestionPasagalego> = mutableListOf()
        questions.add(QuestionPasagalego("Estofado de carne de vacún cortada do xarrete, co seu oso e caña incluídos", "OSOBUCO"))
        questions.add(QuestionPasagalego("Parte frontal dun proxectil", "OXIVA"))
        questions.add(QuestionPasagalego("Tema ou cuestión que trata unha ciencia ou estudo", "OBXECTO"))
        questions.add(QuestionPasagalego("Realizar unha operación quirúrxica", "OPERAR"))
        questions.add(QuestionPasagalego("Nome do satélite artificial lanzado por Irán en 2009, que foi o primeiro producido íntegramente en ese país", "OMID"))
        return questions
    }

    private fun getPasagalegoQuestionsP() : MutableList<QuestionPasagalego> {
        var questions : MutableList<QuestionPasagalego> = mutableListOf()
        questions.add(QuestionPasagalego("Cartel ou edicto que se fixaba nas esquinas para coñecemento da xente", "PLACARTE"))
        questions.add(QuestionPasagalego("Pé de un moble", "PATA"))
        questions.add(QuestionPasagalego("Licor orixinario de Navarra que se obtén macerando endrinas en anís", "PACHARAN"))
        questions.add(QuestionPasagalego("Parte máis alta e sobresaínte dunha rocha ou dunha montaña", "PENACHO"))
        questions.add(QuestionPasagalego("Sustancia, xeralmente líquida, que se utiliza para dar bo olor", "PERFUME"))
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
        questions.add(QuestionPasagalego("Actitude da persoa que garda as consideracións debidas ás persoas ou cousas", "RESPETO"))
        questions.add(QuestionPasagalego("Peza de tenreira, que se corta en forma case cilíndrica, dende a parte inmediata ata a contracuberta", "REDONDO"))
        questions.add(QuestionPasagalego("Camiño establecido ou planificado para unha expedición ou viaxe", "RUTA"))
        questions.add(QuestionPasagalego("Cada unha das continxencias que poden ser obxecto de un contrato de seguro", "RISCO"))
        questions.add(QuestionPasagalego("Bebida alcohólica obtida por fermentación de caña de azúcar", "RON"))
        return questions
    }

    private fun getPasagalegoQuestionsS() : MutableList<QuestionPasagalego> {
        var questions : MutableList<QuestionPasagalego> = mutableListOf()
        questions.add(QuestionPasagalego("Título da película de 1969, dirixida por Pierre Grimblat e protagonizada por Serge Gainsbourg e Jane Birkin", "SLOGAN"))
        questions.add(QuestionPasagalego("En tenis e outros deportes similares, saque", "SERVICIO"))
        questions.add(QuestionPasagalego("Dise do monumento clásico adornado con columnatas cuxos espazos entre columnas son catro módulos.", "SISTILO"))
        questions.add(QuestionPasagalego("Adverbio que significa 'únicamente, soamente'", "SO"))
        questions.add(QuestionPasagalego("Fotografía de unha ou máis persoas, feita por unha delas, con un teléfono e para compartila", "SELFI"))
        return questions
    }

    private fun getPasagalegoQuestionsT() : MutableList<QuestionPasagalego> {
        var questions : MutableList<QuestionPasagalego> = mutableListOf()
        questions.add(QuestionPasagalego("Mechón de pelo que se leva levantado sobre a frente", "TUPE"))
        questions.add(QuestionPasagalego("Corpo xeométrico limitado por catro caras que son triángulos", "TETRAEDRO"))
        questions.add(QuestionPasagalego("Río de Asia que, xunto co Éufrates, definía os límites da antiga rexión de Mesopotamia", "TIGRIS"))
        questions.add(QuestionPasagalego("Apelido do escultor que realizou, no Teatro Colón de Buenos Aires, os bustos de compositores como Verdi", "TRINCHERO"))
        questions.add(QuestionPasagalego("Apelido do economista e político autor do libre 'La otra campana'", "TOMBOLINI"))
        return questions
    }

    private fun getPasagalegoQuestionsU() : MutableList<QuestionPasagalego> {
        var questions : MutableList<QuestionPasagalego> = mutableListOf()
        questions.add(QuestionPasagalego("Dicese do fillo que carece de irmáns", "UNICO"))
        questions.add(QuestionPasagalego("O que hai máis aló ou ao outro lado das montañas", "ULTRAMONTANO"))
        questions.add(QuestionPasagalego("Interxección usada para indicar cansanzo, molestia ou asfixia", "UF"))
        questions.add(QuestionPasagalego("Especialista no estudo dos fenómenos asociaos aos ovnis", "UFOLOGO"))
        questions.add(QuestionPasagalego("Xerundio do verbo untar", "UNTANDO"))
        return questions
    }

    private fun getPasagalegoQuestionsV() : MutableList<QuestionPasagalego> {
        var questions : MutableList<QuestionPasagalego> = mutableListOf()
        questions.add(QuestionPasagalego("Traspasar a alguén polo precio convido a propiedade do que se posee", "VENDER"))
        questions.add(QuestionPasagalego("Calidade da persoa que exerce unha profesión durante moito tempo ou ten experiencia nunha actividade", "VETERANIA"))
        questions.add(QuestionPasagalego("Ventre dos pescados", "VENTRESCA"))
        questions.add(QuestionPasagalego("Primeira persoa do plural do futuro de indicativo do verbo vencer", "VENCEREMOS"))
        questions.add(QuestionPasagalego("Vapor que despiden os corpos de determinadas condicións de temperatura e humidade", "VAHO"))
        return questions
    }

    private fun getPasagalegoQuestionsX() : MutableList<QuestionPasagalego> {
        var questions : MutableList<QuestionPasagalego> = mutableListOf()
        questions.add(QuestionPasagalego("Microorganismo que pode causar ou propagar enfermidades", "XERMEN"))
        questions.add(QuestionPasagalego("Persoa que coida e cultiva un xardín de oficio", "XARDINEIRO"))
        questions.add(QuestionPasagalego("Recipiente groso no medio e estreito na boca e na base, con asa e bico, que se emprega para conter líquidos e para servilos", "XERRA"))
        return questions
    }

    private fun getPasagalegoQuestionsZ() : MutableList<QuestionPasagalego> {
        var questions : MutableList<QuestionPasagalego> = mutableListOf()
        questions.add(QuestionPasagalego("Ignorante, torpe e moi tardo en aprender", "ZOQUETE"))
        questions.add(QuestionPasagalego("Coser [unha peza de roupa] por onde está rachada ou moi gastada, imitando o tecido e de maneira que se note o menos posible", "ZURCIR"))
        questions.add(QuestionPasagalego("Elemento químico metálico, de símbolo ZR", "ZIRCONIO"))
        questions.add(QuestionPasagalego("Persoa que fabrica, repara ou vende zapatos", "ZAPATEIRO"))
        return questions
    }

}