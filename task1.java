import java.util.*;

public class task1 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the first number");
        int num1 = sc.nextInt();
        System.out.println("Enter the second number");
        int num2 = sc.nextInt();

        while (true) {
            System.out.println("1. ADDITION");
            System.out.println("2. SUBTRACTION");
            System.out.println("3. MULTIPLICATION");
            System.out.println("4. DIVISION");
            System.out.println("5. EXIT");

            System.out.println("Enter your choice:");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Sum:" + (num1 + num2));
                    break;
                case 2:
                    System.out.println("Difference:" + (num1 - num2));
                    break;
                case 3:
                    System.out.println("Product:" + (num1 * num2));
                    break;
                case 4:
                    if (num2 != 0) {
                        System.out.println("Quotient:" + (num1 / num2));
                    } else {
                        System.out.println("Error: Division by zero");
                    }
                    break;
                case 5:
                    sc.close(); 
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
