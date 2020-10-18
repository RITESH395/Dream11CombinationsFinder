package singh.ritesh.dream11combinations.helper;

import singh.ritesh.dream11combinations.bo.DreamTeam;
import singh.ritesh.dream11combinations.bo.Player;

import java.util.List;

public class Dream11RuleHelper
{
    /*As per rule you can have only below combinations*/
    public static int[][] getPlayerTypeValidCombinations()
    {
        return new int[][]
                {
                        /* 1WK AND 3 BATSMAN*/
                        {1,3,1,6},
                        {1,3,2,5},
                        {1,3,3,4},
                        {1,3,4,3},

                        /* 1WK AND 4 BATSMAN*/
                        {1,4,1,5},
                        {1,4,2,4},
                        {1,4,3,3},

                        /* 1WK AND 5 BATSMAN*/
                        {1,5,1,4},
                        {1,5,2,3},

                        /* 1WK AND 6 BATSMAN*/
                        {1,6,1,3},

                        /* 2WK AND 3 BATSMAN*/
                        {2,3,1,5},
                        {2,3,2,4},
                        {2,3,3,3},

                        /* 2WK AND 4 BATSMAN*/
                        {2,4,1,4},
                        {2,4,2,3},
                        {2,4,3,3},

                        /* 2WK AND 5 BATSMAN*/
                        {2,5,1,3},

                        /* 3WK AND 3 BATSMAN*/
                        {3,3,1,4},
                        {3,3,2,3},

                        /* 4WK AND 3 BATSMAN*/
                        {4,3,1,3}
                };
    }

    public static void addPLayersToDreamTeam(List<Player> possibleDreamTeam , List<DreamTeam> dreamTeams)
    {
        boolean canItBeADreamTeam;
        DreamTeam dreamTeam = new DreamTeam();
        try
        {
            for (Player player : possibleDreamTeam)
            {
                dreamTeam.addFinalPLayers(player);
            }
            canItBeADreamTeam = true;
        }
        /*catch (PlayerSizeExceededException e)
        {
            canItBeADreamTeam = false;
        }*/
        catch (Exception e)
        {
            canItBeADreamTeam = false;
        }
        if(canItBeADreamTeam)
            dreamTeams.add(dreamTeam);
    }
}
