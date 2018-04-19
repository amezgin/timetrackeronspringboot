package ru.mezgin.tracker.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.mezgin.tracker.model.User;

import java.util.List;

/**
 * The class DataJpaUserRepository.
 *
 * @author Alexander Mezgin
 * @version 1.0
 * @since 17.04.2018
 */
@Repository
public class DataJpaUserRepository implements UserRepository {

    private static final Sort SORT_USER_ASC = new Sort(Sort.Direction.ASC, "name");

    @Autowired
    private CrudUserRepository crudUserRepository;

    @Override
    public User save(User user) {
        return this.crudUserRepository.save(user);
    }

    @Override
    public int delete(int id) {
        return this.crudUserRepository.delete(id);
    }

    @Override
    public User get(int id) {
        return this.crudUserRepository.findById(id).orElse(null);
    }

    @Override
    public User getByLogin(String login) {
        return this.crudUserRepository.getByName(login);
    }

    @Override
    public List<User> getAll() {
        return this.crudUserRepository.findAll(SORT_USER_ASC);
    }
}
