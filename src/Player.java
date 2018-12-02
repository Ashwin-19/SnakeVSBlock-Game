import javafx.event.Event;
import javafx.stage.Stage;

import java.io.Serializable;
import java.util.Comparator;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

public class Player implements Serializable, Cloneable
{
    private String Name;
    private int Score;
    private int HighScore;
    private int Coins;
    private boolean savegame;
    private static Player current_player;

    public Player(String name)
    {
        this.Name = name;
        this.Score = 0;
        this.Coins = 0;
        this.HighScore = 0;
        this.savegame = false;
    }

    public static Player getCurrent_player()
    {
        return current_player;
    }

    public static void setCurrent_player(Player current_player)
    {
        Player.current_player = current_player;
    }

    public int getHighScore()
    {
        return HighScore;
    }

    public void setHighScore(int highScore)
    {
        HighScore = highScore;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getScore() {
        return Score;
    }

    public void setScore(int score) {
        Score = score;
    }

    public int getCoins() {
        return Coins;
    }

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

    public void start_game(Event event) throws Exception
    {
        Game game = new Game();
        game.simulate(event);
    }

    public void resume_game(Event event) throws Exception
    {
        Game.deserialize_board(event);
    }

    public void increase_score(int points)
    {
        this.setScore(this.getScore() + points);
    }

}

class PlayerComparator implements Comparator<Player>
{
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