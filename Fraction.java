public class Fraction {
    private int numerator;
    private int denominator;

    public int getNumerator() {
        return this.numerator;
    }

    public int getDenominator() {
        return this.denominator;
    }

    public String getFraction() {
        return this.numerator + "/" + this.denominator;
    }

    public Fraction(int numerator, int denominator) throws NumberFormatException {
        if (denominator != 0) {
            if (denominator < 0) {
                numerator = -numerator;
                denominator = -denominator;
            }
            reduction(numerator, denominator);
        } else {
            exp();
        }
    }

    public Fraction() {
        this.numerator = 1;
        this.denominator = 1;

    }

    private static int getNod(int a, int b) {
        a = Math.abs(a);
        do {
            if (a > b)
                a %= b;
            else b %= a;
        } while (a * b > 0);
        return a + b;
    }

    private static int getNok(int a, int b) {
        return (a * b) / (getNod(a, b));
    }

    private void reduction(int a, int b) {
        if (a != 0) {
            this.numerator = a / getNod(a, b);
            this.denominator = b / getNod(a, b);
        } else {
            this.numerator = 0;
            this.denominator = 1;
        }
    }

    public void sum(int numerator1, int denominator1) throws NumberFormatException {
        if (denominator1 != 0) {
            if (denominator1 < 0) {
                numerator1 = -numerator1;
                denominator1 = -denominator1;
            }
            int tempDenominator = getNok(denominator1, this.denominator);
            int tempNumerator = (tempDenominator / denominator1) * numerator1 + (tempDenominator / this.denominator) * this.numerator;
            reduction(tempNumerator, tempDenominator);
        } else {
            exp();
        }

    }

    public void sum(int numerator1, int denominator1, int numerator2, int denominator2) throws NumberFormatException {
        if (denominator1 != 0 & denominator2 != 0) {
            if (denominator1 < 0) {
                numerator1 = -numerator1;
                denominator1 = -denominator1;
            }
            if (denominator2 < 0) {
                numerator2 = -numerator2;
                denominator2 = -denominator2;
            }
            int tempDenominator = getNok(denominator1, denominator2);
            int tempNumerator = (tempDenominator / denominator1) * numerator1 + (tempDenominator / denominator2) * numerator2;
            reduction(tempNumerator, tempDenominator);
        } else {
            exp();
        }

    }

    public void sub(int numerator1, int denominator1) throws NumberFormatException {
        if (denominator1 != 0) {
            if (denominator1 < 0) {
                numerator1 = -numerator1;
                denominator1 = -denominator1;
            }
            int tempDenominator = getNok(denominator1, this.denominator);
            int tempNumerator = -((tempDenominator / denominator1) * numerator1) + (tempDenominator / this.denominator) * this.numerator;
            reduction(tempNumerator, tempDenominator);
        } else {
            exp();
        }

    }

    public void sub(int numerator1, int denominator1, int numerator2, int denominator2) throws NumberFormatException {
        if (denominator1 != 0 & denominator2 != 0) {
            if (denominator1 < 0) {
                numerator1 = -numerator1;
                denominator1 = -denominator1;
            }
            if (denominator2 < 0) {
                numerator2 = -numerator2;
                denominator2 = -denominator2;
            }
            int tempDenominator = getNok(denominator1, denominator2);
            int tempNumerator = (tempDenominator / denominator1) * numerator1 - (tempDenominator / denominator2) * numerator2;
            reduction(tempNumerator, tempDenominator);
        } else {
            exp();
        }

    }

    public void mul(int numerator1, int denominator1) throws NumberFormatException {
        if (denominator1 != 0) {
            if (denominator1 < 0) {
                numerator1 = -numerator1;
                denominator1 = -denominator1;
            }
            int tempDenominator = denominator1 * this.denominator;
            int tempNumerator = numerator1 * this.numerator;
            reduction(tempNumerator, tempDenominator);
        } else {
            exp();
        }

    }

    public void mul(int numerator1, int denominator1, int numerator2, int denominator2) throws NumberFormatException {
        if (denominator1 != 0 & denominator2 != 0) {
            if (denominator1 < 0) {
                numerator1 = -numerator1;
                denominator1 = -denominator1;
            }
            if (denominator2 < 0) {
                numerator2 = -numerator2;
                denominator2 = -denominator2;
            }
            int tempDenominator = denominator1 * denominator2;
            int tempNumerator = numerator1 * numerator2;
            reduction(tempNumerator, tempDenominator);
        } else {
            exp();
        }

    }

    public void div(int numerator1, int denominator1) throws NumberFormatException {
        if (denominator1 != 0) {
            if (numerator1 < 0) {
                numerator1 = -numerator1;
                denominator1 = -denominator1;
            }
            int tempDenominator = numerator1 * this.denominator;
            int tempNumerator = denominator1 * this.numerator;
            reduction(tempNumerator, tempDenominator);
        } else {
            exp();
        }

    }

    public void div(int numerator1, int denominator1, int numerator2, int denominator2) throws NumberFormatException {
        if (denominator1 != 0 & denominator2 != 0) {
            if (denominator1 < 0) {
                numerator1 = -numerator1;
                denominator1 = -denominator1;
            }
            if (numerator2 < 0) {
                numerator2 = -numerator2;
                denominator2 = -denominator2;
            }
            int tempDenominator = denominator1 * numerator2;
            int tempNumerator = numerator1 * denominator2;
            reduction(tempNumerator, tempDenominator);
        } else {
            exp();
        }

    }

    private void exp() throws NumberFormatException {
        throw new NumberFormatException("о в знаменателе");
    }

    public static Fraction sum(Fraction fraction1, Fraction fraction2) {
        int tempDenominator = getNok(fraction1.denominator, fraction2.denominator);
        int tempNumerator = (tempDenominator / fraction1.denominator) * fraction1.numerator + (tempDenominator / fraction2.denominator) * fraction2.numerator;
        return new Fraction(tempNumerator, tempDenominator);

    }

    public static Fraction sub(Fraction fraction1, Fraction fraction2) {
        int tempDenominator = getNok(fraction1.denominator, fraction2.denominator);
        int tempNumerator = -((tempDenominator / fraction1.denominator) * fraction1.numerator) + (tempDenominator / fraction2.denominator) * fraction2.numerator;
        return new Fraction(tempNumerator, tempDenominator);

    }

    public static Fraction mul(Fraction fraction1, Fraction fraction2) {
        int tempDenominator = fraction1.denominator * fraction2.denominator;
        int tempNumerator = fraction1.numerator * fraction2.numerator;
        return new Fraction(tempNumerator, tempDenominator);

    }

    public static Fraction div(Fraction fraction1, Fraction fraction2) {
        if (fraction2.numerator < 0) {
            fraction2.numerator = -fraction2.numerator;
            fraction2.denominator = -fraction2.denominator;
        }
        int tempDenominator = fraction1.denominator * fraction2.numerator;
        int tempNumerator = fraction1.numerator * fraction2.denominator;
        return new Fraction(tempNumerator, tempDenominator);

    }
}
