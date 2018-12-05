import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * 
 * @author check
 *
 */
public class EndGame extends Application implements Initializable
{
    @FXML
    private Label Name;

    @FXML
    private Label Score;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        primaryStage.setTitle("Snake VS Block");
        Parent root = FXMLLoader.load(getClass().getResource("EndGame.fxml"));
        Scene EndGame = new Scene(root, 600,800);
        primaryStage.setScene(EndGame);
        primaryStage.show();
    }

    @FXML
    protected void Back(ActionEvent e) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        Stage Menu = (Stage) ((Node)e.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 600, 800);
        Menu.setScene(scene);
        Menu.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        Name.setText(Player.getCurrent_player().getName());
        Score.setText(Integer.toString(Player.getCurrent_player().getScore()));
    }
}
