import java.util.Random;
import java.util.Scanner;

public class task2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        int count = 1;
        int com = rand.nextInt(101) ;
        System.out.println("Guess the number:");
        int user = sc.nextInt();
        while (0 <= user && user<= 100) {
            if (user == com) {
                System.out.println("you Guessed it right in "+count+" attempts");
                break;
            } else if (user > com) {
                System.out.println("you Guessed it high");
                 user = sc.nextInt();
                 count++;
            } else if (user < com) {
                System.out.println("you Guessed it low");
                count++;
                 user = sc.nextInt();
            }
        }
        sc.close();
    }
}
