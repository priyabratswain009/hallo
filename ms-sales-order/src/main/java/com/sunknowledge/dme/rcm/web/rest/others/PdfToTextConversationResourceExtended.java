package com.sunknowledge.dme.rcm.web.rest.others;

import com.convertapi.client.Config;
import com.convertapi.client.ConvertApi;
import com.convertapi.client.Param;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.concurrent.ExecutionException;

@Validated
@RestController
@RequestMapping("/api")
public class PdfToTextConversationResourceExtended {
    @GetMapping(value = "convertApi")
    public void convertApi() throws IOException, ExecutionException, InterruptedException {
        System.out.println("Hello");
        Config.setDefaultSecret("HCAggDwi7NfBOlpx");
        ConvertApi.convert("pdf", "txt",    new Param("File", Paths.get("D:/Pritam/projectData/Input/PAR_114_Fax_Request 1.pdf"))).get().saveFilesSync(Paths.get("D:/Pritam/projectData/Output"));
        System.out.println("Success");
    }
}
