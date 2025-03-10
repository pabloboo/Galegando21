package com.galegando21.day22Brisca

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.galegando21.R
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import com.galegando21.utils.setOnBackPressed
import kotlin.random.Random
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.view.ViewGroup
import android.view.animation.LinearInterpolator

class BriscaGameActivity : AppCompatActivity() {
    private lateinit var playerHand: LinearLayout
    private lateinit var machineHand: LinearLayout
    private lateinit var playedCards: LinearLayout
    private lateinit var deck: ImageView
    private lateinit var trumpCard: ImageView

    private val deckCards = mutableListOf<Card>()
    private val playerCards = mutableListOf<Card>()
    private val machineCards = mutableListOf<Card>()
    private val playedTrick = mutableListOf<Card>()
    private var trump: Card? = null

    private var machinePoints = 0
    private var playerPoints = 0
    private var nextTurn = "player"
    private var allCardsDealed = false
    private var modo = "facil"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_brisca_game)

        playerHand = findViewById(R.id.player_hand)
        machineHand = findViewById(R.id.machine_hand)
        playedCards = findViewById(R.id.played_cards)
        deck = findViewById(R.id.deck)
        trumpCard = findViewById(R.id.trump_card)

        modo = intent.getStringExtra("modo") ?: "facil"

        initGame()

        setOnBackPressed(this, BriscaInicioActivity::class.java)
    }

    private fun initGame() {
        // Inicializar la baraja y barajarla
        deckCards.addAll(generateDeck())
        deckCards.shuffle()

        // Repartir las cartas iniciales
        repeat(3) {
            Handler(Looper.getMainLooper()).postDelayed({
                dealNextCards()
            }, (it * 1000).toLong())
        }

        // Establecer la carta de triunfo
        trump = deckCards.removeAt(0)
        trumpCard.setImageResource(trump!!.imageRes)

        // Actualizar la interfaz de usuario con las cartas iniciales
        updatePlayerHand()
        updateMachineHand()
    }

    private fun generateDeck(): List<Card> {
        val suits = listOf("oros", "copas", "espadas", "bastos")
        val values = listOf(1, 2, 3, 4, 5, 6, 7, 10, 11, 12)
        val points = mapOf(1 to 11, 3 to 10, 12 to 4, 11 to 3, 10 to 2)
        val deck = mutableListOf<Card>()
        for (suit in suits) {
            for (value in values) {
                val point = points.getOrDefault(value, 0)
                deck.add(Card(suit, value, getImageRes(suit, value), point))
            }
        }
        return deck
    }

    private fun getImageRes(suit: String, value: Int): Int {
        val resourceName = "card_${suit}_${value}"
        return resources.getIdentifier(resourceName, "drawable", packageName)
    }

    private fun updatePlayerHand(unsetListener: Boolean = false) {
        playerHand.removeAllViews()
        for (card in playerCards) {
            playerHand.addView(createCardView(card, isPlayer = true, unSetListenerPlayer = unsetListener))
        }
    }

    private fun updateMachineHand() {
        machineHand.removeAllViews()
        for (card in machineCards) {
            machineHand.addView(createCardView(card, isMachine = true))
        }
    }

    private fun playCard(card: Card, isPlayer: Boolean) {
        if (isPlayer) {
            playerCards.remove(card)
        } else {
            machineCards.remove(card)
        }
        playedTrick.add(card)
        playedCards.addView(createCardView(card))

        if (playedTrick.size == 2) {
            determineWinner()
        } else if (playedTrick.size == 1 && nextTurn == "player") {
            // Si es la primera carta de la baza, es el turno de la máquina para jugar una carta
            machineTurn()
        }

        updateMachineHand()
    }

    private fun machineTurn() {
        if (machineCards.isNotEmpty()) {
            if (modo == "facil") {
                machineTurnEasy()
            } else {
                machineTurnDifficult()
            }
        }
    }

    private fun machineTurnEasy() {
        val randomIndex = Random.nextInt(machineCards.size)
        val selectedCard = machineCards.removeAt(randomIndex)
        playCard(selectedCard, isPlayer = false)
    }

    private fun machineTurnDifficult() {
        if (machineCards.isNotEmpty()) {
            val selectedCard = when {
                // Si la máquina tiene el primer turno, juega la carta de menor valor posible
                playedTrick.isEmpty() -> machineCards.minByOrNull { it.points }

                // Si la máquina tiene el segundo turno
                else -> {
                    val playerCard = playedTrick[0]

                    when {
                        // Si la máquina tiene una carta de mayor valor del mismo palo (que no sea triunfo))
                        machineCards.any { it.suit == playerCard.suit && it.points > playerCard.points && playerCard.suit != trump!!.suit} -> {
                            machineCards.first { it.suit == playerCard.suit && it.points > playerCard.points && playerCard.suit != trump!!.suit}
                        }

                        // Si la carta jugada es un as o un tres (sin ser triunfo) y la máquina tiene un triunfo
                        (playerCard.value in listOf(1, 3) && playerCard.suit != trump?.suit && machineCards.any { it.suit == trump?.suit }) -> {
                            machineCards.first { it.suit == trump?.suit }
                        }

                        // En cualquier otro caso, juega la carta de menor puntuación
                        else -> machineCards.minByOrNull { it.points }
                    }
                }
            }

            selectedCard?.let {
                machineCards.remove(it)
                playCard(it, isPlayer = false)
            }
        }
    }

    // Determinar el ganador de la baza
    private fun determineWinner() {
        // Si es el turno del jugador, la primera carta es la del jugador
        var playerCard = playedTrick[0]
        var machineCard = playedTrick[1]
        if (nextTurn == "player") {
            playerCard = playedTrick[0]
            machineCard = playedTrick[1]
        } else {
            playerCard = playedTrick[1]
            machineCard = playedTrick[0]
        }
        val trumpSuit = trump!!.suit

        Log.d("BriscaGameActivity", "Player card: ${playerCard.suit} ${playerCard.value} ${playerCard.points}")
        Log.d("BriscaGameActivity", "Machine card: ${machineCard.suit} ${machineCard.value} ${machineCard.points}")
        val winningCard = determineTrickWinner(playerCard, machineCard, trumpSuit)

        if (winningCard == playerCard) {
            Log.d("BriscaGameActivity", "Player wins the trick")
            for (card in playedTrick) {
                playerPoints += card.points
            }
            nextTurn = "player"
        } else {
            Log.d("BriscaGameActivity", "Machine wins the trick")
            for (card in playedTrick) {
                machinePoints += card.points
            }
            nextTurn = "machine"
        }

        // Limpiar las cartas jugadas
        playedTrick.clear()
        updatePlayerHand(unsetListener = true)
        playedCards.postDelayed({
            playedCards.removeAllViews()

            // Coger dos cartas del mazo
            dealNextCards()

            val gameEnd = checkGameEnd()

            if (!gameEnd && nextTurn == "machine") {
                machineTurn()
            }
        }, 3000)
    }

    private fun determineTrickWinner(playerCard: Card, machineCard: Card, trumpSuit: String): Card {
        val winningCard = when {
            // Si es el mismo palo gana el más alto
            playerCard.suit == machineCard.suit && playerCard.points > machineCard.points -> playerCard
            playerCard.suit == machineCard.suit && playerCard.points < machineCard.points -> machineCard
            playerCard.suit == machineCard.suit && playerCard.points == machineCard.points -> { // Si la puntuación es la misma se mira el valor de la carta
                if (playerCard.value > machineCard.value) playerCard else machineCard
            }
            // Si no es el mismo palo, gana el triunfo
            playerCard.suit == trumpSuit -> playerCard
            machineCard.suit == trumpSuit -> machineCard
            else -> { // Si no hay triunfo, gana el primer jugador
                if (nextTurn == "player") playerCard else machineCard
            }
        }
        return winningCard
    }

    private fun dealNextCards() {
        if (!allCardsDealed) {
            animateCard(deck, playerHand)
            animateCard(deck, machineHand)
            Handler(Looper.getMainLooper()).postDelayed({
                if (nextTurn == "player") {
                    if (deckCards.isNotEmpty()) {
                        playerCards.add(deckCards.removeAt(0))
                    }
                    if (deckCards.isNotEmpty()) {
                        machineCards.add(deckCards.removeAt(0))
                    } else { // Si no quedan cartas en el mazo, se coge el triunfo
                        machineCards.add(trump!!)
                        allCardsDealed = true
                        findViewById<RelativeLayout>(R.id.deck_area).visibility = View.GONE
                    }
                } else {
                    if (deckCards.isNotEmpty()) {
                        machineCards.add(deckCards.removeAt(0))
                    }
                    if (deckCards.isNotEmpty()) {
                        playerCards.add(deckCards.removeAt(0))
                    } else { // Si no quedan cartas en el mazo, se coge el triunfo
                        playerCards.add(trump!!)
                        allCardsDealed = true
                        findViewById<RelativeLayout>(R.id.deck_area).visibility = View.GONE
                    }
                }
                // Actualizar la IU con las cartas nuevas
                updatePlayerHand()
                updateMachineHand()
            }, 1000)
        } else {
            updatePlayerHand()
            updateMachineHand()
        }
    }

    private fun exchangeTrumpCard(sevenTrump: Card) {
        // Encuentra el índice de la carta del siete del triunfo en la mano del jugador
        val index = playerCards.indexOf(sevenTrump)

        // Intercambia la carta del siete del triunfo con la carta del triunfo
        playerCards[index] = trump!!
        trump = sevenTrump

        // Actualiza la vista de la carta del triunfo
        trumpCard.setImageResource(trump!!.imageRes)

        // Actualiza la mano del jugador
        updatePlayerHand()
    }

    private fun checkGameEnd(): Boolean {
        if (playerCards.isEmpty() && machineCards.isEmpty() && deckCards.isEmpty()) {
            Intent(this, BriscaResultsActivity::class.java).also {
                it.putExtra("modo", modo)
                it.putExtra("player_points_brisca", playerPoints)
                it.putExtra("computer_points_brisca", machinePoints)
                startActivity(it)
                finish()
                return true
            }
            return true
        }
        return false
    }

    private fun createCardView(card: Card, isPlayer: Boolean = false, isMachine: Boolean = false, unSetListenerPlayer: Boolean = false): ImageView {
        val cardView = ImageView(this)
        cardView.setImageResource(card.imageRes)

        if (isMachine) {
            cardView.setImageResource(R.drawable.card_back)
        }

        val layoutParams = LinearLayout.LayoutParams(100, 150)
        cardView.layoutParams = layoutParams

        if (isPlayer) {
            cardView.setOnClickListener { playCard(card, isPlayer = true) }
            cardView.setOnLongClickListener {
                if ((card.suit == trump?.suit && card.value == 7) || // El siete del triunfo se puede intercambiar por la carta del triunfo
                    (card.suit == trump?.suit && card.value == 2 && trump?.value in 4..7)) { // El dos del triunfo se puede intercambiar por la carta del triunfo si el triunfo es 7,6,5 o 4
                    exchangeTrumpCard(card)
                    true
                } else {
                    false
                }
            }
        }
        if (unSetListenerPlayer) { // Mientras se muestra la baza, no se puede jugar
            cardView.setOnClickListener(null)
        }

        return cardView
    }

    private fun animateCard(fromView: View, toView: View, duration: Long = 1000) {
        val cardView = ImageView(this)
        cardView.setImageResource(R.drawable.card_back)
        val layoutParams = LinearLayout.LayoutParams(100, 150)
        cardView.layoutParams = layoutParams

        val mainLayout = findViewById<RelativeLayout>(R.id.activity_brisca_game_relative_layout)
        mainLayout.addView(cardView)

        val startLocation = IntArray(2)
        fromView.getLocationOnScreen(startLocation)

        val endLocation = IntArray(2)
        toView.getLocationOnScreen(endLocation)

        // Calcular las posiciones centrales de los views
        val startX = startLocation[0] + fromView.width / 2f - cardView.width / 2f
        val startY = startLocation[1] + fromView.height / 2f - cardView.height / 2f
        val endX = endLocation[0] + toView.width / 2f - cardView.width / 2f
        val endY = endLocation[1] + toView.height / 2f - cardView.height / 2f

        val xAnimator = ObjectAnimator.ofFloat(cardView, "x", startX, endX)
        val yAnimator = ObjectAnimator.ofFloat(cardView, "y", startY, endY)

        xAnimator.duration = duration
        yAnimator.duration = duration

        xAnimator.interpolator = LinearInterpolator()
        yAnimator.interpolator = LinearInterpolator()

        xAnimator.start()
        yAnimator.start()

        yAnimator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                (cardView.parent as ViewGroup).removeView(cardView)
            }
        })
    }
}

data class Card(val suit: String, val value: Int, val imageRes: Int, val points: Int)