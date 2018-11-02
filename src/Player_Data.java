import java.io.Serializable;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public class Player_Data implements Serializable
{
    public static TreeSet<Player> Leaderboard = new TreeSet<>();
    public static ArrayList<Player> All_Players = new ArrayList<>();

    public static TreeSet<Player> getLeaderboard() {
        return Leaderboard;
    }

    public static ArrayList<Player> getAll_Players() {
        return All_Players;
    }

    public static int add_to_database(Player A)
    {
        for (int i = 0; i < All_Players.size(); i++)
        {
            if(All_Players.get(i).getName().equals(A.getName()))
            {
                return 1;
            }
        }

        All_Players.add(A);
        return -1;
    }

    public static void add_to_leaderboard(Player A)
    {

    }
}
