package singh.ritesh.dream11combinations.endpoint;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@RestController
public class TeamsEndpoint
{
    @RequestMapping("/")
    public String testing()
    {
        return "Yes its workig fine..!! Go ahead";
    }

    @RequestMapping("/download")
    public ResponseEntity<InputStreamResource> getTeamsExcel() throws FileNotFoundException
    {
        File file = new File("output.xlsx");

        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=output.xls");
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
