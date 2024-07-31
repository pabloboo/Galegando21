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

@app.route('/api/desafio-personalizado', methods=['GET'])
def desafio_personalizado():
    experience = int(request.args.get('experience', 0))
    challenge = generate_challenge(experience)
    return jsonify(challenge)

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


def generate_feedback(streak, experience, badges):
    # Genera feedback basado en la racha, experiencia y número de insignias desbloqueadas
    feedback = "Tes unha racha de " + str(streak) + " días. "
    if streak < 7:
        feedback += "Trata de mellorala xogando cada día.\n\n"
    elif streak < 21:
        feedback += "Vas por bo camiño, non perdas a racha!\n\n"
    else:
        feedback += "Impresionante!\n\n"

    feedback += "Tes " + str(experience) + " puntos de experiencia. "
    if experience < 5000:
        feedback += "Canto máis xogues máis experiencia conseguirás, ánimo!\n\n"
    elif experience < 10000:
        feedback += "Estás cerca de conseguir a insignia de 10k, sigue así!\n\n"
    else:
        feedback+= "Moi ben! usa o resto de insignias de experiencia para motivarte a seguir.\n\n"

    feedback += "Tes " + str(badges) + " insignias. "

    if badges == 0:
        feedback += "Comeza por buscar unha insignia fácil de obter.\n"
    elif badges == 1:
        feedback += "Obtiveches a túa primeira insignia!\n"
    elif badges < 7:
        feedback += "Xa obtiveches varias insignias, segue así!\n"
    elif badges < 15:
        feedback += "Increíble! Tes máis insignias que a maioría, segue brillando!\n"
    elif badges == 27:
        feedback += "Noraboa! Completaches todas as insignias.\n"
    else:
        feedback += "Tes un bo número de insignias, intenta conseguilas todas!\n"

    return {
        "feedback": feedback
    }

@app.route('/api/feedback', methods=['GET'])
def get_feedback():
    streak = int(request.args.get('streak', 0))
    experience = int(request.args.get('experience', 0))
    badges = int(request.args.get('badges', 0))

    feedback = generate_feedback(streak, experience, badges)
    return jsonify(feedback)

if __name__ == '__main__':
    app.run(debug=True)