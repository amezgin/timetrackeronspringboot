package ru.mezgin.tracker.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.mezgin.tracker.model.Role;

/**
 * The class DataJpaRoleRepository.
 *
 * @author Alexander Mezgin
 * @version 1.0
 * @since 17.04.2018
 */
@Repository
public class DataJpaRoleRepository implements RoleRepository {

    @Autowired
    private CrudRoleRepository crudRoleRepository;

    @Override
    public Role get(int id) {
        return this.crudRoleRepository.findById(id).orElse(null);
    }

    @Override
    public Role get(String name) {
        return this.crudRoleRepository.getByName(name);
    }
}
