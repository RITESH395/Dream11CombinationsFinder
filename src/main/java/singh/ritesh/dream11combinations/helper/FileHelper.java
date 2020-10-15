package singh.ritesh.dream11combinations.helper;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import singh.ritesh.dream11combinations.bo.Franchise;
import singh.ritesh.dream11combinations.bo.Player;
import singh.ritesh.dream11combinations.bo.PlayerType;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FileHelper
{
    public static List<Player> parseInputExcel()
    {
        try
        {
            FileInputStream file = new FileInputStream(new File("input.xls"));
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);

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
            file.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }


    public static void ReadExcel()
    {
        InputStream inputStream = null;
        try
        {
            inputStream = new FileInputStream(new File("input.xls"));
            Workbook wb = WorkbookFactory.create(inputStream);
            int numberOfSheet = wb.getNumberOfSheets();

            for (int i = 0; i < numberOfSheet; i++)
            {
                Sheet sheet = wb.getSheetAt(i);
                //.... Customize your code here
                // To get sheet name, try -> sheet.getSheetName()
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static List<Player> getHardcodedPlayers()
    {
        List<Player> list = new ArrayList<>();
        /*01*/list.add(new Player("J Buttler", 10.0, Franchise.RR, PlayerType.WK));
        /*02*/list.add(new Player("S Samson", 9.5, Franchise.RR, PlayerType.WK));
        /*03*/list.add(new Player("S Smith", 9.5, Franchise.RR, PlayerType.BAT));
        /*04*/list.add(new Player("R Uthapppa",8.5, Franchise.RR, PlayerType.BAT));
        /*05*/list.add(new Player("D Miller", 8.5, Franchise.RR, PlayerType.BAT));
        /*06*/list.add(new Player("B Strokes", 9.5, Franchise.RR, PlayerType.AR));
        /*07*/list.add(new Player("R Tewatia", 8.5, Franchise.RR, PlayerType.AR));
        /*08*/list.add(new Player("M Lomror", 8, Franchise.RR, PlayerType.AR));
        /*09*/list.add(new Player("J Archer", 9, Franchise.RR, PlayerType.BOWL));
        /*10*/list.add(new Player("J Unadkat", 8.5, Franchise.RR, PlayerType.BOWL));
        /*11*/list.add(new Player("S Gopal", 8.5, Franchise.RR, PlayerType.BOWL));
        /* *********************TEAM 2************************************/
        /*01*/list.add(new Player("A Carey", 8.5, Franchise.DC, PlayerType.WK));
        /*02*/list.add(new Player("S Iyer", 9.5, Franchise.DC, PlayerType.BAT));
        /*03*/list.add(new Player("P Saw", 9, Franchise.DC, PlayerType.BAT));
        /*04*/list.add(new Player("S Dhawan",9, Franchise.DC, PlayerType.BAT));
        /*05*/list.add(new Player("S Hetmyer", 8.5, Franchise.DC, PlayerType.BAT));
        /*06*/list.add(new Player("M Stoinis", 9, Franchise.DC, PlayerType.AR));
        /*07*/list.add(new Player("A Patel", 8.5, Franchise.DC, PlayerType.AR));
        /*08*/list.add(new Player("K Rabada", 9.5, Franchise.DC, PlayerType.BOWL));
        /*09*/list.add(new Player("R Ashwin", 9, Franchise.DC, PlayerType.BOWL));
        /*10*/list.add(new Player("A Nortje", 8.5, Franchise.DC, PlayerType.BOWL));
        /*11*/list.add(new Player("H Patel", 8, Franchise.DC, PlayerType.BOWL));

        return list;
    }
}