/**
 * Class for smaller tic tac toe boards
 * @version 4/27/20
 * @author Kevin Harris
 */
public class SubBoard{
    private char[][] spaces;
    
    public SubBoard(){
        for(int y = 0; y < spaces.length; y++){
            for(int x = 0; x < spaces[0].length; x++){
                spaces[y][x] = ' ';
            }
        }
    }
    /**
     * gets the value of one space of the board
     * @param x x position of requested space
     * @param y y position of requested space
     */
    public char getSpace(int x, int y){
        return spaces[y][x];
    }
}