package geng.ui;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import geng.tasks.Deadlines;
import geng.tasks.Events;
import geng.tasks.Task;

/**
 * The Ui class is responsible for displaying messages to the user.
 * It provides methods to show various types of messages, including
 * task lists, errors, and task updates.
 */
public class Ui {

    /**
     * Displays the initial greeting message when the program starts.
     *
     * @return String representation of greeting message.
     */
    public String showInitialMessage() {
        return "Hey yo! I'm Geng! How can I help you?";
    }

    /**
     * Displays the exit message when the user ends the session.
     */
    public String showExitMessage() {
        return "Thank you for patronising. Hope to see you again soon!";
    }

    /**
     * Displays an error message.
     *
     * @param message The error message to be displayed.
     */
    public String showErrorMessage(String message) {
        return "ERROR! " + message;
    }

    /**
     * Displays a loading error message when tasks cannot be loaded from the file.
     */
    public String showLoadingError() {
        return "ERROR! Unable to load tasks from file.";
    }

    /**
     * Displays the list of all tasks.
     * If there are no tasks, a message indicating no tasks is displayed.
     *
     * @param taskList The list of tasks to be displayed.
     */
    public String showTaskList(ArrayList<Task> taskList) {
        if (taskList.isEmpty()) {
            return "No texts stored yet! Talk to me more!";
        } else {
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < taskList.size(); i++) {
                result.append(i + 1).append(". ").append(taskList.get(i)).append("\n");
            }
            return result.toString().trim();
        }
    }

    /**
     * Displays tasks for a specific date based on user input.
     *
     * @param input    The user input containing the date to filter tasks by.
     * @param taskList The list of tasks to be checked.
     * @throws GengException If the date format is incorrect.
     */
    public String showTaskListByDate(String input, ArrayList<Task> taskList) throws GengException {
        try {
            String date = input.substring(9).trim();
            LocalDate targetDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            StringBuilder result = new StringBuilder();
            result.append("Tasks on ")
                    .append(targetDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy")))
                    .append(":\n");

            boolean isExist = false;

            for (Task task : taskList) {
                if (task instanceof Deadlines deadlineTask) {
                    LocalDateTime deadline = deadlineTask.getDeadline();
                    if (deadline.toLocalDate().equals(targetDate)) {
                        result.append(task.toString()).append("\n");
                        isExist = true;
                    }
                } else if (task instanceof Events eventTask) {
                    LocalDateTime from = eventTask.getStartDatetime();
                    LocalDateTime to = eventTask.getEndDatetime();
                    if (from.toLocalDate().equals(targetDate) || to.toLocalDate().equals(targetDate)) {
                        result.append(task.toString()).append("\n");
                        isExist = true;
                    }
                }
            }

            if (!isExist) {
                return "No tasks found for this date.";
            }

            return result.toString().trim(); // Return formatted list of tasks

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
    public String showTaskAdded(Task task, int size) {
        return "Got it. I've added this task:\n"
                + "  " + task.toString() + "\n"
                + "Now you have " + size + " tasks in the list.";
    }

    /**
     * Displays a message indicating a task has been deleted.
     *
     * @param task The task that was deleted.
     * @param size The current size of the task list after deletion.
     */
    public String showTaskDeleted(Task task, int size) {
        return "Alright! I've removed this task:\n"
                + "  " + task.toString() + "\n"
                + "Now you have " + size + " tasks in the list.";
    }

    /**
     * Displays a message indicating a task has been marked as complete.
     *
     * @param task The task that was marked as complete.
     */
    public String showTaskMarked(Task task) {
        return "Good Job! I've marked this task as done:\n"
                + "  " + task.toString() + "\n";
    }

    /**
     * Displays a message indicating a task has been marked as incomplete.
     *
     * @param task The task that was marked as incomplete.
     */
    public String showTaskUnmarked(Task task) {
        return "Oki, I've marked this task as not done yet:\n"
                + "  " + task.toString();
    }

    /**
     * Displays a list of Tasks for a specific date.
     *
     * @param matchedTask The list of task to be displayed.
     */
    public String showSchedule(ArrayList<Task> matchedTask) {
        if (matchedTask.isEmpty()) {
            return "No tasks found for this date.";
        } else {
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < matchedTask.size(); i++) {
                result.append(i + 1).append(". ").append(matchedTask.get(i)).append("\n");
            }
            return result.toString().trim();
        }
    }
}
