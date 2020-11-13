import java.util.Date;

public class Test {

    public static void main(String[] args) {


        Date date = new Date();
        System.out.println(date.getTime());

        Proxy superProxy = new Proxy("M:\\Програмирование\\MyProjects\\ParserHTML\\proxy1.txt");
        superProxy.test();

        for (int i=0; i<360; i++){

            superProxy.setRandomProxy();
        }






    }


}
