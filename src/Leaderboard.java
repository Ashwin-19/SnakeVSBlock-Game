import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Leaderboard extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        primaryStage.setTitle("Snake VS Block");
        Parent root = FXMLLoader.load(getClass().getResource("Leaderboard.fxml"));
        Scene Leaderboard = new Scene(root, 600,800);
        primaryStage.setScene(Leaderboard);
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
}