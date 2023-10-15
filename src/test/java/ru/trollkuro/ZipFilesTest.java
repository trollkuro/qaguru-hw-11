package ru.trollkuro;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipFilesTest {
    ClassLoader cl = ZipFilesTest.class.getClassLoader();

    @Test
    void zipTest() throws Exception{
        try (InputStream stream = cl.getResourceAsStream("qa guru.zip");
             ZipInputStream zis = new ZipInputStream(stream)) {

            ZipEntry entry;

            while ((entry = zis.getNextEntry()) != null) {
                //check csv
                if (entry.getName().contains("csv")){
                    CSVReader csvReader = new CSVReader(new InputStreamReader(zis));
                    List<String[]> content = csvReader.readAll();

                    //check content size
                    Assertions.assertEquals(3, content.size());

                    //check second row
                    final String[] secondRow = content.get(1);
                    Assertions.assertArrayEquals(new String[] {"Season", " summer"}, secondRow);
                }
                //check xlsx
                if (entry.getName().contains("xlsx")){
                    XLS xls = new XLS(zis);
                    final String nickname = xls.excel.getSheetAt(1).getRow(3).getCell(1).getStringCellValue();
                    final int sheetCount = xls.excel.getNumberOfSheets();

                    //check sheet#2, humster's nickname
                    Assertions.assertEquals("Orange", nickname);

                    //check number of sheets
                    Assertions.assertEquals(2, sheetCount);
                }
                //check pdf
                if (entry.getName().contains("pdf")){
                    PDF pdf = new PDF(zis);

                    //check number of pages
                    Assertions.assertEquals(1, pdf.numberOfPages);

                    //check creator
                    Assertions.assertEquals("Microsoft® Word 2019", pdf.creator);

                    //check title
                    Assertions.assertEquals("Article about cats", pdf.title);
                }

            }
        }
    }
}
