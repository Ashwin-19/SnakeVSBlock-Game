import javafx.event.Event;

import java.io.Serializable;

public class Game implements Serializable
{
    private static String theme;

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
        Board B = new Board();
        B.move(event);
    }
}