# Galegando21

**Aplicación móbil para fomentar o uso do galego a través de minixogos lúdicos**

![Logo de Galegando21](https://github.com/pabloboo/Galegando21/blob/main/app/src/main/res/drawable/g21_logo.png)

## Descrición do Proxecto

**Galegando21** é unha aplicación móbil deseñada para promover o uso da lingua galega de forma lúdica e interactiva. A aplicación inclúe 21 minixogos educativos que axudan aos usuarios a aprender vocabulario, cultura e expresións en galego, mentres se divirten. A aplicación está pensada para usuarios de todas as idades, especialmente para os máis novos, co obxectivo de crear un hábito de uso do galego no seu día a día.

A aplicación está desenvolvida en **Kotlin** e utiliza técnicas de recuperación de información para obter datos para os xogos.

## Tecnoloxías Utilizadas

- **Linguaxe de programación**: Kotlin
- **Ferramentas de desenvolvemento**: Android Studio, Figma (para o deseño de mockups)
- **Tecnoloxías de backend**: Flask (para a API), Supabase (para o almacenamento en nube)
- **Técnicas de recuperación de información**: Scrapy, Selenium (para o scraping de datos)
- **Xestión de datos**: SharedPreferences (para o almacenamento local)

## Estrutura do Proxecto

O proxecto está organizado en varios módulos e paquetes principais:

- **Minixogos**: Cada minixogo ten o seu propio paquete (`dayXX`) coa lóxica e interface de usuario correspondente.
- **Menú**: Contén as pantallas e lóxica do menú principal, incluíndo estadísticas, tenda e axustes.
- **Modelo**: Define as estruturas de datos e entidades utilizadas na aplicación.
- **Onboarding**: Xestiona o proceso de introdución para novos usuarios.
- **Utils**: Inclúe utilidades e compoñentes reutilizables.

Ademais, o proxecto inclúe unha **API** implementada en Flask e despregada en PythonAnywhere, que proporciona funcionalidades adicionais como a obtención de desafíos personalizados, eventos culturais e feedback para os usuarios e unha base de datos en **Supabase** para permitir ao usuario gardar ou obter os seus datos de xogo.

## Instalación e Uso

### Requisitos previos

- Android Studio (recoméndase a última versión).
- Dispositivo Android ou emulador.
- Python 3.x (para a execución dos scrapers).

### Instalación

1. Clona o repositorio:
   ```bash
   git clone https://github.com/pabloboo/Galegando21.git
   ```
2. Abre o proxecto en Android Studio.
3. Configura o teu dispositivo ou emulador Android.
4. Compila e executa a aplicación.

### Execución dos Scrapers

Para obter os datos necesarios para os minixogos, podes executar os scrapers incluídos no proxecto. Segue as instrucións detalladas no ficheiro [Execución dos Scrapers](https://github.com/pabloboo/Galegando21/blob/main/ragscraper/README.md). Este paso non é necesario para a execución da aplicación xa que os datos están persistidos no directorio `app/src/main/assets`.

## Como Contribuír

Se queres contribuír a este proxecto, por favor, consulta o ficheiro [CONTRIBUTING.md](CONTRIBUTING.md) para obter instrucións detalladas sobre como facelo. Agradezco calquera contribución, desde correccións de erros ata novas funcionalidades.

## Estado do Proxecto

**Galegando21** está actualmente en **produción** e dispoñible na Play Store. A aplicación é estable e recibe actualizacións regulares para mellorar a experiencia do usuario e engadir novas funcionalidades.

## Documentación Adicional

- [Documentación da API](https://github.com/pabloboo/Galegando21/blob/main/api/README.md)
- [Guía de usuario](#)

## Licenza

Este proxecto está baixo a licenza [GPL-3.0](https://www.gnu.org/licenses/gpl-3.0.html). Podes consultar o arquivo [LICENSE](LICENSE) para máis detalles.

---

**Galegando21** é un proxecto aberto e colaborativo. Se tes calquera pregunta ou suxestión, non dubides en contactar comigo (booiglesiaspablo@gmail.com) ou abrir unha incidencia no repositorio.
