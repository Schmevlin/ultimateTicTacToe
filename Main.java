import javax.swing.JFrame;
import java.awt.*;

public class Main {

    private static int SIZE = 800;

    public static void main(String[] args) {
        createWindow();

        Board board = new Board();
    }

    private static void createWindow(){
        JFrame frame = new JFrame();
        GameWindow window = new GameWindow(SIZE);
        frame.add(window);
        frame.setSize(new Dimension(SIZE, SIZE));
        frame.setTitle("Ultimate Tic Tac Toe");
        frame.setVisible(true);
    }
}

