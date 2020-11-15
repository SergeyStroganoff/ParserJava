import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Set;

public class Exel {

    String filePathName;
    private final Workbook book;
    private final Sheet sheet;
    public static int countOfStringEmails;

    public Exel(String filePathName) {
        this.filePathName = filePathName;
        book = new HSSFWorkbook();
        sheet = book.createSheet("Mails");

        // Нумерация начинается с нуля
        Row row = sheet.createRow(0);
        Cell name = row.createCell(0);
        name.setCellValue("Emails value");
        countOfStringEmails = 0;
    }


    public void writeEmailStringIntoExellFile(String stringOfEmail) {

        Row row = sheet.createRow(countOfStringEmails + 1);
        Cell name = row.createCell(0);
        name.setCellValue(stringOfEmail);
        countOfStringEmails++;
        System.out.println("Write in Row: " + (countOfStringEmails) + " " + stringOfEmail);

    }

    public void writeArrayInExell(@org.jetbrains.annotations.NotNull Set<String> arayOfAllemails) {

        System.out.println("Size of array " + arayOfAllemails.size());

        for (String s : arayOfAllemails) {
            writeEmailStringIntoExellFile(s);
        }
    }

    public void saveExel() {

        try {
            // Записываем всё в файл
            File file = new File(filePathName);
            FileOutputStream outFile = new FileOutputStream(file);
            book.write(outFile);
            System.out.println("Created file: " + file.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("We have got a mistake");
            e.getMessage();

        }

    }


}



