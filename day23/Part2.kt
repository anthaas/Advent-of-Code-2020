class Node(var value: Int) {
    var next: Node? = null
}

fun main(args: Array<String>) {
    val input = "186524973".toCharArray().map { it.toString().toInt() }.toMutableList()
    (10..1000000).map { input.add(it) }
    val nodeMap = mutableMapOf<Int, Node>()

    //init
    var current = Node(input[0])
    val head = current
    nodeMap[input[0]] = current
    current.next = head
    input.removeFirst()

    //create linked list
    input.map {
        current.next = Node(it)
        current = current.next!!
        nodeMap[it] = current
        current.next = head
    }

    //do the magic
    current = head
    repeat(10000000) { current = step(nodeMap, current) }
    val result = nodeMap[1]!!.next!!.value.toLong() * nodeMap[1]!!.next!!.next!!.value.toLong()
    println(result)
}

private fun step(nodeMap: Map<Int, Node>, currentCup: Node): Node {
    val first = currentCup.next!!
    val second = currentCup.next!!.next!!
    val third = currentCup.next!!.next!!.next!!
    currentCup.next = third.next!!
    val take3 = listOf(first.value, second.value, third.value)

    var target = currentCup.value
    do {
        target--
        if (target < 1) {
            target = 1000000
        }
    } while (target in take3)

    val destinationCup = nodeMap[target]!!
    third.next = destinationCup.next
    destinationCup.next = first

    return currentCup.next!!
}



