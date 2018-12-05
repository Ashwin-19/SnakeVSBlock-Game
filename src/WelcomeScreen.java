import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import static javafx.scene.media.MediaPlayer.INDEFINITE;

/**
 * 
 * @author check
 *
 */
public class WelcomeScreen extends Application
{
    /**
     * Name input box
     */
    @FXML
    private TextField textField;

    public static AudioClip audio;

    /**
     * To start the Welcome screen
     * @param primaryStage The Main Stage of the game
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        final Task task = new Task() {

            @Override
            protected Object call() throws Exception
            {
                int s = INDEFINITE;
                audio = new AudioClip(getClass().getResource("assets/Music/track.mp3").toExternalForm());
                audio.setVolume(0.5f);
                audio.setCycleCount(s);
                audio.play();
                return null;
            }
        };

        Thread thread = new Thread(task);
        thread.start();

        primaryStage.setTitle("Snake VS Block");
        Parent root = FXMLLoader.load(getClass().getResource("WelcomeScreen.fxml"));
        Scene Welcome_screen = new Scene(root, 600,800);
        primaryStage.setScene(Welcome_screen);
        primaryStage.show();
    }

    /**
     * To validate the name entered
     * @param e Enter button pressed
     * @throws Exception
     */
    @FXML
    protected void Enter(ActionEvent e) throws Exception
    {
        String Name = textField.getText();
        Player player = new Player(Name);
        int val = Player_Data.add_to_database(player);

        if(val == -1)
        {
            //serialize
        }
        else
        {
            Player.setCurrent_player(player);
            Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
            Stage welcome_screen = (Stage) ((Node)e.getSource()).getScene().getWindow();
            Scene Menu = new Scene(root, 600, 800);
            welcome_screen.setScene(Menu);
            welcome_screen.show();
        }

        int flag = 0;
        ArrayList<Player> leaderboard = null;

        try
        {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(new File("Board_Database.txt")));
            leaderboard = (ArrayList<Player>) in.readObject();
            flag = 1;
        }
        catch (Exception ex)
        {

        }

        if(flag == 1)
        {
            Player_Data.Leaderboard = leaderboard;
        }
    }
}
