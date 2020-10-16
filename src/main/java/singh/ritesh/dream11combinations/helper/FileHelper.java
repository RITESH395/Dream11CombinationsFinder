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


    public static void ReadExcel()
    {
        InputStream inputStream = null;
        try
        {
            inputStream = new FileInputStream(new File("input.xlsx"));
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
        /*01*/list.add(new Player(1,"J Buttler", 10.0, Franchise.RR, PlayerType.WK));
        /*02*/list.add(new Player(2,"S Samson", 9.5, Franchise.RR, PlayerType.WK));
        /*03*/list.add(new Player(3,"S Smith", 9.5, Franchise.RR, PlayerType.BAT));
        /*04*/list.add(new Player(4,"R Uthapppa",8.5, Franchise.RR, PlayerType.BAT));
        /*05*/list.add(new Player(5,"D Miller", 8.5, Franchise.RR, PlayerType.BAT));
        /*06*/list.add(new Player(6,"B Strokes", 9.5, Franchise.RR, PlayerType.AR));
        /*07*/list.add(new Player(7,"R Tewatia", 8.5, Franchise.RR, PlayerType.AR));
        /*08*/list.add(new Player(8,"M Lomror", 8, Franchise.RR, PlayerType.AR));
        /*09*/list.add(new Player(9,"J Archer", 9, Franchise.RR, PlayerType.BOWL));
        /*10*/list.add(new Player(10,"J Unadkat", 8.5, Franchise.RR, PlayerType.BOWL));
        /*11*/list.add(new Player(11,"S Gopal", 8.5, Franchise.RR, PlayerType.BOWL));
        /* *********************TEAM 2************************************/
        /*01*/list.add(new Player(12,"A Carey", 8.5, Franchise.DC, PlayerType.WK));
        /*02*/list.add(new Player(13,"S Iyer", 9.5, Franchise.DC, PlayerType.BAT));
        /*03*/list.add(new Player(14,"P Saw", 9, Franchise.DC, PlayerType.BAT));
        /*04*/list.add(new Player(15,"S Dhawan",9, Franchise.DC, PlayerType.BAT));
        /*05*/list.add(new Player(16,"S Hetmyer", 8.5, Franchise.DC, PlayerType.BAT));
        /*06*/list.add(new Player(17,"M Stoinis", 9, Franchise.DC, PlayerType.AR));
        /*07*/list.add(new Player(18,"A Patel", 8.5, Franchise.DC, PlayerType.AR));
        /*08*/list.add(new Player(19,"K Rabada", 9.5, Franchise.DC, PlayerType.BOWL));
        /*09*/list.add(new Player(20,"R Ashwin", 9, Franchise.DC, PlayerType.BOWL));
        /*10*/list.add(new Player(21,"A Nortje", 8.5, Franchise.DC, PlayerType.BOWL));
        /*11*/list.add(new Player(22,"H Patel", 8, Franchise.DC, PlayerType.BOWL));

        return list;
    }
}