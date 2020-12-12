import java.io.File
import kotlin.math.abs


fun main(args: Array<String>) {
    val input = File("input.txt").readLines().map { Pair(it[0], it.substring(1).toInt()) }
    var northDistanceFromStartWaypoint = 1
    var eastDistanceFromStartWaypoint = 10

    var northDistanceFromStartShip = 0
    var eastDistanceFromStartShip = 0

    for (entry in input) {
        val (instruction, value) = entry
        when (instruction) {
            'N' -> northDistanceFromStartWaypoint += value
            'S' -> northDistanceFromStartWaypoint -= value
            'E' -> eastDistanceFromStartWaypoint += value
            'W' -> eastDistanceFromStartWaypoint -= value
            'L', 'R' -> {
                val new =
                    rotateWaypoint(northDistanceFromStartWaypoint to eastDistanceFromStartWaypoint, instruction, value)
                northDistanceFromStartWaypoint = new.first
                eastDistanceFromStartWaypoint = new.second
            }
            'F' -> {
                northDistanceFromStartShip += value * northDistanceFromStartWaypoint
                eastDistanceFromStartShip += value * eastDistanceFromStartWaypoint
            }
            else -> error("unknown instruction")

        }
    }

    println(abs(northDistanceFromStartShip) + abs(eastDistanceFromStartShip))
}

private fun rotateWaypoint(waypoint: Pair<Int, Int>, direction: Char, angle: Int): Pair<Int, Int> {
    return when (direction) {
        'L' -> {
            when (angle) {
                90 -> waypoint.second to -waypoint.first
                180 -> -waypoint.first to -waypoint.second
                270 -> -waypoint.second to waypoint.first
                else -> error("wrong angle L")
            }
        }
        'R' -> {
            when (angle) {
                90 -> -waypoint.second to waypoint.first
                180 -> -waypoint.first to -waypoint.second
                270 -> waypoint.second to -waypoint.first
                else -> error("wrong angle R")
            }
        }
        else -> error("wrong direction")
    }
}



