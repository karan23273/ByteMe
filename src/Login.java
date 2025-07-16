import java.util.InputMismatchException;
import java.util.Scanner;

abstract class Users {
    abstract int login(int x);
    abstract int Method();

    int getYesOrNo(Scanner scanner) {
        int choice;
        while (true) {
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
                if (choice == 1 || choice == 0) {
                    break;
                } else {
                    System.out.println("Invalid input! Please enter 1 or 0.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter 1 or 0.");
                scanner.next();
            }
        }
        return choice;
    }
    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return false;
        } catch (NumberFormatException e) {
            return true;
        }
    }
}

public class Login {

    public void Applicant_type(int user_mode){
        System.out.println("Login as:\t○ Customer\t\t\t○ STAFF");
        System.out.println("Press 1 -> Customer\nPress 0 -> Staff ");

        Scanner input_ = new Scanner(System.in);

        int number = 0;
        boolean validInput = false;

        while (!validInput) {
            System.out.print("Enter : ");
            try {
                number = input_.nextInt();
                validInput = true;
            } catch (InputMismatchException e) {
                System.err.println("Invalid input! Please enter a valid number between 1/0 ");
                input_.next();
            }
        }

        if (number == 1){
            Customer S1 = new Customer();
            int m = -1;
            m = S1.login(user_mode);
            while (m!=0){

                m = S1.Method();
            }

        }
        else if (number == 0) {
            Administrator P1 = new Administrator();
            int m = -1;
            m = P1.login(user_mode);
            while (m != 0){
                m = P1.Method();
            }
        }

    }


}
