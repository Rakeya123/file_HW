package files;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import files.model.TypeMail;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParsingFileTest {
    private ClassLoader cl = ParsingFileTest.class.getClassLoader();
    private static final ObjectMapper mapper = new ObjectMapper();
    ;


    @Test
    void parsingXlsFileTest() throws Exception {

        ZipFile zf = new ZipFile("src/test/resources/save.zip");

        ZipEntry entry = zf.getEntry("sample1.xls");

        InputStream inputStream = zf.getInputStream(entry);
        try {

            XLS xls = new XLS(inputStream);
            String actualValue = xls.excel.getSheetAt(0).getRow(0).getCell(0).getStringCellValue();
            assertTrue(actualValue.contains("test1"));
        } finally {
            inputStream.close();


        }
    }

    @Test
    void parsingPdfFileTest() throws Exception {
        ZipFile zf = new ZipFile(new File("src/test/resources/save.zip"));
        ZipEntry entry = zf.getEntry("obrazec.pdf");
        InputStream inputStream = zf.getInputStream(entry);

        try {
            PDF pdf = new PDF(inputStream);
            Assertions.assertEquals("ЗАЯВЛЕНИЕ О ВЫДАЧЕ ПАСПОРТА", pdf.title);


        } finally {
            inputStream.close();

        }
    }


    @Test
    void parsingCsvFileTest() throws Exception {
        ZipFile zf = new ZipFile(new File("src/test/resources/save.zip"));
        ZipEntry entry = zf.getEntry("example2.csv");
        InputStream inputStream = zf.getInputStream(entry);

        try {
            CSVReader csvReader = new CSVReader(new InputStreamReader(inputStream));
            List<String[]> data = csvReader.readAll();
            Assertions.assertEquals(4, data.size());
            Assertions.assertArrayEquals(
                    new String[]{"Name", "Job Title", "Address", "State", "City"},
                    data.get(1));

        } finally {
            inputStream.close();
        }

    }
 @Test
 void tryToParsingNoExistsFileTest() throws Exception{
     ZipFile zf = new ZipFile(new File("src/test/resources/save.zip"));
     ZipEntry entry = zf.getEntry("hui.csv");
     Assertions.assertNull(entry);


 }

    @Test
    void parsingJsonTest() throws Exception {

        File file = new File("src/test/resources/mailtype.json");
        TypeMail mailType = mapper.readValue(file, TypeMail.class);
        List<String> actualValues = mailType.getDimensions();
        assertTrue(mailType.getTaskType().equals("PICKUP_UKD"));

        Assertions.assertEquals("height", actualValues.get(0));
        Assertions.assertEquals("length", actualValues.get(1));
        Assertions.assertEquals("width", actualValues.get(2));
        Assertions.assertEquals(actualValues.size(), 3);
    }

}



