import java.util.Arrays;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Fraction {
    private int numerator;
    private int denominator;

    public int getNumerator() {
        return this.numerator;
    }

    public int getDenominator() {
        return this.denominator;
    }
    @Override
    public String toString() {
        return this.numerator + "/" + this.denominator;
    }

    public Fraction(int numerator, int denominator){
        if (denominator != 0) {
            if (denominator < 0) {
                numerator = -numerator;
                denominator = -denominator;
            }
            reduction(numerator, denominator);
        } else {
            throw new ArithmeticException();
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

    public static Fraction calculate(String str) {
        Stack<Fraction> fractionStack = new Stack<>();
        Stack<String> symbolStack = new Stack<>();
        String[] expression = toReadForm(str);
        String[] temp;
        Fraction tempFrac1;
        Fraction tempFrac2;
        Pattern patternFraction = Pattern.compile("^(-?\\d+)/(-?\\d+)$");
        for (int i = 0; i < expression.length; i++) {


            if (expression[i].length() > 1) {
                Matcher m = patternFraction.matcher(expression[i]);
                m.find();
                fractionStack.push(new Fraction(Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2))));
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
            } else if ((arrayString[i].equals("-")) && (arrayString[i - 1].equals("("))) {
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
