package br.com.adaicollege.studentPortal.config.Import.strategy;

import br.com.adaicollege.studentPortal.config.Import.enums.ImportEntityType;
import br.com.adaicollege.studentPortal.model.academic.student.CreateStudent;
import br.com.adaicollege.studentPortal.repository.academic.CreateStudentRepository;
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
public class StudentImportStrategy implements ImportStrategy {

    @Autowired
    private CreateStudentRepository repository;

    @Override
    public ImportEntityType getType() {
        return ImportEntityType.STUDENT;
    }

    @Override
    public void importCsv(MultipartFile file) {

        try (
                Reader reader = new InputStreamReader(
                        file.getInputStream(),
                        StandardCharsets.UTF_8
                );
                CSVParser parser = CSVFormat.DEFAULT
                        .withFirstRecordAsHeader()
                        .withIgnoreEmptyLines()
                        .parse(reader)
        ) {

            List<CreateStudent> students = new ArrayList<>();

            for (CSVRecord record : parser) {

                CreateStudent student = new CreateStudent();
                student.setFirstName(record.get("first_name"));
                student.setFamilyName(record.get("family_name"));
                student.setEmail(record.get("email"));
                student.setSocialSecurityNumber(record.get("ssn"));

                students.add(student);
            }

            repository.saveAll(students);

        } catch (Exception e) {
            throw new RuntimeException("Error importing students CSV", e);
        }
    }

    @Override
    public void importExcel(MultipartFile file) {

        try (Workbook workbook = WorkbookFactory.create(file.getInputStream())) {

            Sheet sheet = workbook.getSheetAt(0);
            List<CreateStudent> students = new ArrayList<>();

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {

                Row row = sheet.getRow(i);
                if (row == null) continue;

                CreateStudent student = new CreateStudent();
                student.setFirstName(row.getCell(0).getStringCellValue());
                student.setFamilyName(row.getCell(1).getStringCellValue());
                student.setEmail(row.getCell(2).getStringCellValue());
                student.setSocialSecurityNumber(row.getCell(3).getStringCellValue());

                students.add(student);
            }

            repository.saveAll(students);

        } catch (Exception e) {
            throw new RuntimeException("Error importing students Excel", e);
        }
    }
}
