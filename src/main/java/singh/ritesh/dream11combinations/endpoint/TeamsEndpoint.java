package singh.ritesh.dream11combinations.endpoint;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import singh.ritesh.dream11combinations.bo.DreamTeam;
import singh.ritesh.dream11combinations.bo.Player;
import singh.ritesh.dream11combinations.helper.FileHelper;
import singh.ritesh.dream11combinations.service.CombinationFinderService;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
public class TeamsEndpoint
{
	
	@Autowired
	CombinationFinderService combinationFinderService;
	
    @RequestMapping("/")
    public String testing()
    {
        return "Yes its workig fine..!! Go ahead";
    }

    @RequestMapping("/download")
    public ResponseEntity<InputStreamResource> getTeamsExcel() throws InvalidFormatException, IOException
    {
    	
    	
    	List<Player> players = FileHelper.readInputExcel();
    	Map<String, List<DreamTeam>> allCombinations = combinationFinderService.getAllCombinations(players);
    	
        
        XSSFWorkbook workbook = new XSSFWorkbook();
//        Workbook workBook = WorkbookFactory.create(file);
        FileHelper.writeCombinationsToExcel(allCombinations, workbook);
        File file = new File("output.xlsx");
        FileOutputStream out = new FileOutputStream(file);
        workbook.write(out);
        
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=output.xlsx");
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        headers.add("Owner", "Ritesh");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}
