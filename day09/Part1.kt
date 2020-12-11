import java.io.File

fun main(args: Array<String>) {
    val input = File("input.txt").readLines().map { it.toLong() }

    var index = 25
    var valid = true
    while (valid) {
        valid = existsSum(input.subList(index - 25, index), input[index])
        index++
    }

    println(input[index - 1])
}

private fun existsSum(input: List<Long>, searchValue: Long): Boolean {
    input.forEach { a ->
        input.forEach { b ->
            if (a + b == searchValue) {
                return true
            }
        }

    }

    return false
}

