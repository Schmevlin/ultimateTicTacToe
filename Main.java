import javax.swing.JFrame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.*;
/**
 * Main class for starting the program
 * 
 * @author Andy Burris and Kevin Harris
 * @version 5/4/20
 */
public class Main {

    private static int SIZE = 800; //size of window
    /**
     * starts the program
     * @param args doesn't matter
     * @author Andy Burris and Kevin Harris
     */
    public static void main(String[] args) {
        Board board = new Board();
        createWindow(board);
    }
    /**
     * Creates window and listeners for user input
     * @param board the board object that will be used in the program
     * @author Andy Burris and Kevin Harris
     */
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

