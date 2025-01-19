import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hey yo! I'm Geng");
        System.out.println("How can I help you?");

        Scanner scanner = new Scanner(System.in);
        String input;

        while(true) {
            input = scanner.nextLine();

            if (input.equals("bye")) {
                System.out.println("Thank you for patronising. Hope to see you again soon!");
                break;
            }

            System.out.println(input);

        }
        scanner.close();
    }
}
