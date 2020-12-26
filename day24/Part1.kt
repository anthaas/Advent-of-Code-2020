import java.io.File

fun main(args: Array<String>) {
    val input = File("input.txt").readLines().map {
        it.replace("ne", "a").replace("nw", "b").replace("se", "c").replace("sw", "d")
    }
    // cube coordinates
    // n: invalid
    // ne = a: x+1,y,z-1
    // e: x+1,y-1,z
    // se = c: x,y-1,z+1
    // s: invalid
    // sw = d: x-1,y,z+1
    // w: x-1,y+1,z
    // nw = b: x,y+1,z-1

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
        when (val count = visitedTiles[tile]) {
            null -> visitedTiles[tile] = 1
            else -> visitedTiles[tile] = count + 1
        }
    }

    val result = visitedTiles.filter { (_, value) -> value % 2 != 0 }.size
    println(result)
}

