import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import util.Pair;

/**
 * Class for smaller tic tac toe boards
 * 
 * @version 5/4/20
 * @author Kevin Harris and Andy Burris
 */
public class SubBoard {
    /**
     * array of spaces in a subboard
     */
    public char[][] spaces = new char[3][3];
    private char columnWinner;

    /**
     * creates a new blank subboard
     * @author Kevin Harris
     */
    public SubBoard() {
        for (int y = 0; y < spaces.length; y++) {
            for (int x = 0; x < spaces[0].length; x++) {
                spaces[y][x] = ' ';
            }
        }
    }

    /**
     * gets the value of one space of the board
     * 
     * @param x x position of requested space
     * @param y y position of requested space
     * @author Kevin Harris
     */
    public char getSpace(int x, int y) {
        return spaces[y][x];
    }

    /**
     * updates a space in a subboard
     * @param x x pos to change
     * @param y y pos to change
     * @param symbol character to put in space
     * @return whether or not it is a legal play
     */
    public boolean setSpace(int x, int y, char symbol) {
        if (getSpace(x, y) != ' ') {
            return false;
        } else {
            spaces[y][x] = symbol;
            return true;
        }
    }

    /**
     * finds a line that wins the game 
     * @return the line that won the game or a blank line
     * @author Kevin Harris and Andy Burris
     */
    public WinningLine isWon() {
        for (int y = 0; y < spaces.length; y++) {
            char rowWinner = checkRow(y);
            if (rowWinner != ' ') {
                Line line;
                switch (y) {
                    case 0: line = Line.HORZ_TOP; break;
                    case 1: line = Line.HORZ_CENTER; break;
                    case 2: line = Line.HORZ_BOTTOM; break;
                    default: line = Line.NONE; break; // should never be called
                }
                return new WinningLine(line, rowWinner);
            }
        }
        for (int x = 0; x < spaces[0].length; x++) {
            columnWinner = checkColumn(x);
            if (columnWinner != ' ') {
                Line line;
                switch (x) {
                    case 0: line = Line.VERT_LEFT; break;
                    case 1: line = Line.VERT_CENTER; break;
                    case 2: line = Line.VERT_RIGHT; break;
                    default: line = Line.NONE; break; // should never be called
                }
                return new WinningLine(line, columnWinner);
            }
        }
        if(spaces[0][0] == spaces[1][1] && spaces[1][1] == spaces[2][2]){
            if(spaces[0][0] != ' '){
                return new WinningLine(Line.DIAG_135, spaces[0][0]);
            }
        }
        if(spaces[2][0] == spaces[1][1] && spaces[1][1] == spaces[0][2]){
            if(spaces[2][0] != ' '){
                return new WinningLine(Line.DIAG_45, spaces[2][0]);
            }
        }
        
        return new WinningLine(Line.NONE, ' ');
    }

    private char checkRow(int y) {
        char[] possibilities = { 'x', 'o' };
        for (char possibility : possibilities) {
            if(String.valueOf(spaces[y]).chars().allMatch(i -> (char)i == possibility)){
                return possibility;
            }
        }
        return ' ';
    }

    private char checkColumn(int x) {
        char[] possibilities = { 'x', 'o' };
        List<Character> column = Arrays.stream(spaces).map(a -> a[x]).collect(Collectors.toList());
        for (char possibility : possibilities) {
            if(column.stream().allMatch(c -> c == possibility)){
                return possibility;
            }
        }
        return ' ';
    }
    
}
