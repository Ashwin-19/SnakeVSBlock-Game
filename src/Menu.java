import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Menu extends Application
{

    Stage MenuStage;

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

    @FXML
    protected void Leaderboard(ActionEvent e) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("Leaderboard.fxml"));
        Stage Leaderboard = (Stage) ((Node)e.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 600, 800);
        Leaderboard.setScene(scene);
        Leaderboard.show();
    }

    @FXML
    protected void Settings(ActionEvent e) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("Settings.fxml"));
        Stage Settings = (Stage) ((Node)e.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 600, 800);
        Settings.setScene(scene);
        Settings.show();
    }

    @FXML
    protected void Exit(ActionEvent e)
    {
        System.exit(-1);
    }

    @FXML
    protected void StartGame(ActionEvent e) throws Exception
    {
        Player player = new Player("");
        player.start_game(e);
    }
}
