package singh.ritesh.dream11combinations.service.implementation;

import singh.ritesh.dream11combinations.bo.DreamTeam;
import singh.ritesh.dream11combinations.bo.Player;
import singh.ritesh.dream11combinations.bo.PlayerType;
import singh.ritesh.dream11combinations.helper.Dream11RuleHelper;
import singh.ritesh.dream11combinations.helper.MathOperationHelper;
import singh.ritesh.dream11combinations.service.CombinationFinderService;

import java.util.*;

public class CombinationFinderServiceImpl implements CombinationFinderService
{
    @Override
    public Map<String, List<DreamTeam>> getAllCombinations(List<Player> players)
    {
        if(players.size() < 11)
            return null;/*not possible*/

        /*combining both teams in one*/
        List<Player> keepersOfBothTeam = new ArrayList<>();
        List<Player> batsmanOfBothTeam = new ArrayList<>();
        List<Player> allroundersOfBothTeam = new ArrayList<>();
        List<Player> bowlersOfBothTeam = new ArrayList<>();

        for(Player player : players)
        {
            PlayerType playerType = player.getPlayerType();
            switch (playerType)
            {
                case WK:
                    keepersOfBothTeam.add(player);
                    break;
                case BAT:
                    batsmanOfBothTeam.add(player);
                    break;
                case AR:
                    allroundersOfBothTeam.add(player);
                    break;
                case BOWL:
                    bowlersOfBothTeam.add(player);
                    break;
            }

        }

        Map<PlayerType, List<Player>> playerTypeMap = new HashMap<>();
        playerTypeMap.put(PlayerType.WK, keepersOfBothTeam);
        playerTypeMap.put(PlayerType.BAT, batsmanOfBothTeam);
        playerTypeMap.put(PlayerType.AR, allroundersOfBothTeam);
        playerTypeMap.put(PlayerType.BOWL, bowlersOfBothTeam);

        /*check if input data is valid*/
        validateData(playerTypeMap);

        Map<String, List<DreamTeam>> sheetWiseResult = new HashMap<>();
        int[][] validCombOfPlayerType = Dream11RuleHelper.getPlayerTypeValidCombinations();
        for(int [] arr : validCombOfPlayerType)
        {
            int wkCount = arr[0],batCount = arr[1],arCount = arr[2], bowlCount = arr[3];
            List<DreamTeam> dreamTeams = getDreamTeamsForOneCombinations(wkCount, batCount, arCount, bowlCount, playerTypeMap);

            String sheetName = wkCount + "-" + batCount + "-" + arCount + "-" + bowlCount+" ("+dreamTeams.size()+")";
            sheetWiseResult.put(sheetName, dreamTeams);
        }

        return sheetWiseResult;
    }


    private List<DreamTeam> getDreamTeamsForOneCombinations(int wk, int bat, int ar, int bowl, Map<PlayerType, List<Player>> playersMap)
    {
        if(wk+bat+ar+bowl != 11)
            return new ArrayList<>();

        List<Player> keepers = playersMap.get(PlayerType.WK);
        List<Player> batsmans = playersMap.get(PlayerType.BAT);
        List<Player> allrounders = playersMap.get(PlayerType.AR);
        List<Player> bowlers = playersMap.get(PlayerType.BOWL);

        if(keepers.size()< wk)return new ArrayList<>();
        else if(batsmans.size()< bat)return new ArrayList<>();
        else if(allrounders.size()< ar)return new ArrayList<>();
        else if(bowlers.size()< bowl)return new ArrayList<>();

        List<Set<Player>> wkCombinations = MathOperationHelper.getPlayerCombination(keepers, wk);
        List<Set<Player>> batCombinations = MathOperationHelper.getPlayerCombination(batsmans, bat);
        List<Set<Player>> arCombinations = MathOperationHelper.getPlayerCombination(allrounders, ar);
        List<Set<Player>> bowlCombinations = MathOperationHelper.getPlayerCombination(bowlers, bowl);

        List<DreamTeam> dreamTeams = new ArrayList<>();
        for(Set<Player> kpLoop : wkCombinations)
        {
            double creditsOfKeeper = MathOperationHelper.getCreditCount(kpLoop);
            for(Set<Player> batLoop : batCombinations)
            {
                double creditsOfBatsman = MathOperationHelper.getCreditCount(batLoop);
                for(Set<Player> arLoop : arCombinations)
                {
                    double creditsOfAllRounder = MathOperationHelper.getCreditCount(arLoop);
                    for(Set<Player> bowlLoop : bowlCombinations)
                    {
                        double creditsOfBowler = MathOperationHelper.getCreditCount(bowlLoop);
                        double creditSum = creditsOfKeeper + creditsOfBatsman + creditsOfAllRounder + creditsOfBowler;
                        if(creditSum <= 100.00 )
                        {
                            List<Player> possibleDreamTeam = new ArrayList<>();
                            possibleDreamTeam.addAll(kpLoop);
                            possibleDreamTeam.addAll(batLoop);
                            possibleDreamTeam.addAll(arLoop);
                            possibleDreamTeam.addAll(bowlLoop);
                            Dream11RuleHelper.addPLayersToDreamTeam(possibleDreamTeam, dreamTeams);
                        }
                    }
                }
            }
        }


        return dreamTeams;
    }

    private void validateData(Map<PlayerType, List<Player>> playersMap)
    {
        List<Player> keepers = playersMap.get(PlayerType.WK);
        List<Player> batsman = playersMap.get(PlayerType.BAT);
        List<Player> allrounders = playersMap.get(PlayerType.AR);
        List<Player> bowlers = playersMap.get(PlayerType.BOWL);

        if(keepers.size() < 1)
            throw new RuntimeException("Atleast one keeper in team required");
        else if(batsman.size() < 3)
            throw new RuntimeException("Atleast three batsman in team required");
        else if(allrounders.size() < 1)
            throw new RuntimeException("Atleast one all rounders in team required");
        else if(bowlers.size() < 3)
            throw new RuntimeException("Atleast three bowlers in team required");

    }
}
