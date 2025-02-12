package geng;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * The entry point of the JavaFX application.
 * This class extends {@link Application} and creates a simple GUI window
 * displaying a "Hello World!" label.
 */
public class Main extends Application {

    /**
     * Starts the JavaFX application by setting up the primary stage.
     * A simple scene containing a label with the text "Hello World!" is created and displayed.
     *
     * @param stage The primary stage for this application.
     */
    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!");
        Scene scene = new Scene(helloWorld);

        stage.setScene(scene);
        stage.show();
    }
}

