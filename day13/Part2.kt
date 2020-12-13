import java.io.File
import kotlin.math.min

fun main(args: Array<String>) {
    //cheating
    //https://www.wolframalpha.com/input/?i=solve+(t+++0)+mod+13+=+0;(t+++7)+mod+37+=+0;(t+++13)+mod+461+=+0;(t+++27)+mod+17+=+0;(t+++32)+mod+19+=+0;(t+++42)+mod+29+=+0;(t+++44)+mod+739+=+0;(t+++54)+mod+41+=+0;(t+++67)+mod+23+=+0;

    //it won't end in reasonable time
    val input = File("input.txt").readLines()
    var timestamp = 100000000000000L
    val buses = input[1].split(",").filterNot { it == "x" }.map { input[1].split(",").indexOf(it) to it.toInt() }
    buses.map { print("(t + ${it.first}) mod ${it.second} = 0;") }

    var found = false
    while (!found) {
        timestamp++
        found = buses.map { (minutes, line) -> (timestamp + minutes) % line == 0L }.all { it }
    }
    println(timestamp)

}
