
public class WinningLine {
    private Line line;
    private char winner;

    public WinningLine(Line line, char winner){
        this.line = line;
        this.winner = winner;
    }

    public char getWinner(){
        return winner;
    }

    public Line getLine(){
        return line;
    }

    public boolean hasWinner(){
        return line != Line.NONE && winner != ' ';
    }
}