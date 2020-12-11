import java.io.File

fun main(args: Array<String>) {
    val input = File("input.txt").readLines().map { it.toLong() }

    var index = 25
    var valid = true
    while (valid) {
        valid = existsSum(input.subList(index - 25, index), input[index])
        index++
    }

    val invalidNumber = input[index - 1]

    println(findRangeSum(input, invalidNumber))
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

private fun findRangeSum(input: List<Long>, sum: Long): Long {
    var l = 0
    var r = 0
    while (true) {
        val sublistSum = input.subList(l, r).sum()
        if (sublistSum == sum) {
            return input.subList(l, r).min()!! + input.subList(l, r).max()!!
        }
        if (sublistSum < sum) {
            r++
        }
        if (sublistSum > sum) {
            l++
            r = l
        }
    }
}

