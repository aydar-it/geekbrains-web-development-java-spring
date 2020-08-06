package geekbrains.homework5.services;

import geekbrains.homework5.entity.Product;
import geekbrains.homework5.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductService {
    private ProductRepository repository;

    @Autowired
    public void setRepository(ProductRepository repository) {
        this.repository = repository;
    }

    public Long save(Product product) {
        return repository.save(product);
    }

    public Product find(Long id) {
        return repository.find(id);
    }

    public List<Product> getAll() {
        return repository.getAll();
    }
}
