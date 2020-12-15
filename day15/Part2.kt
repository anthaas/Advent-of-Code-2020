fun main(args: Array<String>) {
    val input = listOf(-1,11,18,0,20,1,7,16).toMutableList()
    for( i in (input.size .. 30000000)) {
        val lastNumber = input.last()
        val lastIndex = input.lastIndexOf(lastNumber)
        if (lastIndex == input.indexOf(lastNumber)) {
            input.add(0)
        } else {
            input.add(lastIndex - input.subList(0,lastIndex).lastIndexOf(lastNumber))
        }
        if (i % 100000 == 0) {
            println(i)
        }
    }

    println(input.last())

}