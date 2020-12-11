import java.io.File

fun main(args: Array<String>) {
    val instructions = File("input.txt").readLines().map { it.split(" ").let { (op, arg) -> op to arg.toInt() } }.toMutableList()

    var opToChange = 0
    var acc = -1
    while (acc == -1) {
        val (originalInstruction, value) = instructions[opToChange]
        instructions[opToChange] = when (originalInstruction) {
            "acc" -> Pair(originalInstruction, value)
            "nop" -> Pair("jmp", value)
            "jmp" -> Pair("nop", value)
            else -> error("wrong operation")
        }
        acc = runCode(instructions)
        instructions[opToChange] = Pair(originalInstruction, value)
        opToChange += 1
    }

    println(acc)
}

private fun runCode(instructions: List<Pair<String,Int>>): Int {
    val visited = mutableListOf<Int>()
    var pointer = 0
    var acc = 0

    while (pointer < instructions.size) {
        if (pointer in visited) {
            return -1
        }
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

    return acc
}

