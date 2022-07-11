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

public class GameController {

    private Stage primaryStage;
    private Scene scene;
    private Parent root;
    private String username;

    @FXML
    private Label hintMessages;

    @FXML
    private Button guessButton;

    @FXML
    private Button playAgain;

    @FXML
    private TextField inputNumber;

    private double generatedNumber;
    private int start;
    private int end;

    public void initialize(String start, String end, String username) throws IOException
    {
        this.username = username;
        guessButton.setDisable(true);
        playAgain.setVisible(false);
        guessButton.setDefaultButton(true);
        this.start = Integer.parseInt(start);
        this.end = Integer.parseInt(end);
        this.generatedNumber = (int)Math.floor(Math.random()*(this.end-this.start+1)+this.start);
        System.out.println("Generated Number: " + this.generatedNumber);
    }

    public void checkNumber()
    {
        double input = Double.parseDouble(inputNumber.getText());
        double range;
        inputNumber.clear();
        if(input < generatedNumber)
        {
            range = input/this.generatedNumber;
            if(range < 0.25)
            hintMessages.setText((int)input + " is Too Low!");
            else if(range >= 0.25 && range < 0.75)
            hintMessages.setText((int)input + " is Low!");
            else
            hintMessages.setText((int)input + " is Close!");
        }

        else if (input > generatedNumber)
        {
            range = 1 / (input / (double)this.generatedNumber);
            if(range < 0.40)
            hintMessages.setText((int)input + " is Too High!");
            else if(range >= 0.40 && range < 0.75)
            hintMessages.setText((int)input + " is High!");
            else
            hintMessages.setText((int)input + " is Close!");
        }

        else
        {
            hintMessages.setText("Congratulations, you found the number! It was: " + Integer.toString((int)this.generatedNumber));
            inputNumber.setDisable(true);
            guessButton.setDisable(true);
            playAgain.setVisible(true);
            playAgain.setDefaultButton(true);
        }
    }

    public void handleKeyReleased()
    {
        String input = inputNumber.getText();
        boolean disableButton = (input.isEmpty() || input.trim().isEmpty()) || (!input.matches("[+-]?\\d*(\\.\\d+)?"));
        guessButton.setDisable(disableButton);
    }

    public void resetGame(ActionEvent event) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("RangeSelection.fxml"));
        root = loader.load();
        RangeController rangeController = loader.getController();
        rangeController.initialize(this.username);
        primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 600, 400);
        JMetro jMetro = new JMetro();
        jMetro.setScene(scene);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
