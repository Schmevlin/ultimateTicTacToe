import java.awt.*;
import java.util.ArrayList;
import java.util.Optional;

/**
 * Class that handles drawing of the ultimate tic-tac-toe board
 * @author Andy Burris and Kevin Harris
 * @version 4 May 2020
 */
public class BoardDrawer {
    private static int LINE_WIDTH = 10;

    public Board board;
    private int size;
    private ArrayList<SubBoardDrawer> subBoardDrawers = new ArrayList<>();

    public BoardDrawer(Board board, int size) {
        this.board = board;
        this.size = size;
        for (int row = 0; row < board.spaces.length; row++) {
            for (int col = 0; col < board.spaces[row].length; col++) {
                SubBoard subBoard = board.spaces[row][col];
                int x = row * (size / 3);
                int y = col * (size / 3);
                SubBoardDrawer drawer = new SubBoardDrawer(subBoard, x, y, size / 3);
                System.out.println("creating drawer at x = " + x + ", y = " + y);
                subBoardDrawers.add(drawer);
            }
        }
    }

    /**
     * Method to handle click on the board, delegate it to the click handler in the correct SubBoardDrawer
     * @param x absolute x position of mouse click
     * @param y absolute y position of mouse click
     * @param symbol char representing player who clicked (either 'x' or 'o')
     * @return true if click was successfully handled, false otherwise(e.g. if click was on an already filled square or board)
     */
    public boolean handleClick(int x, int y, char symbol) {
        if(board.isWon() != ' '){
            return false;
        }

        Optional<SubBoardDrawer> handler = subBoardDrawers.stream()
                .filter(d -> !d.subBoard.isWon().hasWinner() && d.canHandleClick(x, y))
                .findFirst();

        if(!handler.isPresent()){
            return false;
        } else {
            return handler.get().handleClick(x, y, symbol);
        }
    }

    /**
     * Method that handles drawing of board, and draws all subboards through their respective drawers.
     * @param g Graphics2D context to draw on
     */
    public void draw(Graphics2D g) {
        drawBoard(g);
        subBoardDrawers.forEach(d -> d.draw(g));
    }

    /**
     * Method that draws lines of ultimate tic-tac-toe board
     * @param g Graphics2D context to draw on
     */
    private void drawBoard(Graphics2D g) {
        g.setColor(Color.GRAY);
        int lineOffset = (LINE_WIDTH * 2 / 3);
        int oneThirdPosition = (size / 3) - lineOffset;
        int twoThirdsPosition = (size * 2 / 3) - lineOffset;

        g.fillRect(oneThirdPosition, 0, LINE_WIDTH, size);
        g.fillRect(twoThirdsPosition, 0, LINE_WIDTH, size);
        g.fillRect(0, oneThirdPosition, size, LINE_WIDTH);
        g.fillRect(0, twoThirdsPosition, size, LINE_WIDTH);
    }
}