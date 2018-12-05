import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * 
 * @author check
 *
 */
public class Instructions extends Application
{
    /**
     * To open the Instructions Page
     * @param primaryStage Main stage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        primaryStage.setTitle("Snake VS Block");
        Parent root = FXMLLoader.load(getClass().getResource("Instructions.fxml"));
        Scene Instructions = new Scene(root, 600,800);
        primaryStage.setScene(Instructions);
        primaryStage.show();
    }

    /**
     * To go back to the main menu from instructions page
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
}
