import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Proxy {

    private String proxyHost;
    private String proxyPort;

    List<String> proxyList = new ArrayList<>();


    public Proxy(String filePath){   // proxy.txt

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)))){ // StandardCharsets.UTF_8
            String line;
            while ((line = reader.readLine()) != null) {
                proxyList.add(line);
            }
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла с прокси: " + filePath);
        }
    }

    public boolean setRandomProxy() {



        Random r = new Random();
        int indexOfProxyList = r.nextInt(proxyList.size()) ;
        boolean resultConnection = false;
        String httpSocksstatus;

        String proxyStrting = proxyList.get(indexOfProxyList);
        String[] proxyStrtingWords = proxyStrting.split("\t");
        proxyHost = proxyStrtingWords[0];
        proxyPort = proxyStrtingWords[1];
        httpSocksstatus = proxyStrtingWords[4];

        if (httpSocksstatus.equals("HTTP")) {

            System.setProperty("http.proxyHost", proxyHost);
            System.setProperty("http.proxyPort", proxyPort);
        } else {
            //System.setProperty("socksProxyHost", proxyHost);
            //System.setProperty("socksProxyPort", proxyPort);
        }

        try {

            URL url = new URL("https://www.tver-flowers.ru");
            URLConnection con = url.openConnection();//...
            Date current = new Date();
            long connectionTime =  current.getTime() - con.getDate() ;
            resultConnection = true;

            System.out.println("Установлен прокси:"+ proxyHost+":"+proxyPort + "Время ответа: " + current.getTime() +"time" +con.getDate());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Ошибка подключения к прокси: " + "");
        }
        //   Если мы позже сбросим соответствующие системные свойства вручную, прокси больше не будет использоваться
        //   System.setProperty("http.proxyHost", null);
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
