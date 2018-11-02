import javafx.event.Event;
import javafx.stage.Stage;

import java.io.Serializable;

public class Player implements Serializable, Cloneable
{
    private String Name;
    private int Score;
    private int Coins;
    private boolean savegame;

    public Player(String name)
    {
        this.Name = name;
        this.Score = 0;
        this.Coins = 0;
        this.savegame = false;
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

    public void increase_score(int points)
    {
        this.setScore(this.getScore() + points);
    }
}
