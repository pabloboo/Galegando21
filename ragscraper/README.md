# Execución dos Scrapers

Abrir o proxecto en unha terminal ubuntu (wsl de windows por exemplo)

Crear o entorno virtual:
```
python3 -m venv venv
source ./venv/bin/activate
pip3 install scrapy
pip3 install Unidecode
```

## Executar o Scraper de Digalego:
```
cd ragscraper/
scrapy crawl digalego -O output.json
```

## Executar o Scraper do diccionario da Real Academia Galega:

Instalar as novas dependencias no mesmo entorno virtual:
```
pip install selenium
```

Instalar o chromedriver: https://skolo.online/documents/webscrapping/#pre-requisites

Comprobar versión de google chrome e descargar o chromedriver correspondente no link https://developer.chrome.com/docs/chromedriver/downloads:
```
google-chrome --version
```

Descargar e instalar o chromedriver:
```
wget https://storage.googleapis.com/chrome-for-testing-public/125.0.6422.141/linux64/chromedriver-linux64.zip
sudo apt install unzip
unzip chromedriver-linux64.zip
sudo mv chromedriver-linux64 /usr/bin/chromedriver
sudo chown root:root /usr/bin/chromedriver
sudo chmod +x /usr/bin/chromedriver
```

Executar o scraper:
```
cd ragscraper/
python3 ragscraper/spiders/rag.py
```

Por cada url procesada os resultandos van gardandose no ficheiro `rag.json` no directorio actual. En caso de querer reanudar
o scraper podese cambiar a url de partida pola última procesada en lugar de comezar desde o principio. En este arquivo é importante engadir, 
facendo uso de un IDE ou un editor de código, unha coma ao final de cada termo traducido e englobar os términos en corchetes ([]) para que o arquivo sexa unha lista de termos en formato JSON.

## Executar o Scraper do traductor de Gaio:

Para executar este scraper é necesario ter instaladas as dependencias dos scrapers anteriores.

Obter as palabras comúns:
Partese de un ficheiro, en este caso 'palabras_comunes_es', que contén unha lista de palabras comúns
no idioma español separadas por saltos de liña e por comas. O escraper 'gaio.py' obtén a traducción ao galego
de cada termo usando o traductor de Gaio.

Executar o scraper:
```
cd ragscraper/
python3 ragscraper/spiders/gaio.py
```

Unha vez finalizado o scraper os resultados gardanse no ficheiro 'palabras_comuns_gl.json'.
Por último executase o seguinte comando (tendo, no mesmo directorio os arquivos 'rag.json', 'palabras_comuns_gl.json' e 'find_definicions_comuns.py'):
```
python3 find_definicions_comuns.py
```
Este último comando crea un novo ficheiro .json chamado palabras_basicas.json que contén as palabras comúns en galego e as súas definicións.
O arquivo 'find_definicions_comuns.py' busca todas as palabras traducidas no ficheiro 'rag.json' para obter as súas definicións.

## Executar o Scraper do traductor de Gaio de inglés a galego:

Para executar este scraper é necesario ter instaladas as dependencias dos scrapers anteriores. Este scraper ten un proceso similar ao scraper anterior.

Obter as palabras comúns:
Partese de un ficheiro, en este caso 'english_cambridge_pet.txt', que contén unha lista de palabras pertencentes ao nivel PET
de Cambridge separadas por saltos de liña. O escraper 'gaio_en.py' obtén a traducción ao galego de cada termo usando o traductor de Gaio.

Executar o scraper:
```
cd ragscraper/
python3 ragscraper/spiders/gaio_en.py
```

Unha vez finalizado o scraper os resultados gardanse no ficheiro 'palabras_comuns_gl_pet.json'. En este arquivo é importante engadir, facendo uso de un IDE ou un
editor de código, unha coma ao final de cada termo traducido e englobar os términos en corchetes ([]) para que o arquivo sexa unha lista de termos en formato JSON.

Por último executase o seguinte comando (tendo, no mesmo directorio os arquivos 'rag.json', 'palabras_comuns_gl_pet.json' e 'find_definiciones_comuns_en.py'):
```
python3 find_definiciones_comuns_en.py
```
Este último comando crea un novo ficheiro .json chamado palabras_basicas_pet.json que contén as palabras comúns en galego e as súas definicións.
O arquivo 'find_definiciones_comuns_en.py' busca todas as palabras traducidas no ficheiro 'rag.json' para obter as súas definicións.

## Executar o Scraper dos refráns:
Executar o scraper:
```
cd ragscraper/
python3 ragscraper/spiders/refrans.py
```

Unha vez finalizado o scraper os resultados gardanse no ficheiro 'refrans-complete.json'. A este arquivo engadese a lista de refrans do arquivo 'refrans_cervantesVirtual.txt'
obtidos de forma manual. O resultado de este proceso pode encontrarse no arquivo 'app/src/main/assets/refrans.json'.
