package geekbrain.java.lesson2;

import geekbrain.java.lesson2.entity.Bucket;
import geekbrain.java.lesson2.entity.Buyer;
import geekbrain.java.lesson2.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;
import java.util.StringTokenizer;

public class MainApp {

    public static void main(String[] args) {
        EntityManager em = null;
        try {
            em = MyFactory.getEntityManager();
            Dao dao = new Dao(em);
            MainApp.init(dao);
            MainApp.start(dao, MyFactory.getScanner(), MyFactory.getPrinter());
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public static void start(Dao dao, Scanner sc, Printer printer) {
        while (true) {
            StringTokenizer tokenizer = new StringTokenizer(sc.nextLine());
            if (!tokenizer.hasMoreTokens()) {
                break;
            }
            String inputStart = tokenizer.nextToken();
            if (inputStart.equals("/showProductsByConsumer")) {
                printer.print(dao.showProductsByConsumer(tokenizer.nextToken("\n").trim()).toString());
            } else if (inputStart.equals("/showConsumersByProductTitle")) {
                printer.print(dao.showConsumersByProductTitle(tokenizer.nextToken("\n").trim()).toString());
            } else if (inputStart.equals("/deleteConsumer")) {
                printer.print(String.valueOf(dao.deleteConsumer(tokenizer.nextToken("\n").trim())));
            } else if (inputStart.equals("/deleteProduct")) {
                printer.print(String.valueOf(dao.deleteProduct(tokenizer.nextToken("\n").trim())));
            } else if (inputStart.equals("/buy") && tokenizer.countTokens() == 2) {
                dao.buy(Long.parseLong(tokenizer.nextToken()), Long.parseLong(tokenizer.nextToken()));
            } else {
                break;
            }
        }
    }

    public static void init(Dao dao) {
        Buyer a = new Buyer("A");
        Buyer b = new Buyer("B");
        Buyer c = new Buyer("C");
        Product z = new Product("A", 10);
        Product x = new Product("B", 20);
        Product y = new Product("C", 30);
        dao.insertBuyer(a);
        dao.insertBuyer(b);
        dao.insertBuyer(c);
        dao.insertProduct(z);
        dao.insertProduct(x);
        dao.insertProduct(y);
        dao.insertBucket(new Bucket(a, z, z.getPresentValue()));
        dao.insertBucket(new Bucket(a, x, x.getPresentValue()));
        dao.insertBucket(new Bucket(a, y, y.getPresentValue()));
        dao.insertBucket(new Bucket(b, z, z.getPresentValue()));
        dao.insertBucket(new Bucket(b, x, x.getPresentValue()));
        dao.insertBucket(new Bucket(c, z, z.getPresentValue()));
    }
}
