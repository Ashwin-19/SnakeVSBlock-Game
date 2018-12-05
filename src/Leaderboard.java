import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.Serializable;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * 
 * @author check
 *
 */
public class Leaderboard extends Application implements Initializable, Serializable
{
    @FXML
    private Label Player1;

    @FXML
    private Label Player2;

    @FXML
    private Label Player3;

    @FXML
    private Label Player4;

    @FXML
    private Label Player5;

    @FXML
    private Label Player6;

    @FXML
    private Label Player7;

    @FXML
    private Label Player8;

    @FXML
    private Label Player9;

    @FXML
    private Label Player10;

    /**
     * To open the Leaderboard Page
     * @param primaryStage Main stage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        primaryStage.setTitle("Snake VS Block");
        Parent root = FXMLLoader.load(getClass().getResource("Leaderboard.fxml"));
        Scene Leaderboard = new Scene(root, 600,800);
        primaryStage.setScene(Leaderboard);
        primaryStage.show();
    }

    /**
     * To go back to the main menu from Leaderboard page
     * @param e
     * @throws Exception
     */
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
        if(Player_Data.getLeaderboard().get(0) != null)
        {
            Player1.setText(Player_Data.getLeaderboard().get(0).getName() + " " + Player_Data.getLeaderboard().get(0).getHighScore());
            Player1.setTextFill(Color.WHITE);
            Player1.setFont(Font.font(15));
        }
        else
        {
            Player1.setText("");
        }

        if(Player_Data.getLeaderboard().size() >= 2 && Player_Data.getLeaderboard().get(1) != null)
        {
            Player2.setText(Player_Data.getLeaderboard().get(1).getName() + " " + Player_Data.getLeaderboard().get(1).getHighScore());
            Player2.setTextFill(Color.WHITE);
            Player2.setFont(Font.font(15));
        }
        else
        {
            Player2.setText("");
        }

        if(Player_Data.getLeaderboard().size() >= 3 && Player_Data.getLeaderboard().get(2) != null)
        {
            Player3.setText(Player_Data.getLeaderboard().get(2).getName() + " " + Player_Data.getLeaderboard().get(2).getHighScore());
            Player3.setTextFill(Color.WHITE);
            Player3.setFont(Font.font(15));
        }
        else
        {
            Player3.setText("");
        }

        if(Player_Data.getLeaderboard().size() >= 4 && Player_Data.getLeaderboard().get(3) != null)
        {
            Player4.setText(Player_Data.getLeaderboard().get(3).getName() + " " + Player_Data.getLeaderboard().get(3).getHighScore());
            Player4.setTextFill(Color.WHITE);
            Player4.setFont(Font.font(15));
        }
        else
        {
            Player4.setText("");
        }

        if(Player_Data.getLeaderboard().size() >= 5 && Player_Data.getLeaderboard().get(4) != null)
        {
            Player5.setText(Player_Data.getLeaderboard().get(4).getName() + " " + Player_Data.getLeaderboard().get(4).getHighScore());
            Player5.setTextFill(Color.WHITE);
            Player5.setFont(Font.font(15));
        }
        else
        {
            Player5.setText("");
        }

        if(Player_Data.getLeaderboard().size() >= 6 && Player_Data.getLeaderboard().get(5) != null)
        {
            Player6.setText(Player_Data.getLeaderboard().get(5).getName() + " " + Player_Data.getLeaderboard().get(5).getHighScore());
            Player6.setTextFill(Color.WHITE);
            Player6.setFont(Font.font(15));
        }
        else
        {
            Player6.setText("");
        }

        if(Player_Data.getLeaderboard().size() >= 7 && Player_Data.getLeaderboard().get(6) != null)
        {
            Player7.setText(Player_Data.getLeaderboard().get(6).getName() + " " + Player_Data.getLeaderboard().get(6).getHighScore());
            Player7.setTextFill(Color.WHITE);
            Player7.setFont(Font.font(15));
        }
        else
        {
            Player7.setText("");
        }

        if(Player_Data.getLeaderboard().size() >= 8 && Player_Data.getLeaderboard().get(7) != null)
        {
            Player8.setText(Player_Data.getLeaderboard().get(7).getName() + " " + Player_Data.getLeaderboard().get(7).getHighScore());
            Player8.setTextFill(Color.WHITE);
            Player8.setFont(Font.font(15));
        }
        else
        {
            Player8.setText("");
        }

        if(Player_Data.getLeaderboard().size() >= 9 && Player_Data.getLeaderboard().get(8) != null)
        {
            Player9.setText(Player_Data.getLeaderboard().get(8).getName() + " " + Player_Data.getLeaderboard().get(8).getHighScore());
            Player9.setTextFill(Color.WHITE);
            Player9.setFont(Font.font(15));
        }
        else
        {
            Player9.setText("");
        }

        if(Player_Data.getLeaderboard().size() >= 10 && Player_Data.getLeaderboard().get(9) != null)
        {
            Player10.setText(Player_Data.getLeaderboard().get(9).getName() + " " + Player_Data.getLeaderboard().get(9).getHighScore());
            Player10.setTextFill(Color.WHITE);
            Player10.setFont(Font.font(15));
        }
        else
        {
            Player10.setText("");
        }
    }
}
