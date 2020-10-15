package singh.ritesh.dream11combinations.service;

import singh.ritesh.dream11combinations.bo.DreamTeam;
import singh.ritesh.dream11combinations.bo.Player;

import java.util.List;
import java.util.Map;

public interface CombinationFinderService
{
    Map<String, List<DreamTeam>> getAllCombinations(List<Player> players);

}
