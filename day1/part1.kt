import java.io.File

fun main(args: Array<String>) {
    val input = File("input.txt").readLines().map { it.toInt() }

    input.forEach { a ->
        input.forEach { b ->
            if (a + b == 2020) {
                print(a * b)
                return
            }
        }

    }
}