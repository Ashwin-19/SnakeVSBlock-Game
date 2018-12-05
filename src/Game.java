import javafx.event.Event;

import java.io.*;

/**
 * 
 * @author check
 *
 */
public class Game implements Serializable
{
    /**
     * Theme of the board
     */
    private static String theme;
    /**
     * Board object of the game
     */
    private static Board B;

    /**
     * To get the current theme of the game
     * @return
     */
    public static String getTheme()
    {
        return theme;
    }

    /**
     * To set the current theme of the game
     * @param Theme
     */
    public static void setTheme(String Theme)
    {
        theme = Theme;
    }

    /**
     * To simulate the board movement
     * @param event
     * @throws Exception
     */
    public void simulate(Event event) throws Exception
    {
        B = new Board();
        B.move(event);
    }
}