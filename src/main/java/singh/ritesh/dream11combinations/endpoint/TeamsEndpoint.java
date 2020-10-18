package singh.ritesh.dream11combinations.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import singh.ritesh.dream11combinations.service.CombinationFinderService;

import java.io.FileInputStream;
import java.io.IOException;

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
    public ResponseEntity<InputStreamResource> getTeamsExcel() throws IOException
    {
        String fileName = "output.xlsx";
    	combinationFinderService.populateExcel(fileName);

        InputStreamResource resource = new InputStreamResource(new FileInputStream(fileName));
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        headers.add("Owner", "Ritesh");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(fileName.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}
