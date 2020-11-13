import java.util.*;


public class Parser {

    public static Set<String> listOfALLEmails = new HashSet<>();


    public static void main(String[] args) {


        String site =  "https://lihoslavl.jsprav.ru";   //"https://lihoslavl.jsprav.ru";// "M:\\tver.jsprav.htm"; //"https://tver.jsprav.ru";

        Map<String, String> bigMapOfHref = new HashMap<>();
        Map<String, String> smallMapOfHref = new HashMap<>();

        bigMapOfHref.put(site, "-"); // инициализируем большой map адресом сайта и значением непроверенной сыылки


        Date curentData=new Date();
        final String filePathname = "D:\\mail" + curentData.getTime()+".xls";
        Exel newExel = new Exel(filePathname); // инициализируем  новый файл эксель

        Random random = new Random();
        while (bigMapOfHref.containsValue("-")) {

            try {
                int nanosecundToSleep = random.nextInt(10) ;
                Thread.sleep(nanosecundToSleep);// случайная пауза !!!!!!!!!!!!!!!!!
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }

            Iterator<Map.Entry<String, String>> iterator = bigMapOfHref.entrySet().iterator();
            while (iterator.hasNext()) {
                //получение «пары» элементов
                Map.Entry<String, String> pair = iterator.next();
                String key = pair.getKey();            //ключ - адрес сайта или страницы
                String value = pair.getValue();        //значение

                if (value.equals("-") && key.contains(site) ) { //  && key.contains(site) ---     переходим по ссылке только внутри заданного сайта и если еще не отработана

                    System.out.println("Переходим по ссылке: " + key + "Со значением " + value);
                    newHTML newPage = new newHTML(key, newExel);
                    try {

                        ///////////////////TODO


                       newPage.findEmailonPage("span.value.email", listOfALLEmails); // извлекаем емайл и помещаем в масив listOfEmailsOnCurrentPage// fuyfg
                       newPage.findEmailonPage("a[href*=mailto]", listOfALLEmails);
//                     newPage.findEmailOnPageAndWrite("span.value.email",newExel);      // пишим найденный емейл сразу же в файл эксель

                    } catch (NullPointerException e) {
                        System.out.println("Ошибка поиска email");
                    }

                    System.out.println("Новое значение ссылки в мапе " + bigMapOfHref.get(key));

                    try {
                        newPage.FindAllUrl(site, bigMapOfHref, smallMapOfHref);        //   GetHrefUrl(adrrSite,urrmap);
                    } catch (NullPointerException e) {
                        System.out.println("Ошибка поиска ссылок на странице NullPointerException ");
                    }
                    System.out.println(key);
                }

                bigMapOfHref.replace(key, "+"); // заменяем значение на отработанное
            }
            bigMapOfHref.putAll(smallMapOfHref);
            smallMapOfHref.clear();
        }


        // пишем емыйлы в файл
        newExel.writeArrayInExell(listOfALLEmails);
        newExel.saveExel();
        System.out.println("Finish");

    }


}








