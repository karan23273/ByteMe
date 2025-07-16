import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("\n                                                               WELCOME TO BYTE ME              ");
        Scanner input = new Scanner(System.in);
        while (true){
            System.out.println("                 ○  ENTER THE APPLICATION                                                                ○  EXIT THE APPLICATION\n");
            System.out.println("Choose 1 -> ENTER Application");
            System.out.println("Choose 0 -> EXIT Application\n");
            try {
                int num = input.nextInt();
                if (num == 0){
                    break;
                }else {
                    System.out.println("                ○ LOG IN                                               ○ SIGN UP");
                    System.out.println("Log in -> 1");
                    System.out.println("Sign Up -> 0");
                    int user_mode = input.nextInt();
                    Login u1 = new Login();
                    u1.Applicant_type(user_mode);
                }
            }catch (InputMismatchException e){
                System.err.println("Invalid Input: Please choose between 1 and 2");
                input.next();
            }

        }
        System.out.println("YOU EXITED THE APPLICATION :)                   ");
    }
}
