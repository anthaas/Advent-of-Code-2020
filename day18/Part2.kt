import java.io.File

fun main(args: Array<String>) {
    val input = File("input.txt").readLines().map { eval(it) }.sum()
    println(input)

}

private fun eval(input: String): Long {
    var expression = input
    while ('(' in expression) {
        expression = expression.replace(Regex("""\([^()]*\)""")) {
            eval(it.value.substring(1, it.value.length - 1)).toString()
        }
    }

    return solve(expression)
}

private fun solve(expr: String): Long {
    val tokens = expr.split(" ").filterNot { it == "" }.toMutableList()

    while ("+" in tokens) {
        val i = tokens.indexOf("+")
        val operand1 = tokens[i-1].toLong()
        val operand2 = tokens[i+1].toLong()
        tokens[i-1] = (operand1 + operand2).toString()
        tokens.removeAt(i)
        tokens.removeAt(i)
    }

    return tokens.filterNot { it == "*"}.map { it.toLong() }.reduce { acc, l -> acc * l }
}