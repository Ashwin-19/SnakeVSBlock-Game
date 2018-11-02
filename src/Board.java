import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.Serializable;
import java.util.Random;

import javafx.animation.*;

public class Board extends Application implements Serializable {
    // In screen components
    private Stage game_window;
    private Scene Game;
    private AnchorPane game_layout;
    private GridPane game_layout_1;
    private GridPane game_layout_2;
    private HBox Menu;
    private Button pause;
    private static final String PAUSE_URL = "assets/gameicons/PNG/White/2x/pause.png";
    private static final String EXIT_URL = "assets/gameicons/PNG/White/2x/return.png";
    private Button exit;
    private static final int GAME_WIDTH = 600;
    private static final int GAME_HEIGHT = 820;
    private static final String BACKGROUND_URL = "assets/gamebg/BG2.jpg";
    private static final String BACKGROUND_URL_2 = "assets/gamebg/BG2.jpg";
    private Block[] Blocks;
    private Block[] Blocks_1;
    private static final String BLOCK_R1 = "assets/Blocks/Special/brickSpecial01.png";
    private static final String BLOCK_R2 = "assets/Blocks/Special/brickSpecial10.png";
    private static final String BLOCK_R3 = "assets/Blocks/Special/brickSpecial08.png";
    private Image image1;
    private Image image2;
    private Image image3;
    private Ball[] Balls;
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
    private BoardObjects board[][];
    private Snake snake_head;
    private int speed;
    private AnimationTimer animationTimer;

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getColour() {
        return colour;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void move(Event event) throws Exception {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        this.start(stage);

    }

    public void collision(BoardObjects obj) {

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.hide();
        game_window = new Stage();
        game_window.setTitle("Snake VS Block");
        game_window.setResizable(false);
        game_layout = new AnchorPane();

        make_game();
        make_background();
        make_snake();
        generate_blocks();
        generate_balls();
        generate_coins();
        generateDestroyBlocks();
        generate_walls();
        generate_magnets();
        generate_shields();
        make_game_loop();
        createKeyListeners();
        RandomPosition = new Random();
    }

    private void createKeyListeners() {
        Game.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.LEFT)
                {
                    snake_head.setLeftkey(true);
                    snake_head.move();
                }
                else if (event.getCode() == KeyCode.RIGHT)
                {
                    snake_head.setRightkey(true);
                    snake_head.move();
                }
            }
        });

        Game.setOnKeyReleased(new EventHandler<KeyEvent>() {
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
        Game = new Scene(game_layout, 600, 800);
        game_window.setScene(Game);
        game_window.show();
    }

    private void make_game_loop() {
        animationTimer = new AnimationTimer()
        {
            @Override
            public void handle(long now)
            {
                move_background();
                MoveBlocks();
                MoveBalls();
                MoveMagnets();
                MoveCoins();
                MoveShields();
                MoveWalls();
                MoveDBlocks();
                Relocate();
                RelocateBalls();
                RelocateMagnets();
                RelocateCoins();
                RelocateShields();
                RelocateWalls();
                RelocateDBlocks();
                snake_head.move();
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

//        pause = new Button();
//        String PAUSE_Style = "-fx-background-image: url('" + PAUSE_URL + "')";
//        pause.setStyle(PAUSE_Style);
//
//        exit = new Button();
//        String EXIT_Style = "-fx-background-image: url('" + EXIT_URL + "')";
//        exit.setStyle(EXIT_Style);
//
//        Menu = new HBox(10);
//        Menu.setAlignment(Pos.CENTER_LEFT);
//        Menu.getChildren().addAll(exit, pause);
//        AnchorPane.setTopAnchor(Menu, 0.0);
//        game_layout.getChildren().add(Menu);
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
        Image image = new Image("assets/snake/snake.png");
        snake_head = new Snake(350, 700, image);
        game_layout.getChildren().add(snake_head);
    }

    private void generate_balls()
    {
        Balls = new Ball[1];
        Image BALL_IMG = new Image(BALL_URL);

        for (int i = 0; i < Balls.length; i++)
        {
            Balls[i] = new Ball(BALL_IMG);
            set_element_position(Balls[i]);
            game_layout.getChildren().add(Balls[i]);
        }
    }

    private void set_element_position(ImageView element)
    {
        int relocate = RandomPosition.nextInt(1000);

        if(relocate == 0)
        {
            double x = RandomPosition.nextDouble();
            double y = RandomPosition.nextDouble();

            int index1 = 0;
            int index2 = 0;

            for (int i = 0; i < Blocks.length; i++)
            {
                if(Blocks[i] != null)
                {
                    index1 = i;
                    break;
                }
            }

            for (int i = 0; i < Blocks_1.length; i++)
            {
                if(Blocks_1[i] != null)
                {
                    index2 = i;
                    break;
                }
            }

            element.setLayoutX(x*GAME_WIDTH);
            element.setLayoutY(y*((Blocks[index1].getLayoutY() + Blocks_1[index2].getLayoutY())/2));
        }

        else
        {
            element.setLayoutY(1100);
        }
    }

    private void generate_blocks()
    {
        RandomPosition = new Random();
        int empty_indices = RandomPosition.nextInt(3);
        int empty_index_0 = -1;
        int empty_index_1 = -1;
        int empty_index_2 = -1;

        Blocks = new Block[9];
        Blocks_1 = new Block[9];

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

        CreateBlockRow(empty_index_0, empty_index_1, empty_index_2);

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

        CreateBlockRow1(empty_index_0, empty_index_1, empty_index_2);
    }

    private void CreateBlockRow(int empty_index_0, int empty_index_1, int empty_index_2)
    {
        Image image1 = new Image(BLOCK_R1);
        Image image2 = new Image(BLOCK_R2);
        Image image3 = new Image(BLOCK_R3);

        for (int i = 0; i < 9; i++)
        {
            if (i == empty_index_0  || i == empty_index_1 || i == empty_index_2)
            {
                Blocks[i] = null;
            }
            else if (i % 2 == 0)
            {
                Block R1 = new Block(image1);
                Blocks[i] = R1;
                set_block_position(Blocks[i],i);
                game_layout.getChildren().add(Blocks[i]);
            }
            else if (i % 3 == 0)
            {
                Block R2 = new Block(image2);
                Blocks[i] = R2;
                set_block_position(Blocks[i],i);
                game_layout.getChildren().add(Blocks[i]);
            }
            else
            {
                Block R3 = new Block(image3);
                Blocks[i] = R3;
                set_block_position(Blocks[i],i);
                game_layout.getChildren().add(Blocks[i]);
            }
        }
    }

    private void CreateBlockRow1(int empty_index_0, int empty_index_1, int empty_index_2)
    {
        Image image1 = new Image(BLOCK_R1);
        Image image2 = new Image(BLOCK_R2);
        Image image3 = new Image(BLOCK_R3);

        for (int i = 0; i < 9; i++)
        {
            if (i == empty_index_0  || i == empty_index_1 || i == empty_index_2)
            {
                Blocks_1[i] = null;
            }
            else if (i % 2 == 0)
            {
                Block R1 = new Block(image1);
                Blocks_1[i] = R1;
                set_block_position1(Blocks_1[i],i);
                game_layout.getChildren().add(Blocks_1[i]);
            }
            else if (i % 3 == 0)
            {
                Block R2 = new Block(image2);
                Blocks_1[i] = R2;
                set_block_position1(Blocks_1[i],i);
                game_layout.getChildren().add(Blocks_1[i]);
            }
            else
            {
                Block R3 = new Block(image3);
                Blocks_1[i] = R3;
                set_block_position1(Blocks_1[i],i);
                game_layout.getChildren().add(Blocks_1[i]);
            }
        }
    }

    private void set_block_position(ImageView element, int index)
    {
        element.setLayoutX(index*75);
        element.setLayoutY(-600);
    }

    private void set_block_position1(ImageView element, int index)
    {
        int index1 = 0;

        for (int i = 0; i < Blocks.length; i++)
        {
            if(Blocks[i] != null)
            {
                index1 = i;
                break;
            }
        }

        element.setLayoutX(index*75);
        element.setLayoutY(-300);
    }

    private void MoveBlocks()
    {
        for (int i = 0; i < Blocks.length; i++)
        {
            if(Blocks[i] != null)
            {
                Blocks[i].setLayoutY(Blocks[i].getLayoutY() + 3);
            }
        }

        for (int i = 0; i < Blocks_1.length; i++)
        {
            if(Blocks_1[i] != null)
            {
                Blocks_1[i].setLayoutY(Blocks_1[i].getLayoutY() + 3);
            }
        }
    }

    private void MoveBalls()
    {
        for(int i = 0; i < Balls.length; i++)
        {
            Balls[i].setLayoutY(Balls[i].getLayoutY() + 3);
        }
    }

    private void RelocateBalls()
    {
        for(int i = 0; i < Balls.length; i++)
        {
            if(Balls[i].getLayoutY() >= GAME_HEIGHT)
            {
                set_element_position(Balls[i]);
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
                if (Blocks[i].getLayoutY() >= GAME_HEIGHT)
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
                if (Blocks_1[i].getLayoutY() >= GAME_HEIGHT)
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
            Magnets[i].setLayoutY(Magnets[i].getLayoutY() + 3);
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
            CoinsArr[i].setLayoutY(CoinsArr[i].getLayoutY() + 3);
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
            Shields[i].setLayoutY(Shields[i].getLayoutY() + 3);
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
        DBlocks = new Destroy_Blocks[1];
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
            DBlocks[i].setLayoutY(DBlocks[i].getLayoutY() + 3);
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
        Walls = new Wall[1];
        Image WALL_IMG = new Image(WALL_URL);

        for (int i = 0; i < Walls.length; i++)
        {
            Walls[i] = new Wall(WALL_IMG);
            set_wall_position(Walls[i]);
            game_layout.getChildren().add(Walls[i]);
        }
    }

    private void MoveWalls()
    {
        for (int i = 0; i < Walls.length; i++)
        {
            Walls[i].setLayoutY(Walls[i].getLayoutY() + 3);
        }
    }

    private void RelocateWalls()
    {
        for (int i = 0; i < Walls.length; i++)
        {
            if(Walls[i].getLayoutY() >= GAME_HEIGHT)
            {
                set_wall_position(Walls[i]);
            }
        }
    }

    private void set_wall_position(Wall wall)
    {
        int randomize = RandomPosition.nextInt(3);

        if(randomize == 0)
        {
            int index1 = 0;

            for (int i = 0; i < Blocks.length; i++)
            {
                if(Blocks[i] != null)
                {
                    index1 = i;
                    break;
                }
            }

            wall.setLayoutY(Blocks[index1].getLayoutY());
        }
        else if (randomize == 1)
        {
            int index1 = 0;

            for (int i = 0; i < Blocks_1.length; i++)
            {
                if(Blocks_1[i] != null)
                {
                    index1 = i;
                    break;
                }
            }

            wall.setLayoutY(Blocks_1[index1].getLayoutY());
        }
        else
        {
            set_element_position(wall);
        }
    }
}