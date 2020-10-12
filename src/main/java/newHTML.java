import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;


public class newHTML {

    String adresshtml; // adress site
    Document doc;


    public newHTML(String adresshtml) {
        this.adresshtml = adresshtml;


        try {

            if (adresshtml.contains("http")) {

                this.doc = Jsoup.connect(adresshtml).get();

            } else {

                this.doc = Jsoup.parse(new File(adresshtml), "ISO-8859-1"); //"M:\\tver.jsprav.htm"
            }

        } catch (IOException e) {
            System.out.println("Incorect adres of html");
        } catch (IllegalArgumentException e) {
            System.out.println("Incorect adress of link" + e.toString());
        }

    }


/* set and get

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
*/


    public void FindEmail(String cssQuery, ArrayList<String> emaillist) throws NullPointerException {

        // Elements extends ArrayList<Element>.

        System.out.println("Заходим в процедуру поиска емайл ");
        Elements aElements = doc.select(cssQuery); // a[href*=mailto], "span.value.email"
        for (Element aElement : aElements) {
            //   String atribute = aElement.attr();
            String val = aElement.val();
            String text = aElement.text();

            // System.out.println("Нашли атрибут емел: " + atribute); // выводим ссылки
            System.out.println("Нашли текст емел: " + text); // выводим ссылки
            //   System.out.println("Нашли значение емел: " + val); // выводим ссылки
            emaillist.add(aElement.text());
        }

    }


    public void FindAllUrl(String site, Map<String, String> Bigmap, Map<String, String> smallmap) throws NullPointerException {


        Elements aElements = doc.getElementsByTag("a");

        for (Element aElement : aElements) {
            String href = aElement.attr("href");

            String text = aElement.text();

            // System.out.println( "Нашли ссылку: " + href); // выводим ссылки

            /// Заносим все ссылки со страницы в массив array


            String adrrSite;
            if (!href.contains("http")) {
                adrrSite = site + href;
            } else {
                adrrSite = href;
            }

            //   System.out.println( "Скомпоновали  ссылку: " + adrrSite);


            if (!smallmap.containsKey(adrrSite)) { // основное действие метода
                if (!Bigmap.containsKey(adrrSite)) {

                    smallmap.put(adrrSite, "-");
                    System.out.println("Перенесли ссылку в глобальный массив: " + adrrSite);
                }
            }// если в мапе нет ключа то вносим адрес !!!


        }
    }

}


////////////////////


