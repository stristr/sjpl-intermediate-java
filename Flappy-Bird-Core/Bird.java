import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Bird {
    public static final int RAD = 25;
    public float x, y, vy;
    private Image img;

    public Bird() {
        reset();
        try {
            img = ImageIO.read(new File("res/bird.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void fly() {
        y += vy;
        vy += 0.5f;
    }

    public void update(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawImage(img, Math.round(x - RAD), Math.round(y - RAD), 2 * RAD, 2 * RAD, null);
    }

    public void jump() {
        vy = -8;
    }

    public void reset() {
        x = FlappyBird.WIDTH / 2;
        y = FlappyBird.WIDTH / 2;
        vy = 0;
    }

    public boolean intersects(Rectangle r) {
        return r.contains(x + RAD, y + RAD) || r.contains(x + RAD, y - RAD) ||
                r.contains(x - RAD, y + RAD) || r.contains(x - RAD, y - RAD);
    }
}
