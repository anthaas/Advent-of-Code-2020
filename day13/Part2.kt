import java.io.File

fun main(args: Array<String>) {


    val input = File("input.txt").readLines()
    val buses = input[1].split(",").filterNot { it == "x" }.map { input[1].split(",").indexOf(it) to it.toLong() }
        .sortedBy { it.second }.reversed()
    //buses.map { print("(t + ${it.first}) mod ${it.second} = 0;") }
    //cheating
    //https://www.wolframalpha.com/input/?i=solve+(t+++0)+mod+13+=+0;(t+++7)+mod+37+=+0;(t+++13)+mod+461+=+0;(t+++27)+mod+17+=+0;(t+++32)+mod+19+=+0;(t+++42)+mod+29+=+0;(t+++44)+mod+739+=+0;(t+++54)+mod+41+=+0;(t+++67)+mod+23+=+0;

    // now for real
    //get firt three slowest, calculate interval
    val slowestBuses = buses.subList(0,3)
    var timestamp = slowestBuses[0].second - slowestBuses[0].first
    var interval = slowestBuses[0].second

    do {
        if (slowestBuses.map { (timestamp + it.first) % it.second == 0L }.all { it }) break
        timestamp += interval
    } while (true)

    interval = slowestBuses.map { it.second }.reduce { acc, l -> acc * l }

    //check rest of the buses
    val busesWithoutSlowest = buses.subList(3,buses.size)
    do {
        if (busesWithoutSlowest.map { (timestamp + it.first) % it.second == 0L }.all { it }) break
        timestamp += interval
    } while (true)

    println(timestamp)
}
