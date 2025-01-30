import jdk.jfr.Event;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Geng {
    private static final String FILE_PATH = "./data/geng.txt";

    public static void main(String[] args) {
        ArrayList<Task> taskList = new ArrayList<>();

        System.out.println("Hey yo! I'm Geng");
        System.out.println("How can I help you?");

        loadTasksFromFile(taskList);

        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            input = scanner.nextLine();
            try {
                if (input.equals("bye")) {
                    System.out.println("Thank you for patronising. Hope to see you again soon!");
                    break;
                }

                if (input.equals("list")) {
                    listTasks(taskList);
                } else if (input.startsWith("list-date")) {
                    listTaskByDate(input, taskList);
                } else if (input.startsWith("delete")) {
                    deleteTask(input, taskList);
                } else if (input.startsWith("mark")) {
                    markTask(input, taskList);
                } else if (input.startsWith("unmark")) {
                    unmarkTask(input, taskList);
                } else if (input.startsWith("todo")) {
                    addTodoTask(input, taskList);
                } else if (input.startsWith("deadline")) {
                    addDeadlineTask(input, taskList);
                } else if (input.startsWith("event")) {
                    addEventTask(input, taskList);
                } else {
                    throw new GengException("My brain is not big enough to understand... Input: todo/deadline/event");
                }

                saveTasksToFile(taskList);
            } catch (GengException e) {
                System.out.println("ERROR! " + e.getMessage());
            }
        }
        scanner.close();
    }

    private static void loadTasksFromFile(ArrayList<Task> taskList) {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
                return;
            }

            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                Task task = parseTask(line);
                if (task != null) {
                    taskList.add(task);
                }
            }
            fileScanner.close();
        } catch (IOException e) {
            System.out.println("ERROR! Unable to load tasks from file: " + e.getMessage());
        }
    }

    private static Task parseTask(String line) {
        try {
            String[] parts = line.split(" \\| ");
            String type = parts[0];
            int isDone = Integer.parseInt(parts[1]);
            String description = parts[2];

            switch (type) {
            case "T":
                ToDos todoTask = new ToDos(description);
                if (isDone == 1) {
                    todoTask.markComplete();
                }
                return todoTask;

            case "D":
                String deadline = parts[3];
                LocalDateTime by = LocalDateTime.parse(deadline, DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm a"));
                String deadline2 = by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
                Deadlines deadlineTask = new Deadlines(description, deadline2);
                if (isDone == 1) {
                    deadlineTask.markComplete();
                }
                return deadlineTask;

            case "E":
                String[] eventParts = parts[3].split(" - ");
                String from = eventParts[0];
                String to = eventParts[1];
                Events eventTask = new Events(description, from, to);
                if (isDone == 1) {
                    eventTask.markComplete();
                }
                return eventTask;
            default:
                throw new GengException("ERROR! Corrupted file: Unknown task type.");
            }
        } catch (Exception e) {
            System.out.println("ERROR! Corrupted task in file: " + line);
            return null;
        }
    }

    private static void saveTasksToFile(ArrayList<Task> taskList) {
        try {
            BufferedWriter fileWriter = new BufferedWriter(new FileWriter(FILE_PATH));
            for (Task task : taskList) {
                String taskString = task.toString();
                fileWriter.write(taskString);
                fileWriter.newLine();
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("ERROR! Unable to save tasks to file: " + e.getMessage());
        }
    }

    private static void listTasks(ArrayList<Task> taskList) {
        if (taskList.isEmpty()) {
            System.out.println("No texts stored yet! Talk to me more!");
        } else {
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println(i + 1 + ". " + taskList.get(i).toString());
            }
        }
    }

    private static void listTaskByDate(String input, ArrayList<Task> taskList) throws GengException{
        try {
            String date = input.substring(9).trim();
            LocalDate targetDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            System.out.println("Tasks on " + targetDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + ":");
            boolean existTask = false;

            for (Task task : taskList) {
                if (task instanceof Deadlines) {
                    Deadlines deadlineTask = (Deadlines) task;
                    LocalDateTime deadline = deadlineTask.getDeadline();
                    if (deadline.toLocalDate().equals(targetDate)) {
                        System.out.println(task.toString());
                        existTask = true;
                    }
                } else if (task instanceof Events) {
                    Events eventTask = (Events) task;
                    LocalDateTime from = eventTask.getStartDatetime();
                    LocalDateTime to = eventTask.getEndDatetime();
                    if (from.toLocalDate().equals(targetDate) || to.toLocalDate().equals(targetDate)) {
                        System.out.println(task.toString());
                        existTask = true;
                    }
                }
            }

            if (!existTask) {
                System.out.println("No tasks found for this date");
            }
        } catch (Exception e) {
            throw new GengException("Invalid date format. Use list-date yyyy-MM-dd.");
        }
    }

    private static void deleteTask(String input, ArrayList<Task> taskList) throws GengException {
        try {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;

            if (taskList.isEmpty()) {
                throw new GengException("Invalid task number. Please enter a task first.");
            } else if (index < 0 || index >= taskList.size()) {
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
    }

    private static void markTask(String input, ArrayList<Task> taskList) throws GengException {
        try {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;

            if (index < 0 || index >= taskList.size()) {
                System.out.println("Invalid task number. Please enter a number from 1 to " + taskList.size());
            }

            Task task = taskList.get(index);
            task.markComplete();

            System.out.println("Good Job! I've marked this task as done:");
            System.out.println("  " + task.toString());
        } catch (Exception e) {
            System.out.println("Invalid command format. Use mark (task no.)");
        }
    }

    private static void unmarkTask(String input, ArrayList<Task> taskList) throws GengException {
        try {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;

            if (index < 0 || index >= taskList.size()) {
                System.out.println("Invalid task number. Please enter a number from 1 to 100");
            }

            Task task = taskList.get(index);
            task.markUncomplete();

            System.out.println("Oki, I've marked this task not done yet:");
            System.out.println("  " + task.toString());
        } catch (Exception e) {
            System.out.println("Invalid command format. Use unmark (task no.)");
        }
    }

    private static void addTodoTask(String input, ArrayList<Task> taskList) throws GengException {
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
    }

    private static void addDeadlineTask(String input, ArrayList<Task> taskList) throws GengException {
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
            throw new GengException("Invalid command format. Use deadline <description> /by <yyyy-MM-dd HHmm>");
        }
    }

    private static void addEventTask(String input, ArrayList<Task> taskList) throws GengException {
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
            throw new GengException("Invalid command format. Use event <description> /from <yyyy-MM-dd HHmm> " +
                    "/to <yyyy-MM-dd HHmm>");
        }
    }
}
