import java.io.File

fun main(args: Array<String>) {
    val instructions = File("input.txt").readLines().map { it.split(" ").let { (op, arg) -> op to arg.toInt() } }

    val visited = mutableListOf<Int>()
    var pointer = 0
    var acc = 0

    while (pointer !in visited) {
        visited.add(pointer)
        val (op, arg) = instructions[pointer]
        pointer += when (op) {
            "nop" -> 1
            "jmp" -> arg
            "acc" -> {
                acc += arg
                1
            }
            else -> error("wrong instruction")
        }
    }

    println(acc)
}

