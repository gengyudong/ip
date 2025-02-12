package geng;

import java.util.Scanner;

import geng.commands.Command;
import geng.tasks.TaskList;
import geng.ui.GengException;
import geng.ui.Parser;
import geng.ui.Storage;
import geng.ui.Ui;

/**
 * Represents the main application class for Geng.
 * It initializes the user interface, task list, and storage, and manages the main application flow.
 */
public class Geng {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Constructs a Geng application instance with the specified file path for task storage.
     * Initializes the user interface and attempts to load saved tasks from the storage file.
     *
     * @param filePath The path of the file where tasks are stored.
     */
    public Geng(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (GengException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the main loop of the Geng application. Continuously accepts user input,
     * processes commands, and updates the task list until the user decides to exit.
     */
    public void run() {
        ui.showInitialMessage();
        Parser parser = new Parser();
        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            input = scanner.nextLine();
            try {
                // Exit the loop if the user types "bye"
                if (input.equals("bye")) {
                    ui.showExitMessage();
                    break;
                }
                Command command = parser.parseInput(input);
                command.execute(tasks, ui, storage);
            } catch (GengException e) {
                ui.showErrorMessage(e.getMessage());
            }
        }
        scanner.close();
    }

    /**
     * The entry point of the application. Initializes and runs the Geng application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        new Geng("data/geng.txt").run();
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) throws GengException {
        Parser parser = new Parser();
        Command command = parser.parseInput(input);
        return command.execute(tasks, ui, storage);
    }
}
