package geekbrain.java.lesson3;

import geekbrain.java.lesson3.entity.Lot;
import geekbrain.java.lesson3.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.LockModeType;
import javax.persistence.OptimisticLockException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

import static java.lang.Thread.sleep;

public class MainApp {
    private static Long i = 1L;
    private final Object lock = new Object();
    private final Random random = new Random();

    public Long getI() {
        synchronized (lock) {
            return i++;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SessionFactory sf = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
        System.out.println(new MainApp().startOptimist(sf)); //3571
//        System.out.println(new MainApp().startPessimist(sf)); // 2800
    }

    private long startPessimist(SessionFactory sf) throws InterruptedException {
        PrepareData.forcePrepareData();

        Callable<Integer> callable = () -> {
            Session ses = sf.openSession();
            ses.getTransaction().begin();
            User user = ses.find(User.class, getI());
            ses.getTransaction().commit();
            ses.close();
            for (int i = 0; i < 1000; i++) {
                try (Session session = sf.openSession()) {
                    session.beginTransaction();
                    Lot lot = session.createQuery("FROM Lot l WHERE l.id = :id", Lot.class)
                            .setParameter("id", (long) random.nextInt(4) + 1)
                            .setLockMode(LockModeType.PESSIMISTIC_WRITE)
                            .getSingleResult();
                    lot.setBet(lot.getBet() + 100);
                    lot.setUser(user);
                    session.save(lot);
                    session.getTransaction().commit();
                    sleep(1);
                }
            }
            return 0;
        };
        ExecutorService es = Executors.newFixedThreadPool(8);
        List<Callable<Integer>> list = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            list.add(callable);
        }
        long time = new Date().getTime();
        es.invokeAll(list);
        es.shutdown();
        return new Date().getTime() - time;
    }

    private long startOptimist(SessionFactory sf) throws InterruptedException {
        PrepareData.forcePrepareData();

        Callable<Integer> callable = () -> {
            Session ses = sf.openSession();
            ses.getTransaction().begin();
            User user = ses.find(User.class, getI());
            ses.getTransaction().commit();
            ses.close();
            for (int i = 0; i < 1000; i++) {
                try (Session session = sf.openSession()) {
                    session.beginTransaction();
                    Lot lot = session.get(Lot.class, (long) random.nextInt(4) + 1);
                    lot.setBet(lot.getBet() + 100);
                    lot.setUser(user);
                    try {
                        session.save(lot);
                        session.getTransaction().commit();
                    } catch (OptimisticLockException e) {
                        i--;
                        session.getTransaction().rollback();
                    }
                }
                sleep(1);
            }
            return 0;
        };
        ExecutorService es = Executors.newFixedThreadPool(8);
        List<Callable<Integer>> list = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            list.add(callable);
        }
        long time = new Date().getTime();
        es.invokeAll(list);
        es.shutdown();
        return new Date().getTime() - time;
    }
}
