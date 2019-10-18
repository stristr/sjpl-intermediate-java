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
        String valueAsString = Double.toString(value);
        if (valueAsString.contains("E") || valueAsString.contains("e")) {
            leftOperand.reset(valueAsString.chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
            return;
        }

        DecimalFormat formatter = new DecimalFormat("###.#################");

        leftOperand.reset(formatter.format(value).chars().mapToObj(c -> (char) c).collect(Collectors.toList()));
        leftOperand.isFrozen = true;
    }

    private double evaluateExpression() {
        if (operation != null && !rightOperand.isEmpty()) {
            // Evaluate the expression.
            double leftValue = leftOperand.getValue();
            double rightValue = rightOperand.getValue();
            switch (operation) {
                case PLUS:
                    return BooleanOperator.add(leftValue, rightValue);
                case MINUS:
                    return BooleanOperator.subtract(leftValue, rightValue);
                case TIMES:
                    return BooleanOperator.multiply(leftValue, rightValue);
                case DIVIDED_BY:
                    return BooleanOperator.divide(leftValue, rightValue);
                case EXPONENT:
                    if (rightValue == (int) rightValue) {
                        return BooleanOperator.intPower(leftValue, (int) rightValue);
                    } else {
                        return BooleanOperator.power(leftValue, rightValue);
                    }
                case MODULO:
                    return BooleanOperator.modulus(leftValue, rightValue);
            }
        }

        return leftOperand.getValue();
    }

    private void evaluate() {
        double expressionValue = evaluateExpression();
        if (Double.isInfinite(expressionValue) || Double.isNaN(expressionValue)) {
            throw new ArithmeticException();
        }
        updateLeftOperand(expressionValue);

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
                if (activeOperand.isFrozen) {
                    clear();
                }
                activeOperand.add(button);
                return;
            case CLEAR:
                clear();
                return;
            case PLUS:
            case MINUS:
            case TIMES:
            case DIVIDED_BY:
            case EXPONENT:
            case MODULO:
                if (leftOperand.isEmpty()) {
                    return;
                }
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
