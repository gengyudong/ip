package geng.gui;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;


/**
 * Represents a dialog box in Geng chatbot interface.
 * This class extends {@code HBox} and consists of a {@code Label} to display text
 * and an {@code ImageView} to show the speaker's profile picture.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructs a {@code DialogBox} with the specified text and display picture.
     * @param text The message to be displayed in the dialog box.
     * @param img The profile image of the speaker.
     */
    private DialogBox(String text, Image img, boolean isUser) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.setText(text);
        displayPicture.setImage(img);

        if (isUser) {
            dialog.setTextAlignment(javafx.scene.text.TextAlignment.RIGHT);
        } else {
            dialog.setTextAlignment(javafx.scene.text.TextAlignment.LEFT);
        }
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
        this.setAlignment(Pos.TOP_RIGHT);
    }

    public static DialogBox getGengDialog(String text, Image img) {
        return new DialogBox(text, img, false);
    }

    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img, true);
        db.flip();
        return db;
    }
}
