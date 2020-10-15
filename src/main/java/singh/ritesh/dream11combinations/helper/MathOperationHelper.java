package singh.ritesh.dream11combinations.helper;

import com.google.common.collect.Sets;
import singh.ritesh.dream11combinations.bo.Player;

import java.util.*;

public class MathOperationHelper
{
    public static List<Set<Player>> getPlayerCombination(List<Player> players , int combinationSize)
    {
        Set<Set<Player>> combinations = Sets.combinations(new HashSet<>(players), combinationSize);
        return new ArrayList<>(combinations);
    }

    public static double getCreditCount(Set<Player> players)
    {
        double credit = 0.0;
        for(Player player : players)
        {
            credit = credit + player.getPlayerDreamCredit();
        }
        return credit;
    }

}
