package ru.mezgin.tracker.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.mezgin.tracker.model.Status;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
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

    /**
     * Entity manager.
     */
    private EntityManager entityManager;

    /**
     * Status repository.
     */
    private CrudStatusRepository crudStatusRepository;

    /**
     * User repository.
     */
    private CrudUserRepository crudUserRepository;

    /**
     * The constructor.
     *
     * @param entityManager        Entity manager.
     * @param crudStatusRepository Status repository.
     * @param crudUserRepository   User repository.
     */
    @Autowired
    public DataJpaStatusRepository(EntityManager entityManager, CrudStatusRepository crudStatusRepository, CrudUserRepository crudUserRepository) {
        this.entityManager = entityManager;
        this.crudStatusRepository = crudStatusRepository;
        this.crudUserRepository = crudUserRepository;
    }

    @Override
    public Status save(Status status, int userId) {
        if (!status.isNew()) {
            return null;
        }
        status.setUser(this.crudUserRepository.getOne(userId));
        return this.crudStatusRepository.save(status);
    }

    @Override
    public Status getLastStartWorkDay(int userId) {
        String hql = "SELECT s FROM Status s WHERE s.user.id=:userId AND s.startNewWorkDay=true ORDER BY s.dateTime DESC";
        Status result = (Status) this.entityManager.createQuery(hql)
                .setParameter("userId", userId)
                .setMaxResults(1)
                .getSingleResult();
        return result;
    }

    @Override
    public Status getLastEndWorkDay(int userId) {
        String hql = "SELECT s FROM Status s WHERE s.user.id=:userId AND s.endWorkDay=true ORDER BY s.dateTime DESC";
        Status result = (Status) this.entityManager.createQuery(hql)
                .setParameter("userId", userId)
                .setMaxResults(1)
                .getSingleResult();
        return result;
    }

    @Override
    public Status getLastActionUser(int userId) {
        String hql = "SELECT s FROM Status s WHERE s.user.id=:userId ORDER BY s.dateTime DESC";
        Query query = this.entityManager.createQuery(hql);
        Status result = null;
        try {
            result = (Status) query
                    .setParameter("userId", userId)
                    .setMaxResults(1)
                    .getSingleResult();
        } catch (NoResultException nre) {

        }
        return result;
    }

    @Override
    public List<Status> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return this.crudStatusRepository.getBetween(startDate, endDate, userId);
    }
}
