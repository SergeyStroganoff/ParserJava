
import java.io.IOException;
import java.util.*;


public class parser {



    public static void main(String[] args)  {

        ArrayList<String> emaillist = new ArrayList<String>();
        String  site = "https://lihoslavl.jsprav.ru";// "M:\\tver.jsprav.htm"; //"https://tver.jsprav.ru";

        Map<String, String> Bigmap = new HashMap<>();
        Map<String, String> smallmap = new HashMap<>();

        Bigmap.put(site,"-"); // инициализируем большой map адресом сайта и значением непроверенной сыылки



        while (Bigmap.containsValue("-")) {

            try {
                Thread.sleep(10000);} // пауза !!!!!!!!!!!!!!!!!
                catch (InterruptedException e) {
                    e.printStackTrace();
                }


            Iterator<Map.Entry<String, String>> iterator = Bigmap.entrySet().iterator();
            while (iterator.hasNext()) {
                //получение «пары» элементов
                Map.Entry<String, String> pair = iterator.next();
                String key = pair.getKey();            //ключ - адрес сайта или страницы
                String value = pair.getValue();        //значение


               if (value.equals("-")  && key.contains(site)) { // переходим по ссылке только внутри заданного сайта и если еще не отработана

                System.out.println("Переходим по ссылке: " +key+ "Со значением " + value);
                newHTML newPage = new newHTML(key);
                try {
                    newPage.FindEmail("span.value.email",emaillist); // извлекаем емайл и помещаем в масив emaillist
                    newPage.FindEmail("a[href*=mailto]",emaillist);
                } catch (NullPointerException e) {
                    System.out.println("Ошибка поиска email");
                }

                Bigmap.replace(key, "+");                              // заменяем значение на отработанное
                System.out.println("Новое значение ссылки в мапе " + Bigmap.get(key));


                    try {
                        newPage.FindAllUrl(site, Bigmap,smallmap);        //   GetHrefUrl(adrrSite,urrmap);
                    }
                    catch (NullPointerException e) {
                        System.out.println("Ошибка поиска ссылок на странице NullPointerException ");
                    }


                    System.out.println(key);

                } else {    Bigmap.replace(key, "+");   }


            }
            Bigmap.putAll(smallmap);
            smallmap.clear();
        }



        // пишем емыйлы в файл



            System.out.println("Write to file");
            Exel newExel = new Exel("D:\\mail.xls");
            newExel.WriteArrayInExell(emaillist);
            newExel.saveExel();

            //writeIntoExcel(emaillist,"D:\\mail.xls" );
            System.out.println("Finish");

        }




    }








