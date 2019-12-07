import javax.swing.*;
import java.util.ArrayList;

/**
 * The FlappyBird class provides the entry point of the game.
 */
public class FlappyBird {
    // This is the frames per second of the game.
    // What happens if you change it?
    public static final int framesPerSecond = 60;
    public static final int width = 640;
    public static final int height = 480;
    // This is the velocity of pipes. What happens when you change it?
    public static final int pipeSpeed = 3;
    static boolean paused;
    private static JFrame frame = new JFrame("Flappy Bird");
    // Can you make a class extension of Avatar other than Bird?
    private Avatar avatar = new Bird();
    private ArrayList<Pipe> pipes = new ArrayList<>();

    public static void main(String[] args) {
        new FlappyBird().go();
    }

    public static int getScore() {
        return GameLoop.time;
    }

    public static void lose() {
        JOptionPane.showMessageDialog(frame, "You lose!\n" + "Your score was: " + GameLoop.time + ".");
    }

    public static boolean paused() {
        return paused;
    }

    public void go() {
        JPanel panel = new UserInterface(avatar, pipes);
        frame.add(panel);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.addKeyListener(new Controls(avatar));

        paused = true;

        new Timer(1000 / framesPerSecond, new GameLoop(avatar, panel, pipes)).start();
    }
}
