import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controls implements KeyListener {
    private Avatar avatar;

    public Controls(Avatar avatar) {
        this.avatar = avatar;
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            avatar.jump();
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            FlappyBird.paused = false;
        }
    }

    public void keyReleased(KeyEvent e) {
        // See keyPressed().
    }

    public void keyTyped(KeyEvent e) {
        // See keyPressed().
    }
}
