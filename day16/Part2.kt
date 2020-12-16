import java.io.File

fun main(args: Array<String>) {
    val input = File("input.txt").bufferedReader().use { it.readText() }.split("\n\n")
    val rules = input[0].split("\n").map {
        it.split(":")[1].split(" or ")
            .map { it.split('-').let { (low, high) -> low.trim().toInt() to high.trim().toInt() } }
    }
    val myTicket = input[1].split("\n")[1].split(",").map { it.toLong() }

    val nearbyTickets =
        input[2].split("\n").subList(1, input[2].split("\n").size).map { it.split(",").map { it.toInt() } }
    val filteredNearbyTickets =
        nearbyTickets.filter { checkIfAnyRuleNotValid(it, rules) }
    val columns =
        (0 until filteredNearbyTickets[0].size).map { i -> filteredNearbyTickets.map { it[i] }.toList() }.toList()
    val rulesCanBeAppliedToColumns = (0 until rules.size).map { ruleIndex ->
        ruleIndex to columns.mapIndexed { index, list -> if (checkIfOneRule(list, rules[ruleIndex])) index else -1 }
            .filter { it != -1 }.toMutableList()
    }.toMutableList()
    val exactOneRule = mutableListOf<Pair<Int, List<Int>>>()

    while (rulesCanBeAppliedToColumns.isNotEmpty()) {
        exactOneRule.addAll(rulesCanBeAppliedToColumns.filter { it.second.size == 1 })
        exactOneRule.forEach { if (it in rulesCanBeAppliedToColumns) rulesCanBeAppliedToColumns.remove(it) }
        rulesCanBeAppliedToColumns.map { rule ->
            exactOneRule.forEach {
                if (it.second[0] in rule.second) rule.second.remove(
                    it.second[0]
                )
            }
        }
    }
    val sorted = exactOneRule.sortedBy { it.first }.map { it.first to it.second[0] }
    val result = (0 until 6).map { myTicket[sorted[it].second] }


    println(result.reduce { acc, i -> acc * i })
}

private fun checkIfAnyRuleNotValid(input: List<Int>, rules: List<List<Pair<Int, Int>>>): Boolean {
    return input.map { value -> rules.map { it.map { value in it.first..it.second }.any { it } }.any { it } }.all { it }
}

private fun checkIfOneRule(input: List<Int>, rule: List<Pair<Int, Int>>): Boolean {
    return input.map { value -> rule.map { value in it.first..it.second }.any { it } }.all { it }
}

