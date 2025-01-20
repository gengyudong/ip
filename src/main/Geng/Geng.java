import java.util.ArrayList;
import java.util.Scanner;

public class Geng {
    public static void main(String[] args) {
        //Task[] taskList = new Task[100];
        //int inputCount = 0;
        ArrayList<Task> taskList = new ArrayList<>();

        System.out.println("Hey yo! I'm Geng");
        System.out.println("How can I help you?");

        Scanner scanner = new Scanner(System.in);
        String input;

        while(true) {
            input = scanner.nextLine();
            try {
                if (input.equals("bye")) {
                    System.out.println("Thank you for patronising. Hope to see you again soon!");
                    break;
                }

                if (input.equals("list")) {
                    if (taskList.isEmpty()) {
                        System.out.println("No texts stored yet! Talk to me more!");
                    } else {
                        for (int i = 0; i < taskList.size(); i++) {
                            System.out.println(i + 1 + "." + taskList.get(i).toString());
                        }
                    }
                } else if (input.startsWith("delete")) {
                    try {
                        int index = Integer.parseInt(input.split(" ")[1]) - 1;

                        if (taskList.isEmpty()) {
                            throw new GengException("Invalid task number. Please enter a task first.");
                        } else if (index < 0 || index > taskList.size()) {
                            throw new GengException("Invalid task number. Please enter a number from 1 to " + taskList.size());
                        }

                        Task task = taskList.get(index);
                        taskList.remove(index);

                        System.out.println("Alright! I've removed this task:");
                        System.out.println("  " + task.toString());
                        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                    } catch (GengException e) {
                        System.out.println("ERROR! " + e.getMessage());
                    }
                } else if (input.startsWith("mark")) {
                    try {
                        int index = Integer.parseInt(input.split(" ")[1]) - 1;
                        Task task = taskList.get(index);

                        if (index < 0 || index > 99) {
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
                        Task task = taskList.get(index);

                        if (index < 0 || index > 99) {
                            System.out.println("Invalid task number. Please enter a number from 1 to 100");
                        }

                        task.markUncomplete();
                        System.out.println("Oki, I've marked this task not done yet:");
                        System.out.println("  " + task.toString());
                    } catch (Exception e) {
                        System.out.println("Invalid command format. Use unmark (task no.)");
                    }
                } else if (input.startsWith("todo")) {
                    try {
                        String taskDescription = input.substring(4).trim();
                        if (taskDescription.isEmpty()) {
                            throw new GengException("The description cannot be empty yo! Input: todo <description>");
                        }
                        ToDos todoTask = new ToDos(taskDescription);
                        taskList.add(todoTask);

                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + todoTask.toString());
                        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                    } catch (GengException e) {
                        System.out.println("ERROR! " + e.getMessage());
                    }
                } else if (input.startsWith("deadline")) {
                    try {
                        String details = input.substring(8).trim();
                        if (details.isEmpty()) {
                            throw new GengException("The description cannot be empty yo! Input: deadline <description>");
                        }
                        String[] parts = details.split("/by", 2);
                        String description = parts[0].trim();
                        String deadline = parts[1].trim();

                        Deadlines deadlineTask = new Deadlines(description, deadline);
                        taskList.add(deadlineTask);

                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + deadlineTask.toString());
                        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                    } catch (GengException e) {
                        System.out.println("ERROR! " + e.getMessage());
                    }

                } else if (input.startsWith("event")) {
                    try {
                        String details = input.substring(5).trim();
                        if (details.isEmpty()) {
                            throw new GengException("The description cannot be empty yo! Input: event <description>");
                        }
                        String[] fromParts = details.split("/from", 2);
                        String[] toParts = fromParts[1].split("/to", 2);
                        String description = fromParts[0].trim();
                        String fromDatetime = toParts[0].trim();
                        String toDatetime = toParts[1].trim();

                        Events eventTask = new Events(description, fromDatetime, toDatetime);
                        taskList.add(eventTask);

                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + eventTask.toString());
                        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                    } catch (GengException e) {
                        System.out.println("ERROR! " + e.getMessage());
                    }
                } else {
                    throw new GengException("My brain is not big enough to understand... Input: todo/deadline/event");
                }
            } catch (GengException e) {
                System.out.println("ERROR! " + e.getMessage());
            }
        }
        scanner.close();
    }
}
