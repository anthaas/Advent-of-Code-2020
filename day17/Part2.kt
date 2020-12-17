import java.io.File

private const val ALIVE = '#'

fun main(args: Array<String>) {
    val input = File("input.txt").readLines()
    var aliveCells =
        input.mapIndexed { x, s ->
            s.toCharArray().mapIndexed { y, c -> if (c == ALIVE) listOf(x, y, 0, 0) else emptyList() }
                .filter { it.isNotEmpty() }
        }.flatten()

    for (i in (1..6)) {
        aliveCells = evolve(aliveCells)
        println("end of cycle $i")
    }

    println(aliveCells.size)
}

private fun evolve(aliveCells: List<List<Int>>): List<List<Int>> {
    val cellsToCheck = aliveCells.map { getNeighborsPosition(it[0], it[1], it[2], it[3]) }.flatten().distinct()
    val aliveForNextIter = mutableListOf<List<Int>>()
    cellsToCheck.forEach {
        val currentCell = listOf(it[0], it[1], it[2], it[3])
        val neighbors = getNeighborsPosition(it[0], it[1], it[2], it[3])
        val aliveNeighbors = neighbors.map { it in aliveCells }.count { it }
        if (currentCell in aliveCells && (aliveNeighbors == 2 || aliveNeighbors == 3)) {
            aliveForNextIter.add(currentCell)
        }
        if (currentCell !in aliveCells && aliveNeighbors == 3) {
            aliveForNextIter.add(currentCell)
        }
    }

    return aliveForNextIter
}

private fun getNeighborsPosition(x: Int, y: Int, z: Int, w: Int): List<List<Int>> {
    val result = mutableListOf<List<Int>>()
    (-1..1).map { a ->
        (-1..1).map { b ->
            (-1..1).map { c ->
                (-1..1).map { d ->
                    if (!(a == 0 && b == 0 && c == 0 && d == 0)) result.add(listOf(x + a, y + b, z + c, w + d))
                }
            }
        }
    }
    return result
}
