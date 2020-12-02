import java.io.File

fun main(args: Array<String>) {
    val input = File("input.txt").readLines()
    val correctEntriesCount = input.map { checkCharsRepetitions(it) }.count { it }
    println(correctEntriesCount)
}

fun checkCharsRepetitions(line: String): Boolean {
    //pattern 3-5 f: fgfff
    val splitted = line.split("-", " ").map { it.trim().replace(":", "") }

    val min = splitted[0].toInt()
    val max = splitted[1].toInt()
    val letter = splitted[2].toCharArray()[0]
    val input = splitted[3]

    val count = input.filter { it == letter }.count()
    return count in min..max
}
