enum CalculatorButton {
    // Row 1
    SEVEN('7'), EIGHT('8'), NINE('9'), PLUS('+'), CLEAR('C'),

    // Row 2
    FOUR('4'), FIVE('5'), SIX('6'), MINUS('-'), MODULO('%'),

    // Row 3
    ONE('1'), TWO('2'), THREE('3'), TIMES('×'), EXPONENT('^'),

    // Row 4
    ZERO('0'), DECIMAL('.'), DIVIDED_BY('÷'), EQUALS('=');

    char symbol;

    CalculatorButton(char symbol) {
        this.symbol = symbol;
    }
}
