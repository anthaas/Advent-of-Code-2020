import java.io.File
import java.math.BigInteger

fun main(args: Array<String>) {
    val input = File("input.txt").bufferedReader().use { it.readText() }.split("\n")
    val mem = mapOf<Int,Long>().toMutableMap()
    var mask = ""
    input.map { when {
        it.startsWith("mask") -> mask = it.split(" = ")[1]
        it.startsWith("mem") ->
            it.split(" = ").let { (index, value) ->
                index.replace("mem[", "").replace("]", "").toInt() to value.toInt() }
                    .let { (index, value) -> mem.put(index, getValueWithMask(value, mask)) }
        else -> error("unknown op")
    } }
    println(mem.values.sum())

}

private fun getValueWithMask(value: Int, mask: String): Long {
    val binaryString = Integer.toBinaryString(value)
    val binaryValue = "0".repeat(mask.length - binaryString.length) + binaryString
    val maskedValue = binaryValue.zip(mask) {c, m -> when(m) {
        'X' -> c
        else -> m
    } }.toCharArray()
    val result = BigInteger(String(maskedValue), 2).toLong()

    return result
}

