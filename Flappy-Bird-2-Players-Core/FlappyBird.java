import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;

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
    private static JFrame frame = new JFrame("Flappy Bird");
    // Can you make a class extension of Avatar other than Bird?
    private static Avatar[] avatars = {new Bird("res/bird.png", 0, KeyEvent.VK_UP), new Bird("res/angrybird.png", 50, KeyEvent.VK_W)};
    private ArrayList<Pipe> pipes = new ArrayList<>();

    public static void main(String[] args) {
        new FlappyBird().go();
    }

    public static void lose() {
        Avatar winner = Arrays.stream(avatars).max(Avatar::defeated).get();
        JOptionPane.showMessageDialog(frame, "Game over! Player " + (Arrays.asList(avatars).indexOf(winner) + 1) + " wins!");
    }

    public void go() {
        JPanel panel = new UserInterface(avatars, pipes);
        frame.add(panel);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.addKeyListener(new Controls(avatars));

        new Timer(1000 / framesPerSecond, new GameLoop(avatars, panel, pipes)).start();
    }
}
