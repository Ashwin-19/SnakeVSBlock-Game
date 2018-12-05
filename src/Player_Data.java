import java.io.Serializable;
import java.util.*;

/**
 * 
 * @author check
 *
 */
public class Player_Data implements Serializable
{
    public static ArrayList<Player> Leaderboard = new ArrayList<>();
    public static ArrayList<Player> All_Players = new ArrayList<>();

    private static PlayerComparator cmp;

    public static ArrayList<Player> getLeaderboard() {
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
                return -1;
            }
        }

        All_Players.add(A);
        return 1;
    }

    public static void add_to_leaderboard(Player A)
    {
        int size = Leaderboard.size();
        cmp = new PlayerComparator();

        if(size >= 10)
        {
            if(Leaderboard.get(size-1).getHighScore() > A.getHighScore())
            {
                //don't add
            }
            else
            {
                for (int i = 0; i < size; i++)
                {
                    if(Leaderboard.get(i).getHighScore() < A.getHighScore())
                    {
                        Leaderboard.remove(i);
                        Leaderboard.add(A);
                        Collections.sort(Leaderboard, cmp);
                        break;
                    }
                }
            }
        }
        else
        {
            Leaderboard.add(A);
            Collections.sort(Leaderboard, cmp);
        }
    }
}
