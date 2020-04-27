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

    public boolean canHandleClick(int x, int y){
        int relativeX = x - this.x;
        int relativeY = y - this.y;
        return 0 < relativeX || relativeX < size || 0 < relativeY || relativeY < size;
    }

    public void handleClick(int x, int y, char symbol){
        if(!canHandleClick(x, y)){
            throw new Error("Wrong SubBoard picked to handle click");
        }
        int relativeX = x - this.x;
        int relativeY = y - this.y;
        int row = relativeY / (size/3);
        int col = relativeX / (size/3);
        subBoard.setSpace(col, row, symbol); 
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