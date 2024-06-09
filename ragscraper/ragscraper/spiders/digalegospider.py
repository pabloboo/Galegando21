import json
import unidecode
import scrapy
from scrapy import Request
import string

ALFABETO = "ABCDEFGHILMNÑOPQRSTUVXZ"

class DigalegoSpider(scrapy.Spider):
    name = 'digalego'
    start_urls = ['https://digalego.xunta.gal/es/termo/65888/zurron']

    def start_requests(self):
        headers = {
            'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/125.0.0.0 Safari/537.36'
        }
        for url in self.start_urls:
            yield scrapy.Request(url, headers=headers, callback=self.parse)

    def parse(self, response):
        palabra = response.css('h1.page-header::text').get()
        definicion = response.css('span.xeslin-detalle-acepcions-acepcion p::text').get()

        # Elimina espacios en blanco adicionales
        definicion = definicion.strip() if definicion else None

        # Procesamiento de la palabra y definición
        palabra = self.custom_unidecode(palabra).upper()
        palabra = palabra.split(" ")[0]

        # Comprobar si todos los caracteres de la palabra están en ALFABETO
        if all(char in ALFABETO for char in palabra):
            if definicion is None or definicion == "" or definicion == "." or definicion[-1] != ".":
                definicion = None

            if definicion and len(palabra) > 1:
                yield {
                    'palabra': palabra,
                    'definicion': definicion
                }

        # Extraer los tres primeros enlaces de la lista de enlaces de palabras vecinas
        enlaces = response.css('span.xeslin-detalle-neighbours-antes a::attr(href)')
        primer_enlace = enlaces[0].get() if enlaces else None
        segundo_enlace = enlaces[1].get() if enlaces else None
        tercer_enlace = enlaces[2].get() if enlaces else None

        if primer_enlace:
            url_completa_primer_enlace = response.urljoin(primer_enlace)
            print(f"Primer enlace: {url_completa_primer_enlace}")
            yield Request(url_completa_primer_enlace)

        if segundo_enlace:
            url_completa_segundo_enlace = response.urljoin(segundo_enlace)
            print(f"Segundo enlace: {url_completa_segundo_enlace}")
            yield Request(url_completa_segundo_enlace)

        if tercer_enlace:
            url_completa_tercer_enlace = response.urljoin(tercer_enlace)
            print(f"Tercer enlace: {url_completa_tercer_enlace}")
            yield Request(url_completa_tercer_enlace)

    def custom_unidecode(self, input_string):
        # Convertir la cadena de entrada a una lista de caracteres
        characters = list(input_string)
        for i in range(len(characters)):
            # Solo desacentuar el carácter si no es 'ñ'
            if characters[i] != 'ñ' and characters[i] != 'Ñ':
                characters[i] = unidecode.unidecode(characters[i])
        # Convertir la lista de caracteres de vuelta a una cadena
        return ''.join(characters)