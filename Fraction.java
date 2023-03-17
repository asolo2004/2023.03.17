import java.util.Arrays;
import java.util.Stack;

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

    public static Fraction count(String str) {
        Stack<Fraction> fractionStack = new Stack<>();
        Stack<String> symbolStack = new Stack<>();
        String[] expression = toReadForm(str);
        String[] temp = new String[2];
        Fraction tempFrac1 = new Fraction();
        Fraction tempFrac2 = new Fraction();

        for (int i = 0; i < expression.length; i++) {


            if (expression[i].length() > 1) {
                temp = expression[i].split("/");
                fractionStack.push(new Fraction(Integer.parseInt(temp[0]), Integer.parseInt(temp[1])));
            } else if ((expression[i].equals("(")) || (symbolStack.empty())) {
                symbolStack.push(expression[i]);
            } else if ((symbolStack.peek().equals("(")) && (expression[i].equals(")"))) {
                symbolStack.pop();
            } else if (symbolStack.peek().equals("(")) {
                symbolStack.push(expression[i]);
            } else if (expression[i].equals(")")) {
                while (!symbolStack.peek().equals("(")) {
                    tempFrac1 = fractionStack.peek();
                    fractionStack.pop();
                    tempFrac2 = fractionStack.peek();
                    fractionStack.pop();
                    fractionStack.push(stringCount(symbolStack.peek(), tempFrac1, tempFrac2));
                    symbolStack.pop();
                }
                symbolStack.pop();

            } else if (priority(symbolStack.peek(), expression[i])) {
                symbolStack.push(expression[i]);
            } else if (!priority(symbolStack.peek(), expression[i])) {
                tempFrac1 = fractionStack.peek();
                fractionStack.pop();
                tempFrac2 = fractionStack.peek();
                fractionStack.pop();
                fractionStack.push(stringCount(symbolStack.peek(), tempFrac1, tempFrac2));
                symbolStack.pop();
                symbolStack.push(expression[i]);
            }
        }

        while (!symbolStack.empty()) {
            tempFrac1 = fractionStack.peek();
            fractionStack.pop();
            tempFrac2 = fractionStack.peek();
            fractionStack.pop();
            fractionStack.push(stringCount(symbolStack.peek(), tempFrac1, tempFrac2));
            symbolStack.pop();
        }
        return fractionStack.peek();

    }

    private static String[] toReadForm(String str) {

        String[] tempExpression = new String[str.length()];
        int i, curNum = 0;
        String tempString = "";
        str = str.replaceAll("\\s", "");
        String[] arrayString = str.split("");
        for (i = 0; i < str.length(); i++) {
            if ((arrayString[i].equals("+")) || (arrayString[i].equals("*")) || (arrayString[i].equals("(")) || (arrayString[i].equals(")")) || (arrayString[i].equals(":"))) {
                if (tempString.length() != 0) {
                    tempExpression[curNum] = tempString;
                    curNum += 1;
                    tempString = "";
                }
                tempExpression[curNum] = arrayString[i];
                curNum += 1;
            } else if (i == 0) {
                tempString += arrayString[i];
            } else if ((arrayString[i].equals("-")) && ((arrayString[i - 1]).equals("(")) || ((arrayString[i - 1]).equals(")")) && (arrayString[i + 1]).equals("\\d")) {
                tempString += arrayString[i];
            } else if (arrayString[i].equals("-") && (arrayString[i - 1]).equals("/")) {
                tempString += arrayString[i];
            } else if (arrayString[i].equals("-")) {
                if (tempString.length() != 0) {
                    tempExpression[curNum] = tempString;
                    curNum += 1;
                    tempString = "";
                }
                tempExpression[curNum] = arrayString[i];
                curNum += 1;
            } else {
                tempString += arrayString[i];
            }

        }
        if (tempString.length() != 0) {
            tempExpression[curNum] = tempString;
            curNum += 1;
        }
        return Arrays.copyOfRange(tempExpression, 0, curNum);
    }

    private static boolean priority(String str1, String str2) {
        if ((str1.equals("-") || str1.equals("+")) && ((str2.equals("*")) || (str2.equals(":")))) {
            return true;
        } else {
            return false;
        }
    }

    private static Fraction stringCount(String str, Fraction frac2, Fraction frac1) {
        switch (str.toCharArray()[0]) {
            case ':':
                return Fraction.div(frac1, frac2);
            case '-':
                return Fraction.sub(frac2, frac1);
            case '*':
                return Fraction.mul(frac1, frac2);
            case '+':
                return Fraction.sum(frac1, frac2);
            default:
                return frac1;
        }
    }
}
