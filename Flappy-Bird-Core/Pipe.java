import java.awt.*;

/**
 * This is a class which represents a pipe's rectangular outline.
 * Because of how the graphics work, we need to know if the pipe's position is at the
 * TOP or the BOTTOM of the screen.
 */
public class Pipe extends Rectangle {
    Position position;

    Pipe(int x, int y, int width, int height, Position position) {
        super(x, y, width, height);
        this.position = position;
    }

    enum Position {
        TOP, BOTTOM,
    }
}
