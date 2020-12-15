fun main(args: Array<String>) {
    val indexMap = mutableMapOf<Int, MutableList<Int>>()
    val input = mutableListOf(-1, 11, 18, 0, 20, 1, 7, 16)
    input.forEachIndexed { index, i -> indexMap[i] = mutableListOf(index) }

    (input.size..30000000).forEach { i ->
        val result = when (indexMap[input.last()]!!.size) {
            1 -> 0
            else -> indexMap[input.last()]!!.takeLast(2).let { it[1] - it[0] }
        }
        input.add(result)
        if (indexMap[result] == null) indexMap[result] = mutableListOf(i) else indexMap[result]!!.add(i)
    }

    println(input.last())
}
