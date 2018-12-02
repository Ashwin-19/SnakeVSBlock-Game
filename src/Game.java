import javafx.event.Event;

import java.io.*;

public class Game implements Serializable
{
    private static String theme;
    private static Board B;

    public static String getTheme()
    {
        return theme;
    }

    public static void setTheme(String Theme)
    {
        theme = Theme;
    }

    public void simulate(Event event) throws Exception
    {
        B = new Board();
        B.move(event);
    }
}