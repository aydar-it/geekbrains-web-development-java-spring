package geekbrain.java.lesson2;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.Scanner;

public class MyFactory {
    public static EntityManager getEntityManager() {
        return Persistence.createEntityManagerFactory("geekbrains").createEntityManager();
    }

    public static Scanner getScanner() {
        return new Scanner(System.in);
    }

    public static Printer getPrinter() {
        return str -> System.out.println(str);
    }
}
