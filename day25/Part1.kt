fun main(args: Array<String>) {
    val cardPubKey = 1327981L
    val doorPubKey = 2822615L

    var value = 1L
    var loop = 1
    while (true) {
        value *= 7
        value %= 20201227
        if (value == cardPubKey) {
            value = 1L
            repeat(loop) {
                value *= doorPubKey
                value %= 20201227
            }
            break
        }
        loop++
    }

    println(value)

}
