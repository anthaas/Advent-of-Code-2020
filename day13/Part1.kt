import java.io.File

fun main(args: Array<String>) {
    val input = File("input.txt").readLines()
    val timestamp = input[0].toInt()
    val result = input[1].split(",").filterNot { it == "x" }.map { it.toInt() }.map { it to it - timestamp % it }
        .minBy { it.second }!!.toList().reduce { acc, i -> acc * i }
    println(result)

}

