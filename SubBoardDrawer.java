import javax.swing.JPanel;
import java.awt.*;
import java.awt.geom.Rectangle2D;

class SubBoardDrawer{
    SubBoard subBoard;
    int x;
    int y;
    int size;

    public SubBoardDrawer(SubBoard subBoard, int x, int y, int size){
        this.subBoard = subBoard;
        this.x = x;
        this.y = y;
        this.size = size;
    }

    public void draw(Graphics2D g){
        for(int row = 0; row < subBoard.spaces.length; row++){
            for(int col = 0; col < subBoard.spaces[row].length; col++){
                char space = subBoard.spaces[row][col];
                drawSpace(g, row, col, space);
            }
        }
    }

    private void drawSpace(Graphics2D g, int row, int col, char space){ 
        int spaceLength = size / 3;
        Rectangle2D fontRect = g.getFontMetrics().getStringBounds(String.valueOf(space), g);
        int x = (int) (this.x + (row * spaceLength) + spaceLength/2 - fontRect.getWidth()/2);
        int y = (int) (this.y + (col * spaceLength) + spaceLength/2 + fontRect.getHeight()/2);
        System.out.println("drawing " + space + " at x = " + x + ", y = " + y);
        g.drawString(String.valueOf(space), x, y);
    }
}