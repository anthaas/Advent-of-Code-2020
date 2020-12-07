import java.io.File

fun main(args: Array<String>) {
    val rules = File("input.txt").readLines().associate {
        it.split("contain").let { (key, value) ->
            key.replace("bags", "").replace("bag", "").trim() to value.split(",")
                .map { it.replace("bags", "").replace("bag", "").replace(".", "").trim().substring(2) }
        }
    }

    var list = getParents("shiny gold", rules)
    val tmpList = mutableSetOf<String>()
    val count = mutableSetOf<String>()
    while (list.isNotEmpty()) {
        count.addAll(list)
        for (item in list) {
            tmpList.addAll(getParents(item, rules))
        }
        list = tmpList.toMutableSet()
        tmpList.clear()
    }

    println(count.size)
}

private fun getParents(item: String, rules: Map<String, List<String>>): Set<String> {
    val tmp = mutableSetOf<String>()

    for (rule in rules.entries) {
        if (rule.value.contains(item)) {
            tmp.add(rule.key)
        }
    }

    return tmp
}

