import java.io.File

fun main(args: Array<String>) {
    val input = File("input.txt").readLines().map { it.toInt() }.toMutableList()
    input.add(0)
    input.add(input.max()!!+3)
    input.sort()

    val dp = LongArray(input.size) { 0 }
    dp[0] = 1
    for (i in (1 until input.size)) {
        for (j in (0 until i)) {
            if (input[i] - input[j] <= 3) {
                dp[i] += dp[j]
            }
        }
    }

    print(dp.last())
}








