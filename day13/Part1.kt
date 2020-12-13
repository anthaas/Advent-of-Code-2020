import java.io.File

fun main(args: Array<String>) {
    val input = File("input.txt").readLines()
    val timestamp = input[0].toInt()
    val busIds = input[1].split(",").filterNot { it == "x" }.map { it.toInt() }
    val departments = busIds.map { busId -> busId to getMinimumGreaterThan(busId, timestamp) - timestamp }
    val result = departments.minBy { it.second }!!.toList().reduce { acc, i -> acc * i }
    println(result)

}

private fun getMinimumGreaterThan(number: Int, limit: Int): Int {
    var acc = 0
    while (acc < limit) {
        acc += number
    }

    return acc
}


