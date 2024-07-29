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

@app.route('/api/desafio-personalizado', methods=['POST'])
def desafio_personalizado():
    data = request.json
    experience = data.get('experience')

    challenge = generate_challenge(experience)
    return jsonify(challenge)

if __name__ == '__main__':
    app.run(debug=True)