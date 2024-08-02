import json
import unidecode
import string
from selenium import webdriver
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from selenium.common.exceptions import NoSuchElementException, ElementClickInterceptedException, ElementNotInteractableException
import time

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
url = "http://refraneirogalego.com/"
driver.get(url)
driver.implicitly_wait(5)

# Initialize an empty list to store sayings
sayings = []

# Function to scroll down the page
def scroll_down():
    driver.find_element(By.TAG_NAME, 'body').send_keys(Keys.END)
    time.sleep(2)  # Wait for new content to load

try:
    while True:
        # Get all the current titles on the page
        elements = driver.find_elements(By.CSS_SELECTOR, 'h1.entry-title a')

        for element in elements:
            saying = element.text
            if saying not in sayings:  # Avoid duplicates
                sayings.append(saying)
                with open("refrans.json", "a", encoding="utf-8") as json_file:
                    if saying is not None:
                        json.dump(saying, json_file, ensure_ascii=False)
                        json_file.write("\n")

        # Scroll down to load more content
        scroll_down()

        # Check if new content has been loaded by comparing lengths
        new_elements = driver.find_elements(By.CSS_SELECTOR, 'h1.entry-title a')
        if len(new_elements) == len(elements):
            break  # No more new content is being loaded

except NoSuchElementException:
    print("No more elements found or an error occurred.")
except Exception as e:
    print(f"An error occurred: {e}")

# Save the sayings to a JSON file
with open("refrans-complete.json", "w", encoding="utf-8") as json_file:
    json.dump(sayings, json_file, ensure_ascii=False, indent=4)

# Clean up: quit the WebDriver
driver.quit()