import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class Model {
    private static final int BOARD_SIZE = 4;
    private static final int NUM_CARDS = BOARD_SIZE * BOARD_SIZE;
    private static final int NUM_PAIRS = NUM_CARDS / 2;
    private static ArrayList<Color> colors;
    private ArrayList<Card> cards;
    private ArrayList<Card> faceUpUnmatchedCards;

    public Model() {
        this.cards = new ArrayList<>();
        this.faceUpUnmatchedCards = new ArrayList<>();
        this.colors = new ArrayList<>();
        colors.add(Color.GREEN);
        colors.add(Color.BLUE);
        colors.add(Color.ORANGE);
        colors.add(Color.YELLOW);
        colors.add(Color.CYAN);
        colors.add(Color.RED);
        colors.add(Color.GRAY);
        colors.add(Color.MAGENTA);
        for (int n = 0; n < NUM_PAIRS; ++n) {
            Card card1 = new Card(colors.get(n));
            Card card2 = new Card(colors.get(n));
            this.cards.add(card1);
            this.cards.add(card2);
        }
        Collections.shuffle(this.cards);
    }

    void selectCard(int n) {
        Card selectedCard = cards.get(n);
        if (selectedCard.isFaceUp()) {
            return;
        }

        if (faceUpUnmatchedCards.size() == 2) {
            Card card1 = faceUpUnmatchedCards.get(0);
            Card card2 = faceUpUnmatchedCards.get(1);
            if (!card1.isMatched()) {
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
        if (card1.getColor() == card2.getColor()) {
            card1.setMatched(true);
            card2.setMatched(true);
        }
    }

    void reset() {
        for (Card card : cards) {
            if (card.isFaceUp()) {
                card.flip();
            }
            card.setMatched(false);
        }
        Collections.shuffle(cards);
        faceUpUnmatchedCards.clear();
    }

    ArrayList<Card> getCards() {
        return this.cards;
    }
}
