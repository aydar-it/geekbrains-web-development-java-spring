package geekbrain.java.lesson2;

import geekbrain.java.lesson2.entity.Bucket;
import geekbrain.java.lesson2.entity.Buyer;
import geekbrain.java.lesson2.entity.Product;

import javax.persistence.EntityManager;
import java.util.List;

public class Dao {
    private final EntityManager em;

    public Dao(EntityManager em) {
        this.em = em;
    }

    public void insertBuyer(Buyer buyer) {
        em.getTransaction().begin();
        em.persist(buyer);
        em.getTransaction().commit();
    }

    public void insertProduct(Product product) {
        em.getTransaction().begin();
        em.persist(product);
        em.getTransaction().commit();
    }

    public void insertBucket(Bucket bucket) {
        em.getTransaction().begin();
        em.persist(bucket);
        em.getTransaction().commit();
    }

    public List<Product> showProductsByConsumer(String name) {
        return em.createQuery("select product from Bucket b where b.buyer.name = :name", Product.class)
                .setParameter("name", name)
                .getResultList();
    }

    public List<Buyer> showConsumersByProductTitle(String name) {
        return em.createQuery("select buyer from Bucket b where b.product.name = :name", Buyer.class)
                .setParameter("name", name)
                .getResultList();
    }

    public int deleteConsumer(String name) {
        em.getTransaction().begin();
        int res = em.createQuery("delete from Buyer where name = :name")
                .setParameter("name", name)
                .executeUpdate();
        em.getTransaction().commit();
        return res;
    }

    public int deleteProduct(String name) {
        em.getTransaction().begin();
        int res = em.createQuery("delete from Product where name = :name")
                .setParameter("name", name)
                .executeUpdate();
        em.getTransaction().commit();
        return res;
    }

    public void buy(long idBuyer, long idProduct) {
        em.getTransaction().begin();
        Buyer buyer = em.find(Buyer.class, idBuyer);
        Product product = em.find(Product.class, idProduct);
        if (buyer == null || product == null) {
            throw new RuntimeException("Non-existent buyer or product");
        }
        em.persist(new Bucket(buyer, product, product.getPresentValue()));
        em.getTransaction().commit();
    }
}
