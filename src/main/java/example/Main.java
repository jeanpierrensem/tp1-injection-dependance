package example;

import core.ApplicationContext;

public class Main {
    public static void main(String[] args) {

        //Injection des dependances via le fichier xml de configuration des beans
        System.out.println("Injection de la dépendance ServiceB par fichier de configuration xml");
        ApplicationContext context =
                new ApplicationContext("beans.xml");
        ServiceB b = context.getBean(ServiceB.class);

        System.out.println(b.run());
    }
}