package ru.mezgin.tracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.mezgin.tracker.model.Status;

import java.time.LocalDateTime;
import java.util.List;

/**
 * The interface CrudStatusRepository.
 *
 * @author Alexander Mezgin
 * @version 1.0
 * @since 17.04.2018
 */
@Transactional(readOnly = true)
public interface CrudStatusRepository extends JpaRepository<Status, Integer> {

    @Override
    @Transactional
    Status save(Status status);

    /**
     * Returns a list of recent user actions between specific dates. The startDate  and the endDate must not be null.
     * The list is ordered date time by desc.
     *
     * @param startDate the date of start period.
     * @param endDate   the date of end period.
     * @param userId    the user ID.
     * @return a list of recent user actions.
     */
    @SuppressWarnings("JpaQlInspection")
    @Query("SELECT s FROM Status s WHERE s.user.id=:userId AND s.dateTime BETWEEN :startDate AND :endDate ORDER BY s.dateTime DESC")
    List<Status> getBetween(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate, @Param("userId") int userId);
}
