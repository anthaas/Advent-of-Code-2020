import java.io.File
import java.math.BigInteger

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
    val maskedIndex = String(masked)
    val allCombinations = ArrayList(replaceFirastX(maskedIndex))
    while (allCombinations.map { it.contains("X") }.any { it }) {
        val toAdd = mutableListOf<String>()
        val toRemove = mutableListOf<String>()
        for (entry in allCombinations) {
            if (entry.contains("X")) {
                toRemove.add(entry)
                toAdd.addAll(replaceFirastX(entry))
            }
        }
        allCombinations.removeAll(toRemove)
        allCombinations.addAll(toAdd)
    }

    return allCombinations.map { BigInteger(it, 2).toLong() }
}

private fun replaceFirastX(entry: String): List<String> {
    val changed = mutableListOf<String>()
    entry.forEachIndexed { i, c ->
        if (c == 'X') {
            changed.add(entry.substring(0, i) + "0" + entry.substring(i + 1, entry.length))
            changed.add(entry.substring(0, i) + "1" + entry.substring(i + 1, entry.length))
            return changed
        }
    }
    return emptyList()
}

