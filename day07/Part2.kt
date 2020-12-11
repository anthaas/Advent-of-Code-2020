import java.io.File

fun main(args: Array<String>) {
    val rules = File("input.txt").readLines().associate {
        it.split("contain").let { (key, value) ->
            key.replace("bags", "").replace("bag", "").trim() to value.split(",")
                .map { it.replace("bags", "").replace("bag", "").replace(".", "").trim() }
                .map { Pair(it[0], it.substring(2)) }
        }
    }

    val count = getBagsCount("shiny gold", rules) - 1
    println(count)


}

private fun getBagsCount(bag: String, rules: Map<String, List<Pair<Char, String>>>): Int {
    val tmp = rules[bag]!!
    var count = 1
    for (item in tmp) {
        if (item.first == 'n') {
            return 1
        }
        count += item.first.toString().toInt() * getBagsCount(item.second, rules)
    }
    return count
}
