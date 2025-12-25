package br.com.adaicollege.studentPortal.config.Import.strategy;

import br.com.adaicollege.studentPortal.config.Import.enums.ImportEntityType;
import br.com.adaicollege.studentPortal.model.academic.Modules;
import br.com.adaicollege.studentPortal.model.enums.CollegeCourse;
import br.com.adaicollege.studentPortal.repository.academic.ModulesRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Component
public class ModuleImportStrategy implements ImportStrategy {

    @Autowired
    private ModulesRepository repository;

    @Override
    public ImportEntityType getType() {
        return ImportEntityType.MODULE;
    }

    @Override
    public void importCsv(MultipartFile file) {

        try (
                Reader reader = new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8);
                CSVParser parser = CSVFormat.DEFAULT
                        .withFirstRecordAsHeader()
                        .parse(reader)
        ) {

            List<Modules> modules = new ArrayList<>();

            for (CSVRecord record : parser) {

                Modules module = new Modules();
                module.setModuleName(record.get("module_name"));
                module.setModuleDescription(record.get("module_description"));
                module.setTeacherName(record.get("teacher_name"));
                module.setCourse(CollegeCourse.valueOf(record.get("course")));
                module.setQuantityClasses(Integer.valueOf(record.get("quantity_classes")));

                modules.add(module);
            }

            repository.saveAll(modules);

        } catch (Exception e) {
            throw new RuntimeException("Error importing modules CSV", e);
        }
    }

    @Override
    public void importExcel(MultipartFile file) {

        try (Workbook workbook = WorkbookFactory.create(file.getInputStream())) {

            Sheet sheet = workbook.getSheetAt(0);
            List<Modules> modules = new ArrayList<>();

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {

                Row row = sheet.getRow(i);
                if (row == null) continue;

                Modules module = new Modules();
                module.setModuleName(row.getCell(0).getStringCellValue());
                module.setModuleDescription(row.getCell(1).getStringCellValue());
                module.setTeacherName(row.getCell(2).getStringCellValue());
                module.setCourse(CollegeCourse.valueOf(row.getCell(3).getStringCellValue()));
                module.setQuantityClasses(Integer.valueOf(row.getCell(4).getStringCellValue()));

                modules.add(module);
            }

            repository.saveAll(modules);

        } catch (Exception e) {
            throw new RuntimeException("Error importing modules Excel", e);
        }
    }
}
