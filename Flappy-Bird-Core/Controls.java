import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * This class is responsible for handling keyboard input.
 */
public class Controls implements KeyListener {
    private Avatar avatar;

    public Controls(Avatar avatar) {
        this.avatar = avatar;
    }

    /**
     * This method will fire for every keystroke on the keyboard.
     */
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            avatar.jump();
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            FlappyBird.paused = false;
        }
    }

    public void keyReleased(KeyEvent e) {
        // Nothing to do here...yet!
    }

    public void keyTyped(KeyEvent e) {
        // Nothing to do here...yet!
    }
}
