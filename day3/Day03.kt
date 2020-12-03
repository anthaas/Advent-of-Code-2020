import java.io.File

const val TREE = '#'

fun main(args: Array<String>) {
    val forrest = File("input.txt").readLines()

    println(getTreeHits(forrest, 3, 1))

    val slopeConfigurations = listOf(Pair(1, 1), Pair(3, 1), Pair(5, 1), Pair(7, 1), Pair(1, 2))
    val result = slopeConfigurations.map { (x, y) -> getTreeHits(forrest, x, y) }.reduce { acc, l -> acc * l }
    println(result)
}

fun getTreeHits(forrest: List<String>, shiftX: Int, shiftY: Int): Long {
    var x = 0
    var y = 0
    var treeCount = 0L

    while (y < forrest.size) {
        x %= forrest[y].length

        if (forrest[y][x] == TREE) {
            treeCount++
        }

        x += shiftX
        y += shiftY
    }

    return treeCount
}