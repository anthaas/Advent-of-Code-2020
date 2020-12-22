import java.io.File

fun main(args: Array<String>) {
    val input = File("input.txt").bufferedReader().use { it.readText() }.split("\n\n")
        .map { it.split("\n").let { it.subList(1, it.size) }.map { it.toLong() } }
    val player1 = input[0].toMutableList()
    val player2 = input[1].toMutableList()

    while (player1.isNotEmpty() && player2.isNotEmpty()) {
        val p1 = player1.removeFirst()
        val p2 = player2.removeFirst()
        if (p1 > p2) {
            player1.add(p1)
            player1.add(p2)
        } else {
            player2.add(p2)
            player2.add(p1)
        }
    }

    val result = player1.reversed().mapIndexed { index, l -> l * (index + 1) }.sum() + player2.reversed()
        .mapIndexed { index, l -> l * (index + 1) }.sum()
    println(result)
}

