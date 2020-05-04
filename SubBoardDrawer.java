import java.awt.*;
import java.awt.geom.Rectangle2D;

import util.Pair;

/**
 * Class that handles drawing an individual tic-tac-toe board
 * @author Andy Burris and Kevin Harris
 * @version 4 May 2020
 */
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

    /**
     * Checks if this subboard can handle a given mouse click
     * @param clickX absolute x position of mouse click
     * @param clickY absolute y position of mouse click
     * @return true if click is within this board, false otherwise
     */
    public boolean canHandleClick(int clickX, int clickY) {
        return x < clickX && clickX < (x + size) && y < clickY && clickY < (y + size);
    }

    /**
     * Handles mouse click and updates this subboard with the new character. Throws error if coordinates are not applicable to sub-board (use {@link #canHandleClick(int, int)} to check before passing arguments)
     * @param x absolute x position of mouse click
     * @param y absolute y position of mouse click
     * @param symbol character to update
     * @return true if click was received and current player should switch, false otherwise (i.e. if click was on an already filled space)
     */
    public boolean handleClick(int x, int y, char symbol) {
        if (!canHandleClick(x, y)) {
            throw new Error("Wrong SubBoard picked to handle click");
        }
        int relativeX = x - this.x;
        int relativeY = y - this.y;
        int spaceLength = (size / 3);
        System.out.println("relativeX = " + relativeX + "relativeY = " + relativeY + ", spaceLength = " + spaceLength);
        int row = relativeY / spaceLength;
        int col = relativeX / spaceLength;
        if(subBoard.getSpace(col, row) != ' '){
            return false;
        }
        subBoard.setSpace(col, row, symbol);
        return true;
    }

    /**
     * Method that handles drawing of subboard, filled spaces, and winning line if board is won.
     * @param g Graphics2D context to draw on
     */
    public void draw(Graphics2D g) {
        drawBoard(g);
        drawSpaces(g);
        drawWinningLine(g);
    }

    /**
     * Method that draws lines of tic-tac-toe board
     * @param g Graphics2D context to draw on
     */
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

    /**
     * Draws all spaces of subboard with whichever char it holds (either 'x', 'o', or ' ')
     * @param g Graphics2D context to draw on
     */
    private void drawSpaces(Graphics2D g){
        for (int row = 0; row < subBoard.spaces.length; row++) {
            for (int col = 0; col < subBoard.spaces[row].length; col++) {
                char space = subBoard.spaces[row][col];
                drawSpace(g, row, col, space);
            }
        }
    }

    /**
     * Draws individual space on subboard with whatever char it holds
     * @param g Graphics2D context to draw on
     * @param row this space's row on the subboard
     * @param col this space's column on the subboard
     * @param space char that space holds (either 'x', 'o', or ' ')
     */
    private void drawSpace(Graphics2D g, int row, int col, char space) {
        g.setColor(space == 'x' ? Color.BLUE : Color.RED);
        int lineLength = size - PADDING * 2;
        int spaceLength = lineLength / 3;
        Rectangle2D fontRect = g.getFontMetrics().getStringBounds(String.valueOf(space), g);
        int x = (int) (this.x + (col * spaceLength) + spaceLength / 2  + PADDING - fontRect.getWidth() / 2);
        int y = (int) (this.y + (row * spaceLength) + spaceLength / 2 + PADDING + fontRect.getHeight() / 4);
        //System.out.println("drawing " + space + " at x = " + x + ", y = " + y);
        g.drawString(String.valueOf(space), x, y);
    }

    /**
     * Draws line through any three-in-a-row on the board
     * @param g Graphics2D context to draw on
     */
    private void drawWinningLine(Graphics2D g){
        if(subBoard.isWon().hasWinner()){
            WinningLine wl = subBoard.isWon();
            Pair<Integer, Integer> coord1;
            Pair<Integer, Integer> coord2;
            switch(wl.getLine()){
                case VERT_LEFT: coord1 = new Pair<>(0, 0); coord2 = new Pair<>(0, 2); break;
                case VERT_CENTER: coord1 = new Pair<>(1, 0); coord2 = new Pair<>(1, 2); break;
                case VERT_RIGHT: coord1 = new Pair<>(2, 0); coord2 = new Pair<>(2, 2); break;
                case HORZ_TOP: coord1 = new Pair<>(0, 0); coord2 = new Pair<>(2, 0); break;
                case HORZ_CENTER: coord1 = new Pair<>(0, 1); coord2 = new Pair<>(2, 1); break;
                case HORZ_BOTTOM: coord1 = new Pair<>(0, 2); coord2 = new Pair<>(2, 2); break;
                case DIAG_45: coord1 = new Pair<>(0, 2); coord2 = new Pair<>(2, 0); break;
                case DIAG_135: coord1 = new Pair<>(0, 0); coord2 = new Pair<>(2, 2); break;
                case NONE: return;
                default: return;
            }
            int lineLength = size - PADDING * 2;
            Pair<Integer, Integer> mapped1 = new Pair<>(
                x + PADDING + coord1.first * (lineLength/3) + lineLength/6,
                y + PADDING + coord1.second * (lineLength/3) + lineLength/6
            );

            Pair<Integer, Integer> mapped2 = new Pair<>(
                x + PADDING + coord2.first * (lineLength/3) + lineLength/6,
                y + PADDING + coord2.second * (lineLength/3) + lineLength/6
            );
            g.setColor(wl.getWinner() == 'x' ? Color.BLUE : Color.RED);
            System.out.println("showing winning line");
            g.drawLine(mapped1.first, mapped1.second, mapped2.first, mapped2.second);
        }
    }
}