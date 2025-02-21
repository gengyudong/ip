package geng.ui;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import geng.tasks.Deadlines;
import geng.tasks.Events;
import geng.tasks.Task;
import geng.tasks.ToDos;

/**
 * The Storage class is responsible for loading and saving tasks to/from a file.
 * It provides methods to load tasks from the file at initialization and save
 * updated tasks back to the file.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructor for Storage that initializes the file path.
     *
     * @param filePath The path to the file where tasks will be saved or loaded from.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the task list from the file. If the file does not exist, it creates the file.
     *
     * @return An ArrayList containing the tasks loaded from the file.
     * @throws GengException If an error occurs while loading tasks from the file.
     */
    public ArrayList<Task> load() throws GengException {
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
                return taskList;
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
            throw new GengException("ERROR! Unable to load tasks from file.");
        }
        return taskList;
    }

    /**
     * Parses a task from a line in the file. The line format is expected to be:
     * - T | isDone | description
     * - D | isDone | description | deadline
     * - E | isDone | description | start-end
     *
     * @param line The line to be parsed into a Task object.
     * @return A Task object if parsing is successful, otherwise null.
     */
    private Task parseTask(String line) {
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

    /**
     * Saves the task list to the file.
     *
     * @param taskList The list of tasks to be saved to the file.
     * @throws GengException If an error occurs while saving tasks to the file.
     */
    public void saveTasksToFile(ArrayList<Task> taskList) throws GengException {
        try {
            BufferedWriter fileWriter = new BufferedWriter(new FileWriter(filePath));
            for (Task task : taskList) {
                String taskString = task.toString();
                fileWriter.write(taskString);
                fileWriter.newLine();
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new GengException("ERROR! Unable to save tasks to file.");
        }
    }
}
