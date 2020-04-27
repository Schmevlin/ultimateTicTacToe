import java.awt.*;
import java.util.ArrayList;

public class BoardDrawer {
    private Board board;
    private int size;
    private ArrayList<SubBoardDrawer> subBoardDrawers = new ArrayList<>();

    public BoardDrawer(Board board, int size){
        this.board = board;
        this.size = size;
        for(int row = 0; row < board.spaces.length; row++){
            for(int col = 0; col < board.spaces[row].length; col++){
                SubBoard subBoard = board.spaces[row][col];
                int x = row * (size / 3);
                int y = col * (size / 3);
                SubBoardDrawer drawer = new SubBoardDrawer(subBoard, x, y, size / 3);
                subBoardDrawers.add(drawer);
            }
        }
    }

    public void handleClick(int x, int y, char symbol){
        SubBoardDrawer handler = subBoardDrawers.stream().filter(d -> d.canHandleClick(x, y)).findFirst().get();
        handler.handleClick(x, y, symbol);
    }
    
    public void draw(Graphics2D g){
        //TODO: draw board lines, game winners
        subBoardDrawers.forEach(d -> d.draw(g));
    }
}