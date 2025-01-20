import java.util.Scanner;

public class Geng {
    public static void main(String[] args) {
        Task[] taskList = new Task[100];
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
                        String status = taskList[i].getStatusIcon();
                        System.out.println(i + 1 + "." + status + " " + taskList[i].getDescription());
                    }
                }
            } else if (input.startsWith("mark")) {
                try {
                    int index = Integer.parseInt(input.split(" ")[1]) - 1;
                    Task task = taskList[index];

                    if (index < 0 || index > inputCount) {
                        System.out.println("Invalid task number. Please enter a number from 1 to 100");
                    }

                    task.markComplete();
                    System.out.println("Good Job! I've marked this task as done:");
                    System.out.println("  " + task.getStatusIcon() + " " + task.getDescription());
                } catch (Exception e) {
                    System.out.println("Invalid command format. Use mark (task no.)");
                }
            } else if (input.startsWith("unmark")) {
                try {
                    int index = Integer.parseInt(input.split(" ")[1]) - 1;
                    Task task = taskList[index];

                    if (index < 0 || index > inputCount) {
                        System.out.println("Invalid task number. Please enter a number from 1 to 100");
                    }

                    task.markUncomplete();
                    System.out.println("Oki, I've marked this task not done yet:");
                    System.out.println("  " + task.getStatusIcon() + " " + task.getDescription());
                } catch (Exception e) {
                    System.out.println("Invalid command format. Use mark (task no.)");
                }
            } else {
                Task task = new Task(input);
                taskList[inputCount] = task;
                inputCount++;
                System.out.println("added: " + input);
            }
        }
        scanner.close();
    }
}
