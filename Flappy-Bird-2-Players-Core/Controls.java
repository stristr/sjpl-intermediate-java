import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * This class is responsible for handling keyboard input.
 */
public class Controls implements KeyListener {
    private Avatar[] avatars;

    public Controls(Avatar[] avatars) {
        this.avatars = avatars;
    }

    /**
     * This method will fire for every keystroke on the keyboard.
     */
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            GameLoop.paused = false;
            return;
        }

        for (Avatar a : avatars) {
            a.handleKeyCode(e.getKeyCode());
        }
    }

    public void keyReleased(KeyEvent e) {
        // Nothing to do here...yet!
    }

    public void keyTyped(KeyEvent e) {
        // Nothing to do here...yet!
    }
}
