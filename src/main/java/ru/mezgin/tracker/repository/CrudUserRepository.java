package ru.mezgin.tracker.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.mezgin.tracker.model.User;

import java.util.List;
import java.util.Optional;

/**
 * The interface CrudUserRepository.
 *
 * @author Alexander Mezgin
 * @version 1.0
 * @since 17.04.2018
 */
@Transactional(readOnly = true)
public interface CrudUserRepository extends JpaRepository<User, Integer> {

    @Override
    @Transactional
    User save(User user);

    @Transactional
    @Modifying
    @Query("DELETE FROM User u WHERE u.id=:id")
    int delete(@Param("id") int id);

    @Override
    Optional<User> findById(Integer id);

    @Override
    List<User> findAll(Sort sort);

    User getByName(String name);
}