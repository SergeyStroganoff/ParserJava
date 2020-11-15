import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Proxy {

    private String proxyHost;
    private String proxyPort;
    Random r = new Random();

    List<String> proxyList = new ArrayList<>();


    public Proxy(String filePath) {   // proxy.txt

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)))) { // StandardCharsets.UTF_8
            String line;
            while ((line = reader.readLine()) != null) {
                proxyList.add(line);
            }
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла с прокси: " + filePath);
        }

    }

    public boolean setRandomProxy() {

        int indexOfProxyList = r.nextInt(proxyList.size());
        System.out.println("Индекс равен" + indexOfProxyList + "размер листа" + proxyList.size());
        boolean resultConnection = false;
        String httpSocksstatus;

        String proxyStrting = proxyList.get(indexOfProxyList);
        String[] proxyStrtingWords = proxyStrting.split("\t");
        proxyHost = proxyStrtingWords[0];
        proxyPort = proxyStrtingWords[1];
        httpSocksstatus = proxyStrtingWords[4];

        if (httpSocksstatus.equals("HTTP") || httpSocksstatus.equals("HTTP, HTTPS")) {
            // System.setProperty("socksProxyHost", "127.0.0.1");
            System.setProperty("http.proxyHost", proxyHost);
            System.setProperty("http.proxyPort", proxyPort);
            System.out.println("HTTP");


            try {

                URL url = new URL("https://www.tver-flowers.ru");
                Date current = new Date();
                URLConnection con = url.openConnection();//...
                if (con.getDate() <= 0) System.out.println(con.getDate());

                long connectionTime = con.getDate() - current.getTime();
                resultConnection = true;

                System.out.println("Установлен прокси:" + proxyHost + ":" + proxyPort + " Время ответа: " + connectionTime / 1000.0);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Ошибка подключения к прокси: " + "");
            }


        } else {
            System.out.println("SOCS");

            // System.setProperty("http.proxyHost", "127.0.0.1");
            // System.setProperty("socksProxyHost", proxyHost);
            // System.setProperty("socksProxyPort", proxyPort);
        }


        //   Если мы позже сбросим соответствующие системные свойства вручную, прокси больше не будет использоваться
       // System.setProperty("http.proxyHost", "");
        return resultConnection;
    }

    //public void removeProxy() {
    //    System.setProperty("http.proxyHost", );
    //}

    public void test() {

        for (String proxyStrting : proxyList) {

            String[] proxyStringWords = proxyStrting.split("\t");
            proxyHost = proxyStringWords[0];
            proxyPort = proxyStringWords[1];
            // System.out.println(proxyStrting);
            System.out.println(proxyHost + "+++++" + proxyPort);

        }
    }


}
