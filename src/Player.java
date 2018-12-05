
import javafx.event.Event;
import javafx.stage.Stage;

import java.io.Serializable;
import java.util.Comparator;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

/**
 * 
 * @author check
 *
 */
public class Player implements Serializable, Cloneable
{
    /**
     *  Name of the player
     */
    private String Name;

    /**
     *  Score of the Player
     */
    private int Score;

    /**
     *  High-Score of the Player
     */
    private int HighScore;

    /**
     *  Coins collected by the Player
     */
    private int Coins;

    /**
     *  Boolean to check If the player wants to save the game
     */
    private boolean savegame;

    /**
     *  Score of the Player
     */
    private static Player current_player;

    /**
     * This is the constructor to initialize the player object of the game
     * @param name
     */
    public Player(String name)
    {
        this.Name = name;
        this.Score = 0;
        this.Coins = 0;
        this.HighScore = 0;
        this.savegame = false;
    }

    /**
     * To get the current player object
     * @return Current player
     */
    public static Player getCurrent_player()
    {
        return current_player;
    }

    /**
     * To set the current player object
     * @param current_player Person yo want to set as current player
     */
    public static void setCurrent_player(Player current_player)
    {
        Player.current_player = current_player;
    }

    /**
     * To get the highest score of the the player
     * @return Highest score
     */
    public int getHighScore()
    {
        return HighScore;
    }

    /**
     * To set the highest score of the the player
     * @return The high-score you want to set
     */
    public void setHighScore(int highScore)
    {
        HighScore = highScore;
    }

    /**
     * To get the name of the player
     * @return Name of the player
     */
    public String getName() {
        return Name;
    }

    /**
     * To set the name of the player
     * @param name Name of the player to be set
     */
    public void setName(String name) {
        Name = name;
    }

    /**
     * To get the score of the player
     * @return Score of the player
     */
    public int getScore() {
        return Score;
    }

    /**
     * To set the score of the player
     * @return Score of the player to be set
     */
    public void setScore(int score) {
        Score = score;
    }

    /**
     * To get number of coins collected by the player
     * @return Coins collected by player
     */
    public int getCoins() {
        return Coins;
    }

    /**
     * To set number of coins collected by the player
     * @return Coins collected by player to be set
     */
    public void setCoins(int coins) {
        Coins = coins;
    }

    public boolean isSavegame() {
        return savegame;
    }

    public void setSavegame(boolean savegame) {
        this.savegame = savegame;
    }

    public void add_coins(int value)
    {
        this.setCoins(this.getCoins() + value);
    }


    /**
     * To start the game Afresh
     * @param event
     * @throws Exception
     */
    public void start_game(Event event) throws Exception
    {
        Game game = new Game();
        game.simulate(event);
    }

    /**
     * To resume the game from last progress
     * @param event
     * @throws Exception
     */
    public void resume_game(Event event) throws Exception
    {
    }



}

class PlayerComparator implements Comparator<Player>
{
    /**
     * To compare 2 different players
     * @param player First player to be compared
     * @param t1 Second player to be compared
     * @return Returns 1 if player 1 has better high score, else return 0 if player 2 has better high score
     */
    @Override
    public int compare(Player player, Player t1)
    {
        if(t1.getHighScore() > player.getHighScore())
        {
            return 1;
        }
        else if(t1.getHighScore() < player.getHighScore())
        {
            return -1;
        }
        else
        {
            return 0;
        }
    }
}