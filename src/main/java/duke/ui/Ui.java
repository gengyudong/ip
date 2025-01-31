package duke.ui;

import duke.tasks.Deadlines;
import duke.tasks.Events;
import duke.tasks.Task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * The Ui class is responsible for displaying messages to the user.
 * It provides methods to show various types of messages, including
 * task lists, errors, and task updates.
 */
public class Ui {

    /**
     * Displays the initial greeting message when the program starts.
     */
    public void showInitialMessage() {
        System.out.println("Hey yo! I'm Geng");
        System.out.println("How can I help you?");
    }

    /**
     * Displays the exit message when the user ends the session.
     */
    public void showExitMessage() {
        System.out.println("Thank you for patronising. Hope to see you again soon!");
    }

    /**
     * Displays an error message.
     *
     * @param message The error message to be displayed.
     */
    public void showErrorMessage(String message) {
        System.out.println("ERROR! " + message);
    }

    /**
     * Displays a loading error message when tasks cannot be loaded from the file.
     */
    public void showLoadingError() {
        System.out.println("ERROR! Unable to load tasks from file.");
    }

    /**
     * Displays the list of all tasks.
     * If there are no tasks, a message indicating no tasks is displayed.
     *
     * @param taskList The list of tasks to be displayed.
     */
    public void showTaskList(ArrayList<Task> taskList) {
        if (taskList.isEmpty()) {
            System.out.println("No texts stored yet! Talk to me more!");
        } else {
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println(i + 1 + ". " + taskList.get(i).toString());
            }
        }
    }

    /**
     * Displays tasks for a specific date based on user input.
     *
     * @param input    The user input containing the date to filter tasks by.
     * @param taskList The list of tasks to be checked.
     * @throws GengException If the date format is incorrect.
     */
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

    /**
     * Displays a message indicating a task has been added.
     *
     * @param task The task that was added.
     * @param size The current size of the task list after addition.
     */
    public void showTaskAdded(Task task, int size) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task.toString());
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    /**
     * Displays a message indicating a task has been deleted.
     *
     * @param task The task that was deleted.
     * @param size The current size of the task list after deletion.
     */
    public void showTaskDeleted(Task task, int size) {
        System.out.println("Alright! I've removed this task:");
        System.out.println("  " + task.toString());
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    /**
     * Displays a message indicating a task has been marked as complete.
     *
     * @param task The task that was marked as complete.
     */
    public void showTaskMarked(Task task) {
        System.out.println("Good Job! I've marked this task as done:");
        System.out.println("  " + task.toString());
    }

    /**
     * Displays a message indicating a task has been marked as incomplete.
     *
     * @param task The task that was marked as incomplete.
     */
    public void showTaskUnmarked(Task task) {
        System.out.println("Oki, I've marked this task as not done yet:");
        System.out.println("  " + task.toString());
    }
}