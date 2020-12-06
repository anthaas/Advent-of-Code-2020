import java.io.File

fun main(args: Array<String>) {
    val groupsSum = File("input.txt").bufferedReader().use { it.readText() }.split("\r\n\r\n")
        .map { it.replace("\r\n", "").toCharArray().toSet().count() }.sum()
    println(groupsSum)
}

