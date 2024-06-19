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
        questions.add(QuestionAtrapameSePodes("Que tipo de ser vivo é un salgueiro?", "ARBORE"))
        questions.add(QuestionAtrapameSePodes("Que ponte da provincia de Pontevedra lle dá nome a unha batalla do 1702?", "RANDE"))
        questions.add(QuestionAtrapameSePodes( "Cal é o produto polo que é máis coñecida a editorial italiana Panini", "CROMOS"))
        questions.add(QuestionAtrapameSePodes( "Dentro de que familia de instrumentos musicais encaixan as castañolas?", "PERCUSION"))
        questions.add(QuestionAtrapameSePodes( "Que deputación provincial outorga o premio ourensanía?", "OURENSE"))
        questions.add(QuestionAtrapameSePodes("Que provincia galegas limita con Porgual e está situada máis ao oeste?", "PONTEVEDRA"))
        questions.add(QuestionAtrapameSePodes("Segundo a canción popular, que virxe parece unha rianxeira descalciña pola area", "VIRXE DE GUADALUPE"))
        questions.add(QuestionAtrapameSePodes("Primeira persoa do singular do presente de indicativo do verbo ir", "VOU"))
        questions.add(QuestionAtrapameSePodes("En que século tivo lugar a Revolución Francesa?", "XVIII"))
        questions.add(QuestionAtrapameSePodes("Que nome artístico e de animal ten o cantante do éxito agro-rock 'Opá, yo viazé un corrá'", "KOALA"))
        questions.add(QuestionAtrapameSePodes("Con que instrumento relacionas o músico James Rhodes, amante confeso de Galicia?", "PIANO"))
        questions.add(QuestionAtrapameSePodes("Alimento cárnico asado ou cocido que ten unha festa na súa honra en Silleda en xullo e se adoita acompañar con grelos?", "LACON"))
        questions.add(QuestionAtrapameSePodes("En que continente se atopan as cidades autónomas de Ceuta e Melilla?", "AFRICA"))
        questions.add(QuestionAtrapameSePodes("Que cidade é a capital de Andalucía?", "SEVILLA"))
        return questions
    }

    private fun getAtrapameSePodesQuestionsLevel1() : MutableList<QuestionAtrapameSePodes> {
        var questions : MutableList<QuestionAtrapameSePodes> = mutableListOf()
        questions.add(QuestionAtrapameSePodes( "Que apelido ten ocineasta dirixiu a película 'Mujeres al borde de un ataque de nervios'?", "ALMODOVAR"))
        questions.add(QuestionAtrapameSePodes( "Que película converteu en celebridade a Julia Roberts no papel dunha prostituta que cambia de vida xunto a Richard Gere?", "PRETTY WOMAN"))
        questions.add(QuestionAtrapameSePodes( "Para que un bebé sexa muller, que letra se repite dúas veces nos cromosomas sexuais?", "X"))
        questions.add(QuestionAtrapameSePodes("En que cidade galega podes visitar o centro comercial Camelias?", "VIGO"))
        questions.add(QuestionAtrapameSePodes("En que cidade galega se atopa a catedral de San Martiño?", "OURENSE"))
        questions.add(QuestionAtrapameSePodes("Que animal é o pai dun poldro?", "CABALO"))
        questions.add(QuestionAtrapameSePodes("Cal é o ingrediente principal do prato francés chamado 'ratatouille'?", "VEXETAIS"))
        questions.add(QuestionAtrapameSePodes( "En que profesión destaca Naomi Campbell", "MODELO"))
        questions.add(QuestionAtrapameSePodes( "Como se chama o principal río de Exipto?", "NILO"))
        questions.add(QuestionAtrapameSePodes( "Que roqueiro español conta cos éxitos 'Terriblemente Cruel', 'Lady Madrid' e 'La llamada' no seu repertorio", "LEIVA"))
        questions.add(QuestionAtrapameSePodes("Cal é o único queixo galego afumado que conta coa Denominación de Orixe Protexida", "SAN SIMON"))
        questions.add(QuestionAtrapameSePodes("Que dorsal tivo no F.C. Barcelona Gerard Piqué entre os anos 2008 e 2022?", "3"))
        questions.add(QuestionAtrapameSePodes("Que animal acaricia Vito Corleone na primeira escea de 'O Padriño'?", "GATO"))
        questions.add(QuestionAtrapameSePodes("Que título nobiliario ten Tamara Falcó", "MARQUESA"))
        questions.add(QuestionAtrapameSePodes("Onde tes as trompas de Eustaquio?", "OIDO"))
        questions.add(QuestionAtrapameSePodes("Ademáis da de trigo, que outra fariña leva a famosa torta de Guitiriz", "MILLO"))
        questions.add(QuestionAtrapameSePodes("Forma coloquial para referirnos á raza de can 'teckel'", "SALCHICHA"))
        questions.add(QuestionAtrapameSePodes("Por que tubérculo son especialmente coñecidos os concellos de Coristanco e Xinzo de Limia", "PATACA"))
        questions.add(QuestionAtrapameSePodes("Cal é o doce máis popular de Allariz", "AMENDOADOS"))
        questions.add(QuestionAtrapameSePodes("Que actor hawaiano de grande envergadura e longa melena protagonizou 'Aquaman'?", "JASON MOMOA"))
        return questions
    }

    private fun getAtrapameSePodesQuestionsLevel2() : MutableList<QuestionAtrapameSePodes> {
        var questions : MutableList<QuestionAtrapameSePodes> = mutableListOf()
        questions.add(QuestionAtrapameSePodes("Que magnitude se mide co higrómetro?", "HUMIDADE"))
        questions.add(QuestionAtrapameSePodes("A que cidade pertencen os barrios de Covent Garden, Litte Venice ou Soho", "LONDRES"))
        questions.add(QuestionAtrapameSePodes("De que grupo de cumbia arxentino, autor de 'Mentirosa', era vocalista principal Rodrigo Tapari?", "RAFAGA"))
        questions.add(QuestionAtrapameSePodes( "De que cantante é o disco do ano 2000 'El alma al aire'?", "ALEJANDRO SANZ"))
        questions.add(QuestionAtrapameSePodes( "En que cidade galega está o barrio de Vite?", "SANTIAGO"))
        questions.add(QuestionAtrapameSePodes( "Que eslovena, muller de Donald Trump, foi primeira dama dos EE.UU.?", "MELANIA"))
        questions.add(QuestionAtrapameSePodes( "En que cidade estadounidense se atopa o aeroporto internacional Jhon F. Kennedy", "NOVA YORK"))
        questions.add(QuestionAtrapameSePodes("Por cantos membros está conformado o proxecto musical Baiuca?", "UN"))
        questions.add(QuestionAtrapameSePodes("Cantos minutos dura un partido de rugby?", "80"))
        questions.add(QuestionAtrapameSePodes("Que illa do parque nacional das Illas Atlánticas é famosa por ter un gran monte de loureiro", "CORTEGADA"))
        questions.add(QuestionAtrapameSePodes("Segundo apelido do ourensán Vicente Martínez, autor da teoría 'Teoría do nacionalismo galego', publicada en 1920?", "RISCO"))
        questions.add(QuestionAtrapameSePodes("Que apelido ten a tenista campioa de Wimbledon no 2021?", "BARTY"))
        questions.add(QuestionAtrapameSePodes("Que grupo británico, liderado por Mick Jagger, deu un concerto o 17 de xullo de 1998 en Vigo?", "ROLLING STONES"))
        questions.add(QuestionAtrapameSePodes("Que grupo musical español popularizou o tema 'Chiquilla' en 1991?", "SEGURIDAD SOCIAL"))
        questions.add(QuestionAtrapameSePodes("Cal foi a única película ambientada en Galicia que levou un Óscar?", "MAR ADENTRO"))
        questions.add(QuestionAtrapameSePodes("En que concello da comarca de Ferrol se atopa a sede de Pull and Bear?", "NARON"))
        questions.add(QuestionAtrapameSePodes("En que país europeo naceron David e Victoria Beckham", "INGLATERRA"))
        questions.add(QuestionAtrapameSePodes("Que nome recibe a receita tradicional galega na que o polbo é o protagonista", "A FEIRA"))
        questions.add(QuestionAtrapameSePodes("Que papa foi o predecesor de Bieito XVI?", "JUAN PABLO II"))
        questions.add(QuestionAtrapameSePodes("Que fragata da Armada francesa naufragou en augas de Fisterra no ano 1803?", "BAYONNAISE"))
        questions.add(QuestionAtrapameSePodes("Cal era a profesión de Peter Lindberh e Steven Meisel, cuxa obra estivo exposta na cidade da Coruña?", "FOTOGRAFOS"))
        return questions
    }

    private fun getAtrapameSePodesQuestionsLevel3() : MutableList<QuestionAtrapameSePodes> {
        var questions : MutableList<QuestionAtrapameSePodes> = mutableListOf()
        questions.add(QuestionAtrapameSePodes("En que ría desemboca o río Lérez?", "PONTEVEDRA"))
        questions.add(QuestionAtrapameSePodes("Que cantante arxentino adquiriu sona mundia grazas á canción 'Color Esperanza'?", "DIEGO TORRES"))
        questions.add(QuestionAtrapameSePodes("Cal é o nome do protagonista zoupón e miope da serie de animación 'Padre de Familia'", "PETER"))
        questions.add(QuestionAtrapameSePodes( "En que mítica serie da galega estaba o personaxe de Petróleo", "MAREAS VIVAS"))
        questions.add(QuestionAtrapameSePodes( "De que supermercados galego é presidente Roberto Tojeiro", "GADIS"))
        questions.add(QuestionAtrapameSePodes( "Cal é o apelido da presentadora e actriz vasca que leva dende finais dos anos 90 á fronte do programa rosa 'Corazón' en TVE?", "IGARTIBURU"))
        questions.add(QuestionAtrapameSePodes( "Cal é o nome da actriz coa que casou o cómico galego Javier Veiga?", "MARTA"))
        questions.add(QuestionAtrapameSePodes( "Que cafetería madrileña acollía os faladoiros de membros da xeración do 27 e de figuras coma Benito Pérez Galdós ou o vilanovés Valle-Inclán", "CAFE GIJON"))
        questions.add(QuestionAtrapameSePodes("Quen foi o arquitecto do fermoso edificio Chrysler de Nova York, exemplo de 'art déco'", "WILLIAM VAN ALEN"))
        questions.add(QuestionAtrapameSePodes("Como se chama o personaxe interpretado por Humprey Bogart en 'Casablanca'", "RICK"))
        questions.add(QuestionAtrapameSePodes("Que galego, expresidente de Banesto, foi o fundador en 2011 do partido político Sociedad Civil y Democracia?", "MARIO CONDE"))
        questions.add(QuestionAtrapameSePodes("Que estrela internacional de orixe galega gravou un videoclip do tema 'Noche y día' en 2015 no mosteiro de Carboeiro, en Silleda?", "ENRIQUE IGLESIAS"))
        questions.add(QuestionAtrapameSePodes("En que país mediterráneo tivo lugar en 2022 o festival de Eurovisión?", "ITALIA"))
        questions.add(QuestionAtrapameSePodes("En que profesión destacou Lluís Llongueras, falecido en maio de 2023", "PERRUQUERIA"))
        questions.add(QuestionAtrapameSePodes("Que actor protagoniza as películas do personaxe de Jason Bourne", "MATT DAMON"))
        questions.add(QuestionAtrapameSePodes("Cal é a capital de Eslovaquia?", "BRATISLAVA"))
        questions.add(QuestionAtrapameSePodes("Con que pan de millo é tradicional comer as sardiñas no san Xoán?", "BROA"))
        questions.add(QuestionAtrapameSePodes("Durante que réxime se aprobou o Proxecto de Autonomía de Galicia, que finalmente non puido sair adiante?", "SEGUNDA REPUBLICA"))
        questions.add(QuestionAtrapameSePodes("Que banda de rock foi formada en Australia polos irmáns Malcolm Young e Angus Young?", "AC/DC"))
        questions.add(QuestionAtrapameSePodes("Quen dirixiu a película 'American Beauty'?", "SAM MENDES"))
        return questions
    }

    private fun getAtrapameSePodesQuestionsLevel4() : MutableList<QuestionAtrapameSePodes> {
        var questions : MutableList<QuestionAtrapameSePodes> = mutableListOf()
        questions.add(QuestionAtrapameSePodes( "Segundo o calendario relixioso, en que mes é a festividade da Virxe das Dores?", "SETEMBRO"))
        questions.add(QuestionAtrapameSePodes("Cal é a nacionalidade da cantante e compositora Enya, que triunfou no ano 2000 co álbum 'A day without rain'", "IRLANDESA"))
        questions.add(QuestionAtrapameSePodes( "Cal é o nome do humorista que é o presentador da versión do 'Atrápame si puedes' de Aragón TV", "IÑAKI"))
        questions.add(QuestionAtrapameSePodes( "Cal é o apelido do mecánico madrileño que é un recoñecido perito xudicial e revela na rede defectos de coches para 3 millóns de seguidores?", "GAITAN"))
        questions.add(QuestionAtrapameSePodes( "Que enxeñeiro barcelonés é un famoso escritor e guionista con libros coma 'Lo mejor de ir es volver'?", "ALBERT ESPINOSA"))
        questions.add(QuestionAtrapameSePodes( "Que libro publicou en 1966 Ramón Sampedro?", "CARTAS DESDE EL INFIERNO"))
        questions.add(QuestionAtrapameSePodes("Cal é o libro de Albert Camus que fai referencia a un gobernador romano?", "CALIGULA"))
        questions.add(QuestionAtrapameSePodes("Cal é o apelido da autora que publicou en 1847 a novela 'Cumbres borrascosas' baixo o pseudónimo de Ellis Bell", "BRONTE"))
        questions.add(QuestionAtrapameSePodes("Con que nome se coñece o pintor lalinense José Otero Abeledo?", "LAXEIRO"))
        questions.add(QuestionAtrapameSePodes("Que banda de rock lanzou nos 80 temas como 'You Give Love a Bad Name' ou 'Born To Be My Baby'?", "BON JOVI"))
        questions.add(QuestionAtrapameSePodes("De que Dj e productor francés son os discos e concertos eivisencos publicados co título 'Fuck Me I'm Famous'", "DAVID GUETTA"))
        return questions
    }
}