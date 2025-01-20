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
                        System.out.println(i + 1 + "." + taskList[i].toString());
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
                    System.out.println("  " + task.toString());
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
                    System.out.println("  " + task.toString());
                } catch (Exception e) {
                    System.out.println("Invalid command format. Use unmark (task no.)");
                }
            } else if (input.startsWith("todo")) {
                String taskDescription = input.substring(4).trim();
                ToDos todoTask = new ToDos(taskDescription);
                taskList[inputCount] = todoTask;
                inputCount++;

                System.out.println("Got it. I've added this task:");
                System.out.println("  " + todoTask.toString());
                System.out.println("Now you have " + inputCount + " tasks in the list.");
            } else if (input.startsWith("deadline")) {
                String details = input.substring(8).trim();
                String[] parts = details.split("/by", 2);
                String description = parts[0].trim();
                String deadline = parts[1].trim();

                Deadlines deadlineTask = new Deadlines(description, deadline);
                taskList[inputCount] = deadlineTask;
                inputCount++;

                System.out.println("Got it. I've added this task:");
                System.out.println("  " + deadlineTask.toString());
                System.out.println("Now you have " + inputCount + " tasks in the list.");
            } else if (input.startsWith("event")) {
                String details = input.substring(5).trim();
                String[] fromParts = details.split("/from", 2);
                String[] toParts = fromParts[1].split("/to", 2);
                String description = fromParts[0].trim();
                String fromDatetime = toParts[0].trim();
                String toDatetime = toParts[1].trim();

                Events eventTask = new Events(description, fromDatetime, toDatetime);
                taskList[inputCount] = eventTask;
                inputCount++;

                System.out.println("Got it. I've added this task:");
                System.out.println("  " + eventTask.toString());
                System.out.println("Now you have " + inputCount + " tasks in the list.");
            }
        }
        scanner.close();
    }
}
