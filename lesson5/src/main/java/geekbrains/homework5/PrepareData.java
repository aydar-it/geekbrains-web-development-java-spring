package geekbrains.homework5;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class PrepareData {
    public static void forcePrepareData() {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
        Session session = null;
        try {
            String sqlStr = Files.lines(Paths.get("lesson5\\src\\main\\resources\\init.sql"))
                    .collect(Collectors.joining());
            session = factory.openSession();
            session.beginTransaction();
            session.createNativeQuery(sqlStr).executeUpdate();
            session.getTransaction().commit();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
            factory.close();
        }
    }
}
