package GuessTheNumber;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;

public class Controller {
    
    private Stage primaryStage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField username;

    @FXML
    private Button nextButton;

    public void initialize()
    {
        nextButton.setDisable(true);
        nextButton.setDefaultButton(true);
    }

    public void switchToRangeSelection(ActionEvent event) throws IOException
    {
        String name = username.getText();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("RangeSelection.fxml"));
        root = loader.load();

        RangeController rangeController = loader.getController();
        rangeController.initialize(name);
        
        primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 600, 400);
        JMetro jMetro = new JMetro();
        jMetro.setScene(scene);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    public void handleKeyReleased()
    {
        String name = username.getText();
        boolean disableButton = name.isEmpty() || name.trim().isEmpty();
        nextButton.setDisable(disableButton);
    }

}

