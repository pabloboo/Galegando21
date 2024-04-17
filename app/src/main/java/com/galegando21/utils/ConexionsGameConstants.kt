package com.galegando21.utils

import com.galegando21.model.Conexions

object ConexionsGameConstants {
    fun getConexions() : MutableList<Conexions> {
        var conexionsList : MutableList<Conexions> = mutableListOf()
        conexionsList.add(
            Conexions(
                "Gritar", "Xenxibre", "Arroz", "Dardo",
                "Revolto", "Machado", "Prisa", "Anel",
                "Algas", "Ferradura", "Ferver", "Patada",
                "Sésamo", "Fritir", "Emoción", "Escalfar",
                "Formas de preparar ovos", listOf("Ferver", "Fritir", "Escalfar", "Revolto"),
                "Sentimento de euforia", listOf("Alboroto", "Gritar", "Prisa", "Emoción"),
                "Lanzados en xogos de precisión", listOf("Machado", "Dardo", "Ferradura", "Anel"),
                "Ingredientes de cociña asiática", listOf("Sésamo", "Algas", "Xenxibre", "Arroz")
            )
        )
        conexionsList.add(
            Conexions(
                "Fiction", "Capitán", "Verde", "Terreos",
                "Campo", "Iniciación", "Bastardos", "Celebración",
                "Director", "Transformación", "Líder", "Odiosos",
                "Renacemento", "Desencadenado", "Céspede", "Xefe",
                "Persoas en cargo", listOf("Director", "Xefe", "Líder", "Capitán"),
                "Zona herbosa", listOf("Campo", "Verde", "Terreos", "Céspede"),
                "Elementos comúns en rituais de paso", listOf("Iniciación", "Transformación", "Renacemento", "Celebración"),
                "Segundas palabras en películas de Tarantino", listOf("Fiction", "Desencadenado", "Odiosos", "Bastardos")
            )
        )
        conexionsList.add(
            Conexions(
                "Dominó", "Superficie", "Improvisar", "Crear",
                "Superficial", "Balbucear", "Cosmético", "Lateral",
                "Inventar", "Gatear", "Freestyle", "Mamar",
                "Placebo", "Dormir", "Bolboreta", "Externo",
                "Inventar sobre a marcha", listOf("Improvisar", "Freestyle", "Crear", "Inventar"),
                "Cousas que fan os bebés", listOf("Balbucear", "Gatear", "Mamar", "Dormir"),
                "Superficial", listOf("Cosmético", "Externo", "Superficial", "Superficie"),
                "Efecto ___", listOf("Dominó", "Placebo", "Lateral", "Bolboreta")
            )
        )
        conexionsList.add(
            Conexions(
                "Botar","Golpear","Esquí","Patinaxe",
                "Bobsleigh","Francesa","Ballet","Rusa",
                "Cubana","Curling","Hip-hop","Driblar",
                "Butoh","Patear","Flamenco","Americana",
                "Cousas que se poden facer cunha pelota", listOf("Patear", "Botar", "Golpear", "Driblar"),
                "Revolución ___", listOf("Francesa", "Rusa", "Americana", "Cubana"),
                "Estilos de danza", listOf("Ballet", "Hip-hop", "Flamenco", "Butoh"),
                "Deportes de inverno", listOf("Esquí", "Patinaxe", "Bobsleigh", "Curling")
            )
        )
        conexionsList.add(
            Conexions(
            "Conductismo","Solar","Humanismo","Máquina de vapor",
            "Diplomacia","Guerra","Nuclear","Telescopio",
            "Tratados de paz","Xeotérmica","Gestalt","Psicoanálisis",
            "Espada samurái","Pedra Rosetta","Organizacións internacionais","Eólica",
            "Formas de enerxía", listOf("Solar", "Nuclear", "Xeotérmica", "Eólica"),
            "Artefactos históricos", listOf("Espada samurái", "Pedra Rosetta", "Máquina de vapor", "Telescopio"),
            "Teorías psicolóxicas", listOf("Psicoanálisis", "Conductismo", "Humanismo", "Gestalt"),
            "Relacións internacionais", listOf("Diplomacia", "Guerra", "Tratados de paz", "Organizacións internacionais")
            )
        )
        conexionsList.add(
            Conexions(
                "Anarquismo", "Cambio climático", "Contaminación", "Caza",
                "Modernismo", "Terra plana", "Populismo", "Realismo",
                "Postmodernismo", "Romanticismo", "Reptilianos", "Illuminati",
                "Socialismo", "Farsa de aluzinaxe", "Conservadurismo", "Deforestación",
                "Movementos literarios", listOf("Romanticismo", "Realismo", "Modernismo", "Postmodernismo"),
                "Causas de extinción de especies", listOf("Contaminación", "Caza", "Deforestación", "Cambio climático"),
                "Movementos políticos", listOf("Populismo", "Conservadurismo", "Socialismo", "Anarquismo"),
                "Teorías conspiranoicas", listOf("Terra plana", "Farsa de aluzinaxe", "Illuminati", "Reptilianos")
            )
        )
        conexionsList.add(
            Conexions(
                "Superman", "Tipografía", "Mickey Mouse", "Darth Vader",
                "Vasco de Gama", "Marie Curie", "Isaac Newton", "Roald Amundsen",
                "Galileo Galilei", "Art Deco", "Marco Polo", "Minimalismo",
                "Ilustración", "Cristóbal Colón", "Mario Bros", "Charles Darwin",
                "Estilos de deseño gráfico", listOf("Minimalismo", "Art Deco", "Tipografía", "Ilustración"),
                "Elementos da cultura pop", listOf("Superman", "Mickey Mouse", "Mario Bros", "Darth Vader"),
                "Grandes exploradores", listOf("Marco Polo", "Cristóbal Colón", "Vasco de Gama", "Roald Amundsen"),
                "Científicos pioneiros", listOf("Galileo Galilei", "Marie Curie", "Isaac Newton", "Charles Darwin")
            )
        )
        conexionsList.add(
            Conexions(
                "Artículo", "Susto", "Medo", "Palco",
                "Historia", "Balcón", "Informe", "Sorpresa",
                "Choque", "Ao mesmo nivel", "Reportaxe", "Escenario",
                "Orquestra", "Nivelado", "Plano", "Igual",
                "Deixar sen fala", listOf("Susto", "Medo", "Choque", "Sorpresa"),
                "Algo de periodismo", listOf("Artículo", "Reportaxe", "Informe", "Historia"),
                "No mismo plano", listOf("Igual", "Plano", "Ao mesmo nivel", "Nivelado"),
                "Seccións de un teatro", listOf("Balcón", "Palco", "Orquestra", "Escenario")
            )
        )
        conexionsList.add(
            Conexions(
                "Interés", "Observar", "Poste", "Secretos",
                "Información", "Datos", "Acción", "Pilar",
                "Intelixencia", "Manter en mente", "Porcentaxe", "Considerar",
                "Seguir", "Columna", "Pata", "Participación",
                "Apoio vertical", listOf("Columna", "Pilar", "Poste", "Pata"),
                "Asignación", listOf("Interés", "Porcentaxe", "Participación", "Acción"),
                "Recolectado por espías", listOf("Información", "Intelixencia", "Secretos", "Datos"),
                "Prestar atención, como regras", listOf("Seguir", "Manter en mente", "Observar", "Considerar")
            )
        )
        return conexionsList
    }
}