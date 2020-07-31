package geekbrain.java.lesson2;

import javax.persistence.EntityManager;
import java.util.Scanner;
import java.util.StringTokenizer;

public class MainApp {

    public static void main(String[] args) {
        EntityManager em = null;
        try {
            em = MyFactory.getEntityManager();
            MainApp.start(new Dao(em), MyFactory.getScanner(), MyFactory.getPrinter());
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
}
