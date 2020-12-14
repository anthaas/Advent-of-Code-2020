import java.io.File

fun main(args: Array<String>) {
    val input = File("input.txt").bufferedReader().use { it.readText() }.split("\n")
    val mem = mapOf<Long, Long>().toMutableMap()
    var mask = ""
    input.map {
        when {
            it.startsWith("mask") -> mask = it.split(" = ")[1]
            it.startsWith("mem") ->
                it.split(" = ").let { (index, value) ->
                    index.replace("mem[", "").replace("]", "").toInt() to value.toLong()
                }
                    .let { (index, value) ->
                        getAllIndexesByMask(index, mask).map { mem.put(it, value) }

                    }
            else -> error("unknown op")
        }
    }

    println(mem.values.sum())
}

fun getAllIndexesByMask(index: Int, mask: String): List<Long> {
    val binaryString = Integer.toBinaryString(index)
    val binaryIndex = "0".repeat(mask.length - binaryString.length) + binaryString
    val masked = binaryIndex.zip(mask) { c, m ->
        when (m) {
            '0' -> c
            else -> m
        }
    }.toCharArray()
    return getAllCombinations(String(masked)).map { it.toLong(2) }
}

private fun getAllCombinations(entry: String): List<String> {
    if ('X' !in entry) {
        return listOf(entry)
    }
    return getAllCombinations(entry.replaceFirst('X', '0')) + getAllCombinations(entry.replaceFirst('X', '1'))
}

