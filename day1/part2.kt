import java.io.File

fun main(args: Array<String>) {
    val input = File("input.txt").readLines().map { it.toInt() }

    input.forEach { a ->
        input.forEach { b ->
            input.forEach { c ->
                if (a + b + c == 2020) {
                    print(a * b * c)
                    return
                }
            }
        }

    }
}