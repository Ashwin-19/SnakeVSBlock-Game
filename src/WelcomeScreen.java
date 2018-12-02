import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class WelcomeScreen extends Application
{
    @FXML
    private TextField textField;

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
    }
}
