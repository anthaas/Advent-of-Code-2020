import java.io.File

private const val EMPTY = 'L'
private const val OCCUPIED = '#'


fun main(args: Array<String>) {
    var board = File("input.txt").readLines().map { it.toCharArray() }
    var nextIter = evolve(board)
    var same = areSame(board, nextIter)
    while (!same) {
        nextIter = evolve(board)
        same = areSame(board, nextIter)
        board = nextIter
    }

    val occupiedSeats = board.map { it.map { it == OCCUPIED }.count { it } }.sum()
    println(occupiedSeats)
}

private fun areSame(board: List<CharArray>, nextIter: List<CharArray>): Boolean {
    return board.zip(nextIter)
        .map { outer -> outer.first.zip(outer.second).map { inner -> inner.first == inner.second }.all { it } }
        .all { it }
}

private fun countOccupiedNeigboursOf(x: Int, y: Int, board: List<CharArray>): Int {
    var count = 0
    (-1..1).forEach { i ->
        (-1..1).forEach { j ->
            count += if (i == 0 && j == 0) 0 else countOccupiedInDirection(Pair(x + i, y + j), i, j, board)
        }
    }

    return count
}

fun countOccupiedInDirection(position: Pair<Int, Int>, i: Int, j: Int, board: List<CharArray>): Int {
    var (x, y) = position
    while (!((x !in 0 until board.size) || (y !in 0 until board[0].size))) {
        when (board[x][y]) {
            EMPTY -> return 0
            OCCUPIED -> return 1
        }
        x += i
        y += j
    }

    return 0
}

private fun evolve(board: List<CharArray>): List<CharArray> {
    val newBoard = MutableList(board.size) { " ".repeat(board[0].size).toCharArray() }
    board.forEachIndexed { x, line ->
        line.forEachIndexed { y, actualPositionSymbol ->
            newBoard[x][y] = when (actualPositionSymbol) {
                EMPTY -> if (countOccupiedNeigboursOf(x, y, board) == 0) OCCUPIED else EMPTY
                OCCUPIED -> if (countOccupiedNeigboursOf(x, y, board) > 4) EMPTY else OCCUPIED
                else -> actualPositionSymbol
            }
        }
    }

    return newBoard
}