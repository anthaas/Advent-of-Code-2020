import java.io.File

fun main(args: Array<String>) {
    val tiles = File("input.txt").bufferedReader().use { it.readText() }.split("\n\n")
        .associate { it.split("\n").let { it[0].replace(":", "").split(" ")[1].toLong() to it.subList(1, it.size) } }
        .toMutableMap()

    //known corner tiles from part1
    //val corners = listOf(3181L, 2543L, 1453L, 1459L)
    val puzzle = Array(12) {
        Array<List<String>?>(12) {
            null
        }
    }

    val firstTile = tiles[3181L]!!
    //get first corner and make right orientation
    puzzle[0][0] = firstTile
    tiles.remove(3181L)
    val tilesList = tiles.values.toMutableList()

    var edgeTop = 0
    var edgeRight = 0
    var edgeBottom = 0
    var edgeLeft = 0
    val edgesTile00 = getTileEdges(firstTile)
    tilesList.map { tile ->
        val edgesOtherTile = getTileEdges(tile)
        edgeTop += edgesOtherTile.map { it == edgesTile00[0] }.count { it }
        edgeTop += edgesOtherTile.map { it.reversed() == edgesTile00[0] }.count { it }
        edgeRight += edgesOtherTile.map { it == edgesTile00[1] }.count { it }
        edgeRight += edgesOtherTile.map { it.reversed() == edgesTile00[1] }.count { it }
        edgeBottom += edgesOtherTile.map { it == edgesTile00[2] }.count { it }
        edgeBottom += edgesOtherTile.map { it.reversed() == edgesTile00[2] }.count { it }
        edgeLeft += edgesOtherTile.map { it == edgesTile00[3] }.count { it }
        edgeLeft += edgesOtherTile.map { it.reversed() == edgesTile00[3] }.count { it }
    }

    if (edgeTop == 0 && edgeRight == 0) { // top & right -> rotate CW 3
        puzzle[0][0] = rotateTileCW(firstTile, 3)
    } else if (edgeRight == 0 && edgeBottom == 0) { // right & bottom -> rotate CW 2
        puzzle[0][0] = rotateTileCW(firstTile, 2)
    } else if (edgeBottom == 0 && edgeLeft == 0) { // bottom & left -> rotate CW 1
        puzzle[0][0] = rotateTileCW(firstTile, 1)
    }
    //first tile is placed correctly
    //build next tiles
    for (r in 0 until puzzle.size) {
        for (c in 0 until puzzle[0].size) {
            if (puzzle[r][c] == null) {
                if (r == 0) {
                    //match left-right
                    val edgeToMatch = getTileEdges(puzzle[r][c - 1]!!)[1]
                    var i = 0
                    var tileFound = false
                    while (!tileFound) {
                        val t = tilesList[i]
                        val tEdges = getTileEdges(t)
                        if (tEdges[0] == edgeToMatch) {
                            puzzle[r][c] = rotateTileCW(flipHorizontal(t), 3)
                            tilesList.removeAt(i)
                            tileFound = true
                        } else if (tEdges[1] == edgeToMatch) {
                            puzzle[r][c] = flipHorizontal(t)
                            tilesList.removeAt(i)
                            tileFound = true
                        } else if (tEdges[2] == edgeToMatch) {
                            puzzle[r][c] = rotateTileCW(t, 1)
                            tilesList.removeAt(i)
                            tileFound = true
                        } else if (tEdges[3] == edgeToMatch) {
                            puzzle[r][c] = t
                            tilesList.removeAt(i)
                            tileFound = true
                        } else if (tEdges[0].reversed() == edgeToMatch) {
                            puzzle[r][c] = rotateTileCW(t, 3)
                            tilesList.removeAt(i)
                            tileFound = true
                        } else if (tEdges[1].reversed() == edgeToMatch) {
                            puzzle[r][c] = rotateTileCW(t, 2)
                            tilesList.removeAt(i)
                            tileFound = true
                        } else if (tEdges[2].reversed() == edgeToMatch) {
                            puzzle[r][c] = rotateTileCW(flipHorizontal(t), 1)
                            tilesList.removeAt(i)
                            tileFound = true
                        } else if (tEdges[3].reversed() == edgeToMatch) {
                            puzzle[r][c] = rotateTileCW(flipHorizontal(t), 2)
                            tilesList.removeAt(i)
                            tileFound = true
                        }
                        i++
                    }
                } else {
                    //match bottom-top
                    val edgeToMatch = getTileEdges(puzzle[r - 1][c]!!)[2]
                    var i = 0
                    var tileFound = false
                    while (!tileFound) {
                        val t = tilesList[i]
                        val tEdges = getTileEdges(t)
                        if (tEdges[0] == edgeToMatch) {
                            puzzle[r][c] = t
                            tilesList.removeAt(i)
                            tileFound = true
                        } else if (tEdges[1] == edgeToMatch) {
                            puzzle[r][c] = rotateTileCW(t, 3)
                            tilesList.removeAt(i)
                            tileFound = true
                        } else if (tEdges[2] == edgeToMatch) {
                            puzzle[r][c] = rotateTileCW(flipHorizontal(t), 2)
                            tilesList.removeAt(i)
                            tileFound = true
                        } else if (tEdges[3] == edgeToMatch) {
                            puzzle[r][c] = flipHorizontal(rotateTileCW(t, 1))
                            tilesList.removeAt(i)
                            tileFound = true
                        } else if (tEdges[0].reversed() == edgeToMatch) {
                            puzzle[r][c] = flipHorizontal(t)
                            tilesList.removeAt(i)
                            tileFound = true
                        } else if (tEdges[1].reversed() == edgeToMatch) {
                            puzzle[r][c] = rotateTileCW(flipHorizontal(t), 1)
                            tilesList.removeAt(i)
                            tileFound = true
                        } else if (tEdges[2].reversed() == edgeToMatch) {
                            puzzle[r][c] = rotateTileCW(t, 2)
                            tilesList.removeAt(i)
                            tileFound = true
                        } else if (tEdges[3].reversed() == edgeToMatch) {
                            puzzle[r][c] = rotateTileCW(t, 1)
                            tilesList.removeAt(i)
                            tileFound = true
                        }
                        i++
                    }
                }
            }
        }
    }

    //now it should be connected as one image
    var gridWithMonsters = getAssembledImage(puzzle)
    //find monsters for every rotation and for flipped map
    (0..3).map { gridWithMonsters = findMonsters(rotateTileCW(gridWithMonsters, 1).map { it.toCharArray() }) }
    gridWithMonsters = flipHorizontal(gridWithMonsters)
    (0..3).map { gridWithMonsters = findMonsters(rotateTileCW(gridWithMonsters, 1).map { it.toCharArray() }) }

    val result = gridWithMonsters.map { it.filter { it == '#' }.count() }.sum()
    println(result)
}

private fun getAssembledImage(puzzle: Array<Array<List<String>?>>): List<String> {
    val image = List(96) { "" }.toMutableList()
    var ri = 0
    (0 until puzzle.size).map { i ->
        (0 until puzzle[0].size).map { j ->
            val tile = puzzle[i][j]!!
            ri = i * 8
            for (r in 1 until tile.size - 1) {
                image[ri] += tile[r].substring(1, tile[r].length - 1)
                ri++
            }
        }
    }
    return image
}

private fun findMonsters(grid: List<CharArray>): List<String> {
    (0 until grid.size).map { r ->
        (0 until grid[0].size).map { c ->
            if (c + 19 < grid[0].size && r + 2 < grid.size) {
                //lochness HA HA
                if (grid[r][c + 18] == '#' && grid[r + 1][c] == '#' && grid[r + 1][c + 5] == '#'
                    && grid[r + 1][c + 6] == '#' && grid[r + 1][c + 11] == '#' && grid[r + 1][c + 12] == '#'
                    && grid[r + 1][c + 17] == '#' && grid[r + 1][c + 18] == '#' && grid[r + 1][c + 19] == '#'
                    && grid[r + 2][c + 1] == '#' && grid[r + 2][c + 4] == '#' && grid[r + 2][c + 7] == '#'
                    && grid[r + 2][c + 10] == '#' && grid[r + 2][c + 13] == '#' && grid[r + 2][c + 16] == '#'
                ) {
                    grid[r][c + 18] = 'O'
                    grid[r + 1][c] = 'O'
                    grid[r + 1][c + 5] = 'O'
                    grid[r + 1][c + 6] = 'O'
                    grid[r + 1][c + 11] = 'O'
                    grid[r + 1][c + 12] = 'O'
                    grid[r + 1][c + 17] = 'O'
                    grid[r + 1][c + 18] = 'O'
                    grid[r + 1][c + 19] = 'O'
                    grid[r + 2][c + 1] = 'O'
                    grid[r + 2][c + 4] = 'O'
                    grid[r + 2][c + 7] = 'O'
                    grid[r + 2][c + 10] = 'O'
                    grid[r + 2][c + 13] = 'O'
                    grid[r + 2][c + 16] = 'O'
                }
            }
        }
    }
    return grid.map { it.map { it }.joinToString("") }
}


private fun flipHorizontal(tile: List<String>): List<String> = tile.map { it.reversed() }

private fun getTileEdges(tile: List<String>): List<String> = listOf(
    tile[0],
    tile.joinToString("") { it.takeLast(1) },
    tile.last(),
    tile.map { it[0] }.joinToString("")
)

private fun rotateTileCW(tile: List<String>, steps: Int): List<String> {
    if (steps == 0) {
        return tile
    }
    val n = tile.size
    val rotatedTile = tile.map { it.toCharArray() }
    (0 until n).map { i ->
        (0 until n).map { j ->
            rotatedTile[i][j] = tile[n - j - 1][i]
        }
    }

    return rotateTileCW(rotatedTile.map { it.map { it }.joinToString("") }, steps - 1)

}
