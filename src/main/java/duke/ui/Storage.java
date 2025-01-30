package duke.ui;

import duke.tasks.Deadlines;
import duke.tasks.Events;
import duke.tasks.Task;
import duke.tasks.ToDos;
import duke.ui.GengException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String FILE_PATH;

    public Storage(String filePath) {
        this.FILE_PATH = filePath;
    }

    public ArrayList<Task> load() throws GengException {
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            File file = new File(FILE_PATH);
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

    public void saveTasksToFile(ArrayList<Task> taskList) throws GengException{
        try {
            BufferedWriter fileWriter = new BufferedWriter(new FileWriter(FILE_PATH));
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
