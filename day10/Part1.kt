import java.io.File

fun main(args: Array<String>) {
    val input = File("input.txt").readLines().map { it.toInt() }.sorted()
    //val input = listOf(16, 10, 15, 5, 1, 11, 7, 19, 6, 12, 4).sorted()
    var ones = 1
    var threes = 1
    for (i in 1 until input.size) {
        if (input[i]-input[i-1] == 1) {
            ones++
        }
        if (input[i]-input[i-1] == 3) {
            threes++
        }
    }

    println(ones * threes)

}


