import javax.swing.JFrame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.*;

public class Main {

    private static int SIZE = 800;

    public static void main(String[] args) {
        Board board = new Board();
<<<<<<< HEAD
        createWindow(board);



=======
        board.play(1, 1, 0, 0, 'x');
        System.out.println("Space = " + board.spaces[1][1].getSpace(0, 0));
        createWindow(board);

>>>>>>> 26a3b168eb3067d9de0cf17009ad27c99c5f7d19
    }

    private static void createWindow(Board board){
        JFrame frame = new JFrame();
        GameWindow window = new GameWindow(SIZE, board);
<<<<<<< HEAD
        window.addMouseListener(board);
=======
>>>>>>> 26a3b168eb3067d9de0cf17009ad27c99c5f7d19
        frame.add(window);
        frame.setSize(new Dimension(SIZE, SIZE));
        frame.setTitle("Ultimate Tic Tac Toe");
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}

