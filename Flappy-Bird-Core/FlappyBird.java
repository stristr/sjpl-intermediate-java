import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

/**
 * The FlappyBird class provides the entry point of the game.
 */
public class FlappyBird implements ActionListener {
    // This is the frames per second of the game.
    // What happens if you change it?
    public static final int FPS = 60;

    public static final int WIDTH = 640;
    public static final int HEIGHT = 480;

    // Can you make a class extension of Avatar other than Bird?
    private Avatar avatar = new Bird();
    private JFrame frame = new JFrame("Flappy Bird");
    private JPanel panel;
    private ArrayList<Rectangle> pipes = new ArrayList<>();

    private static int time, scroll;

    static boolean paused;

    public static void main(String[] args) {
        new FlappyBird().go();
    }

    public void go() {
        panel = new UserInterface(avatar, pipes);
        frame.add(panel);
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.addKeyListener(new Controls(avatar));

        paused = true;

        new Timer(1000 / FPS, this).start();
    }

    public void actionPerformed(ActionEvent e) {
        panel.repaint();
        if (paused) {
            return;
        }

        avatar.fly();
        if (scroll % 90 == 0) {
            Rectangle topPipe = new Rectangle(WIDTH, 0, UserInterface.PIPE_W, (int) ((Math.random() * HEIGHT) / 5f + (0.2f) * HEIGHT), RectangleRole.TOP);
            int h2 = (int) ((Math.random() * HEIGHT) / 5f + (0.2f) * HEIGHT);
            Rectangle bottomPipe = new Rectangle(WIDTH, HEIGHT - h2, UserInterface.PIPE_W, h2, RectangleRole.BOTTOM);
            pipes.add(topPipe);
            pipes.add(bottomPipe);
        }
        ArrayList<Rectangle> toRemove = new ArrayList<>();
        boolean game = true;
        for (Rectangle r : pipes) {
            r.x -= 3;
            if (r.x + r.width <= 0) {
                toRemove.add(r);
            }

            if (avatar.intersects(r)) {
                JOptionPane.showMessageDialog(frame, "You lose!\n" + "Your score was: " + time + ".");
                game = false;
            }
        }
        pipes.removeAll(toRemove);
        time++;
        scroll++;

        if (avatar.y > HEIGHT || avatar.y + Avatar.radius < 0) {
            JOptionPane.showMessageDialog(frame, "You lose!\n" + "Your score was: " + time + ".");
            game = false;
        }

        if (!game) {
            pipes.clear();
            avatar.reset();
            time = 0;
            scroll = 0;
            paused = true;
        }
    }

    public static int getScore() {
        return time;
    }

    public static boolean paused() {
        return paused;
    }

    enum RectangleRole {
        TOP, BOTTOM,
    }

    static class Rectangle extends java.awt.Rectangle {
        RectangleRole role;

        Rectangle(int x, int y, int width, int height, RectangleRole role) {
            super(x, y, width, height);
            this.role = role;
        }
    }
}
