package tictactoe

fun main() {
//    var readln = readln().toString()
//    val input = readln.replace("_", " ")

    val grid = mutableListOf(
            mutableListOf(' ', ' ', ' '),
            mutableListOf(' ', ' ', ' '),
            mutableListOf(' ', ' ', ' ')
    )
    printGrid(grid)
    var endGame = false
    var step = 0
    var play = 'X'
    var findWinner = false
    while (step < 9) {

        var userMove = readln()
        var userInput = analyze(userMove)

        if (userInput.size == 2) {
            val move = grid[userInput[0]][userInput[1]]
            if (move.isWhitespace()) {
                grid[userInput[0]][userInput[1]] = play
                findWinner = findWinner(grid, play)
                if (findWinner) {
                    break
                }
                step++
                printGrid(grid)
                play = if (play == 'X') 'O' else 'X'
            } else {
                println("This cell is occupied! Choose another one!")
            }
        }
    }

    printGrid(grid)
    if (findWinner)
        println("$play wins")
    else
        println("Draw")
}

fun findWinner(grid: MutableList<MutableList<Char>>, play: Char): Boolean {

    //horizontals
    if (grid[0][0] == grid[0][1] && grid[0][1] == grid[0][2] && grid[0][0] == play)
        return true
    if (grid[1][0] == grid[1][1] && grid[1][1] == grid[1][2] && grid[1][0] == play)
        return true
    if (grid[2][0] == grid[2][1] && grid[2][1] == grid[2][2] && grid[2][0] == play)
        return true

    //verticals
    if (grid[0][0] == grid[1][0] && grid[1][0] == grid[2][0] && grid[0][0] == play)
        return true
    if (grid[0][1] == grid[1][1] && grid[1][1] == grid[2][1] && grid[0][1] == play)
        return true
    if (grid[0][2] == grid[1][2] && grid[1][2] == grid[2][2] && grid[0][2] == play)
        return true

    //diagonals
    if (grid[0][0] == grid[1][1] && grid[1][1] == grid[2][2] && grid[0][0] == play)
        return true
    return grid[0][2] == grid[1][1] && grid[1][1] == grid[2][0] && grid[0][2] == play
}

private fun analyze(userMove: String): MutableList<Int> {
    var userInput = mutableListOf<Int>()
    for (c in userMove) {
        if (c.isWhitespace())
            continue
        else if (c.isDigit()) {
            val toInt = c.digitToInt()
            if (toInt in 1..3) {
                userInput.add(toInt - 1)
            } else {
                println("Coordinates should be from 1 to 3! ")
                userInput.clear()
                return userInput
            }
        } else {
            println("You should enter numbers!")
            userInput.clear()
            return userInput
        }
    }

    return userInput
}

fun printGrid(grid: MutableList<MutableList<Char>>) {
    println("---------")
    for (i in 0..2) {
        println("| ${grid[i][0]} ${grid[i][1]} ${grid[i][2]} |")
    }
    println("---------")
}