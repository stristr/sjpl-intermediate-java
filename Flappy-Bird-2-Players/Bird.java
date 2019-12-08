import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

/**
 * The Bird class extension of Avatar sets a specific gravity, jump height, and image.
 */
public class Bird extends Avatar {
    private String file;
    public Bird(String file) {
        this.file = file;
        reset();

        // (a) What happens if you make gravity bigger?
        // (b) What happens if you make gravity 0?
        // (c) What happens if you make gravity negative?
        gravity = 0.5f;
        jumpHeight = -8;
        try {
            // What happens if you change the image file to something else?
            image = ImageIO.read(new File(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
