package geekbrains.homework5.services;

import geekbrains.homework5.entity.User;
import geekbrains.homework5.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserService {
    private UserRepository repository;

    @Autowired
    public void setRepository(UserRepository repository) {
        this.repository = repository;
    }

    public Long save(User user) {
        return repository.save(user);
    }

    public User find(Long id) {
        return repository.find(id);
    }

    public List<User> getAll() {
        return repository.getAll();
    }
}
