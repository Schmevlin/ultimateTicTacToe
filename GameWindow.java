import javax.swing.JPanel;
import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputListener;
import java.awt.*;

public class GameWindow extends JPanel implements MouseInputListener {
    private char currentPlayer = 'x';
    private BoardDrawer boardDrawer;
    private int boardSize;

    public GameWindow(int boardSize, Board board) {
        this.boardDrawer = new BoardDrawer(board, boardSize);
        this.boardSize = boardSize;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setFont(new Font("Helvetica", Font.BOLD, 48));
        boardDrawer.draw(g2d);
        paintInfo(g2d);
    }

    private void paintInfo(Graphics2D g) {
        g.setColor(Color.BLACK);
        String display;
        if (boardDrawer.board.isWon() == ' ') {
            display = "Current Player: " + String.valueOf(currentPlayer).toUpperCase();
        } else {
            display = "Player " + String.valueOf(boardDrawer.board.isWon()).toUpperCase() + " is the winner!";
        }
        int stringHeight = (int) g.getFontMetrics().getStringBounds(display, g).getHeight();
        g.drawString(display, 16, boardSize + 50 + stringHeight / 2);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("mouseX : " + e.getX());
        System.out.println("mouseY : " + e.getY());
        boolean handled = boardDrawer.handleClick(e.getX(), e.getY(), currentPlayer);
        if (handled) {
            currentPlayer = (currentPlayer == 'x') ? 'o' : 'x';
        }
        removeAll();
        revalidate();
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
}
