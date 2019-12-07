import java.awt.*;
import java.awt.geom.AffineTransform;

// Avatar is the base class for Bird.
// It implements the basic physics of the Flappy Bird game.
public class Avatar {
    // This is the radius of the Avatar.
    public static final int radius = 25;

    // The horizontal center of the Avatar.
    public float x;

    // The vertical center of the Avatar.
    public float y;

    // The rate at which the Avatar is falling.
    // Negative velocity means the Avatar is rising.
    public float velocity;

    // The rate at which the Avatar's velocity changes.
    public float gravity;

    // How high the Avatar jumps with each jump.
    public float jumpHeight;

    // The image used to draw the Avatar.
    protected Image image;

    // The amount the Avatar should be rotated when it draws.
    private double rotation;

    /**
     * Resets the Avatar to the center of the screen.
     */
    public void reset() {
        x = FlappyBird.WIDTH / 2;
        y = FlappyBird.WIDTH / 2;
        velocity = 0;
        rotation = 0;
    }

    /**
     * fly() updates y, velocity, and rotation according to gravity.
     */
    public void fly() {
        y += velocity;
        velocity += gravity;
        rotation = Math.min(Math.max(velocity, -8), 32) / 16;
    }

    /**
     * jump() immediately updates the velocity of the Avatar to the jump height.
     */
    public void jump() {
        velocity = jumpHeight;
    }

    /**
     * intersects(Rectangle r) checks if the Avatar intersects r.
     */
    public final boolean intersects(Rectangle r) {
        int[][] corners = {{1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
        for (int[] corner : corners) {
            if (r.contains(x + radius * corner[0], y + radius * corner[1])) {
                return true;
            }
        }

        return false;
    }

    /**
     * update(Graphics2D g) redraws the Avatar on the screen.
     */
    public void update(Graphics2D g) {
        AffineTransform old = g.getTransform();
        g.setColor(Color.BLACK);
        g.rotate(Math.toRadians(45 * rotation), x, y);
        if (image != null) {
            g.drawImage(image, Math.round(x - radius), Math.round(y - radius), 2 * radius, 2 * radius, null);
        } else {
            g.drawRect(Math.round(x - radius), Math.round(y - radius), 2 * radius, 2 * radius);
        }
        g.setTransform(old);
    }
}
