fun main(args: Array<String>) {
    val start = System.currentTimeMillis()
    val indexMap = mutableMapOf<Int, Int>()
    val input = listOf(11, 18, 0, 20, 1, 7, 16)
    input.forEachIndexed { index, i -> indexMap[i] = index }
    var last = input.last()
    (input.size until 30000000).forEach { i ->
        if (last !in indexMap) {
            indexMap[last] = i - 1
            last = 0
        } else {
            val distance = i - 1 - indexMap[last]!!
            indexMap[last] = i - 1
            last = distance
        }
    }
    println(last)
    println(System.currentTimeMillis() - start)
}