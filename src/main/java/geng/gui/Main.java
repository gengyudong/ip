package geng.gui;

import java.io.IOException;

import geng.Geng;
import geng.ui.Ui;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The entry point of the JavaFX application.
 * This class extends {@link Application} and creates a simple GUI window
 * displaying a "Hello World!" label.
 */
public class Main extends Application {

    private final Geng geng = new Geng("data/geng.txt");
    private final Ui ui = new Ui();

    /**
     * Starts the JavaFX application by setting up the primary stage.
     * A simple scene containing a label with the text "Hello World!" is created and displayed.
     *
     * @param stage The primary stage for this application.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);

            MainWindow controller = fxmlLoader.getController();
            controller.setGeng(geng);
            controller.showWelcomeMessage(ui);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

