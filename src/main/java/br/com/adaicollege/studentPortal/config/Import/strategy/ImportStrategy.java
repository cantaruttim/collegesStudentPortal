package br.com.adaicollege.studentPortal.config.Import.strategy;

import br.com.adaicollege.studentPortal.config.Import.enums.ImportEntityType;
import org.springframework.web.multipart.MultipartFile;


public interface ImportStrategy {

    ImportEntityType getType();

    void importCsv(MultipartFile file);

    void importExcel(MultipartFile file);

}
