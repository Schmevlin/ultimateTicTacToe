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

            }
        }
    }
}