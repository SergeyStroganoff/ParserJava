import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ExceptionWorld {

    List<String> exceptionWorldList = new ArrayList<>();

    public ExceptionWorld(String filePath) {   // proxy.txt

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)))) { // StandardCharsets.UTF_8
            String line;
            while ((line = reader.readLine()) != null) {
                exceptionWorldList.add(line);
            }
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла слов исключений: " + filePath);
        }

    }


    public boolean isContainExceptionWorld(String checkedString) {
        boolean isContain = false;

        for (String curentString : exceptionWorldList) {

            isContain = checkedString.contains(curentString);

            if (isContain) return true;

        }
        return isContain;


    }


}
