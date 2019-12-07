import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;
import java.text.AttributedString;
import java.util.ArrayList;

public class UserInterface extends JPanel {
    public static final Color bg = new Color(0, 158, 158);
    public static final int PIPE_W = 50, PIPE_H = 30;
    private Avatar avatar;
    private ArrayList<FlappyBird.Rectangle> rects;
    private static Font scoreFont = new Font("Comic Sans MS", Font.BOLD, 18);
    private static Font pauseFont = new Font("Arial", Font.BOLD, 48);
    private Image pipeHead, pipeLength;

    public UserInterface(Avatar avatar, ArrayList<FlappyBird.Rectangle> rects) {
        this.avatar = avatar;
        this.rects = rects;

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
        avatar.update(g2d);
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

        AttributedString score = new AttributedString(" Score: " + FlappyBird.getScore() + " ");
        score.addAttribute(TextAttribute.BACKGROUND, Color.WHITE);
        score.addAttribute(TextAttribute.FONT, scoreFont);
        score.addAttribute(TextAttribute.FOREGROUND, Color.BLACK);
        g.drawString(score.getIterator(), 0, 20);

        if (FlappyBird.paused()) {
            g.setColor(Color.BLACK);
            g.setFont(pauseFont);
            g.setColor(new Color(0, 0, 0, 170));
            g.drawString("PAUSED", FlappyBird.WIDTH / 2 - 100, FlappyBird.HEIGHT / 2 - 100);
            g.drawString("PRESS SPACE TO GO", FlappyBird.WIDTH / 2 - 250, FlappyBird.HEIGHT / 2 + 50);
        }
    }
}
