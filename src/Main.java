import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * 
 * @author check
 *
 */
public class Main extends Application implements EventHandler<ActionEvent>
{
    /**
     * The window object
     */
    Stage Window;
    Scene Start, Menu, Settings, Leaderboard;

    /**
     * The main def of the class Main
     * @param args
     */
    public static void main(String[] args)
    {
        launch(args);
    }

    /**
     *  To start the Welcome screen
     * @param primaryStage Main stage object of the game
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Window = primaryStage;

        // Welcome screen

        // Making basic components
        HBox welcome_layout = new HBox(40);
        Button Enter = new Button("ENTER");
        TextField Name = new TextField();
        Name.setPromptText("Enter Name");

        Enter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event)
            {
                Player A = new Player(Name.getText());
                int query = Player_Data.add_to_database(A);
                if(query == 1)
                {
                    System.out.println("Welcome Returning Player");
                }
                else
                {
                    System.out.println("Welcome New Player");
                }
                Window.setScene(Menu);
            }
        });

        // Adding all components
        welcome_layout.getChildren().addAll(Name, Enter);
        welcome_layout.setAlignment(Pos.CENTER);
        Start = new Scene(welcome_layout, 800, 600);





    }

    /**
     * To handle any event
     * @param event
     */
    @Override
    public void handle(ActionEvent event) {

    }
}
