import javax.swing.JFrame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.*;

public class Main {

    private static int SIZE = 800;

    public static void main(String[] args) {
        Board board = new Board();
        System.out.println("Space = " + board.spaces[1][1].getSpace(0, 0));
        createWindow(board);

    }

    private static void createWindow(Board board){
        JFrame frame = new JFrame();
        GameWindow window = new GameWindow(SIZE, board);
        frame.add(window);
        frame.setSize(new Dimension(SIZE + 20, SIZE + 100 + 50));
        frame.setTitle("Ultimate Tic Tac Toe");
        frame.setVisible(true);
        window.addMouseListener(window);
        frame.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}

