package ru.mezgin.tracker.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.mezgin.tracker.model.Status;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

/**
 * The class DataJpaStatusRepository.
 *
 * @author Alexander Mezgin
 * @version 1.0
 * @since 17.04.2018
 */
@Repository
public class DataJpaStatusRepository implements StatusRepository {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private CrudStatusRepository crudStatusRepository;

    @Autowired
    private CrudUserRepository crudUserRepository;

    @Override
    public Status save(Status status, int userId) {
        if (!status.isNew()) {
            return null;
        }
        status.setUser(this.crudUserRepository.getOne(userId));
        return this.crudStatusRepository.save(status);
    }

    public Status getLastStartWorkDay(int userId) {
        String sql = "SELECT s FROM Status s WHERE s.user.id=:userId AND s.startNewWorkDay=true ORDER BY s.dateTime DESC";
        Status result = (Status) this.entityManager.createQuery(sql)
                .setParameter("userId", userId)
                .setMaxResults(1)
                .getSingleResult();
        return result;
    }

    public Status getLastEndWorkDay(int userId) {
        String sql = "SELECT s FROM Status s WHERE s.user.id=:userId AND s.endWorkDay=true ORDER BY s.dateTime DESC";
        Status result = (Status) this.entityManager.createQuery(sql)
                .setParameter("userId", userId)
                .setMaxResults(1)
                .getSingleResult();
        return result;
    }

    public Status getLastActionUser(int userId) {
        String sql = "SELECT s FROM Status s WHERE s.user.id=:userId ORDER BY s.dateTime DESC";
        Status result = (Status) this.entityManager.createQuery(sql)
                .setParameter("userId", userId)
                .setMaxResults(1)
                .getSingleResult();
        return result;
    }

    @Override
    public List<Status> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return this.crudStatusRepository.getBetween(startDate, endDate, userId);
    }
}
