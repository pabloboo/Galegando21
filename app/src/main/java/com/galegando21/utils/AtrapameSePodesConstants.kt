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
        return questions
    }

    private fun getAtrapameSePodesQuestionsLevel1() : MutableList<QuestionAtrapameSePodes> {
        var questions : MutableList<QuestionAtrapameSePodes> = mutableListOf()
        questions.add(QuestionAtrapameSePodes( "Que cineasta dirixiu a película 'Mujeres al borde de un ataque de nervios'?", "PEDRO ALMODOVAR"))
        questions.add(QuestionAtrapameSePodes( "Que película converteu en celebridade a Julia Roberts no papel dunha prostituta que cambia de vida xunto a Richard Gere?", "PRETTY WOMAN"))
        questions.add(QuestionAtrapameSePodes( "Para que un bebé sexa muller, que letra se repite dúas veces nos cromosomas sexuais?", "X"))
        questions.add(QuestionAtrapameSePodes("En que cidade galega podes visitar o centro comercial Camelias?", "VIGO"))
        questions.add(QuestionAtrapameSePodes("En que cidade galega se atopa a catedral de San Martiño?", "OURENSE"))
        questions.add(QuestionAtrapameSePodes("Que animal é o pai dun poldro?", "CABALO"))
        questions.add(QuestionAtrapameSePodes("Cal é o ingrediente principal do prato francés chamado 'ratatouille'?", "VEXETAIS"))
        questions.add(QuestionAtrapameSePodes( "En que profesión destaca Naomi Campbell", "MODELO"))
        questions.add(QuestionAtrapameSePodes( "Como se chama o principal río de Exipto?", "NILO"))
        questions.add(QuestionAtrapameSePodes( "Que roqueiro español conta cos éxitos 'Terriblemente Cruel', 'Lady Madrid' e 'La llamada' no seu repertorio", "LEIVA"))
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
        return questions
    }

    private fun getAtrapameSePodesQuestionsLevel4() : MutableList<QuestionAtrapameSePodes> {
        var questions : MutableList<QuestionAtrapameSePodes> = mutableListOf()
        questions.add(QuestionAtrapameSePodes( "Segundo o calendario relixioso, que día é a festividade da Virxe das Dores?", "15 DE SETEMBRO"))
        questions.add(QuestionAtrapameSePodes("Cal é a nacionalidade da cantante e compositora Enya, que triunfou no ano 2000 co álbum 'A day without rain'", "IRLANDESA"))
        questions.add(QuestionAtrapameSePodes( "Cal é o nome do humorista que é o presentador da versión do 'Atrápame si puedes' de Aragón TV", "IÑAKI"))
        questions.add(QuestionAtrapameSePodes( "Cal é o apelido do mecánico madrileño que é un recoñecido perito xudicial e revela na rede defectos de coches para 3 millóns de seguidores?", "GAITAN"))
        questions.add(QuestionAtrapameSePodes( "Que enxeñeiro barcelonés é un famoso escritor e guionista con libros coma 'Lo mejor de ir es volver'?", "ALBERT ESPINOSA"))
        questions.add(QuestionAtrapameSePodes( "Que libro publicou en 1966 Ramón Sampedro?", "CARTAS DESDE EL INFIERNO"))
        return questions
    }
}