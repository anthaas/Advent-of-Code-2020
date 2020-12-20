import java.io.File

fun main(args: Array<String>) {
    val tiles = File("input.txt").bufferedReader().use { it.readText() }.split("\n\n")
        .map { it.split("\n").let { it[0].replace(":", "").split(" ")[1].toLong() to it.subList(1, it.size) } }

    val result = tiles.map { tile1 ->
        var count = 0
        tiles.map { tile2 ->
            if (tile1.first != tile2.first) {
                getTileEdges(tile1.second).map { if (it in getTileEdges(tile2.second)) count++ }
                getTileEdges(tile1.second).map { if (it in getTileEdges(tile2.second).map { it.reversed() }) count++ }
            }
        }
        tile1.first to count
    }.filter { it.second == 2 }.map { it.first }.reduce { acc, l -> acc * l }

    println(result)
}

private fun getTileEdges(tile: List<String>): List<String> = listOf(
    tile[0],
    tile.map { it.takeLast(1) }.joinToString(""),
    tile.last(),
    tile.map { it[0] }.joinToString("")
)
