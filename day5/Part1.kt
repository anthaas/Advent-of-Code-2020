import java.io.File

fun main(args: Array<String>) {
    val seats =
        File("input.txt").readLines().map { it.map { if (it == 'B' || it == 'R') '1' else '0' }.joinToString("") }
    val maxId = seats.map { Integer.parseInt(it.substring(0, 7), 2) * 8 + Integer.parseInt(it.substring(7), 2) }.max()
    println(maxId)
}

