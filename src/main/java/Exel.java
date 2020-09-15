import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Exel {

     String filePathName;
 private    Workbook book;
 private    Sheet sheet;

public Exel (String filePathName){
    this.filePathName = filePathName;
    book = new HSSFWorkbook();
    sheet = book.createSheet("Mails");

    // Нумерация начинается с нуля
    Row row = sheet.createRow(0);

    Cell name = row.createCell(0);
    name.setCellValue("Emails");

}

public void WriteArrayInExell (@org.jetbrains.annotations.NotNull ArrayList<String> emaillist) {

    System.out.println("Size of array "+ emaillist.size());

    for (int i=0; i<emaillist.size(); i++){

        Row row1 = sheet.createRow(i+1);
        Cell name1 = row1.createCell(0);
        name1.setCellValue(emaillist.get(i));
        System.out.println("Write in Row: "+ (i+1)+" "+ emaillist.get(i));

    }
}

public void saveExel (){

    try {
        // Записываем всё в файл
        File file = new File(filePathName);
        FileOutputStream outFile = new FileOutputStream(file);
        book.write(outFile);
        System.out.println("Created file: " + file.getAbsolutePath());
    }  catch (IOException e)
    {
        System.out.println("We have got a mistake");
        e.getMessage();

    }

}





    @SuppressWarnings("deprecation")
    public void writeIntoExcel(@org.jetbrains.annotations.NotNull ArrayList<String> emaillist, String pathName) throws IOException {
        Workbook book = new HSSFWorkbook();
        Sheet sheet = book.createSheet("Mails");

        // Нумерация начинается с нуля
        Row row = sheet.createRow(0);


        Cell name = row.createCell(0);
        name.setCellValue("Emails");

        System.out.println("Size of array "+ emaillist.size());

        for (int i=0; i<emaillist.size(); i++){

            Row row1 = sheet.createRow(i+1);
            Cell name1 = row1.createCell(0);
            name1.setCellValue(emaillist.get(i));
            System.out.println("Write in Row: "+ (i+1)+" "+ emaillist.get(i));

        }

        // Записываем всё в файл
        File file = new File(pathName);
        FileOutputStream outFile = new FileOutputStream(file);
        book.write(outFile);
        System.out.println("Created file: " + file.getAbsolutePath());

    }


}



