from selenium import webdriver
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.common.exceptions import NoSuchElementException
from selenium.common.exceptions import TimeoutException
from selenium.webdriver.common.action_chains import ActionChains
import time
import os
import json
import unidecode
import string

## OBTENCIÓN DE PALABRAS COMUNES EN ESPAÑOL
directorio_actual = os.getcwd()
print("Directorio actual:", directorio_actual)
ruta_archivo = './palabras_comunes_es'
# Función para obtener las palabras comunes en español
def obtener_palabras():
    # Abrir el archivo y leer las líneas
    with open(ruta_archivo, 'r') as file:
        palabras = file.readlines()

    # Eliminar los saltos de línea y separar las palabras por comas o espacios
    palabras_separadas = []
    for linea in palabras:
        palabras_separadas.extend(linea.strip().split(', '))

    # Imprimir el array de palabras
    #print(palabras_separadas)
    return palabras_separadas

## FILTROS PARA ELIMINAR PALABRAS NO DESEADAS
ALFABETO = "ABCDEFGHILMNÑOPQRSTUVXZ"
def custom_unidecode(input_string):
    # Convertir la cadena de entrada a una lista de caracteres
    characters = list(input_string)
    for i in range(len(characters)):
        # Solo desacentuar el carácter si no es 'ñ'
        if characters[i] != 'ñ' and characters[i] != 'Ñ':
            characters[i] = unidecode.unidecode(characters[i])
    # Convertir la lista de caracteres de vuelta a una cadena
    return ''.join(characters)

## TRADUCCIÓN DE PALABRAS
# Set up Chrome options
chrome_options = Options()
chrome_options.add_argument("--headless")  # Optional: run in headless mode
chrome_options.add_argument("--no-sandbox")  # Bypass OS security model
chrome_options.add_argument("--disable-dev-shm-usage")  # Overcome limited resource problems

# Path to the ChromeDriver executable
chromedriver_path = "/usr/bin/chromedriver/chromedriver"

# Set up the WebDriver service
service = Service(chromedriver_path)

# Load the initial page
url = "https://tradutorgaio.xunta.gal/TradutorPublico/traducir/index"

#textos = ["Hola", "¿Cómo estás?", "¿Qué tal?", "¿Qué haces?", "¿Qué pasa?"]
textos = obtener_palabras()

# Conjunto para guardar los resultados de traducción
resultados_traduccion = set()
number_of_words_to_translate = 1000000000000000

for texto in textos:
    if len(resultados_traduccion) >= number_of_words_to_translate:
        break

    try:
        # Initialize the WebDriver (se inicializa en cada iteración para evitar el captcha que aparece cada cierto número de peticiones)
        driver = webdriver.Chrome(service=service, options=chrome_options)
        driver.get(url)
        driver.implicitly_wait(3)

        # Wait until the textarea is present and interactable
        textarea = WebDriverWait(driver, 40).until(
            EC.presence_of_element_located((By.ID, "cuadrotexto"))
        )
        textarea.clear()
        texto_deseado = texto
        textarea.send_keys(texto_deseado)

        # Wait for the text to be fully entered
        WebDriverWait(driver, 10).until(
            lambda driver: textarea.get_attribute('value') == texto_deseado
        )

        # Click the "Traducir" button
        traducir_button = WebDriverWait(driver, 20).until(
            EC.element_to_be_clickable((By.ID, "traducir-texto"))
        )
        traducir_button.click()

        # Wait for the translation result to be visible
        time.sleep(3)
        resultado = WebDriverWait(driver, 60).until(
            EC.presence_of_element_located((By.ID, "contenedor"))
        )

        # Print the translation result
        print(f"Original: {texto}")
        palabra = custom_unidecode(resultado.text).upper()
        print(f"Traducido: {palabra}")
        resultados_traduccion.add(palabra)

    except NoSuchElementException:
        print("El elemento no se encontró en la página")
    except TimeoutException:
        print("Se agotó el tiempo de espera para encontrar el elemento")
    finally:
        driver.quit()

# Save to a JSON file
resultados_traduccion_lista = list(resultados_traduccion)
with open("palabras_comuns_gl.json", "w", encoding="utf-8") as json_file:
    json.dump(resultados_traduccion_lista, json_file, ensure_ascii=False, indent=4)

# Clean up: quit the WebDriver
driver.quit()