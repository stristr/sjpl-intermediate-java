import java.awt.*;

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
    Image image;

    // The amount the Avatar should be rotated when it draws.
    double rotation;

    /**
     * Resets the Avatar to the center of the screen.
     */
    public void reset() {
        x = FlappyBird.width / 2;
        y = FlappyBird.height / 2;
        velocity = 0;
        rotation = 0;
    }

    /**
     * go() updates y, velocity, and rotation according to gravity.
     */
    public void go() {
        y += velocity;
        velocity += gravity;

        // What happens if you play with the numbers here?
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
    public final boolean collidesWith(Rectangle r) {
        int[][] corners = {{1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
        for (int[] corner : corners) {
            if (r.contains(x + radius * corner[0], y + radius * corner[1])) {
                return true;
            }
        }

        return false;
    }
}
