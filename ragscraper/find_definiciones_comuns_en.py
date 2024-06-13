import json

# Cargar el archivo palabras_comuns_gl_pet.json
with open('palabras_comuns_gl_pet.json', 'r', encoding='utf-8') as f:
    palabras_comuns_pet = json.load(f)

# Cargar el archivo digalego.json
with open('digalego.json', 'r', encoding='utf-8') as f:
    digalego = json.load(f)

# Diccionario para almacenar las definiciones encontradas
definiciones_encontradas = {}

# Iterar sobre las palabras comunes gallegas PET
for entrada in palabras_comuns_pet:
    palabra = entrada['palabra']
    # Buscar la palabra en el archivo digalego
    for item in digalego:
        if item['palabra'].upper() == palabra.upper():
            definiciones_encontradas[palabra] = item['definicion']
            break  # Si encuentra la palabra, deja de buscar

# Pasar palabras y definiciones a un archivo JSON
with open('palabras_basicas_pet.json', 'w', encoding='utf-8') as f:
    json.dump(definiciones_encontradas, f, ensure_ascii=False, indent=4)