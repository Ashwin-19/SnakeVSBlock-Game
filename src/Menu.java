import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * 
 * @author check
 *
 */
public class Menu extends Application
{
    /**
     * The Main menu stage
     */
    Stage MenuStage;

    /**
     * To start the Main menu
     * @param primaryStage The main stage of the game
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        MenuStage = primaryStage;
        MenuStage.setTitle("Snake VS Block");
        Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        Scene Menu = new Scene(root, 600,800);
        MenuStage.setScene(Menu);
        MenuStage.show();
    }

    /**
     * To go to the leaderboard page
     * @param e
     * @throws Exception
     */
    @FXML
    protected void Leaderboard(ActionEvent e) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("Leaderboard.fxml"));
        Stage Leaderboard = (Stage) ((Node)e.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 600, 800);
        Leaderboard.setScene(scene);
        Leaderboard.show();
    }

    /**
     * To go to the Instructions page
     * @param e
     * @throws Exception
     */
    @FXML
    protected void Instructions(ActionEvent e) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("Instructions.fxml"));
        Stage Instructions = (Stage) ((Node)e.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 600, 800);
        Instructions.setScene(scene);
        Instructions.show();
    }

    /**
     * To go to the Exit page
     * @param e
     * @throws Exception
     */
    @FXML
    protected void Exit(ActionEvent e) throws IOException
    {
        try
        {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(new File("Board_Database.txt")));
            out.writeObject(Player_Data.getLeaderboard());
        }
        catch (Exception ex)
        {

        }
        System.exit(-1);
    }

    /**
     * To go to the Main Game page
     * @param e
     * @throws Exception
     */
    @FXML
    protected void StartGame(ActionEvent e) throws Exception
    {
        Player.getCurrent_player().start_game(e);
    }

    /**
     * To resume the game
     * @param e
     * @throws Exception
     */
    @FXML
    protected void ResumeGame(ActionEvent e) throws Exception
    {
        Player.getCurrent_player().resume_game(e);
    }
}
