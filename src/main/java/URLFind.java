import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class URLFind {   // метод поска ссылок и занесения в мапу с рекурсией


    public static void GetHrefUrl(String site, Map<String, String> urrmap, ArrayList<String> emaillist)  {

// надо абстракцировать на более мелкие части, делать еще одну процедуру которая будет вызывать сама себя тут оставлять тольк
//  результат новую ссылку - проверку ссылки делать в новой процедуре

        try {
            Thread.sleep(1000); // пауза для избегания блокировок!!!!!!!!!!!!!!!!!

            Document doc = Jsoup.connect(site).get();

            // Elements extends ArrayList<Element>.
            Elements aElements = doc.getElementsByTag("a");

            for (Element aElement : aElements) {
                String href = aElement.attr("href");

                String text = aElement.text();

                System.out.println( "Нашли ссылку: " + href); // выводим ссылки

                /// Заносим все ссылки со страницы в массив array

                String adrrSite;
                if (href.contains("http")) {adrrSite =  href; }
                else {adrrSite ="https://tver.jsprav.ru" +  href;}//

                System.out.println( "Скомпоновали  ссылку: " + adrrSite);

                if (!urrmap.containsKey(adrrSite)){ // основное действие метода

                    urrmap.put(adrrSite, "-");
                    System.out.println( "Занесли ссыку: " + adrrSite);
                }  // если в мапе нет ключа то вносим адрес !!!

/////////////////////////////////////////////////////////////////////////

                String oldvalue = urrmap.get(adrrSite);
                System.out.println("Определели значение ссылки в мапе "+ oldvalue);

                if (oldvalue.equals("-")) // идем на рекурсию
                {
                    urrmap.replace(adrrSite, "+");
                    System.out.println("Новое значение ссылки в мапе "+ urrmap.get(adrrSite));
                    //   GetHrefUrl(adrrSite,urrmap); // рекурсия вызываем сами себя параметром найденой ссылки
                    System.out.println(adrrSite);
                }

            }

            // for (String hrefs : biglistUrl){

        }

        catch (IOException e )
        {
            System.out.println("Несуществующая или битая ссылка"+ e.getMessage());
        }


        catch (InterruptedException e) {
            e.printStackTrace();
        }



    }



}
