from flask import Flask, request, jsonify

app = Flask(__name__)

def generate_challenge(experience):
    # Genera un desafío basado en las estadísticas del usuario
    if experience > 5000:
        desafio = "Completa o rosco de Pasagalego con máis de 15 acertos en menos de 3 minutos."
    else:
        desafio = "Completa o rosco de Pasagalego con máis de 8 acertos."

    return {
        "desafio": desafio
    }

eventos = [
    {
        "nome": "Visita a MEGA",
        "data": "01/01/2022-31/12/2030",
        "ubicacion": "Museo MEGA, A Couruña",
        "descricion": "Visita a MEGA - Mundo Estrella Galicia en A Coruña."
    },
    {
        "nome": "Festival de la Luz",
        "data": "23/08/2024-25/08/2024",
        "ubicacion": "Boimorto, A Coruña",
        "descricion": "Un festival solidario de música e arte."
    },
    {
        "nome": "Caudal Fest",
        "data": "20/09/2024",
        "ubicacion": "Lugo",
        "descricion": "Un dos principais festivais de Galicia."
    },
    {
        "nome": "Ana Mena Tour 2024",
        "data": "14/12/2024",
        "ubicacion": "Colliseum, A Coruña",
        "descricion": "A sensación do pop nacional Ana Mena regresa a A Coruña para un concerto único."
    }
]

@app.route('/api/eventos', methods=['GET'])
def get_eventos():
    return jsonify(eventos)

if __name__ == '__main__':
    app.run(debug=True)