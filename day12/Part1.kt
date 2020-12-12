import java.io.File
import kotlin.math.abs

fun main(args: Array<String>) {
    val input = File("input.txt").readLines().map { Pair(it[0], it.substring(1).toInt()) }
    var faceOfTheShip = 'E'
    var northDistanceFromStart = 0
    var eastDistanceFromStart = 0

    for (entry in input) {
        val (instruction, value) = entry
        when (instruction) {
            'N' -> northDistanceFromStart += value
            'S' -> northDistanceFromStart -= value
            'E' -> eastDistanceFromStart += value
            'W' -> eastDistanceFromStart -= value
            'L', 'R' -> faceOfTheShip = turnShip(faceOfTheShip, instruction, value / 90)
            'F' -> {
                when (faceOfTheShip) {
                    'N' -> northDistanceFromStart += value
                    'S' -> northDistanceFromStart -= value
                    'E' -> eastDistanceFromStart += value
                    'W' -> eastDistanceFromStart -= value
                }
            }
            else -> error("unknown instruction")

        }
    }

    println(abs(northDistanceFromStart) + abs(eastDistanceFromStart))
}

private fun turnShip(orientation: Char, direction: Char, numberOfSteps: Int): Char {
    if (numberOfSteps == 0) {
        return orientation
    }
    when (direction) {
        'L' -> {
            when (orientation) {
                'N' -> return turnShip('W', direction, numberOfSteps - 1)
                'S' -> return turnShip('E', direction, numberOfSteps - 1)
                'E' -> return turnShip('N', direction, numberOfSteps - 1)
                'W' -> return turnShip('S', direction, numberOfSteps - 1)
            }
        }
        'R' -> {
            when (orientation) {
                'N' -> return turnShip('E', direction, numberOfSteps - 1)
                'S' -> return turnShip('W', direction, numberOfSteps - 1)
                'E' -> return turnShip('S', direction, numberOfSteps - 1)
                'W' -> return turnShip('N', direction, numberOfSteps - 1)
            }
        }
    }

    error("direction mismatch")
}



