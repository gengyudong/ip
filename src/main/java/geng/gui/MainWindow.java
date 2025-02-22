package geng.gui;

import geng.Geng;
import geng.ui.GengException;
import geng.ui.Ui;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Geng geng;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/musk.jpg"));
    private Image gengImage = new Image(this.getClass().getResourceAsStream("/images/trump.jpg"));

    /**
     * Initializes the main window.
     * This method binds the scroll pane to the dialog container and sets the preferred width of the dialog container.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.prefWidthProperty().bind(scrollPane.widthProperty());
    }

    public void setGeng(Geng geng) {
        this.geng = geng;
    }

    /**
     * Displays a welcome message using the provided Ui instance.
     * This method retrieves the initial message from the Ui and adds it nto the dialog container,
     * displaying it in a dialog box.
     *
     * @param ui The Ui instance that provides the initial message to display.
     */
    public void showWelcomeMessage(Ui ui) {
        String welcomeText = ui.showInitialMessage();
        dialogContainer.getChildren().addAll(DialogBox.getGengDialog(welcomeText, gengImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws GengException {
        String userText = this.userInput.getText();
        String gengText = this.geng.getResponse(userText);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, userImage),
                DialogBox.getGengDialog(gengText, gengImage)
        );
        userInput.clear();
    }
}
