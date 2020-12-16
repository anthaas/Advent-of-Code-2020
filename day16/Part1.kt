import java.io.File

fun main(args: Array<String>) {
    val input = File("input.txt").bufferedReader().use { it.readText() }.split("\n\n")
    val rules = input[0].split("\n").map {
        it.split(":")[1].split(" or ")
            .map { it.split('-').let { (low, high) -> low.trim().toInt() to high.trim().toInt() } }
    }

    val result = input[2].split("\n").subList(1,input[2].split("\n").size)
        .map { it.split(",").map { when(checkIfAnyRule(it.toInt(), rules)) {
            true -> 0
            else -> it.toInt()
        } }.sum() }.sum()

    println(result)
}

private fun checkIfAnyRule(input: Int, rules: List<List<Pair<Int, Int>>>): Boolean {
    return rules.map { it.map { input in it.first .. it.second }.any { it } }.any { it }
}


