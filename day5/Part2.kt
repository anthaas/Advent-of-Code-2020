import java.io.File

fun main(args: Array<String>) {
    val seats = File("input.txt").readLines()
    val seatsIds = seats.map { getSeatId(it) }
    println(findSeat(seatsIds))
}

private fun findSeat(seatsIds: List<Int>): Int {
    val min = seatsIds.min()!!
    val max = seatsIds.max()!!
    (min..max).map { if (it !in seatsIds) return it }
    return 0
}

private fun getSeatId(seat: String): Int{
    return  getSeatRow(seat.substring(0,7), 0, 127) * 8 + getSeatColumn(seat.substring(7), 0, 7)
}

private fun getSeatRow(columnId: String, l: Int, r: Int): Int {
    return when {
        columnId.isEmpty() -> l
        (columnId[0] == 'F') -> getSeatRow(columnId.substring(1), l, (l +r)/2)
        else -> getSeatRow(columnId.substring(1), (l +r)/2+1, r)
    }
}

private fun getSeatColumn(rowId: String, l: Int, r: Int): Int {
    return when {
        rowId.isEmpty() -> l
        (rowId[0] == 'L') -> getSeatColumn(rowId.substring(1), l, (l +r)/2)
        else -> getSeatColumn(rowId.substring(1), (l +r)/2+1, r)
    }
}
