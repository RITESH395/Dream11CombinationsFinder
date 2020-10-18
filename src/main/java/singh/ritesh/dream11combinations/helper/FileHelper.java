package singh.ritesh.dream11combinations.helper;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import singh.ritesh.dream11combinations.bo.DreamTeam;
import singh.ritesh.dream11combinations.bo.Franchise;
import singh.ritesh.dream11combinations.bo.Player;
import singh.ritesh.dream11combinations.bo.PlayerType;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;
import java.util.Map.Entry;

public class FileHelper
{
    public static List<Player> parseInputExcel()
    {
        try
        {
            FileInputStream fis = new FileInputStream(new File("input.xlsx"));
            Workbook workbook = WorkbookFactory.create(fis);
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet)
            {
                Iterator<Cell> cellIterator = row.cellIterator();

                while (cellIterator.hasNext())
                {
                    Cell cell = cellIterator.next();
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    String stringValue = cell.getStringCellValue();
                }
                System.out.println("");
            }
            fis.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    public static List<Player> readInputExcel()
    {
        List<Player> list = new ArrayList<>();
        InputStream inputStream = null;
        try
        {
            inputStream = new FileInputStream(new File("input.xlsx"));
            Workbook wb = WorkbookFactory.create(inputStream);
            int numberOfSheet = wb.getNumberOfSheets();

            for (int i = 0; i < numberOfSheet; i++)
            {
                Sheet sheet = wb.getSheetAt(i);
                Iterator<Row> rowIterator = sheet.rowIterator();
                String playerFranchise = sheet.getSheetName();
                while (rowIterator.hasNext())
                {
                    Row row = rowIterator.next();
                    if (row.getRowNum() == 0)
                    {
                        continue;
                    }
                    String id = cellValue(row.getCell(0)); //Id of player
                    String playerName = cellValue(row.getCell(1)); // Name of Player
                    String playerDreamCredit = cellValue(row.getCell(2)); // dream 11 points
                    String playerType = cellValue(row.getCell(3)); // Role of Player(WK, BAT, AR, BOWL)

                    Player p = new Player(Integer.valueOf(id), playerName, Double.valueOf(playerDreamCredit),
                            Franchise.valueOf(playerFranchise), PlayerType.valueOf(playerType));
                    list.add(p);
                    p = null;
                }
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return list;
    }

    private static String cellValue(Cell cell)
    {
        cell.setCellType(Cell.CELL_TYPE_STRING);
        return cell.getStringCellValue();
    }

    private static Cell setCellValue(Cell cell, String value)
    {
        cell.setCellType(Cell.CELL_TYPE_STRING);
        cell.setCellValue(value);
        return cell;
    }

    private static void createHeaders(Sheet sheet, String value)
    {
        Row row = sheet.createRow(0);
        setCellValue(row.createCell(0), value);
    }

    public static void writeCombinationsToExcel(Map<String, List<DreamTeam>> allCombinations, XSSFWorkbook workbook)
    {

        for (Entry<String, List<DreamTeam>> entry : allCombinations.entrySet())
        {
            String sheetName = entry.getKey();
            Sheet sheet = workbook.createSheet(sheetName);

            List<DreamTeam> combination = entry.getValue();

            for (int i = 0; i < combination.size(); i++)
            {

                Set<Player> players = combination.get(i).getFinalPLayers();
                int playerCount = 0;
                double totalPoints = 0;
                for (Player player : players)
                {
                    setCellValue(sheet.createRow(playerCount++).createCell(i), player.getPlayerName() + " (" + player.getPlayerFranchise().toString() + ")");
                    totalPoints = totalPoints + player.getPlayerDreamCredit();
                }
                setCellValue(sheet.createRow(playerCount).createCell(i), String.valueOf(totalPoints));
            }

        }

    }
}