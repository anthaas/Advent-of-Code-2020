fun main(args: Array<String>) {
    val input = "186524973".toCharArray().map { it.toString().toInt() }.toList()

    var cycle = input.toMutableList()
    repeat(100) {
        val currentCup = cycle[0]
        cycle = step(cycle, currentCup).toMutableList()
    }

    var result = cycle.subList(cycle.indexOf(1)+1, cycle.size).joinToString("")
    result += cycle.subList(0,cycle.indexOf(1)).joinToString("")

    println(result)
}

private fun step(arr: MutableList<Int>, x: Int): List<Int> {
    val take3 = arr.subList(1, 4)
    val rest = arr.subList(4, arr.size)
    var cycle = (listOf(x) + rest).toMutableList()
    var destinationCup = x
    do {
        destinationCup--
        if (destinationCup < arr.min()!!) {
            destinationCup = arr.max()!!
        }
    } while (destinationCup in take3)

    val indexOfDestinationCup = cycle.indexOf(destinationCup)
    cycle = (cycle.subList(0, indexOfDestinationCup + 1) + take3 + cycle.subList(
        indexOfDestinationCup + 1,
        cycle.size
    )).toMutableList()
    cycle.removeFirst()
    cycle.add(x)

    return cycle
}

