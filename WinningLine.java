/**
 * Class to represent any three-in-a-row spaces on a SubBoard. 
 * Holds both the position of the line and the player that won the line (either 'x' or 'o'). 
 * Can also represent no line through {@link Line#NONE} and ' ' as winner.
 * @author Andy Burris and Kevin Harris
 * @version 4 May 2020
 */
public class WinningLine {
    private Line line;
    private char winner;

    public WinningLine(Line line, char winner){
        this.line = line;
        this.winner = winner;
    }

    /**
     * @return winner that this represents (either 'x', 'o', or ' ')
     */
    public char getWinner(){
        return winner;
    }

    /**
     * @return {@link Line} that this represents (any of the values of Line)
     */
    public Line getLine(){
        return line;
    }

    /**
     * Utility method to check if this line represents a winner
     * @return false if line is {@link Line#NONE} or char is ' ', true otherwise
     */
    public boolean hasWinner(){
        return line != Line.NONE && winner != ' ';
    }
}