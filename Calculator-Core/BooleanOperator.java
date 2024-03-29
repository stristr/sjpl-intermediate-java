class BooleanOperator {
    /**
     * Add a and b.
     */
    static double add(double a, double b) {
        return a + b;
    }

    /**
     * Subtract b from a.
     */
    static double subtract(double a, double b) {
        return a - b;
    }

    /**
     * Multiply a and b.
     */
    static double multiply(double a, double b) {
        return a * b;
    }

    /**
     * Divide a by b.
     */
    static double divide(double a, double b) {
        if (b == 0) {
            return 0;
        }

        return a / b;
    }

    /**
     * Raise a to the power of b.
     */
    static double power(double a, double b) {
        return Math.pow(a, b);
    }

    /**
     * Raise a to the power of b when b is an integer.
     */
    static double intPower(double a, int b) {
        double result = 1;
        for (int i = 0; i < b; ++i) {
            result *= a;
        }
        return result;
    }

    /**
     * Return the value of a modulo b.
     */
    static double modulus(double a, double b) {
        if (b == 0) {
            return 0;
        }

        return a % b;
    }
}
