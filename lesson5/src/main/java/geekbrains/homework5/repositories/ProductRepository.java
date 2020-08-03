package geekbrains.homework5.repositories;

import geekbrains.homework5.entity.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductRepository {
    private SessionFactory sf;

    @Autowired
    public void setSf(SessionFactory sf) {
        this.sf = sf;
    }

    public Long save(Product product) {
        Session session = sf.openSession();
        session.beginTransaction();
        Long id = (Long) session.save(product);
        session.getTransaction().commit();
        session.close();
        return id;
    }

    public Product find(Long id) {
        Session session = sf.openSession();
        session.beginTransaction();
        Product product = session.find(Product.class, id);
        session.getTransaction().commit();
        session.close();
        return product;
    }

    public List<Product> getAll() {
        Session session = sf.openSession();
        session.beginTransaction();
        List<Product> result = session.createQuery("from Product", Product.class).getResultList();
        session.getTransaction().commit();
        session.close();
        return result;
    }
}
