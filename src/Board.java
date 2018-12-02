import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

import javafx.animation.*;

public class Board extends Application implements Serializable {

    // In screen components
    private Stage game_window;
    private Scene game;
    private AnchorPane game_layout;
    private GridPane game_layout_1;
    private GridPane game_layout_2;
    private HBox Menu;
    private Button pause;
    private static final String PAUSE_URL = "assets/gameicons/PNG/White/2x/pause.png";
    private static final String EXIT_URL = "assets/gameicons/PNG/White/2x/cross.png";
    private static final String RESUME_URL = "assets/gameicons/PNG/White/2x/right.png";
    private Button exit;
    private Button resume;
    private static final int GAME_WIDTH = 610;
    private static final int GAME_HEIGHT = 800;
    private static final String BACKGROUND_URL = "assets/gamebg/BG2.jpg";
    private static final String BACKGROUND_URL_2 = "assets/gamebg/BG2.jpg";
    private Block[] Blocks;
    private Block[] Blocks_1;
    private Text[] Blocks_label;
    private Text[] Blocks_label1;
    private int initialized;
    private int initialized_wall;
    private static final String BLOCK_R1 = "assets/Blocks/B_Blocks/yellow.png";
    private static final String BLOCK_R2 = "assets/Blocks/B_Blocks/green.png";
    private static final String BLOCK_R3 = "assets/Blocks/B_Blocks/orange.png";
    private Image image1;
    private Image image2;
    private Image image3;
    private Ball[] Balls;
    private Text[] Ball_Values;
    private static final String BALL_URL = "assets/gameicons/PNG/White/2x/target.png";
    private Magnet[] Magnets;
    private static final String MAGNET_URL = "assets/tokens/magnet.png";
    private Coins[] CoinsArr;
    private static final String COINS_URL = "assets/tokens/Coin.png";
    private Shield[] Shields;
    private static final String SHIELD_URL = "assets/tokens/shield.png";
    private Random RandomPosition;
    private Destroy_Blocks[] DBlocks;
    private static final String DBLOCKS_URL = "assets/tokens/bomb.png";
    private Wall[] Walls;
    private static final String WALL_URL = "assets/tokens/wall.png";

    private int width;
    private int height;
    private String colour;
    private Label score_label;
    private Label score;
    private Label coin_label;
    private Label coins;
    private BoardObjects board[][];

    private Snake[] snake_tail;
    private static final String SNAKE_URL = "assets/snake/snake.png";
    private Snake snake_head;
    private Text snake_value;
    private double speed;
    private AnimationTimer animationTimer;

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getColour() {
        return colour;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void increment_speed()
    {
        if(snake_head.getLength() < 5.0)
        {
            speed = 4.0;
        }
        else
        {
            speed += snake_head.getLength()*0.0001;
        }
    }

    public void move(Event event) throws Exception {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        this.start(stage);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.hide();
        game_window = new Stage();
        game_window.setTitle("Snake VS Block");
        game_window.setResizable(false);
        game_layout = new AnchorPane();

        RandomPosition = new Random();
        initialized = 1;
        initialized_wall = 1;
        speed = 4;

        make_game();
        make_background();
        make_snake();
        generate_balls();
        generate_walls();
        generate_blocks();
        generate_coins();
        generateDestroyBlocks();
        generate_magnets();
        generate_shields();
        make_buttons();
        make_game_loop();
        createKeyListeners();
        RandomPosition = new Random();
    }

    private void make_buttons() throws IOException
    {
        pause = new Button();
        Image pause_img = new Image(PAUSE_URL);
        ImageView pause_imgvw = new ImageView(pause_img);
        pause_imgvw.setFitWidth(40);
        pause_imgvw.setFitHeight(40);
        pause.setGraphic(pause_imgvw);
        pause.setStyle("-fx-background-color: BLACK");
        pause.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                animationTimer.stop();
            }
        });

        exit = new Button();
        Image exit_img = new Image(EXIT_URL);
        ImageView exit_imgvw = new ImageView(exit_img);
        exit_imgvw.setFitHeight(40);
        exit_imgvw.setFitWidth(40);
        exit.setGraphic(exit_imgvw);
        exit.setStyle("-fx-background-color: BLACK");
        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event)
            {
                Parent root = null;
                try
                {
                    root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
                int playerscore = Integer.parseInt(score.getText());
                Player.getCurrent_player().setScore(playerscore);
                if(playerscore > Player.getCurrent_player().getHighScore())
                {
                    Player.getCurrent_player().setHighScore(playerscore);
                    Player_Data.add_to_leaderboard(Player.getCurrent_player());
                }
                try {
                    Game.serialize_board();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Stage Menu = (Stage) ((Node)event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root, GAME_WIDTH, GAME_HEIGHT);
                Menu.setScene(scene);
                Menu.show();
            }
        });

        resume = new Button();
        Image resume_img = new Image(RESUME_URL);
        ImageView resume_imgvw = new ImageView(resume_img);
        resume_imgvw.setFitHeight(40);
        resume_imgvw.setFitWidth(40);
        resume.setGraphic(resume_imgvw);
        resume.setStyle("-fx-background-color: BLACK");
        resume.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                animationTimer.start();
            }
        });

        score_label = new Label("SCORE");
        score_label.setFont(new Font(24));
        score_label.setTextFill(Color.WHITE);
        score_label.setStyle("-fx-background-color: BLACK");

        score = new Label("0");
        score.setFont(new Font(24));
        score.setTextFill(Color.WHITE);
        score.setStyle("-fx-background-color: BLACK");

        coin_label = new Label("COINS");
        coin_label.setFont(new Font(24));
        coin_label.setTextFill(Color.WHITE);
        coin_label.setStyle("-fx-background-color: BLACK");

        coins = new Label("0");
        coins.setFont(new Font(24));
        coins.setTextFill(Color.WHITE);
        coins.setStyle("-fx-background-color: BLACK");

        Menu = new HBox(10);
        Menu.setAlignment(Pos.CENTER_LEFT);
        Menu.getChildren().addAll(exit, pause, resume, score_label, score, coin_label, coins);
        AnchorPane.setTopAnchor(Menu, 0.0);
        game_layout.getChildren().add(Menu);
    }

    private void createKeyListeners() {
        game.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.LEFT)
                {
                    snake_head.setLeftkey(true);
                    snake_head.move(Walls, snake_value);
                }
                else if (event.getCode() == KeyCode.RIGHT)
                {
                    snake_head.setRightkey(true);
                    snake_head.move(Walls, snake_value);
                }
            }
        });

        game.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.LEFT) {
                    snake_head.setLeftkey(false);
                } else if (event.getCode() == KeyCode.RIGHT) {
                    snake_head.setRightkey(false);
                }
            }
        });
    }

    private void make_game()
    {
        game = new Scene(game_layout, GAME_WIDTH, GAME_HEIGHT);
        game_window.setScene(game);
        game_window.show();
    }

    private void make_game_loop()
    {
        animationTimer = new AnimationTimer()
        {
            @Override
            public void handle(long now)
            {
                try
                {
                    move_background();
                    MoveBalls();
                    MoveBlocks();
                    MoveMagnets();
                    MoveCoins();
                    MoveShields();
                    MoveWalls();
                    MoveDBlocks();
                    snake_head.move(Walls, snake_value);
                    move_snaketail();
                    HandleCollisions();
                    RelocateBalls();
                    RelocateWalls();
                    Relocate();
                    RelocateMagnets();
                    RelocateCoins();
                    RelocateShields();
                    RelocateDBlocks();
                    increment_speed();
                }
                catch (java.io.IOException e)
                {

                }
            }
        };

        animationTimer.start();
    }

    private void make_background()
    {
        game_layout_1 = new GridPane();
        game_layout_2 = new GridPane();

        for (int i = 0; i < 12; i++)
        {
            ImageView image_1 = new ImageView(BACKGROUND_URL_2);
            ImageView image_2 = new ImageView(BACKGROUND_URL);
            image_1.setFitWidth(GAME_HEIGHT);
            image_1.setFitHeight(GAME_WIDTH);
            image_2.setFitWidth(GAME_HEIGHT);
            image_2.setFitHeight(GAME_WIDTH);
            GridPane.setConstraints(image_1, i % 3, i / 3);
            GridPane.setConstraints(image_2, i % 3, i / 3);
            game_layout_1.getChildren().add(image_1);
            game_layout_2.getChildren().add(image_2);
        }

        game_layout_2.setLayoutY(-800);

        game_layout.getChildren().addAll(game_layout_1, game_layout_2);
    }

    private void move_background() {
        game_layout_1.setLayoutY(game_layout_1.getLayoutY() + 2);
        game_layout_2.setLayoutY(game_layout_2.getLayoutY() + 2);

        if (game_layout_1.getLayoutY() >= GAME_HEIGHT)
        {
            game_layout_1.setLayoutY(-GAME_HEIGHT);
        }

        if (game_layout_2.getLayoutY() >= GAME_HEIGHT)
        {
            game_layout_2.setLayoutY(-GAME_HEIGHT);
        }
    }

    private void make_snake()
    {
        snake_tail = new Snake[100];
        Image image = new Image(SNAKE_URL);
        snake_head = new Snake(350, 500, image);
        snake_value = new Text("5");
        snake_value.setFill(Color.WHITE);
        snake_value.setFont(Font.font(15));
        snake_value.setVisible(true);
        snake_value.setLayoutX(350);
        snake_value.setLayoutY(480);
        snake_head.setLength(5);
        game_layout.getChildren().add(snake_head);
        game_layout.getChildren().add(snake_value);
        for (int i = 1; i < 5; i++)
        {
            snake_tail[i-1] = new Snake(350, 500 + 25*i, image);
            game_layout.getChildren().add(snake_tail[i-1]);
        }
    }

    private void generate_balls()
    {
        Balls = new Ball[4];
        Ball_Values = new Text[4];

        Image BALL_IMG = new Image(BALL_URL);

        for (int i = 0; i < Balls.length; i++)
        {
            Balls[i] = new Ball(BALL_IMG);
            Ball_Values[i] = new Text();
            set_ball_position(Balls[i], i);
            game_layout.getChildren().addAll(Balls[i], Ball_Values[i]);
        }
    }

    private void set_ball_position(Ball element, int index)
    {
        int relocate = RandomPosition.nextInt(2000);

        if(relocate == 0)
        {
            int ball_value = RandomPosition.nextInt(snake_head.getLength()) + 1;

            Balls[index].setValue(ball_value);
            Ball_Values[index].setText(Integer.toString(ball_value));
            Ball_Values[index].setFill(Color.WHITE);
            Ball_Values[index].setFont(Font.font(15));
            Ball_Values[index].setVisible(true);

            double x = RandomPosition.nextDouble();
            double y = RandomPosition.nextDouble();

            for (int i = 0; i < Blocks.length; i++)
            {
                if(Blocks[i] != null)
                {
                    while(Blocks[i].getBoundsInParent().intersects(x*GAME_WIDTH,-1*y*GAME_HEIGHT, element.getFitWidth(), element.getFitHeight()))
                    {
                        x = RandomPosition.nextDouble();
                        y = RandomPosition.nextDouble();
                    }
                }
            }

            for (int i = 0; i < Blocks_1.length; i++)
            {
                if(Blocks_1[i] != null)
                {
                    while(Blocks_1[i].getBoundsInParent().intersects(x*GAME_WIDTH,-1*y*GAME_HEIGHT, element.getFitWidth(), element.getFitHeight()))
                    {
                        x = RandomPosition.nextDouble();
                        y = RandomPosition.nextDouble();
                    }
                }
            }

            element.setLayoutX(x*(GAME_WIDTH - element.getFitWidth()));
            element.setLayoutY(y*(-GAME_HEIGHT));
            Ball_Values[index].setLayoutX(element.getLayoutX());
            Ball_Values[index].setLayoutY(element.getLayoutY());
        }
        else
        {
            element.setLayoutY(1100);
            if(Ball_Values[index] != null)
            {
                Ball_Values[index].setLayoutY(1100);
            }
            else
            {
                Ball_Values[index] = new Text("");
            }
        }
    }

    private void set_element_position(ImageView element)
    {
        int relocate = RandomPosition.nextInt(1000);

        if(relocate == 0)
        {
            double x = RandomPosition.nextDouble();
            double y = RandomPosition.nextDouble();

            for (int i = 0; i < Blocks.length; i++)
            {
                if(Blocks[i] != null)
                {
                    while(Blocks[i].getBoundsInParent().intersects(x*GAME_WIDTH,-1*y*GAME_HEIGHT, element.getFitWidth(), element.getFitHeight()))
                    {
                        x = RandomPosition.nextDouble();
                        y = RandomPosition.nextDouble();
                    }
                }
            }

            for (int i = 0; i < Blocks_1.length; i++)
            {
                if(Blocks_1[i] != null)
                {
                    while(Blocks_1[i].getBoundsInParent().intersects(x*GAME_WIDTH,-1*y*GAME_HEIGHT, element.getFitWidth(), element.getFitHeight()))
                    {
                        x = RandomPosition.nextDouble();
                        y = RandomPosition.nextDouble();
                    }
                }
            }
            element.setLayoutX(x*(GAME_WIDTH - element.getFitWidth()));
            element.setLayoutY(y*(-GAME_HEIGHT));
        }

        else
        {
            element.setLayoutY(1100);
        }
    }

    private void generate_blocks()
    {
        int empty_indices = RandomPosition.nextInt(3);
        int empty_index_0 = -1;
        int empty_index_1 = -1;
        int empty_index_2 = -1;

        Blocks = new Block[6];
        Blocks_1 = new Block[6];

        Blocks_label = new Text[6];
        Blocks_label1 = new Text[6];

        for (int i = 0; i < empty_indices; i++)
        {
            if (i == 0)
            {
                empty_index_0 = RandomPosition.nextInt(Blocks.length);
            }
            else if(i == 1)
            {
                empty_index_1 = RandomPosition.nextInt(Blocks.length);
            }
            else
            {
                empty_index_2 = RandomPosition.nextInt(Blocks.length);
            }
        }

        CreateBlockRow(empty_index_0, empty_index_1, empty_index_2);

        for (int i = 0; i < empty_indices; i++)
        {
            if (i == 0)
            {
                empty_index_0 = RandomPosition.nextInt(Blocks.length);
            }
            else if(i == 1)
            {
                empty_index_1 = RandomPosition.nextInt(Blocks.length);
            }
            else
            {
                empty_index_2 = RandomPosition.nextInt(Blocks.length);
            }
        }

        CreateBlockRow1(empty_index_0, empty_index_1, empty_index_2);
    }

    private void CreateBlockRow(int empty_index_0, int empty_index_1, int empty_index_2)
    {
        Image image1 = new Image(BLOCK_R1);
        Image image2 = new Image(BLOCK_R2);
        Image image3 = new Image(BLOCK_R3);

        for (int i = 0; i < Blocks.length; i++)
        {
            int image_id = RandomPosition.nextInt(3);

            if (i == empty_index_0  || i == empty_index_1 || i == empty_index_2)
            {
                Blocks[i] = null;
                Blocks_label[i] = null;
            }
            else if (image_id == 0)
            {
                Block R1 = new Block(image1);
                Blocks[i] = R1;

                if(initialized > 0)
                {
                    set_initial_block_position(Blocks[i], i);
                    initialized++;
                }
                else
                {
                    set_block_position(Blocks[i], i);
                }
                game_layout.getChildren().addAll(Blocks[i]);
                game_layout.getChildren().addAll(Blocks_label[i]);
            }
            else if (image_id == 1)
            {
                Block R2 = new Block(image2);
                Blocks[i] = R2;

                if(initialized > 0)
                {
                    set_initial_block_position(Blocks[i], i);
                    initialized++;
                }
                else
                {
                    set_block_position(Blocks[i], i);
                }
                game_layout.getChildren().addAll(Blocks[i]);
                game_layout.getChildren().addAll(Blocks_label[i]);
            }
            else
            {
                Block R3 = new Block(image3);
                Blocks[i] = R3;

                if(initialized > 0)
                {
                    set_initial_block_position(Blocks[i], i);
                    initialized++;
                }
                else
                {
                    set_block_position(Blocks[i],i);
                }
                game_layout.getChildren().addAll(Blocks[i]);
                game_layout.getChildren().addAll(Blocks_label[i]);
            }
        }
    }

    private void CreateBlockRow1(int empty_index_0, int empty_index_1, int empty_index_2)
    {
        Image image1 = new Image(BLOCK_R1);
        Image image2 = new Image(BLOCK_R2);
        Image image3 = new Image(BLOCK_R3);

        for (int i = 0; i < Blocks.length; i++)
        {
            int image_id = RandomPosition.nextInt(3);

            if (i == empty_index_0  || i == empty_index_1 || i == empty_index_2)
            {
                Blocks_1[i] = null;
            }
            else if (image_id == 0)
            {
                Block R1 = new Block(image1);
                Blocks_1[i] = R1;

                if(initialized > 0)
                {
                    set_initial_block_position1(Blocks_1[i],i);
                    initialized++;
                }
                else
                {
                    set_block_position1(Blocks_1[i],i);
                }
                game_layout.getChildren().addAll(Blocks_1[i]);
                game_layout.getChildren().addAll(Blocks_label1[i]);
            }
            else if (image_id == 1)
            {
                Block R2 = new Block(image2);
                Blocks_1[i] = R2;

                if(initialized > 0)
                {
                    set_initial_block_position1(Blocks_1[i],i);
                    initialized++;
                }
                else
                {
                    set_block_position1(Blocks_1[i],i);
                }
                game_layout.getChildren().addAll(Blocks_1[i]);
                game_layout.getChildren().addAll(Blocks_label1[i]);
            }
            else
            {
                Block R3 = new Block(image3);
                Blocks_1[i] = R3;

                if(initialized > 0)
                {
                    set_initial_block_position1(Blocks_1[i],i);
                    initialized++;
                }
                else
                {
                    set_block_position1(Blocks_1[i],i);
                }
                game_layout.getChildren().addAll(Blocks_1[i]);
                game_layout.getChildren().addAll(Blocks_label1[i]);
            }
        }

        if(initialized > 0)
        {
            initialized = -1;
        }
    }

    private void set_initial_block_position(Block element, int index)
    {
        int Block_value = RandomPosition.nextInt(snake_head.getLength())+1;
        element.setValue(Block_value);
        Blocks_label[index] = new Text(Integer.toString(Block_value));
        Blocks_label[index].setFill(Color.WHITE);
        Blocks_label[index].setFont(Font.font(30));
        element.setLayoutX(index*105);
        Blocks_label[index].setLayoutX(index*105 + 40);
        element.setLayoutY(-0.5*GAME_HEIGHT);
        Blocks_label[index].setLayoutY(element.getLayoutY() + 50);
    }

    private void set_initial_block_position1(Block element, int index)
    {
        int Block_value = RandomPosition.nextInt(snake_head.getLength())+1;
        element.setValue(Block_value);
        Blocks_label1[index] = new Text(Integer.toString(Block_value));
        Blocks_label1[index].setFill(Color.WHITE);
        Blocks_label1[index].setFont(Font.font(30));
        element.setLayoutX(index*105);
        Blocks_label1[index].setLayoutX(index*105 + 40);
        element.setLayoutY(-1.5*GAME_HEIGHT);
        Blocks_label1[index].setLayoutY(element.getLayoutY() + 50);
    }

    private void set_block_position(Block element, int index)
    {
        int Block_value = RandomPosition.nextInt(snake_head.getLength())+1;
        element.setValue(Block_value);
        Blocks_label[index] = new Text(Integer.toString(Block_value));
        Blocks_label[index].setFill(Color.WHITE);
        Blocks_label[index].setFont(Font.font(30));
        element.setLayoutX(index*105);
        Blocks_label[index].setLayoutX(index*105 + 40);
        element.setLayoutY(-GAME_HEIGHT);
        Blocks_label[index].setLayoutY(element.getLayoutY() + 50);
    }

    private void set_block_position1(Block element, int index)
    {
        int Block_value = RandomPosition.nextInt(snake_head.getLength())+1;
        element.setValue(Block_value);
        Blocks_label1[index] = new Text(Integer.toString(Block_value));
        Blocks_label1[index].setFill(Color.WHITE);
        Blocks_label1[index].setFont(Font.font(30));
        element.setLayoutX(index*105);
        Blocks_label1[index].setLayoutX(index*105 + 40);
        element.setLayoutY(-GAME_HEIGHT);
        Blocks_label1[index].setLayoutY(element.getLayoutY() + 50);
    }

    private void MoveBlocks()
    {
        for (int i = 0; i < Blocks.length; i++)
        {
            if(Blocks[i] != null)
            {
                Blocks[i].setLayoutY(Blocks[i].getLayoutY() + speed);
                Blocks_label[i].setLayoutY(Blocks[i].getLayoutY() + 60);
            }
        }

        for (int i = 0; i < Blocks_1.length; i++)
        {
            if(Blocks_1[i] != null)
            {
                Blocks_1[i].setLayoutY(Blocks_1[i].getLayoutY() + speed);
                Blocks_label1[i].setLayoutY(Blocks_1[i].getLayoutY() + 60);
            }
        }
    }

    private void move_snaketail()
    {
        for (int i = 0; i < snake_tail.length; i++)
        {
            if(snake_tail[i]!=null)
            {
                if(!(snake_tail[i].isVisible()))
                {
                    snake_tail[i].setVisible(true);
                }
                snake_tail[i].setLayoutX(snake_head.getLayoutX());
            }
        }
    }

    private void MoveBalls()
    {
        for(int i = 0; i < Balls.length; i++)
        {
            Balls[i].setLayoutY(Balls[i].getLayoutY() + speed);

            if(Ball_Values[i]!=null)
            {
                Ball_Values[i].setLayoutY(Balls[i].getLayoutY() + speed + 10);
            }
        }
    }

    private void RelocateBalls()
    {
        for(int i = 0; i < Balls.length; i++)
        {
            if(Balls[i].getLayoutY() >= GAME_HEIGHT)
            {
                set_ball_position(Balls[i], i);
            }
        }
    }

    private void Relocate()
    {
        int empty_indices = RandomPosition.nextInt(3);
        int empty_index_0 = -1;
        int empty_index_1 = -1;
        int empty_index_2 = -1;

        for (int i = 0; i < empty_indices; i++)
        {
            if (i == 0)
            {
                empty_index_0 = RandomPosition.nextInt(9);
            }
            else if(i == 1)
            {
                empty_index_1 = RandomPosition.nextInt(9);
            }
            else
            {
                empty_index_2 = RandomPosition.nextInt(9);
            }
        }


        for (int i = 0; i < Blocks.length; i++)
        {
            if(Blocks[i] != null)
            {
                if (Blocks[i].getLayoutY() >= GAME_HEIGHT + 40)
                {
                    CreateBlockRow(empty_index_0, empty_index_1, empty_index_2);
                }
            }
        }


        for (int i = 0; i < empty_indices; i++)
        {
            if (i == 0)
            {
                empty_index_0 = RandomPosition.nextInt(9);
            }
            else if(i == 1)
            {
                empty_index_1 = RandomPosition.nextInt(9);
            }
            else
            {
                empty_index_2 = RandomPosition.nextInt(9);
            }
        }


        for (int i = 0; i < Blocks_1.length; i++)
        {
            if(Blocks_1[i] != null)
            {
                if (Blocks_1[i].getLayoutY() >= GAME_HEIGHT + 40)
                {
                    CreateBlockRow1(empty_index_0, empty_index_1, empty_index_2);
                }
            }
        }
    }

    private void generate_magnets()
    {
        Magnets = new Magnet[1];
        Image MAGNET_IMG = new Image(MAGNET_URL);

        for (int i = 0; i < Magnets.length; i++)
        {
            Magnets[i] = new Magnet(MAGNET_IMG);
            set_element_position(Magnets[i]);
            game_layout.getChildren().add(Magnets[i]);
        }
    }

    private void MoveMagnets()
    {
        for (int i = 0; i < Magnets.length; i++)
        {
            Magnets[i].setLayoutY(Magnets[i].getLayoutY() + speed);
        }
    }

    private void RelocateMagnets()
    {
        for (int i = 0; i < Magnets.length; i++)
        {
            if(Magnets[i].getLayoutY() >= GAME_HEIGHT)
            {
                set_element_position(Magnets[i]);
            }
        }
    }

    private void generate_coins()
    {
        CoinsArr = new Coins[1];
        Image COINS_IMG = new Image(COINS_URL);

        for (int i = 0; i < CoinsArr.length; i++)
        {
            CoinsArr[i] = new Coins(COINS_IMG);
            set_element_position(CoinsArr[i]);
            game_layout.getChildren().add(CoinsArr[i]);
        }
    }

    private void MoveCoins()
    {
        for (int i = 0; i < CoinsArr.length; i++)
        {
            CoinsArr[i].setLayoutY(CoinsArr[i].getLayoutY() + speed);
        }
    }

    private void RelocateCoins()
    {
        for (int i = 0; i < CoinsArr.length; i++)
        {
            if(CoinsArr[i].getLayoutY() >= GAME_HEIGHT)
            {
                set_element_position(CoinsArr[i]);
            }
        }
    }

    private void generate_shields()
    {
        Shields = new Shield[1];
        Image SHIELD_IMG = new Image(SHIELD_URL);

        for (int i = 0; i < Shields.length; i++)
        {
            Shields[i] = new Shield(SHIELD_IMG);
            set_element_position(Shields[i]);
            game_layout.getChildren().add(Shields[i]);
        }
    }

    private void MoveShields()
    {
        for (int i = 0; i < Shields.length; i++)
        {
            Shields[i].setLayoutY(Shields[i].getLayoutY() + speed);
        }
    }

    private void RelocateShields()
    {
        for (int i = 0; i < Shields.length; i++)
        {
            if(Shields[i].getLayoutY() >= GAME_HEIGHT)
            {
                set_element_position(Shields[i]);
            }
        }
    }

    private void generateDestroyBlocks()
    {
        DBlocks = new Destroy_Blocks[2];
        Image DBLOCKS_IMG = new Image(DBLOCKS_URL);

        for (int i = 0; i < DBlocks.length; i++)
        {
            DBlocks[i] = new Destroy_Blocks(DBLOCKS_IMG);
            set_element_position(DBlocks[i]);
            game_layout.getChildren().add(DBlocks[i]);
        }
    }

    private void MoveDBlocks()
    {
        for (int i = 0; i < DBlocks.length; i++)
        {
            DBlocks[i].setLayoutY(DBlocks[i].getLayoutY() + speed);
        }
    }

    private void RelocateDBlocks()
    {
        for (int i = 0; i < DBlocks.length; i++)
        {
            if(DBlocks[i].getLayoutY() >= GAME_HEIGHT)
            {
                set_element_position(DBlocks[i]);
            }
        }
    }

    private void generate_walls()
    {
        int empty_index_0 = RandomPosition.nextInt(5);
        int empty_index_1 = RandomPosition.nextInt(5);
        int empty_index_2 = RandomPosition.nextInt(5);

        Walls = new Wall[5];

        CreateWalls(empty_index_0, empty_index_1, empty_index_2);
    }

    private void CreateWalls(int empty_index_0, int empty_index_1, int empty_index_2)
    {
        Image WALL_IMG = new Image(WALL_URL);

        for (int i = 0; i < Walls.length; i++)
        {
            if (i == empty_index_0  || i == empty_index_1 || i == empty_index_2)
            {
                Walls[i] = null;
            }
            else
            {
                Wall wall = new Wall(WALL_IMG);
                Walls[i] = wall;

                if(initialized > 0)
                {
                    set_initial_wall_position(Walls[i], i);
                    initialized_wall++;
                }
                else
                {
                    set_wall_position(Walls[i], i);
                }
                game_layout.getChildren().addAll(Walls[i]);
            }
        }

        if(initialized_wall > 0)
        {
            initialized_wall = -1;
        }
    }

    private void set_initial_wall_position(Wall wall, int index)
    {
        wall.setLayoutX(index*105 + 87.5);
        wall.setLayoutY(-0.5*GAME_HEIGHT);
    }

    private void set_wall_position(Wall wall, int index)
    {
        wall.setLayoutX(index*105 + 87.5);
        wall.setLayoutY(-GAME_HEIGHT);
    }

    private void MoveWalls()
    {
        for (int i = 0; i < Walls.length; i++)
        {
            if(Walls[i] != null)
            {
                Walls[i].setLayoutY(Walls[i].getLayoutY() + speed);
            }
        }
    }

    private void RelocateWalls()
    {
        for (int i = 0; i < Walls.length; i++)
        {
            if(Walls[i] != null && Walls[i].getLayoutY() >= GAME_HEIGHT + 200)
            {
                int index1 = RandomPosition.nextInt(5);
                int index2 = RandomPosition.nextInt(5);
                int index3 = RandomPosition.nextInt(5);
                CreateWalls(index1, index2, index3);
            }
        }
    }

    private void HandleCollisions() throws java.io.IOException
    {

        for (int i = 0; i < Magnets.length; i++)
        {
            if(snake_head.getBoundsInParent().intersects(Magnets[i].getBoundsInParent()))
            {
                set_element_position(Magnets[i]);
            }
        }

        for (int i = 0; i < DBlocks.length; i++)
        {
            if(snake_head.getBoundsInParent().intersects(DBlocks[i].getBoundsInParent()))
            {
                for (int j = 0; j < Blocks.length; j++)
                {
                    if(Blocks[j] != null && Blocks_label[j] != null && 0 < Blocks[j].getLayoutY() && Blocks[j].getLayoutY() < GAME_HEIGHT)
                    {
                        int curr_score = Integer.parseInt(score.getText());
                        curr_score += Blocks[j].getValue();
                        score.setText(Integer.toString(curr_score));
                        Blocks[j].setVisible(false);
                        Blocks_label[j].setVisible(false);
                    }
                }

                for (int j = 0; j < Blocks_1.length; j++)
                {
                    if(Blocks_1[j] != null && Blocks_label1[j] != null && 0 < Blocks_1[j].getLayoutY() && Blocks_1[j].getLayoutY() < GAME_HEIGHT)
                    {
                        int curr_score = Integer.parseInt(score.getText());
                        curr_score += Blocks_1[j].getValue();
                        score.setText(Integer.toString(curr_score));
                        Blocks_1[j].setVisible(false);
                        Blocks_label1[j].setVisible(false);
                    }
                }

                set_element_position(DBlocks[i]);
            }
        }

        for (int i = 0; i < Balls.length; i++)
        {
            if(snake_head.getBoundsInParent().intersects(Balls[i].getBoundsInParent()))
            {
                int increment_value = Balls[i].getValue();
                int start = snake_head.getLength();
                int end = start + increment_value;
                increase_snaketail(Balls[i].getValue());
                snake_head.setLength(end);
                set_ball_position(Balls[i], i);
                Ball_Values[i].setVisible(false);
            }
        }

        for (int i = 0; i < CoinsArr.length; i++)
        {
            if(snake_head.getBoundsInParent().intersects(CoinsArr[i].getBoundsInParent()))
            {
                int curr_coins = Integer.parseInt(coins.getText());
                curr_coins += CoinsArr[i].getValue();
                coins.setText(Integer.toString(curr_coins));
                set_element_position(CoinsArr[i]);
            }
        }

        for (int i = 0; i < Shields.length; i++)
        {
            if(snake_head.getBoundsInParent().intersects(Shields[i].getBoundsInParent()))
            {
                set_element_position(Shields[i]);
            }
        }

        for (int i = 0; i < Blocks.length; i++)
        {
            if( Blocks[i]!= null && Blocks[i].isVisible() && snake_head.getBoundsInParent().intersects(Blocks[i].getBoundsInParent()))
            {
                if(Blocks[i].getValue() >= snake_head.getLength())
                {
                    int playerscore = Integer.parseInt(score.getText());
                    Player.getCurrent_player().setScore(playerscore);
                    if(playerscore > Player.getCurrent_player().getHighScore())
                    {
                        Player.getCurrent_player().setHighScore(playerscore);
                        Player_Data.add_to_leaderboard(Player.getCurrent_player());
                    }
                    animationTimer.stop();
                    game_window.close();
                    Parent root = FXMLLoader.load(getClass().getResource("EndGame.fxml"));
                    Stage EndGame = game_window;
                    Scene scene = new Scene(root, 600, 800);
                    EndGame.setScene(scene);
                    EndGame.show();
                }
                else
                {
                    int curr_score = Integer.parseInt(score.getText());
                    curr_score += Blocks[i].getValue();
                    score.setText(Integer.toString(curr_score));
                    snake_head.setLength(snake_head.getLength() - Blocks[i].getValue());
                    speed -= Blocks[i].getValue()*0.001;
                    decrease_snaketail(Blocks[i].getValue());
                }
                Blocks[i].setVisible(false);
                Blocks_label[i].setVisible(false);
            }
        }

        for (int i = 0; i < Blocks_1.length; i++)
        {
            if( Blocks_1[i]!= null && Blocks_1[i].isVisible() && snake_head.getBoundsInParent().intersects(Blocks_1[i].getBoundsInParent()))
            {
                if(Blocks_1[i].getValue() >= snake_head.getLength())
                {
                    int playerscore = Integer.parseInt(score.getText());
                    Player.getCurrent_player().setScore(playerscore);
                    if(playerscore > Player.getCurrent_player().getHighScore())
                    {
                        Player.getCurrent_player().setHighScore(playerscore);
                        Player_Data.add_to_leaderboard(Player.getCurrent_player());
                    }
                    animationTimer.stop();
                    game_window.close();
                    Parent root = FXMLLoader.load(getClass().getResource("EndGame.fxml"));
                    Stage EndGame = game_window;
                    Scene scene = new Scene(root, 600, 800);
                    EndGame.setScene(scene);
                    EndGame.show();
                }
                else
                {
                    int curr_score = Integer.parseInt(score.getText());
                    curr_score += Blocks_1[i].getValue();
                    score.setText(Integer.toString(curr_score));
                    snake_head.setLength(snake_head.getLength() - Blocks_1[i].getValue());
                    speed -= Blocks_1[i].getValue()*0.001;
                    decrease_snaketail(Blocks_1[i].getValue());
                }
                Blocks_1[i].setVisible(false);
                Blocks_label1[i].setVisible(false);
            }
        }
    }

    private void decrease_snaketail(int value)
    {
        int index = 0;

        for(int i = 99; i >= 0; i--)
        {
            if(snake_tail[i] != null)
            {
                index = i;
                break;
            }
        }

        for (int i = index; i > index-value; i--)
        {
            if(snake_tail[i]!=null)
            {
                snake_tail[i].setVisible(false);
                snake_tail[i] = null;
            }
        }

    }

    private void increase_snaketail(int value)
    {
        int index = 0;

        for(int i = 0; i <= 99; i++)
        {
            if(snake_tail[i] == null)
            {
                index = i;
                break;
            }
        }

        Image image = new Image(SNAKE_URL);

        for (int i = index; i < index+value; i++)
        {
            snake_tail[i] = new Snake(350, 500 + 25*(i+1), image);
            snake_tail[i].setVisible(false);
            game_layout.getChildren().add(snake_tail[i]);
        }
    }
}