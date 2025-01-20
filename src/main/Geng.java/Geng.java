import java.util.Scanner;

public class Geng {
    public static void main(String[] args) {
        String[] inputs = new String[100];
        int inputCount = 0;

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

            if (input.equals("list")) {
                if (inputCount == 0) {
                    System.out.println("No texts stored yet! Talk to me more!");
                } else {
                    for (int i = 0; i < inputCount; i++) {
                        System.out.println(i + 1 + ". " + inputs[i]);
                    }
                }
            } else {
                inputs[inputCount] = input;
                inputCount++;
                System.out.println("added: " + input);
            }
        }
        scanner.close();
    }
}
