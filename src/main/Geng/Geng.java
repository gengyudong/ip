import java.util.ArrayList;
import java.util.Scanner;

public class Geng {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

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

    public void run() {
        ui.showInitialMessage();
        Parser parser = new Parser();
        Scanner scanner = new Scanner(System.in);
        String input;

        while(true) {
            input = scanner.nextLine();
            try {
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

    public static void main(String[] args) {
        new Geng("data/geng.txt").run();
    }
}
