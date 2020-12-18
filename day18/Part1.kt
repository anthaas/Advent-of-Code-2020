import java.io.File

fun main(args: Array<String>) {
    val input = File("input.txt").readLines().map { eval(it) }.sum()
    println(input)

}

private fun eval(input: String): Long {
    var expression = input
    while ('(' in expression) {
        expression = expression.replace(Regex("""\([^()]*\)""")) {
            eval(it.value.substring(1,it.value.length-1)).toString()
        }
    }
    return solve(expression)
}

private fun solve(expr: String): Long {
    val tokens = expr.split(" ").filterNot { it == "" }
    var runningResult = tokens.first().toLong()
    val chunkedRest = tokens.subList(1, tokens.size).chunked(2)

    for (pair in chunkedRest) {
        runningResult = when (pair[0]) {
            "+" -> runningResult + pair[1].toLong()
            "*" -> runningResult * pair[1].toLong()
            else -> error("Unknown token")
        }
    }

    return runningResult
}