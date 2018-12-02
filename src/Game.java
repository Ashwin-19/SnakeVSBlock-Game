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

    public static void serialize_board() throws IOException
    {
//        ObjectOutputStream out = null;
//        out = new ObjectOutputStream(new FileOutputStream("C:/Users/rimco/IdeaProjects/SnakeVsBlock/src/Board_Database.txt"));
//        out.writeObject(B);
//        out.close();
    }

    public static void deserialize_board(Event event) throws Exception
    {
        B = new Board();
//        ObjectInputStream in;
//        in = null;
//
//        try
//        {
//            in = new ObjectInputStream(new FileInputStream("C:/Users/rimco/IdeaProjects/SnakeVsBlock/src/Board_Database.txt"));
//            B = (Board)in.readObject();
//        }
//        catch(Exception e)
//        {
//            System.out.println("exception in deserialize");
//        }
        B.move(event);
    }
}