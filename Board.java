import java.awt.*;
import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;


/**
 * Class for outer "ultimate" board
 * 
 * @version 5/4/20
 * @author Kevin Harris and Andy Burris
 */
public class Board{
    /**
     * the list of smaller boards
     */
    public SubBoard[][] spaces = new SubBoard[3][3];
    private char[][] wonBoards = new char[3][3];
    /**
     * creates a board with empty spaces
     * @author Kevin Harris
     */
    public Board() {
        for (int y = 0; y < spaces.length; y++) {
            for (int x = 0; x < spaces[0].length; x++) {
                spaces[y][x] = new SubBoard();
                wonBoards[y][x] = ' ';
            }
        }
    }
    /**
     * changes one space in one subboard
     * @param xOuter x pos of subboard
     * @param yOuter y pos of subboard
     * @param xInner x pos of location on subboard
     * @param yInner y pos of location on subboard
     * @param symbol what to play in the space
     * @return whether or not it was a legal play
     * @author Kevin Harris and Andy Burris
     */
    public boolean play(int xOuter, int yOuter, int xInner, int yInner, char symbol) {
        return spaces[yOuter][xOuter].setSpace(xInner, yInner, symbol);
    }
    /**
     * function to tell if the game is won by updating the list of 
     * won boards then checking if that list has a winning row/column/diagonal
     * @return which letter won the game
     * @author Kevin Harris and Andy Burris
     */
    public char isWon() {
        for (int y = 0; y < spaces.length; y++) {
            for (int x = 0; x < spaces[0].length; x++) {
                wonBoards[y][x] = spaces[y][x].isWon().getWinner();
            }
        }
        return isWonButForChars();
    }

    private char isWonButForChars() { // totally not copied code
        for (int y = 0; y < wonBoards.length; y++) {
            if (checkRow(y) != ' ') {
                return checkRow(y);
            }
        }
        for (int x = 0; x < wonBoards[0].length; x++) {
            if (checkColumn(x) != ' ') {
                return checkColumn(x);
            }
        }
        if (wonBoards[0][0] == wonBoards[1][1] && wonBoards[1][1] == wonBoards[2][2]) {
            if (wonBoards[0][0] != ' ') {
                return wonBoards[0][0];
            }
        }
        if (wonBoards[2][0] == wonBoards[1][1] && wonBoards[1][1] == wonBoards[0][2]) {
            if (wonBoards[2][0] != ' ') {
                return wonBoards[2][0];
            }
        }

        return ' ';
    }

    private char checkRow(int y) {
        char[] possibilities = { 'x', 'o' };
        for (char possibility : possibilities) {
            if(String.valueOf(wonBoards[y]).chars().allMatch(i -> (char)i == possibility)){
                return possibility;
            }
        }
        return ' ';
    }

    private char checkColumn(int x) {
        char[] possibilities = { 'x', 'o' };
        List<Character> column = Arrays.stream(wonBoards).map(a -> a[x]).collect(Collectors.toList());
        for (char possibility : possibilities) {
            if(column.stream().allMatch(c -> c == possibility)){
                return possibility;
            }
        }
        return ' ';
    }


}