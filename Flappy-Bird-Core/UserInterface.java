import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class UserInterface extends JPanel {
    public static final Color bg = new Color(0, 158, 158);
    public static final int PIPE_W = 50, PIPE_H = 30;
    private Bird bird;
    private ArrayList<FlappyBird.Rectangle> rects;
    private FlappyBird fb;
    private Font scoreFont, pauseFont;
    private Image pipeHead, pipeLength;

    public UserInterface(FlappyBird fb, Bird bird, ArrayList<FlappyBird.Rectangle> rects) {
        this.fb = fb;
        this.bird = bird;
        this.rects = rects;
        scoreFont = new Font("Comic Sans MS", Font.BOLD, 18);
        pauseFont = new Font("Arial", Font.BOLD, 48);

        try {
            pipeHead = ImageIO.read(new File("res/pipe_cap.png"));
            pipeLength = ImageIO.read(new File("res/pipe_part.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(bg);
        g.fillRect(0, 0, FlappyBird.WIDTH, FlappyBird.HEIGHT);
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform old = g2d.getTransform();
        bird.update(g2d);
        g.setColor(Color.RED);
        for (FlappyBird.Rectangle r : rects) {
            g2d.setColor(Color.GREEN);
            g2d.translate(r.x + PIPE_W / 2, r.y + PIPE_H / 2);
            if (r.y < FlappyBird.HEIGHT / 2) {
                g2d.translate(0, r.height);
                g2d.rotate(Math.PI);
            }

            g2d.drawImage(pipeLength, -PIPE_W / 2, PIPE_H / 2, UserInterface.PIPE_W, r.height, null);

            if (r.role == FlappyBird.RectangleRole.BOTTOM) {
                g2d.drawImage(pipeHead, -PIPE_W / 2, -PIPE_H / 2, UserInterface.PIPE_W, UserInterface.PIPE_H, null);
            } else {
                g2d.drawImage(pipeHead, -PIPE_W / 2, PIPE_H / 2, UserInterface.PIPE_W, UserInterface.PIPE_H, null);
            }
            g2d.setTransform(old);
        }
        g.setFont(scoreFont);
        g.setColor(Color.BLACK);
        g.drawString("Score: " + fb.getScore(), 10, 20);

        if (fb.paused()) {
            g.setFont(pauseFont);
            g.setColor(new Color(0, 0, 0, 170));
            g.drawString("PAUSED", FlappyBird.WIDTH / 2 - 100, FlappyBird.HEIGHT / 2 - 100);
            g.drawString("PRESS SPACE TO GO", FlappyBird.WIDTH / 2 - 250, FlappyBird.HEIGHT / 2 + 50);
        }
    }
}
