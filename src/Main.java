import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String str;
        for (; ; ) {
            try {
                str = scan.nextLine();
                if (str.equals("quit")) {
                    System.out.println("Завершение работы.");
                    break;
                }
                System.out.println(Fraction.calculate(str).toString());
            } catch (ArithmeticException ignored) {
                System.out.println("Ошибка. Деление на 0.");

            } catch (Exception ignored) {
                System.out.println("Ошибка. Некорректное выражение.");

            }
        }
    }
}


