import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class Model {
    private static final int BOARD_SIZE = 4;
    private static final int NUM_CARDS = BOARD_SIZE * BOARD_SIZE;
    private static final int NUM_PAIRS = NUM_CARDS / 2;
    ArrayList<Card> cards = new ArrayList<>();
    private ArrayList<Card> faceUpUnmatchedCards = new ArrayList<>();

    public Model() {
        reset();
    }

    void selectCard(int n) {
        Card selectedCard = cards.get(n);
        if (selectedCard.isFaceUp) {
            return;
        }

        if (faceUpUnmatchedCards.size() == 2) {
            Card card1 = faceUpUnmatchedCards.get(0);
            Card card2 = faceUpUnmatchedCards.get(1);
            if (!card1.isMatched) {
                card1.flip();
                card2.flip();
            }

            faceUpUnmatchedCards.clear();
            selectedCard.flip();
            faceUpUnmatchedCards.add(selectedCard);
        } else if (faceUpUnmatchedCards.size() == 1) {
            selectedCard.flip();
            faceUpUnmatchedCards.add(selectedCard);
            checkMatch();
        } else if (faceUpUnmatchedCards.isEmpty()) {
            selectedCard.flip();
            faceUpUnmatchedCards.add(selectedCard);
        }
    }

    private void checkMatch() {
        Card card1 = faceUpUnmatchedCards.get(0);
        Card card2 = faceUpUnmatchedCards.get(1);
        if (card1.equals(card2)) {
            card1.setMatched();
            card2.setMatched();
        }
    }

    void reset() {
        Color[] colors = {Color.GREEN, Color.BLUE, Color.ORANGE, Color.YELLOW, Color.CYAN, Color.RED, Color.GRAY, Color.MAGENTA};
        cards.clear();
        faceUpUnmatchedCards.clear();
        for (int n = 0; n < NUM_PAIRS; ++n) {
            cards.add(new Card(colors[n]));
            cards.add(new Card(colors[n]));
        }
        Collections.shuffle(cards);
    }
}
