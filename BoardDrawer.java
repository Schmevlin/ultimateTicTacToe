import java.awt.*;
import java.util.ArrayList;

public class BoardDrawer {
    private static int LINE_WIDTH = 10;

    private Board board;
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

    public void handleClick(int x, int y, char symbol) {
        SubBoardDrawer handler = subBoardDrawers.stream()
                .filter(d -> d.subBoard.isWon() == ' ' && d.canHandleClick(x, y))
                .findFirst()
                .get();
        handler.handleClick(x, y, symbol);
    }

    public void draw(Graphics2D g) {
        // TODO: game winners
        drawBoard(g);
        subBoardDrawers.forEach(d -> d.draw(g));
    }

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