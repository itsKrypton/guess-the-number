package GuessTheNumber;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import javafx.scene.Node;

public class RangeController {
    private Stage primaryStage;
    private Scene scene;
    private Parent root;
    private String name;
    
    @FXML
    private TextField startingNumber;

    @FXML
    private TextField endingNumber;

    @FXML
    private Label welcomeText;

    @FXML
    private Button playButton;

    public void initialize(String username)
    {
        this.name = username;
        welcomeText.setText("Hi " + this.name + "!");
        playButton.setDisable(true);
        playButton.setDefaultButton(true);
    }

    public void initialiseGame(ActionEvent event) throws IOException
    {
        String start = startingNumber.getText();
        String ending = endingNumber.getText();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainGame.fxml"));
        root = loader.load();

        GameController gameController = loader.getController();
        gameController.initialize(start, ending, this.name);

        primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 600, 400);
        JMetro jMetro = new JMetro();
        jMetro.setScene(scene);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void handleKeyReleased()
    {
        String starting = startingNumber.getText();
        String ending = endingNumber.getText();
        boolean disableButton = (starting.isEmpty() || starting.trim().isEmpty()) || (ending.isEmpty() || ending.trim().isEmpty()) || (!starting.matches("[+-]?\\d*(\\.\\d+)?") || !ending.matches("[+-]?\\d*(\\.\\d+)?")) || (Integer.parseInt(starting) >= Integer.parseInt(ending)) || (Integer.parseInt(starting) < 0 || Integer.parseInt(ending) < 0);
        playButton.setDisable(disableButton);
    }
}
