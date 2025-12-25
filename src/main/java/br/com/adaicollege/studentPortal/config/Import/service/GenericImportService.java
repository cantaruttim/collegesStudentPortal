package br.com.adaicollege.studentPortal.config.Import.service;

import br.com.adaicollege.studentPortal.config.Import.enums.ImportEntityType;
import br.com.adaicollege.studentPortal.config.Import.strategy.ImportStrategy;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class GenericImportService {

    private final Map<ImportEntityType, ImportStrategy> strategyMap;

    public GenericImportService(List<ImportStrategy> strategies) {
        this.strategyMap = strategies.stream()
                .collect(Collectors.toMap(
                        ImportStrategy::getType,
                        Function.identity()
                ));
    }

    public void importFile(
            ImportEntityType type,
            MultipartFile file
    ) {

        ImportStrategy strategy = strategyMap.get(type);

        if (strategy == null) {
            throw new IllegalArgumentException("Unsupported import type");
        }

        String filename = file.getOriginalFilename();

        if (filename == null) {
            throw new IllegalArgumentException("Invalid file");
        }

        if (filename.endsWith(".csv")) {
            strategy.importCsv(file);
        } else if (filename.endsWith(".xls") || filename.endsWith(".xlsx")) {
            strategy.importExcel(file);
        } else {
            throw new IllegalArgumentException("Unsupported file format");
        }
    }
}
