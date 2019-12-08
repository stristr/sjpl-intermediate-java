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
    private static JFrame frame = new JFrame("Flappy Bird");
    // Can you make a class extension of Avatar other than Bird?
    private Avatar[] avatars = {new Bird("res/bird.png"), new Bird("res/angrybird.png")};
    private ArrayList<Pipe> pipes = new ArrayList<>();

    public static void main(String[] args) {
        new FlappyBird().go();
    }

    public static void lose() {
        JOptionPane.showMessageDialog(frame, "Game over!\n"); // + "Your score was: " + GameLoop.time + ".");
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
