import javax.swing.JPanel;
import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Class for smaller tic tac toe boards
 * 
 * @version 4/27/20
 * @author Kevin Harris
 */
public class SubBoard {
    public char[][] spaces = new char[3][3];

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
     */
    public char getSpace(int x, int y) {
        return spaces[y][x];
    }

    public boolean setSpace(int x, int y, char symbol) {
        if (getSpace(x, y) != ' ') {
            return false;
        } else {
            spaces[y][x] = symbol;
            return true;
        }
    }

    public char isWon() {
        for (int y = 0; y < spaces.length; y++) {
            if (checkRow(y) != ' ') {
                return checkRow(y);
            }
        }
        for (int x = 0; x < spaces[0].length; x++) {
            if (checkColumn(x) != ' ') {
                return checkColumn(x);
            }
        }
        if(spaces[0][0] == spaces[1][1] && spaces[1][1] == spaces[2][2]){
            if(spaces[0][0] != ' '){
                return spaces[0][0];
            }
        }
        if(spaces[2][0] == spaces[1][1] && spaces[1][1] == spaces[0][2]){
            if(spaces[2][0] != ' '){
                return spaces[2][0];
            }
        }
        
        return ' ';
    }

    private char checkRow(int y) {
        char[] possibilities = { 'x', 'o' };
        char won = ' ';
        for (char possibility : possibilities) {
            for (char space : spaces[y]) {
                if (space != possibility) {
                    break;
                }
                won = possibility;
            }
        }
        return won;
    }

    private char checkColumn(int x) {
        char[] possibilities = { 'x', 'o' };
        char won = ' ';
        for (char possibility : possibilities) {
            for (int y = 0; y < spaces.length; y++) {
                if (spaces[y][x] != possibility) {
                    break;
                }
                won = possibility;
            }
        }
        return won;
    }

    public void draw(Graphics2D g, int size, int xStart, int yStart){
        for(int row = 0; row < spaces.length; row++){
            for(int col = 0; col < spaces[row].length; col++){
                char space = spaces[row][col];
                drawSpace(g, size, xStart, yStart, row, col, space);
            }
        }
    }

    private void drawSpace(Graphics2D g, int size, int xStart, int yStart, int row, int col, char space){
        int spaceLength = size / 3;
        Rectangle2D fontRect = g.getFontMetrics().getStringBounds(String.valueOf(space), g);
        int x = (int) (xStart + (row * spaceLength) + spaceLength/2 );
        int y = (int) (xStart + (col * spaceLength) + spaceLength/2 );
        System.out.println("drawing " + space + " at x = " + x + ", y = " + y);
        g.drawString(String.valueOf(space), x, y);
    }
}