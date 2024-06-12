package sse.edu.SPR2024.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("api/v1/moderator")
public class ModeratorController {

    @PostMapping(value = "/learners/{id}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> importLearnerFromExcel(@RequestParam("file") MultipartFile file, @PathVariable("id")String moderatorId) {
        if (file.isEmpty()) {
            return new ResponseEntity<>("File is empty", HttpStatus.BAD_REQUEST);
        }

        try {
            File excelFile = File.createTempFile("temp", null);
            file.transferTo(excelFile);

            //List<Account> importedData = moderatorService.importLearnerFromExcelData(excelFile,moderatorId);

            // Process imported data as needed

            return new ResponseEntity<>("Excel data imported successfully", HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error importing Excel data", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping(value = "/mentors/{id}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> importMentorFromExcel(@RequestParam("file") MultipartFile file, @PathVariable("id")String moderatorId) {
        if (file.isEmpty()) {
            return new ResponseEntity<>("File is empty", HttpStatus.BAD_REQUEST);
        }

        try {
            File excelFile = File.createTempFile("temp", null);
            file.transferTo(excelFile);

            //List<Account> importedData = moderatorService.importMentorFromExcelData(excelFile,moderatorId);

            // Process imported data as needed

            return new ResponseEntity<>("Excel data imported successfully", HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error importing Excel data", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
