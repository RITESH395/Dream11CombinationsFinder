package singh.ritesh.dream11combinations.helper;

import org.apache.poi.ss.usermodel.*;
import org.springframework.util.CollectionUtils;
import singh.ritesh.dream11combinations.bo.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

public class FileHelper {


  public static List<Player> parseInputExcel() {
    try {
      FileInputStream fis = new FileInputStream(new File("input.xlsx"));
      Workbook workbook = WorkbookFactory.create(fis);
      Sheet sheet = workbook.getSheetAt(0);

      for (Row row : sheet) {
        Iterator<Cell> cellIterator = row.cellIterator();

        while (cellIterator.hasNext()) {
          Cell cell = cellIterator.next();
          String stringValue = cell.getStringCellValue();
        }
        System.out.println("");
      }
      fis.close();
    } catch (Exception e) {
      e.printStackTrace();
    }

    return null;
  }

  public static List<Player> readInputExcel() {
    List<Player> list = new ArrayList<>();
    // InputStream inputStream = null;
    Workbook wb = null;
    try {
      // inputStream = new FileInputStream(new File("input.xlsx"));
      wb = WorkbookFactory.create(new File("input.xlsx"));
      int numberOfSheet = wb.getNumberOfSheets();

      for (int i = 0; i < numberOfSheet; i++) {
        Sheet sheet = wb.getSheetAt(i);
        Iterator<Row> rowIterator = sheet.rowIterator();
        String playerFranchise = sheet.getSheetName();
        while (rowIterator.hasNext()) {
          Row row = rowIterator.next();
          if (row.getRowNum() == 0) {
            continue;
          }
          String id = cellValue(row.getCell(0)); // Id of player
          String playerName = cellValue(row.getCell(1)); // Name of Player
          String playerDreamCredit = cellValue(row.getCell(2)); // dream 11 points
          String playerType = cellValue(row.getCell(3)); // Role of Player(WK, BAT, AR, BOWL)
          String include = cellValue(row.getCell(4)); // Include in dream team ?

          Player p =
              new Player(
                  (int) Double.parseDouble(id),
                  playerName,
                  Double.valueOf(playerDreamCredit),
                  Franchise.valueOf(playerFranchise),
                  PlayerType.valueOf(playerType),
                      include);
          list.add(p);
          p = null;
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        wb.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    return list;
  }

  private static String cellValue(Cell cell) {
    String data;
    switch (cell.getCellType()) {
      case STRING:
        data = cell.getStringCellValue();
        break;
      case NUMERIC:
        data = cell.getNumericCellValue() + "";
        break;
      case FORMULA:
        data = cell.getCellFormula() + "";
        break;
      case BOOLEAN:
        data = cell.getBooleanCellValue() + "";
        break;
      case ERROR:
        data = cell.getErrorCellValue() + "";
        break;
      case BLANK:
        data = "-";
        break;
      default:
        data = "N/A";
    }
    return data;
  }

  private static Cell setCellValue(Cell cell, String value) {
    cell.setCellValue(value);
    return cell;
  }

  private static Cell setHeaderValue(Cell cell, String value, CellStyle cellStyle) {
    cell.setCellValue(value);
    cell.setCellStyle(cellStyle);
    return cell;
  }


  public static void writeCombinationsToExcel(
      Map<String, List<DreamTeam>> allCombinations, Workbook workbook) {

    CellStyle headerCellGreen = workbook.createCellStyle();
    headerCellGreen.setAlignment(HorizontalAlignment.CENTER);
    headerCellGreen.setVerticalAlignment(VerticalAlignment.CENTER);
    Font headerFont = workbook.createFont();
    headerFont.setBold(true);
    headerFont.setFontHeightInPoints((short) 12);
    headerFont.setFontName("Calibri");
    headerCellGreen.setFont(headerFont);
    headerCellGreen.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
    headerCellGreen.setFillPattern(FillPatternType.SOLID_FOREGROUND);

    for (Entry<String, List<DreamTeam>> entry : allCombinations.entrySet())
    {
      String sheetName = entry.getKey();
      List<DreamTeam> dreamTeams = entry.getValue();

      System.out.println("Processing sheet: " + sheetName);

      Sheet sheet = workbook.createSheet(sheetName);
      if (CollectionUtils.isEmpty(dreamTeams))
        continue;

      Row row = sheet.createRow(0);
      List<String> headers = new ArrayList<>();
      PlayersDivision playersDivision = dreamTeams.get(0).getPlayersDivisionCount();
      populateHeaders(playersDivision, headers);
      for (int i = 0; i < headers.size(); i++) {  /* Green header of a sheet*/
        setHeaderValue(row.createCell(i), headers.get(i), headerCellGreen);
        sheet.setColumnWidth(i, 20 * 256);
      }

      for (int i = 0; i < dreamTeams.size(); i++) /* One row of a sheet*/
      {
        int playerCount = 0;
        double totalPoints = 0;

        DreamTeam possibleDreamTeam = dreamTeams.get(i);
        Set<Player> players = possibleDreamTeam.getFinalPLayers();
        List<Player> listPlayers = new ArrayList<>(players);
        players = null;
        Collections.sort(listPlayers);
        row = sheet.createRow(i+1);
          for (Player player : listPlayers) /* One cell of a row */
          {
            String playerName =
                player.getPlayerName() + " (" + player.getPlayerFranchise().toString() + ") " + player.getPlayerType().toString();
            setCellValue(row.createCell(playerCount++), playerName);
            totalPoints = totalPoints + player.getPlayerDreamCredit();
          }
          listPlayers = null;
        setCellValue(row.createCell(playerCount++), String.valueOf(totalPoints));
        Set<Franchise> franchisesList = possibleDreamTeam.getFranchisePlayerCountMap().keySet();
        StringBuilder finalDivisionCount = new StringBuilder();
        for(Franchise franchise : franchisesList){
          finalDivisionCount.append(franchise).append("(").append(possibleDreamTeam.getFranchisePlayerCountMap().get(franchise)).append(") ");
        }
        setCellValue(row.createCell(playerCount++), finalDivisionCount.toString());
        possibleDreamTeam = null;
      }
    }
  }

  private static void populateHeaders(PlayersDivision playersDivision, List<String> headers) {
    for (int i = 0; i < playersDivision.getWKCount(); i++) {
      headers.add("Wicket Keeper");
    }
    for (int i = 0; i < playersDivision.getBATCount(); i++) {
      headers.add("Batsman");
    }
    for (int i = 0; i < playersDivision.getALLCount(); i++) {
      headers.add("All-Rounder");
    }
    for (int i = 0; i < playersDivision.getBOWLCount(); i++) {
      headers.add("Bowler");
    }
    headers.add("Total Credit");
    headers.add("Division");
  }

  /**
   * Write row data to the excel sheet
   * @param data data that is written to the excel row
   * @param row excel row to which data is written
   * @param cellStyle represents style of the cell
   */
  private void writeRow(List<String> data, Row row, CellStyle cellStyle){
    int cellCount = 0;
    for(String cellData:data) {
      Cell cell = row.createCell(cellCount++);
      cell.setCellStyle(cellStyle);
      cell.setCellValue(cellData);
    }
  }
}
