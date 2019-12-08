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
    // This is the background color of the game.
    // What happens if you change the constructor arguments?
    public static final Color bg = new Color(0, 158, 158);

    // These are the dimensions of the pipe. What happens if you change them?
    public static final int PIPE_W = 50, PIPE_H = 30;

    // These are the fonts used for the score and the pause screen.
    // Can you change the fonts used in the game?
    private static Font scoreFont = new Font("Comic Sans MS", Font.BOLD, 18);
    private static Font pauseFont = new Font("Arial", Font.BOLD, 48);

    private Avatar[] avatars;
    private ArrayList<Pipe> rects;
    private Image pipeHead, pipeLength;

    public UserInterface(Avatar[] avatars, ArrayList<Pipe> rects) {
        this.avatars = avatars;
        this.rects = rects;

        try {
            pipeHead = ImageIO.read(new File("res/pipe_cap.png"));
            pipeLength = ImageIO.read(new File("res/pipe_part.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method draws the score in the top right of the screen.
     * Why is this method "static" but drawPipes() is not?
     */
    static void drawScore(Graphics g, int x, int y, int time, Color color) {
        AttributedString score = new AttributedString(" Score: " + time + " ");
        score.addAttribute(TextAttribute.BACKGROUND, color);
        score.addAttribute(TextAttribute.FONT, scoreFont);
        score.addAttribute(TextAttribute.FOREGROUND, Color.BLACK);
        g.drawString(score.getIterator(), x, y);
    }

    /**
     * This method draws the pause screen.
     * Why is this method "static" but drawPipes() is not?
     */
    static void drawPauseScreen(Graphics g) {
        if (GameLoop.paused) {
            g.setColor(Color.BLACK);
            g.setFont(pauseFont);
            g.setColor(new Color(0, 0, 0, 170));
            g.drawString("PAUSED", FlappyBird.width / 2 - 100, FlappyBird.height / 2 - 100);
            g.drawString("PRESS SPACE TO GO", FlappyBird.width / 2 - 250, FlappyBird.height / 2 + 50);
        }
    }

    /**
     * This method draws the Avatar.
     */
    void drawAvatar(Graphics2D g, Avatar avatar) {
        AffineTransform old = g.getTransform();
        g.setColor(Color.BLACK);

        // This line of code rotates the bird according to the rotation stored on the avatar.
        g.rotate(Math.toRadians(45 * avatar.rotation), avatar.x, avatar.y);

        if (avatar.image != null) {
            g.drawImage(avatar.image, Math.round(avatar.x - Avatar.radius), Math.round(avatar.y - Avatar.radius), 2 * Avatar.radius, 2 * Avatar.radius, null);
        } else {
            g.drawRect(Math.round(avatar.x - Avatar.radius), Math.round(avatar.y - Avatar.radius), 2 * Avatar.radius, 2 * Avatar.radius);
        }

        // Notice how we set the transform of the graphics back to what it was before it was rotated?
        // What happens if we comment this line of code?
        g.setTransform(old);
    }
    
    /**
     * This methods draws all the avatars particiapting in the game
     */
    void drawAvatars(Graphics2D g) {
        for(int i = 0; i < avatars.length; i++){
            drawAvatar(g, avatars[i]);
        }
    }

    /**
     * This method draws the pipes.
     */
    void drawPipes(Graphics2D g) {
        g.setColor(Color.RED);
        AffineTransform old = g.getTransform();
        for (Pipe r : rects) {
            g.setColor(Color.GREEN);
            g.translate(r.x + PIPE_W / 2, r.y + PIPE_H / 2);
            if (r.y < FlappyBird.height / 2) {
                g.translate(0, r.height);
                g.rotate(Math.PI);
            }

            g.drawImage(pipeLength, -PIPE_W / 2, PIPE_H / 2, UserInterface.PIPE_W, r.height, null);

            if (r.position == Pipe.Position.BOTTOM) {
                g.drawImage(pipeHead, -PIPE_W / 2, -PIPE_H / 2, UserInterface.PIPE_W, UserInterface.PIPE_H, null);
            } else {
                g.drawImage(pipeHead, -PIPE_W / 2, PIPE_H / 2, UserInterface.PIPE_W, UserInterface.PIPE_H, null);
            }
            g.setTransform(old);
        }
    }

    /**
     * This is the main method of this panel.
     * It is triggered when GameLoop calls panel.repaint().
     */
    public void paintComponent(Graphics g) {
        g.setColor(bg);
        g.fillRect(0, 0, FlappyBird.width, FlappyBird.height);
        Graphics2D g2d = (Graphics2D) g;
        drawAvatars(g2d);
        drawPipes(g2d);
        drawScore(g,0, 20, GameLoop.player1Time, Color.yellow);
        drawScore(g,150, 20, GameLoop.player2Time, Color.red);
        drawPauseScreen(g);
    }
}
