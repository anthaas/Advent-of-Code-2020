import java.io.File

fun main(args: Array<String>) {
    val input = File("input.txt").readLines().map {
        it.replace("ne", "a").replace("nw", "b").replace("se", "c").replace("sw", "d")
    }
    val visitedTiles = mutableMapOf<Triple<Int, Int, Int>, Int>()
    input.map {
        var x = 0
        var y = 0
        var z = 0
        it.forEach {
            when (it) {
                'a' -> {
                    x++
                    z--
                }
                'b' -> {
                    y++
                    z--
                }
                'c' -> {
                    y--
                    z++
                }
                'd' -> {
                    x--
                    z++
                }
                'e' -> {
                    x++
                    y--
                }
                'w' -> {
                    x--
                    y++
                }
                else -> error("unsupported direction")
            }
        }
        val tile = Triple(x, y, z)
        if (visitedTiles[tile] == null) visitedTiles.put(tile, 1) else visitedTiles.put(tile, visitedTiles[tile]!! + 1)
    }

    val result = visitedTiles.filter { (_, value) -> value % 2 != 0 }.size
    println(result)
}

