fun main(args: Array<String>) {
    val input = mutableListOf(-1,11,18,0,20,1,7,16)
    val indexMap = mutableMapOf<Int, MutableList<Int>>()
    for (i in input) {
        indexMap[i] = mutableListOf(input.indexOf(i))
    }

    for (i in (input.size..30000000)) {
        val lastNumber = input.last()
        if (indexMap[lastNumber] == null) {
            //first occurence
            input.add(0)
            indexMap[lastNumber] = mutableListOf(i)
        } else if (indexMap[lastNumber]!!.size == 1) {
            //first occurence
            input.add(0)
            indexMap[0]!!.add(i)
        } else {
            val distance = indexMap[lastNumber]!!.takeLast(2).let { it[1] - it[0] }
            input.add(distance)
            if (indexMap[distance] == null) {
                indexMap[distance] = mutableListOf(i)
            } else {
                indexMap[distance]!!.add(i)
            }
        }

        if (i % 100000 == 0) {
            println(i)
        }
    }

    println(input.last())

}
