import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static void main(String[] args) {
        int number, numberTwo, numberThree;
        try (Scanner scanner = new Scanner(System.in)){
            System.out.print("Digite o 1º número:");
            number = Integer.parseInt(scanner.nextLine());
            System.out.print("Digite o 2º número:");
            numberTwo = scanner.nextInt();
            numberThree = number/numberTwo;
            System.out.println("Valor da divisão é " + numberThree);
        } catch (ArithmeticException e) {
            System.out.println("Erro! divisão por zero");
        } catch (InputMismatchException | NumberFormatException e) {
            System.out.println("Erro! valor digitado não é inteiro");
        } catch (Exception e) {
            System.out.println("Erro!");
        } /* finally {
            scanner.close() // fechar sempre
        }*/


        Product product = new Product();
        product.setId(0);
    }
}
