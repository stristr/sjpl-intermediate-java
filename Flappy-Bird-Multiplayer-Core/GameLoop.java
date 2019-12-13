import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * The game loop is the main class of every game.
 * It is responsible for updating the graphics on the screen on every frame.
 */
public class GameLoop implements ActionListener {
    static int scroll;
    static boolean paused = true;

    private ArrayList<Pipe> pipes;
    private Avatar[] avatars;
    private JPanel panel;

    public GameLoop(Avatar[] avatars, JPanel panel, ArrayList<Pipe> pipes) {
        this.avatars = avatars;
        this.panel = panel;
        this.pipes = pipes;
    }

    /**
     * Starts a new game.
     */
    void newGame() {
        pipes.clear();
        avatars[0].reset();
        avatars[1].reset();
        scroll = 0;
        paused = true;
    }

    /**
     * This method adds new pipes to the end of the level.
     */
    public void createNewPipes() {
        // Why do you think this if condition checks scroll % 90?
        if (scroll % 90 == 0) {
            Pipe topPipe = new Pipe(FlappyBird.width, 0, UserInterface.PIPE_W, (int) ((Math.random() * FlappyBird.height) / 5f + (0.2f) * FlappyBird.height), Pipe.Position.TOP);
            int h2 = (int) ((Math.random() * FlappyBird.height) / 5f + (0.2f) * FlappyBird.height);
            Pipe bottomPipe = new Pipe(FlappyBird.width, FlappyBird.height - h2, UserInterface.PIPE_W, h2, Pipe.Position.BOTTOM);
            pipes.add(topPipe);
            pipes.add(bottomPipe);
        }
    }

    /**
     * This method updates the position of the pipes.
     */
    void updatePipePositions() {
        // Check if we have collided with any of the pipes.
        for (Pipe r : pipes) {
            // This is where we use the pipe speed from the FlappyBird class.
            r.x -= FlappyBird.pipeSpeed;
        }
    }

    /**
     * This method checks if all avatars are dead.
     */
    boolean isGameOver() {
        // Check if we have collided with any of the pipes.
        for (Avatar a : avatars) {
            for (Pipe r : pipes) {
                if (a.collidesWith(r)) {
                    a.isDead = true;
                }
            }

            if (a.y > FlappyBird.height || a.y + Avatar.radius < 0) {
                a.isDead = true;
            }
        }

        // If we got here, that means we're still alive.
        return Arrays.stream(avatars).allMatch(a -> a.isDead);
    }

    /**
     * This method cleans up old pipes that are no longer on the screen.
     * Why do you think we do this?
     */
    void cleanUpOldPipes() {
        pipes.removeIf(r -> r.x + r.width <= 0);
    }

    /**
     * This is where the magic happens! Every time the game loop fires a new frame, this code runs.
     */
    public void actionPerformed(ActionEvent e) {
        // Calling panel.repaint() causes the user interface to update.
        // What happens if you don't call panel.repaint()?
        // Check out the paintComponent() method in UserInterface.java to learn what's happening under the hood.
        panel.repaint();

        // If the game is paused, we should not do anything to update the game's state.
        if (paused) {
            return;
        }

        // Here, we increment the timer and the horizontal scroll.
        // What happens if we don't?
        scroll++;
        for (Avatar a : avatars) {
            if (!a.isDead) {
                // Whenever we're not paused and not dead, we go!
                a.go();
                a.score++;
            } else {
                a.x -= 3;
            }
        }

        // Add pipes if necessary.
        createNewPipes();

        // Update all pipe positions.
        updatePipePositions();

        // Check if the avatar is dead.
        if (isGameOver()) {
            FlappyBird.lose();
            newGame();
        }

        // Clean up any pipes that are no longer on the screen.
        cleanUpOldPipes();
    }
}
