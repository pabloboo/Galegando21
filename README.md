# Galegando21
Aplicación móvil para fomentar o uso do galego

## Execución do Scraper

Abrir o proxecto en unha terminal ubuntu (wsl de windows por exemplo)

Crear o entorno virtual:
```
python3 -m venv venv
source .venv/bin/activate
pip3 install scrapy
pip3 install Unidecode
```

Executar o Scraper:
```
cd ragscraper/
scrapy crawl digalego -O output.json
```