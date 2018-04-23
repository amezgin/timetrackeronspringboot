package ru.mezgin.tracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mezgin.tracker.model.Role;

import java.util.Optional;

/**
 * The interface CrudRoleRepository.
 *
 * @author Alexander Mezgin
 * @version 1.0
 * @since 17.04.2018
 */
public interface CrudRoleRepository extends JpaRepository<Role, Integer> {

    @Override
    Optional<Role> findById(Integer id);

    /**
     * Returns the searched role by the name.
     *
     * @param name the role name.
     * @return role.
     */
    Role getByName(String name);
}
