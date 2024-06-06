import scrapy
from scrapy import Request

class DigalegoSpider(scrapy.Spider):
    name = 'digalego'
    start_urls = ['https://digalego.xunta.gal/es/termo/65615/zafiro']

    def start_requests(self):
        headers = {
            'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/125.0.0.0 Safari/537.36'
        }
        for url in self.start_urls:
            yield scrapy.Request(url, headers=headers, callback=self.parse)

    def parse(self, response):
        palabra = response.css('h1.page-header::text').get()
        definicion = response.css('span.xeslin-detalle-acepcions-acepcion p::text').get()

        yield {
            'palabra': palabra,
            'definicion': definicion.strip() if definicion else None,  # Elimina espacios en blanco adicionales
        }

        # Extraer el primer enlace de la lista de enlaces de palabras vecinas
        primer_enlace = response.css('span.xeslin-detalle-neighbours-antes a::attr(href)').get()
        url_completa = response.urljoin(primer_enlace)
        start_urls = [url_completa]
        yield Request(url_completa)