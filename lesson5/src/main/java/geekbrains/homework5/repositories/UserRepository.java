package geekbrains.homework5.repositories;

import geekbrains.homework5.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserRepository {
    private SessionFactory sf;

    @Autowired
    public void setSf(SessionFactory sf) {
        this.sf = sf;
    }

    public Long save(User user) {
        Session session = sf.openSession();
        session.beginTransaction();
        Long id = (Long) session.save(user);
        session.getTransaction().commit();
        session.close();
        return id;
    }

    public User find(Long id) {
        Session session = sf.openSession();
        session.beginTransaction();
        User user = session.find(User.class, id);
        session.getTransaction().commit();
        session.close();
        return user;
    }

    public List<User> getAll() {
        Session session = sf.openSession();
        session.beginTransaction();
        List<User> result = session.createQuery("from User", User.class).getResultList();
        session.getTransaction().commit();
        session.close();
        return result;
    }
}
