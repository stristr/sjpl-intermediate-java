import java.text.DecimalFormat;
import java.util.stream.Collectors;

class ExpressionEvaluator {
    private CalculatorButton operation = null;
    private CalculatorNumber leftOperand = new CalculatorNumber();
    private CalculatorNumber rightOperand = new CalculatorNumber();
    private CalculatorNumber activeOperand = leftOperand;

    String getDisplay() {
        if (leftOperand.isEmpty()) {
            return "0";
        }

        StringBuilder builder = new StringBuilder();
        builder.append(leftOperand.getDisplay());
        if (operation != null) {
            builder.append(operation.symbol);
        }
        if (!rightOperand.isEmpty()) {
            builder.append(rightOperand.getDisplay());
        }

        return builder.toString();
    }

    private void clear() {
        operation = null;
        leftOperand.clear();
        rightOperand.clear();
        activeOperand = leftOperand;
    }

    private void updateLeftOperand(double value) {
        if (Double.isInfinite(value) || Double.isNaN(value)) {
            leftOperand.clear();
            return;
        }

        String valueAsString = Double.toString(value);
        if (valueAsString.contains("E") || valueAsString.contains("e")) {
            leftOperand.reset(valueAsString.chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
            return;
        }

        DecimalFormat formatter = new DecimalFormat("###.#################");

        leftOperand.reset(formatter.format(value).chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
    }

    private void evaluate() {
        if (operation != null && !rightOperand.isEmpty()) {
            // Evaluate the expression.
            double leftValue = leftOperand.getValue();
            double rightValue = rightOperand.getValue();
            switch (operation) {
                case PLUS:
                    updateLeftOperand(BooleanOperator.add(leftValue, rightValue));
                    break;
                case MINUS:
                    updateLeftOperand(BooleanOperator.subtract(leftValue, rightValue));
                    break;
                case TIMES:
                    updateLeftOperand(BooleanOperator.multiply(leftValue, rightValue));
                    break;
                case DIVIDED_BY:
                    updateLeftOperand(BooleanOperator.divide(leftValue, rightValue));
                    break;
                case EXPONENT:
                    updateLeftOperand(BooleanOperator.power(leftValue, rightValue));
                    break;
                case MODULO:
                    updateLeftOperand(BooleanOperator.modulate(leftValue, rightValue));
                    break;
            }
        }

        operation = null;
        activeOperand = leftOperand;
        rightOperand.clear();
    }

    void handleButton(CalculatorButton button) {
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
            case DECIMAL:
                activeOperand.add(button);
                break;
            case CLEAR:
                clear();
                break;
            case PLUS:
            case MINUS:
            case TIMES:
            case DIVIDED_BY:
            case EXPONENT:
            case MODULO:
                if (activeOperand == rightOperand && !rightOperand.isEmpty()) {
                    evaluate();
                }
                operation = button;
                activeOperand = rightOperand;
                break;
            case EQUALS:
                evaluate();
                break;
        }
    }
}
