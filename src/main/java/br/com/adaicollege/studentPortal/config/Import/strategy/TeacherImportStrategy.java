package br.com.adaicollege.studentPortal.config.Import.strategy;

import br.com.adaicollege.studentPortal.config.Import.enums.ImportEntityType;
import br.com.adaicollege.studentPortal.model.academic.Teacher;
import br.com.adaicollege.studentPortal.model.enums.CollegeCourse;
import br.com.adaicollege.studentPortal.repository.academic.TeacherRepository;
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
public class TeacherImportStrategy implements ImportStrategy {

    @Autowired
    private TeacherRepository repository;

    @Override
    public ImportEntityType getType() {
        return ImportEntityType.TEACHER;
    }

    @Override
    public void importCsv(MultipartFile file) {

        try (
                Reader reader = new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8);
                CSVParser parser = CSVFormat.DEFAULT
                        .withFirstRecordAsHeader()
                        .parse(reader)
        ) {

            List<Teacher> teachers = new ArrayList<>();

            for (CSVRecord record : parser) {

                Teacher teacher = new Teacher();
                teacher.setFirstName(record.get("first_name"));
                teacher.setFamilyName(record.get("family_name"));
                teacher.setCourseLectures(CollegeCourse.valueOf(record.get("course")));

                teachers.add(teacher);
            }

            repository.saveAll(teachers);

        } catch (Exception e) {
            throw new RuntimeException("Error importing teachers CSV", e);
        }
    }

    @Override
    public void importExcel(MultipartFile file) {

        try (Workbook workbook = WorkbookFactory.create(file.getInputStream())) {

            Sheet sheet = workbook.getSheetAt(0);
            List<Teacher> teachers = new ArrayList<>();

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {

                Row row = sheet.getRow(i);
                if (row == null) continue;

                Teacher teacher = new Teacher();
                teacher.setFirstName(row.getCell(0).getStringCellValue());
                teacher.setFamilyName(row.getCell(1).getStringCellValue());

                teachers.add(teacher);
            }

            repository.saveAll(teachers);

        } catch (Exception e) {
            throw new RuntimeException("Error importing teachers Excel", e);
        }
    }
}
