import java.util.ArrayList;
import java.util.Collection;

class CalculatorNumber {
    private ArrayList<Character> characters = new ArrayList<>();
    private int decimalPosition = -1;
    boolean isFrozen = false;

    void clear() {
        characters.clear();
        decimalPosition = -1;
        isFrozen = false;
    }

    void reset(Collection<Character> collection) {
        characters.clear();
        characters.addAll(collection);
    }

    void add(CalculatorButton button) {
        switch (button) {
            case ZERO:
            case ONE:
            case TWO:
            case THREE:
            case FOUR:
            case FIVE:
            case SIX:
            case SEVEN:
            case EIGHT:
            case NINE:
                characters.add(button.symbol);
                break;
            case DECIMAL:
                if (decimalPosition == -1) {
                    if (isEmpty()) {
                        characters.add('0');
                    }
                    characters.add('.');
                    decimalPosition = characters.size();
                }
                break;
        }
    }

    boolean isEmpty() {
        return characters.size() == 0;
    }

    double getValue() {
        String doubleAsString = characters.stream().map(Object::toString).reduce(
                (accumulator, character) -> accumulator + character).get();

        if (doubleAsString.equals("∞")) {
            return Double.POSITIVE_INFINITY;
        }

        if (doubleAsString.equals("-∞")) {
            return Double.NEGATIVE_INFINITY;
        }

        return Double.parseDouble(doubleAsString);
    }

    String getDisplay() {
        StringBuilder builder = new StringBuilder();
        for (char button : characters) {
            builder.append(button);
        }
        return builder.toString();
    }
}
