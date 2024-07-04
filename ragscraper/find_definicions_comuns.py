import json

# Cargar el archivo palabras_comuns_gl.json
with open('palabras_comuns_gl.json', 'r', encoding='utf-8') as f:
    palabras_comuns = json.load(f)

# Cargar el archivo rag.json
with open('rag.json', 'r', encoding='utf-8') as f:
    rag = json.load(f)

# Diccionario para almacenar las definiciones encontradas
definiciones_encontradas = {}

# Iterar sobre las palabras comunes gallegas
for palabra in palabras_comuns:
    # Buscar la palabra en el archivo rag
    for item in rag:
        if item['palabra'].upper() == palabra.upper():
            if item['definicion'] != '':
                definiciones_encontradas[palabra] = item['definicion']
            break  # Si encuentra la palabra, deja de buscar

# Pasar palabras y definiciones a un archivo JSON
with open('palabras_basicas.json', 'w', encoding='utf-8') as f:
    json.dump(definiciones_encontradas, f, ensure_ascii=False, indent=4)