import java.io.File

private const val ALIVE = '#'

fun main(args: Array<String>) {
    val input = File("input.txt").readLines()
    var aliveCells =
        input.mapIndexed { x, s ->
            s.toCharArray().mapIndexed { y, c -> if (c == ALIVE) listOf(x, y, 0) else emptyList() }
                .filter { it.isNotEmpty() }
        }.flatten()

    aliveCells = evolve(aliveCells)
    aliveCells = evolve(aliveCells)
    aliveCells = evolve(aliveCells)
    aliveCells = evolve(aliveCells)
    aliveCells = evolve(aliveCells)
    aliveCells = evolve(aliveCells)


    println(aliveCells.size)
}

private fun evolve(aliveCells: List<List<Int>>): List<List<Int>> {
    val maxX = aliveCells.map { it[0] }.max()!!
    val minX = aliveCells.map { it[0] }.min()!!
    val maxY = aliveCells.map { it[1] }.max()!!
    val minY = aliveCells.map { it[1] }.min()!!
    val maxZ = aliveCells.map { it[2] }.max()!!
    val minZ = aliveCells.map { it[2] }.min()!!
    val aliveForNextIter = mutableListOf<List<Int>>()
    for (x in minX - 1..maxX + 1) {
        for (y in minY - 1..maxY + 1) {
            for (z in minZ - 1..maxZ + 1) {
                val currentCell = listOf(x, y, z)
                val neighbors = getNeighborsPosition(x, y, z)
                val aliveNeighbors = neighbors.map { it in aliveCells }.count { it }
                if (currentCell in aliveCells && (aliveNeighbors == 2 || aliveNeighbors == 3)) {
                    aliveForNextIter.add(currentCell)
                }
                if (currentCell !in aliveCells && aliveNeighbors == 3) {
                    aliveForNextIter.add(currentCell)
                }
            }
        }
    }

    return aliveForNextIter
}

private fun getNeighborsPosition(x: Int, y: Int, z: Int): List<List<Int>> {
    val result = mutableListOf<List<Int>>()
    (-1..1).map { a ->
        (-1..1).map { b ->
            (-1..1).map { c ->
                if (!(a == 0 && b == 0 && c == 0)) result.add(listOf(x + a, y + b, z + c))
            }
        }
    }
    return result
}


