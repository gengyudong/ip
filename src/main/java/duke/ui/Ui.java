package duke.ui;

import duke.tasks.Deadlines;
import duke.tasks.Events;
import duke.tasks.Task;
import duke.ui.GengException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Ui {
    public void showInitialMessage() {
        System.out.println("Hey yo! I'm Geng");
        System.out.println("How can I help you?");
    }

    public void showExitMessage() {
        System.out.println("Thank you for patronising. Hope to see you again soon!");
    }

    public void showErrorMessage(String message) {
        System.out.println("ERROR! " + message);
    }

    public void showLoadingError() {
        System.out.println("ERROR! Unable to load tasks from file.");
    }

    public void showTaskList(ArrayList<Task> taskList) {
        if (taskList.isEmpty()) {
            System.out.println("No texts stored yet! Talk to me more!");
        } else {
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println(i + 1 + ". " + taskList.get(i).toString());
            }
        }
    }

    public void showTaskListByDate(String input, ArrayList<Task> taskList) throws GengException {
        try {
            String date = input.substring(9).trim();
            LocalDate targetDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            System.out.println("Tasks on " + targetDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + ":");
            boolean existTask = false;

            for (Task task : taskList) {
                if (task instanceof Deadlines deadlineTask) {
                    LocalDateTime deadline = deadlineTask.getDeadline();
                    if (deadline.toLocalDate().equals(targetDate)) {
                        System.out.println(task.toString());
                        existTask = true;
                    }
                } else if (task instanceof Events eventTask) {
                    LocalDateTime from = eventTask.getStartDatetime();
                    LocalDateTime to = eventTask.getEndDatetime();
                    if (from.toLocalDate().equals(targetDate) || to.toLocalDate().equals(targetDate)) {
                        System.out.println(task.toString());
                        existTask = true;
                    }
                }
            }

            if (!existTask) {
                this.showErrorMessage("No tasks found for this date");
            }
        } catch (Exception e) {
            throw new GengException("Invalid date format. Use list-date yyyy-MM-dd.");
        }
    }

    public void showTaskAdded(Task task, int size) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task.toString());
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    public void showTaskDeleted(Task task, int size) {
        System.out.println("Alright! I've removed this task:");
        System.out.println("  " + task.toString());
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    public void showTaskMarked(Task task) {
        System.out.println("Good Job! I've marked this task as done:");
        System.out.println("  " + task.toString());
    }

    public void showTaskUnmarked(Task task) {
        System.out.println("Oki, I've marked this task as not done yet:");
        System.out.println("  " + task.toString());
    }
}
