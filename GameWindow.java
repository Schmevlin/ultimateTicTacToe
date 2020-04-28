import javax.swing.JPanel;
import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputListener;
import java.awt.*;

public class GameWindow extends JPanel implements MouseInputListener {

    private static int ULTIMATE_LINE_SIZE = 10;
    private static int LINE_SIZE = 5;
    private int size;
    private BoardDrawer boardDrawer;

    public GameWindow(int size, Board board) {
        this.size = size;
        this.boardDrawer = new BoardDrawer(board, size);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.GRAY);
        paintUltimateLines(g2d);
        g2d.setColor(Color.BLUE);
        paintBoardLines(g2d, 20);
        g2d.setFont(new Font("Helvetica", Font.BOLD, 48));
        boardDrawer.draw(g2d);
    }

    private void paintUltimateLines(Graphics2D g){
        paintBoard(g, 0, 0, size, ULTIMATE_LINE_SIZE);
    }

    private void paintBoardLines(Graphics2D g, int padding){
        int lineOffset = (ULTIMATE_LINE_SIZE * 1 / 3);
        int boardSize = (size / 3) - (2 * padding) - lineOffset * 2;
        int oneThirdPosition = (size / 3) + lineOffset + padding;
        int twoThirdsPosition = (size * 2 / 3) + lineOffset + padding;
        paintBoard(g, padding, padding, boardSize, LINE_SIZE);
        paintBoard(g, oneThirdPosition, padding,  boardSize, LINE_SIZE);
        paintBoard(g, twoThirdsPosition, padding, boardSize, LINE_SIZE);
        paintBoard(g, padding, oneThirdPosition, boardSize, LINE_SIZE);
        paintBoard(g, oneThirdPosition, oneThirdPosition,  boardSize, LINE_SIZE);
        paintBoard(g, twoThirdsPosition, oneThirdPosition, boardSize, LINE_SIZE);
        paintBoard(g, padding, twoThirdsPosition, boardSize, LINE_SIZE);
        paintBoard(g, oneThirdPosition, twoThirdsPosition,  boardSize, LINE_SIZE);
        paintBoard(g, twoThirdsPosition, twoThirdsPosition, boardSize, LINE_SIZE);
    }

    private void paintBoard(Graphics2D g, int xStart, int yStart, int boardSize, int lineSize){
        int lineOffset = (lineSize * 2 / 3);
        int oneThirdPosition = (boardSize / 3) - lineOffset;
        int twoThirdsPosition = (boardSize * 2 / 3) - lineOffset;

        g.fillRect(xStart + oneThirdPosition, yStart, lineSize, boardSize);
        g.fillRect(xStart + twoThirdsPosition, yStart, lineSize, boardSize);
        g.fillRect(xStart, yStart + oneThirdPosition, boardSize, lineSize);
        g.fillRect(xStart, yStart + twoThirdsPosition, boardSize, lineSize);
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("mouseX : " + e.getX());
        System.out.println("mouseY : " + e.getY());
        boardDrawer.handleClick(e.getX(), e.getY(), 'X');
        removeAll();
        revalidate();
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseDragged(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {}
}
