import javax.swing.JFrame;
import java.awt.*;

public class Main {

    private static int SIZE = 800;

    public static void main(String[] args) {
        Board board = new Board();
        createWindow(board);



    }

    private static void createWindow(Board board){
        JFrame frame = new JFrame();
        GameWindow window = new GameWindow(SIZE, board);
        window.addMouseListener(board);
        frame.add(window);
        frame.setSize(new Dimension(SIZE, SIZE));
        frame.setTitle("Ultimate Tic Tac Toe");
        frame.setVisible(true);
    }
}

