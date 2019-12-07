import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class FlappyBird implements ActionListener, KeyListener {

    public static final int FPS = 60, WIDTH = 640, HEIGHT = 480;

    private Bird bird;
    private JFrame frame;
    private JPanel panel;
    private ArrayList<Rectangle> rects;
    private int time, scroll;

    private boolean paused;

    public static void main(String[] args) {
        new FlappyBird().go();
    }

    public void go() {
        frame = new JFrame("Flappy Bird");
        bird = new Bird();
        rects = new ArrayList<>();
        panel = new UserInterface(this, bird, rects);
        frame.add(panel);

        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.addKeyListener(this);

        paused = true;

        new Timer(1000 / FPS, this).start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        panel.repaint();
        if (!paused) {
            bird.fly();
            if (scroll % 90 == 0) {
                Rectangle topPipe = new Rectangle(WIDTH, 0, UserInterface.PIPE_W, (int) ((Math.random() * HEIGHT) / 5f + (0.2f) * HEIGHT), RectangleRole.TOP);
                int h2 = (int) ((Math.random() * HEIGHT) / 5f + (0.2f) * HEIGHT);
                Rectangle bottomPipe = new Rectangle(WIDTH, HEIGHT - h2, UserInterface.PIPE_W, h2, RectangleRole.BOTTOM);
                rects.add(topPipe);
                rects.add(bottomPipe);
            }
            ArrayList<Rectangle> toRemove = new ArrayList<>();
            boolean game = true;
            for (Rectangle r : rects) {
                r.x -= 3;
                if (r.x + r.width <= 0) {
                    toRemove.add(r);
                }

                if (bird.intersects(r)) {
                    JOptionPane.showMessageDialog(frame, "You lose!\n" + "Your score was: " + time + ".");
                    game = false;
                }
            }
            rects.removeAll(toRemove);
            time++;
            scroll++;

            if (bird.y > HEIGHT || bird.y + Bird.radius < 0) {
                game = false;
            }

            if (!game) {
                rects.clear();
                bird.reset();
                time = 0;
                scroll = 0;
                paused = true;
            }
        }
    }

    public int getScore() {
        return time;
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            bird.jump();
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            paused = false;
        }
    }

    public void keyReleased(KeyEvent e) {
        // See keyPressed().
    }

    public void keyTyped(KeyEvent e) {
        // See keyPressed().
    }

    public boolean paused() {
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
