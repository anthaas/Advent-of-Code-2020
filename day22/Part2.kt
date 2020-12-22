import java.io.File

fun main(args: Array<String>) {
    val input = File("input.txt").bufferedReader().use { it.readText() }.split("\n\n")
        .map { it.split("\n").let { it.subList(1, it.size) }.map { it.toInt() } }

    val decks = input[0].toMutableList() to input[1].toMutableList()
    recursiveGame(decks)

    val result =
        decks.first.reversed().mapIndexed { index, l -> l.toLong() * (index + 1) }.sum() + decks.second.reversed()
            .mapIndexed { index, l -> l.toLong() * (index + 1) }.sum()
    println(result)
}

private fun recursiveGame(decks: Pair<MutableList<Int>, MutableList<Int>>): Boolean {
    val previous = mutableListOf<Pair<List<Int>, List<Int>>>()

    while (decks.first.isNotEmpty() && decks.second.isNotEmpty()) {
        if (decks in previous) {
            return true
        }
        previous.add(decks.first.toList() to decks.second.toList())
        val p1 = decks.first.removeFirst()
        val p2 = decks.second.removeFirst()
        if (decks.first.size >= p1 && decks.second.size >= p2) {
            val newDecks = decks.first.toMutableList().subList(0, p1) to decks.second.toMutableList().subList(0, p2)
            if (recursiveGame(newDecks)) {
                decks.first.add(p1)
                decks.first.add(p2)
            } else {
                decks.second.add(p2)
                decks.second.add(p1)
            }
        } else {
            if (p1 > p2) {
                decks.first.add(p1)
                decks.first.add(p2)
            } else {
                decks.second.add(p2)
                decks.second.add(p1)
            }
        }
    }

    return decks.first.size > decks.second.size
}

