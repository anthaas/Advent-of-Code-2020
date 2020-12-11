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
    for (i in -1..1) {
        for (j in -1..1) {
            if (i == 0 && j == 0) {
                continue
            }
            count += countOccupiedInDirection(x, y, i, j, board)
        }
    }

    return count
}

fun countOccupiedInDirection(x: Int, y: Int, i: Int, j: Int, board: List<CharArray>): Int {
    var actualX = x + i
    var actualY = y + j

    while (!((actualX !in 0 until board.size) || (actualY !in 0 until board[0].size))) {
        when {
            board[actualX][actualY] == EMPTY -> return 0
            board[actualX][actualY] == OCCUPIED -> return 1
        }
        actualX += i
        actualY += j
    }

    return 0
}

private fun evolve(board: List<CharArray>): List<CharArray> {
    //rules
    //EMPTY and neighboursCount == 0 -> OCCUPIED
    //OCCUPIED and neighboursCount > 4 -> EMPTY
    //else no change

    val newBoard = MutableList(board.size) { " ".repeat(board[0].size).toCharArray() }
    for (x in (0 until board.size)) {
        for (y in (0 until board[0].size)) {
            val countNeigbours = countOccupiedNeigboursOf(x, y, board)
            newBoard[x][y] = when {
                board[x][y] == EMPTY && countNeigbours == 0 -> OCCUPIED
                board[x][y] == OCCUPIED && countNeigbours > 4 -> EMPTY
                else -> board[x][y]
            }
        }
    }

    return newBoard
}