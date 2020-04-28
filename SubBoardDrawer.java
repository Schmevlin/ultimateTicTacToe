import java.awt.*;
import java.awt.geom.Rectangle2D;

class SubBoardDrawer {

    private static int LINE_WIDTH = 5;
    private static int PADDING = 20;

    SubBoard subBoard;
    int x;
    int y;
    int size;

    public SubBoardDrawer(SubBoard subBoard, int x, int y, int size) {
        this.subBoard = subBoard;
        this.x = x;
        this.y = y;
        this.size = size;
    }

    public boolean canHandleClick(int clickX, int clickY) {

        return x < clickX && clickX < (x + size) && 0 < clickY && clickY < (y + size);
    }

    public void handleClick(int x, int y, char symbol) {
        if (!canHandleClick(x, y)) {
            throw new Error("Wrong SubBoard picked to handle click");
        }
        int relativeX = x - this.x;
        int relativeY = y - this.y;
        int spaceLength = (size / 3);
        System.out.println("relativeX = " + relativeX + "relativeY = " + relativeY + ", spaceLength = " + spaceLength);
        int row = relativeY / spaceLength;
        int col = relativeX / spaceLength;
        subBoard.setSpace(col, row, symbol);
    }

    public void draw(Graphics2D g) {
        drawBoard(g);
        drawSpaces(g);
    }

    private void drawBoard(Graphics2D g) {
        g.setColor(new Color(0, 200, 83));

        int lineLength = size - PADDING * 2;
        int lineOffset = (LINE_WIDTH * 2 / 3);
        int oneThirdPosition = (lineLength / 3) - lineOffset + PADDING;
        int twoThirdsPosition = (lineLength * 2 / 3) - lineOffset + PADDING;

        g.fillRect(x + oneThirdPosition, y + PADDING, LINE_WIDTH, lineLength);
        g.fillRect(x + twoThirdsPosition, y + PADDING, LINE_WIDTH, lineLength);
        g.fillRect(x + PADDING, y + oneThirdPosition, lineLength, LINE_WIDTH);
        g.fillRect(x + PADDING, y + twoThirdsPosition, lineLength, LINE_WIDTH);
    }

    private void drawSpaces(Graphics2D g){
        for (int row = 0; row < subBoard.spaces.length; row++) {
            for (int col = 0; col < subBoard.spaces[row].length; col++) {
                char space = subBoard.spaces[row][col];
                drawSpace(g, row, col, space);
            }
        }
    }

    private void drawSpace(Graphics2D g, int row, int col, char space) {
        g.setColor(space == 'x' ? Color.BLUE : Color.RED);
        int lineLength = size - PADDING * 2;
        int spaceLength = lineLength / 3;
        Rectangle2D fontRect = g.getFontMetrics().getStringBounds(String.valueOf(space), g);
        int x = (int) (this.x + (col * spaceLength) + spaceLength / 2  + PADDING - fontRect.getWidth() / 2);
        int y = (int) (this.y + (row * spaceLength) + spaceLength / 2 + PADDING + fontRect.getHeight() / 4);
        System.out.println("drawing " + space + " at x = " + x + ", y = " + y);
        g.drawString(String.valueOf(space), x, y);
    }

    private void drawWinningLine(Graphics2D g){
        if(subBoard.isWon() != ' '){
            
        }
    }
}