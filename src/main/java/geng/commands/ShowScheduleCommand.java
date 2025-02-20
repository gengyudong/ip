package geng.commands;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import geng.tasks.Deadlines;
import geng.tasks.Events;
import geng.tasks.Task;
import geng.tasks.TaskList;
import geng.ui.GengException;
import geng.ui.Storage;
import geng.ui.Ui;

/**
 * Represents a command to show the schedule for a specific date.
 * This command extracts the date from user input and displays all tasks
 * that occur on that date or later.
 */
public class ShowScheduleCommand implements Command {
    private final String date;

    /**
     * Constructs a {@code ShowScheduleCommand} by parsing user input.
     *
     * @param input The full user command.
     * @throws GengException If the input format is incorrect.
     */
    public ShowScheduleCommand(String input) throws GengException {
        try {
            String[] parts = input.split(" ", 2);
            this.date = parts[1];
        } catch (Exception e) {
            throw new GengException("Invalid date format. Use: show [yyyy-MM-dd]");
        }
    }

    /**
     * Executes the command by displaying all tasks that occur on the specified date or later.
     *
     * @param tasks   The task list to search for tasks.
     * @param ui      The user interface to display messages.
     * @param storage The storage handler to save the updated task list.
     * @throws GengException If there is an error in saving the task.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws GengException {
        ArrayList<Task> matchedTask = new ArrayList<>();
        LocalDate targetDate = LocalDate.parse(this.date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        for (Task task : tasks.getTaskList()) {
            if (task instanceof Events event) {
                LocalDate start = event.getStartDatetime().toLocalDate();
                LocalDate end = event.getEndDatetime().toLocalDate();
                if ((targetDate.isEqual(start) || targetDate.isAfter(start))
                        && (targetDate.isEqual(end) || targetDate.isBefore(end))) {
                    matchedTask.add(event);
                }
            }

            if (task instanceof Deadlines deadline) {
                LocalDate deadlineDate = deadline.getDeadline().toLocalDate();
                if (targetDate.isBefore(deadlineDate) || targetDate.isEqual(deadlineDate)) {
                    matchedTask.add(deadline);
                }
            }
        }
        return ui.showSchedule(matchedTask);
    }
}
