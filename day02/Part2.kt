import java.io.File

fun main(args: Array<String>) {
    val input = File("input.txt").readLines()
    val correctEntriesCount = input.map { checkCharsPositions(it) }.count { it }
    println(correctEntriesCount)
}

fun checkCharsPositions(line: String): Boolean {
    //pattern 3-5 f: fgfff
    val splitted = line.replace(":", "").split("-", " ")

    val position1 = splitted[0].toInt() - 1
    val position2 = splitted[1].toInt() - 1
    val letter = splitted[2][0]
    val input = splitted[3]

    return (input[position1] == letter) xor (input[position2] == letter)
}

