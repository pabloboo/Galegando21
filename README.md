# Galegando21
Aplicación móvil para fomentar o uso do galego

## Execución dos Scrapers

Abrir o proxecto en unha terminal ubuntu (wsl de windows por exemplo)

Crear o entorno virtual:
```
python3 -m venv venv
source .venv/bin/activate
pip3 install scrapy
pip3 install Unidecode
```

### Executar o Scraper de Digalego:
```
cd ragscraper/
scrapy crawl digalego -O output.json
```

### Executar o Scraper do diccionario da Real Academia Galega:

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
cd rag_scraper/
python3 ragscraper/spiders/rag.py
```

Os resultados gardanse no ficheiro `rag.json` na o directorio actual.


### Executar o Scraper do traductor de Gaio:

Para executar este scraper é necesario ter instaladas as dependencias dos scrapers anteriores.

Obter as palabras comúns:
Partese de un ficheiro, en este caso 'palabras_comunes_es', que contén unha lista de palabras comúns
no idioma español separadas por saltos de liña e por comas. O escraper 'gaio.py' obtén a traducción ao galego
de cada termo usando o traductor de Gaio.

Executar o scraper:
```
cd rag_scraper/
python3 ragscraper/spiders/gaio.py
```

Unha vez finalizado o scraper os resultados gardanse no ficheiro 'palabras_comuns_gl.json'.
Por último executase o seguinte comando (tendo, no mesmo directorio os arquivos 'digalego.json', 'palabras_comuns_gl.json' e 'find_definicions_comuns.py'):
```
python3 find_definicions_comuns.py
```
Este último comando crea un novo ficheiro .json chamado palabras_basicas.json que contén as palabras comúns en galego e as súas definicións.
O arquivo 'find_definicions_comuns.py' busca todas as palabras traducidas no ficheiro 'digalego.json' para obter as súas definicións.