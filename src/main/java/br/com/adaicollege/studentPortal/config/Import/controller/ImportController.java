package br.com.adaicollege.studentPortal.config.Import.controller;

import br.com.adaicollege.studentPortal.config.Import.enums.ImportEntityType;
import br.com.adaicollege.studentPortal.config.Import.service.GenericImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/import")
public class ImportController {

    @Autowired
    private GenericImportService service;

    @PostMapping(
            value = "/{entity}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<String> importFile(
            @PathVariable ImportEntityType entity,
            @RequestParam("file") MultipartFile file
    ) {
        service.importFile(entity, file);
        return ResponseEntity.ok("Import completed successfully");
    }
}