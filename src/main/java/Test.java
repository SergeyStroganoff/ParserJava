import java.util.Date;

public class Test {

    public static void main(String[] args) {



        Proxy superProxy = new Proxy("M:\\Програмирование\\MyProjects\\ParserHTML\\proxy1.txt");
        superProxy.test();

        for (int i=0; i<60; i++){

            System.out.println(superProxy.setRandomProxy());
        }






    }


}
