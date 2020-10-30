import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import static java.lang.System.out;


public class newHTML {

    String adressOfHtml; // adress site
    Document doc;

    public newHTML(String adressOfHtml, Exel newExel) {
        this.adressOfHtml = adressOfHtml;

        try {
            if (adressOfHtml.contains("http")) {
                this.doc = Jsoup.connect(adressOfHtml)
                        .userAgent("Mozilla/5.0 Chrome/26.0.1410.64 Safari/537.31")
                        // .ignoreHttpErrors(true)
                        .maxBodySize(1024*1024*3)
                        .followRedirects(true)
                        .timeout(100000)
                        .ignoreContentType(true)
                        .get();
            } else {
                this.doc = Jsoup.parse(new File(adressOfHtml), "ISO-8859-1"); //"M:\\tver.jsprav.htm"
            }
        } catch (IOException e) {
            System.out.println("Incorect adres of html" + e.toString());
            if (e.toString().contains("timed") || e.toString().contains("503")) { //Connection timed out: no further information))
                out.println("Зафиксировали бееее ! от сервера");
                newExel.saveExel();
                System.exit(0);
            };
        }

    }

    public void findEmailOnPageAndWrite(String cssQuery, Exel newExel) throws NullPointerException { // необходимо разбити выдаватпросто искомый элемент

        // Elements extends ArrayList<Element>.

        System.out.println("Заходим в процедуру поиска емайл ");
        Elements aElements = doc.select(cssQuery); // a[href*=mailto], "span.value.email"
        for (Element aElement : aElements) {
            //   String atribute = aElement.attr();
            String val = aElement.val();
            String text = aElement.text();
            newExel.writeEmailStringIntoExellFile(text);
        }

    }



    public void findEmailonPage(String cssQuery, ArrayList<String> listOfEmail) throws NullPointerException { // необходимо разбити выдаватпросто искомый элемент

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
            listOfEmail.add(aElement.text());
        }

    }


    public void FindAllUrl(String site, Map<String, String> bigMapOfHref, Map<String, String> smallmap) throws NullPointerException {


        Elements aElements = doc.getElementsByTag("a");

        for (Element aElement : aElements) {
            String href = aElement.attr("href");
            String text = aElement.text();

//             System.out.println( "Нашли ссылку: " + href); // выводим ссылки
//             Заносим все ссылки со страницы в массив array

            href = href.contains("http") ? href : site + href;
            if (!smallmap.containsKey(href)) { // основное действие метода
                if (!bigMapOfHref.containsKey(href)) {
                    smallmap.put(href, "-");
                    System.out.println("Перенесли ссылку в глобальный массив: " + href);
                }
            }   // если в мапе нет ключа то вносим адрес !!!


        }
    }

}



