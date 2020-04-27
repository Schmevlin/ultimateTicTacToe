/**
 * Class for outer "ultimate" board
 * 
 * @version 4/27/20
 * @author Kevin Harris
 */
public class Board{
    private SubBoard[][] spaces = new SubBoard[3][3];
    private char[][] wonBoards = new char[3][3];

    public Board(){
        for (int y = 0; y < spaces.length; y++) {
            for (int x = 0; x < spaces[0].length; x++) {
                spaces[y][x] = new SubBoard();
                wonBoards[y][x] = ' ';
            }
        }
    }

    public boolean play(int xOuter, int yOuter, int xInner, int yInner, char symbol){
        if(!spaces[yOuter][xOuter].setSpace(xInner, yInner, symbol)){
            return false;
        }
        return true;
    }

    public char isWon(){
        for(int y = 0; y < spaces.length; y++){
            for(int x = 0; x < spaces[0].length; x++){
                wonBoards[y][x] = spaces[y][x].isWon();
            }
        }
        return isWonButForChars();
    }

    private char isWonButForChars(){ //totally not copied code
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
        if(wonBoards[0][0] == wonBoards[1][1] && wonBoards[1][1] == wonBoards[2][2]){
            if(wonBoards[0][0] != ' '){
                return wonBoards[0][0];
            }
        }
        if(wonBoards[2][0] == wonBoards[1][1] && wonBoards[1][1] == wonBoards[0][2]){
            if(wonBoards[2][0] != ' '){
                return wonBoards[2][0];
            }
        }
        
        return ' ';
    }

    private char checkRow(int y) {
        char[] possibilities = { 'x', 'o' };
        char won = ' ';
        for (char possibility : possibilities) {
            for (char space : wonBoards[y]) {
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
            for (int y = 0; y < wonBoards.length; y++) {
                if (wonBoards[y][x] != possibility) {
                    break;
                }
                won = possibility;
            }
        }
        return won;
    }
}