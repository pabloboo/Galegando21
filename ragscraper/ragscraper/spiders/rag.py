import json
import unidecode
import string
from selenium import webdriver
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.common.by import By
from selenium.common.exceptions import NoSuchElementException


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

# Set up Chrome options
chrome_options = Options()
chrome_options.add_argument("--headless")  # Optional: run in headless mode
chrome_options.add_argument("--no-sandbox")  # Bypass OS security model
chrome_options.add_argument("--disable-dev-shm-usage")  # Overcome limited resource problems

# Path to the ChromeDriver executable
chromedriver_path = "/usr/bin/chromedriver/chromedriver"

# Set up the WebDriver service
service = Service(chromedriver_path)

# Initialize the WebDriver
driver = webdriver.Chrome(service=service, options=chrome_options)

# Load the initial page
initial_url = "https://academia.gal/dicionario/-/termo/zurrar"

# Initialize the lists for visited and to-visit links
visited_links = set()
to_visit_links = [initial_url]
number_of_links_to_visit = 1000000000000000

# Initialize an empty dictionary to store words and definitions
word_definitions = {}

while to_visit_links and len(visited_links) < number_of_links_to_visit:
    current_link = to_visit_links.pop(0)
    if current_link in visited_links:
        continue

    # Load the current link
    driver.get(current_link)
    driver.implicitly_wait(2)

    try:
        ## PALABRA
        try:
            palabra = driver.find_element(By.CSS_SELECTOR, 'span.Lemma__LemmaSign').text
        except NoSuchElementException:
            print(f"No hay palabra para: {current_link}")
            palabra = None

        if palabra is not None:
            # If the word has a comma in it, split it and get only the first part
            if "," in palabra:
                palabra = palabra.split(",")[0]
            # Transform the word to uppercase and remove accents
            palabra = custom_unidecode(palabra).upper()

            # Check if all characters in the word are in the ALFABETO
            if not all(char in ALFABETO for char in palabra) or len(palabra) < 2:
                palabra = None

        ## DEFINICIONES
        if palabra is not None:
            try:
                definiciones_elements = driver.find_elements(By.CSS_SELECTOR, 'span.Definition__Definition')
                definiciones = [element.text for element in definiciones_elements]
            except NoSuchElementException:
                print(f"No hay definiciones para: {current_link}")
                definiciones = []

            # Add word and definition to the json file
            with open("rag.json", "a", encoding="utf-8") as json_file:
                if definiciones == []:
                    json.dump({"palabra": palabra, "definicion": ""}, json_file, ensure_ascii=False, indent=4)
                else:
                    for definicion in definiciones:
                        if definicion != "":
                            json.dump({"palabra": palabra, "definicion": definicion}, json_file, ensure_ascii=False, indent=4)

        ## ENLACES
        driver.implicitly_wait(10)
        # Extract the links from the specified <ul> element
        ul_element = driver.find_element(By.CLASS_NAME, 'dicrag-proximitynouns__list')
        a_elements = ul_element.find_elements(By.TAG_NAME, 'a')

        # Retrieve the href attributes of the <a> tags
        new_links = [a.get_attribute('href') for a in a_elements]

        # Add new links to the to-visit list if not already visited
        for link in new_links:
            if link not in visited_links:
                to_visit_links.append(link)

        # Mark the current link as visited
        visited_links.add(current_link)
        print(f"Processed {current_link}")
        print(f"Number of links processed: {len(visited_links)}")

    except Exception as e:
        print(f"Error processing {current_link}: {e}")

# Clean up: quit the WebDriver
driver.quit()