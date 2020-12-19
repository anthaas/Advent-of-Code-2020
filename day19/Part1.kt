import java.io.File

fun main(args: Array<String>) {
    val input = File("input.txt").bufferedReader().use { it.readText() }.split("\n\n")
    val rules = input[0].split("\n").associate { it.split(": ").let { (left, right) -> left.toInt() to right } }
    val inputs = input[1].split("\n")
    val result = convertToRegex(rules).let { regex -> inputs.count { regex.toRegex().matches(it) } }
    println(result)
}

private fun convertToRegex(rules: Map<Int, String>): String {
    var regexStr = rules[0]!!.removeSurrounding("\"").split(" ").let { listOf("(") + it + ")" }

    while (regexStr.any { s -> s.toIntOrNull() != null }) {
        regexStr = regexStr.map { s ->
            when (s.toIntOrNull()) {
                null -> listOf(s)
                else -> rules[s.toInt()]!!.removeSurrounding("\"").split(" ").let { listOf("(") + it + ")" }
            }
        }.flatten()
    }

    return regexStr.joinToString("")
}
