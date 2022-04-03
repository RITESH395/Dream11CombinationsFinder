package singh.ritesh.dream11combinations.service;

import singh.ritesh.dream11combinations.bo.DreamTeam;
import singh.ritesh.dream11combinations.bo.Player;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public interface CombinationFinderService
{
    Map<String, List<DreamTeam>> getAllCombinations(List<Player> players);

    void populateExcel(String fileName) throws IOException;

    public Map<String, Integer> getCount();

}
