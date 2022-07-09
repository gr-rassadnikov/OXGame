package com.example.oxgame

import android.graphics.Color
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.oxgame.game.Board
import com.example.oxgame.game.GameResult
import com.example.oxgame.game.MNKPlayers.HumanPlayer
import com.example.oxgame.game.MNKPlayers.Player
import com.example.oxgame.game.TicTacToeBoard


class TwoPlayerGame(
    private val board: Board,
    public val player1: Player,
    public val player2: Player
) {

    fun makeMove(player: Player, no: Int, log: Boolean, i: Int, j: Int): Int {
        val move = player.makeMove(board.getPosition(), board.getField(), i, j)
        var result = board.makeMove(move)

        return when (result) {
            GameResult.WIN -> no
            GameResult.LOOSE -> 3 - no
            GameResult.DRAW -> 0
            GameResult.UNKNOWN -> -1
            else -> throw AssertionError("Unknown makeMove result $result")
        }
    }

    init {
        player1.n = board.getN()
        player1.m = board.getM()
        player2.n = board.getN()
        player2.m = board.getM()
        player1.setRival(player2)
        player2.setRival(player1)
    }
}

private enum class Symbol {
    O, X, E
}

val colorO = Color.RED
val colorX = Color.BLUE


private fun getNextMove(symbol: Symbol): Symbol {
    return if (symbol == Symbol.O) Symbol.X else Symbol.O
}

private class DataResult() {
    var cnt = 0
    var winO = 0
    var winX = 0
}

private class ButtonsControl(
    val m: Int = 3,
    val n: Int = 3,
    val k: Int = 3,
    val listButton: List<Button>,
    var game: TwoPlayerGame,
    val textViewResult: TextView,
    val textViewProcess: TextView,
    val textViewCntO: TextView,
    val textViewCntX: TextView,

    ) {
    private val size = listButton.size
    private var used: MutableList<Boolean> = MutableList(size) { i -> false }
    private var move: Symbol = Symbol.X
    private var firstMove: Symbol = Symbol.X
    private var dataResult = DataResult()


    fun click(number: Int) {
        if (!used[number]) {
            showButtonBoard(listButton[number], move)
            val result = game.makeMove(
                if (move == Symbol.X) game.player1 else game.player2,
                if (move == Symbol.X) 1 else 2, true, number / n, number % n
            )
            when (result) {
                1 -> {
                    dataResult.winX++
                    showTextViewCnt(textViewCntX, dataResult.winX)

                }
                2 -> {
                    dataResult.winO++
                    showTextViewCnt(textViewCntO, dataResult.winO)
                }
            }
            showTextViewResult(textViewResult, result, move)
            move = getNextMove(move)
            showTextViewProcess(textViewProcess, result, move)
            used[number] = true
        }
    }

    fun restart() {
        move = Symbol.X
        firstMove = move
        dataResult = DataResult()
        used = MutableList(size) { i -> false }
        for (button in listButton) {
            showButtonBoard(button, Symbol.E)
        }
        showTextViewResult(textViewResult, -1, move)
        showTextViewProcess(textViewProcess, -1, move)
        showTextViewCnt(textViewCntO, 0)
        showTextViewCnt(textViewCntX, 0)
        game = TwoPlayerGame(
            TicTacToeBoard(m, n, k), HumanPlayer(), HumanPlayer()
        )
    }

    fun nextRound() {
        dataResult.cnt++
        move = if (dataResult.cnt % 2 == 0) Symbol.X else Symbol.O
        firstMove = move
        used = MutableList(size) { i -> false }
        for (button in listButton) {
            showButtonBoard(button, Symbol.E)
        }
        showTextViewResult(textViewResult, -1, move)
        showTextViewProcess(textViewProcess, -1, move)
        game = TwoPlayerGame(
            TicTacToeBoard(m, n, k), HumanPlayer(), HumanPlayer()
        )
    }

    private fun showButtonBoard(button: Button, symbol: Symbol) {
        when (symbol) {
            Symbol.O -> setStyleO(button)
            Symbol.X -> setStyleX(button)
            Symbol.E -> setStyleE(button)
        }
    }

    private fun showTextViewResult(textViewResult: TextView, result: Int, move: Symbol) {
        when (result) {
            1, 2 -> {
                textViewResult.setText("win ${if (move == Symbol.X) "X" else "O"}")
                textViewResult.setTextColor(if (move == Symbol.X) colorX else colorO)
            }
            0 -> textViewResult.setText("draw")
            -1 -> textViewResult.setText("")
        }
    }

    private fun showTextViewProcess(textView: TextView, result: Int, move: Symbol) {
        if (result == -1) {
            when (move) {
                Symbol.X -> {
                    textView.setText("X")
                    textView.setTextColor(colorX)
                }
                Symbol.O -> {
                    textView.setText("O")
                    textView.setTextColor(colorO)
                }
                else -> return
            }
        }

    }

    private fun showTextViewCnt(textView: TextView, cnt: Int) {
        textView.setText("${cnt}")
    }


    private fun setStyleO(button: Button) {
        button.setText("O")
        button.setTextColor(colorO)
    }

    private fun setStyleX(button: Button) {
        button.setText("X")
        button.setTextColor(colorX)
    }

    private fun setStyleE(button: Button) {
        button.setText("")
    }

    init {
        showTextViewProcess(textViewProcess, -1, move)

    }
}


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val m = 3
        val n = 4
        val k = 3
        val size = m * n
        val board = TicTacToeBoard(m, n, k)

        val tableView: TableLayout = findViewById(R.id.listButton)
        val textViewResult: TextView = findViewById(R.id.textViewResult)
        val buttonRestart: Button = findViewById(R.id.buttonRestart)
        val buttonNextRound: Button = findViewById(R.id.buttonNextRound)
        val textViewCntO: TextView = findViewById(R.id.textViewCntO)
        val textViewCntX: TextView = findViewById(R.id.textViewCntX)
        val textViewCntHeadO: TextView = findViewById(R.id.textViewCntHeadO)
        val textViewCntHeadX: TextView = findViewById(R.id.textViewCntHeadX)
        val textViewProcess: TextView = findViewById(R.id.textViewProcess)

        val listButton: MutableList<Button> = mutableListOf()

        val forRow = TableRow.LayoutParams(
            TableRow.LayoutParams.WRAP_CONTENT,
            TableRow.LayoutParams.WRAP_CONTENT
        )
        val forTable: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.FILL_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        for (i in 0 until m) {
            val newRow: TableRow = TableRow(this)
            for (j in 0 until n) {
                val newButton: Button = Button(this)
                newButton.setLayoutParams(forRow)
                newRow.addView(newButton)
                listButton.add(newButton)
            }
            newRow.setLayoutParams(forTable)
            tableView.addView(newRow)
        }


        val buttonsControl: ButtonsControl = ButtonsControl(
            m, n, k,
            listButton,
            TwoPlayerGame(board, HumanPlayer(), HumanPlayer()),
            textViewResult,
            textViewProcess,
            textViewCntO,
            textViewCntX,
        )


        buttonRestart.setOnClickListener {
            buttonsControl.restart()
        }
        buttonNextRound.setOnClickListener {
            buttonsControl.nextRound()
        }

        for (number in 0 until size) {
            buttonsControl.listButton[number].setOnClickListener {
                buttonsControl.click(number)
            }
        }
    }
}