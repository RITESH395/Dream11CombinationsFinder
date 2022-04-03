package singh.ritesh.dream11combinations.service.implementation;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import singh.ritesh.dream11combinations.bo.DreamTeam;
import singh.ritesh.dream11combinations.bo.Player;
import singh.ritesh.dream11combinations.bo.PlayerType;
import singh.ritesh.dream11combinations.helper.Dream11RuleHelper;
import singh.ritesh.dream11combinations.helper.FileHelper;
import singh.ritesh.dream11combinations.helper.MathOperationHelper;
import singh.ritesh.dream11combinations.service.CombinationFinderService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

@Service
public class CombinationFinderServiceImpl implements CombinationFinderService {
  @Override
  public Map<String, List<DreamTeam>> getAllCombinations(List<Player> players) {
    if (players.size() < 11) return null; /*not possible*/

    /*combining both teams in one*/
    List<Player> keepersOfBothTeam = new ArrayList<>();
    List<Player> batsmanOfBothTeam = new ArrayList<>();
    List<Player> allRoundersOfBothTeam = new ArrayList<>();
    List<Player> bowlersOfBothTeam = new ArrayList<>();
    List<Player> mandatoryPlayer = new ArrayList<>();

    for (Player player : players) {
      if ("No".equalsIgnoreCase(player.getIncludePlayer())) continue;
      else if ("Mandatory".equalsIgnoreCase(player.getIncludePlayer())) mandatoryPlayer.add(player);

      PlayerType playerType = player.getPlayerType();
      switch (playerType) {
        case WK:
          keepersOfBothTeam.add(player);
          break;
        case BAT:
          batsmanOfBothTeam.add(player);
          break;
        case AR:
          allRoundersOfBothTeam.add(player);
          break;
        case BOWL:
          bowlersOfBothTeam.add(player);
          break;
      }
    }

    Map<PlayerType, List<Player>> playerTypeMap = new HashMap<>();
    playerTypeMap.put(PlayerType.WK, keepersOfBothTeam);
    playerTypeMap.put(PlayerType.BAT, batsmanOfBothTeam);
    playerTypeMap.put(PlayerType.AR, allRoundersOfBothTeam);
    playerTypeMap.put(PlayerType.BOWL, bowlersOfBothTeam);

    /*check if input data is valid*/
    validateData(playerTypeMap);

    Map<String, List<DreamTeam>> sheetWiseResult = new HashMap<>();
    int[][] validCombOfPlayerType = Dream11RuleHelper.getPlayerTypeValidCombinations();
    for (int[] arr : validCombOfPlayerType) {
      int wkCount = arr[0], batCount = arr[1], arCount = arr[2], bowlCount = arr[3];
      List<DreamTeam> dreamTeams =
          getDreamTeamsForOneCombinations(
              wkCount, batCount, arCount, bowlCount, playerTypeMap, mandatoryPlayer);

      String sheetName =
          wkCount
              + "-"
              + batCount
              + "-"
              + arCount
              + "-"
              + bowlCount
              + " ("
              + dreamTeams.size()
              + ")";
      sheetWiseResult.put(sheetName, dreamTeams);
    }

    return sheetWiseResult;
  }

  @Override
  public void populateExcel(String fileName) throws IOException {
    List<Player> players = FileHelper.readInputExcel();
    Workbook workbook = new XSSFWorkbook();

    Map<String, List<DreamTeam>> allCombinations = this.getAllCombinations(players);
    FileHelper.writeCombinationsToExcel(allCombinations, workbook);
    workbook.write(new FileOutputStream(new File(fileName)));
    workbook.close();
  }

  @Override
  public Map<String, Integer> getCount() {
    List<Player> players = FileHelper.readInputExcel();

    Map<String, List<DreamTeam>> allCombinations = this.getAllCombinations(players);
    Map<String, Integer> map = new LinkedHashMap<>();
    Integer total = 0;
    for (String sheet : allCombinations.keySet()) {
      int size = allCombinations.get(sheet).size();
      map.put(sheet, size);
      total = total + size;
    }
    map.put("TOTAL", total);
    return map;
  }

  private List<DreamTeam> getDreamTeamsForOneCombinations(
      int wkCount,
      int batCount,
      int arCount,
      int bowlCount,
      Map<PlayerType, List<Player>> playersMap,
      List<Player> mandatoryPlayer) {
    if (wkCount + batCount + arCount + bowlCount != 11) return new ArrayList<>();

    List<Player> keepers = playersMap.get(PlayerType.WK);
    List<Player> batsMens = playersMap.get(PlayerType.BAT);
    List<Player> allRounders = playersMap.get(PlayerType.AR);
    List<Player> bowlers = playersMap.get(PlayerType.BOWL);

    if (keepers.size() < wkCount) return new ArrayList<>();
    else if (batsMens.size() < batCount) return new ArrayList<>();
    else if (allRounders.size() < arCount) return new ArrayList<>();
    else if (bowlers.size() < bowlCount) return new ArrayList<>();

    List<Set<Player>> wkCombinations = MathOperationHelper.getPlayerCombination(keepers, wkCount);
    List<Set<Player>> batCombinations =
        MathOperationHelper.getPlayerCombination(batsMens, batCount);
    List<Set<Player>> arCombinations =
        MathOperationHelper.getPlayerCombination(allRounders, arCount);
    List<Set<Player>> bowlCombinations =
        MathOperationHelper.getPlayerCombination(bowlers, bowlCount);

    List<DreamTeam> dreamTeams = new ArrayList<>();
    for (Set<Player> kpLoop : wkCombinations) {
      double creditsOfKeeper = MathOperationHelper.getCreditCount(kpLoop);
      for (Set<Player> batLoop : batCombinations) {
        double creditsOfBatsman = MathOperationHelper.getCreditCount(batLoop);
        for (Set<Player> arLoop : arCombinations) {
          double creditsOfAllRounder = MathOperationHelper.getCreditCount(arLoop);
          for (Set<Player> bowlLoop : bowlCombinations) {
            double creditsOfBowler = MathOperationHelper.getCreditCount(bowlLoop);
            double creditSum =
                creditsOfKeeper + creditsOfBatsman + creditsOfAllRounder + creditsOfBowler;
            if (creditSum <= 100.00) {
              List<Player> possibleDreamTeam = new ArrayList<>();
              possibleDreamTeam.addAll(kpLoop);
              possibleDreamTeam.addAll(batLoop);
              possibleDreamTeam.addAll(arLoop);
              possibleDreamTeam.addAll(bowlLoop);
              if (allMandatoryInTeam(mandatoryPlayer, possibleDreamTeam)) {
                Dream11RuleHelper.addPLayersToDreamTeam(possibleDreamTeam, dreamTeams);
              }
            }
          }
        }
      }
    }

    return dreamTeams;
  }

  private boolean allMandatoryInTeam(List<Player> mandatoryPlayer, List<Player> possibleDreamTeam) {
    boolean include = true;
    for (Player mandPlayer : mandatoryPlayer) {
      if (!possibleDreamTeam.contains(mandPlayer)) {
        include = false;
        return include;
      }
    }
    return include;
  }

  private void validateData(Map<PlayerType, List<Player>> playersMap) {
    List<Player> keepers = playersMap.get(PlayerType.WK);
    List<Player> batsman = playersMap.get(PlayerType.BAT);
    List<Player> allrounders = playersMap.get(PlayerType.AR);
    List<Player> bowlers = playersMap.get(PlayerType.BOWL);

    if (keepers.size() < 1) throw new RuntimeException("Atleast one keeper in team required");
    else if (batsman.size() < 3)
      throw new RuntimeException("Atleast three batsman in team required");
    else if (allrounders.size() < 1)
      throw new RuntimeException("Atleast one all rounders in team required");
    else if (bowlers.size() < 3)
      throw new RuntimeException("Atleast three bowlers in team required");
  }
}