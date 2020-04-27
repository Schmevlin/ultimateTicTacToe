public class SubBoard{
    private char[][] spaces;
    
    public SubBoard(){
        for(int y = 0; y < spaces.length; y++){
            for(int x = 0; x < spaces[0].length; x++){
                spaces[y][x] = ' ';
            }
        }
    }
}