import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class WelcomeScreen extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        primaryStage.setTitle("Snake VS Block");
        Parent root = FXMLLoader.load(getClass().getResource("WelcomeScreen.fxml"));
        Scene Welcome_screen = new Scene(root, 600,800);
        primaryStage.setScene(Welcome_screen);
        primaryStage.show();
    }

    @FXML
    protected void Enter(ActionEvent e) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        Stage welcome_screen = (Stage) ((Node)e.getSource()).getScene().getWindow();
        Scene Menu = new Scene(root, 600, 800);
        welcome_screen.setScene(Menu);
        welcome_screen.show();
    }
}
