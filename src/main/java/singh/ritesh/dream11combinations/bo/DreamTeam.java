package singh.ritesh.dream11combinations.bo;

import singh.ritesh.dream11combinations.exception.CreditExceededException;
import singh.ritesh.dream11combinations.exception.FranchiseSizeException;
import singh.ritesh.dream11combinations.exception.MaxPLayerFromFranchiseException;
import singh.ritesh.dream11combinations.exception.PlayerSizeExceededException;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DreamTeam
{
    private Set<Player> finalPLayers = new HashSet<>(); /*size must be 11*/
    private double totalCreditCount = 0; /* totalCreditCount must be <= 100.0*/
    private Map<Franchise, Integer> franchisePlayerCountMap = new HashMap<>();//map size must be 2 only and each value <=7
    private PlayersDivision playersDivisionCount = new PlayersDivision();

    public PlayersDivision getPlayersDivisionCount() {
        return playersDivisionCount;
    }

    public Map<Franchise, Integer> getFranchisePlayerCountMap() {
        return new HashMap<>(franchisePlayerCountMap);
    }

    public Set<Player> getFinalPLayers()
    {
        return new HashSet<>(finalPLayers);//immutable
    }

    public void addFinalPLayers(Player pLayer)
    {
        validatePlayerAddition(pLayer);

        this.finalPLayers.add(pLayer);
        totalCreditCount = totalCreditCount + pLayer.getPlayerDreamCredit();
        franchisePlayerCountMap.putIfAbsent(pLayer.getPlayerFranchise(), 0);
        franchisePlayerCountMap.put(pLayer.getPlayerFranchise(), franchisePlayerCountMap.get(pLayer.getPlayerFranchise())+1);
        PlayerType playerType = pLayer.getPlayerType();
        switch (playerType){
            case WK:
                playersDivisionCount.addWKCount();
                break;
            case BAT:
                playersDivisionCount.addBATCount();
                break;
            case AR:
                playersDivisionCount.addALLRCount();
                break;
            case BOWL:
                playersDivisionCount.addBOWLCount();
                break;
        }
    }

    /*
    Dream 11 rules:
     * Total Dream players allowed 11       : PlayerSizeExceededException
     * Credits must not exceed 100.0        : CreditExceededException
     * Only two franchises can play a match : FranchiseSizeException
     * Max players from each franchise <= 7 : MaxPLayerFromFranchiseException
     * */
    private void validatePlayerAddition(Player newPLayer)
    {
        if (this.finalPLayers.size() + 1 > 11)
            throw new PlayerSizeExceededException("Cannot add more players; already 11");
        else if (totalCreditCount + newPLayer.getPlayerDreamCredit() > 100.00)
            throw new CreditExceededException("Credit is exceeding 100");
        else
        {
            Set<Franchise> tempFranchises = new HashSet<>(franchisePlayerCountMap.keySet());
            tempFranchises.add(newPLayer.getPlayerFranchise());
            if (tempFranchises.size() > 2)
                throw new FranchiseSizeException("More than two franchise found "+ tempFranchises.toString());
            else if (franchisePlayerCountMap.get(newPLayer.getPlayerFranchise())!= null && franchisePlayerCountMap.get(newPLayer.getPlayerFranchise()) + 1 > 7)
                throw new MaxPLayerFromFranchiseException(newPLayer.getPlayerFranchise(), "Max player from Franchise");
        }
    }

    @Override
    public String toString()
    {
        StringBuilder output = new StringBuilder();
        for(Player player : finalPLayers)
        {
            output.append(player.getPlayerName()).append("(").append(player.getPlayerFranchise().toString()).append(")");
        }
        return output.toString();
    }
}