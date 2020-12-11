import java.io.File

fun main(args: Array<String>) {
    val groupsSum = File("input.txt").bufferedReader().use { it.readText() }.split("\r\n\r\n")
        .map { it.split("\r\n").map { it.toSet() }.reduce { acc, set -> acc.intersect(set) }.count() }.sum()
    println(groupsSum)
}

