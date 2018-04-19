package ru.mezgin.tracker.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * The class Status. This class describes a user action.
 *
 * @author Alexander Mezgin
 * @version 1.0
 * @since 17.04.2018
 */
@Entity
@Table(name = "statuses", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "date_time"}, name = "status_unique_user_datetime_idx")})
public class Status extends AbstractBaseEntity {

    /**
     * The creation date of the status.
     */
    @Column(name = "date_time", nullable = false)
    @NotNull
    private LocalDateTime dateTime;

    /**
     * If this is a new working day, it is set to true.
     */
    @Column(name = "start_new_work_day", nullable = false, columnDefinition = "bool default false")
    private boolean startNewWorkDay = false;

    /**
     * If this is an end working day, it is set to true.
     */
    @Column(name = "end_work_day", nullable = false, columnDefinition = "bool default false")
    private boolean endWorkDay = false;

    /**
     * The user.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull
    private User user;

    /**
     * The default constructor.
     */
    public Status() {
    }

    /**
     * The constructor.
     *
     * @param id              id.
     * @param name            name.
     * @param dateTime        date of create.
     * @param startNewWorkDay if it is a new working day, it is set to true otherwise false.
     * @param endWorkDay      if it is an end working day, it is set to true otherwise false.
     */
    public Status(Integer id, String name, LocalDateTime dateTime, boolean startNewWorkDay, boolean endWorkDay) {
        super(id, name);
        this.dateTime = dateTime;
        this.startNewWorkDay = startNewWorkDay;
        this.endWorkDay = endWorkDay;
    }

    /**
     * Get the creation date of the status.
     *
     * @return the creation date of the status.
     */
    public LocalDateTime getDateTime() {
        return this.dateTime;
    }

    /**
     * Set the creation date of the status.
     *
     * @param dateTime the creation date of the status.
     */
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * Verifies that it is the new work day.
     *
     * @return true if that is the new work day otherwise false.
     */
    public boolean isStartNewWorkDay() {
        return startNewWorkDay;
    }

    /**
     * Sets true if that is the new work day.
     *
     * @param startNewWorkDay true if that is the new work day.
     */
    public void setStartNewWorkDay(boolean startNewWorkDay) {
        this.startNewWorkDay = startNewWorkDay;
    }

    /**
     * Verifies that it is the end work day.
     *
     * @return true if that is the end work day otherwise false.
     */
    public boolean isEndWorkDay() {
        return endWorkDay;
    }

    /**
     * Sets true if that is the new work day.
     *
     * @param endWorkDay true if that is the end work day.
     */
    public void setEndWorkDay(boolean endWorkDay) {
        this.endWorkDay = endWorkDay;
    }

    /**
     * Get the user.
     *
     * @return the creation date of the status.
     */
    public User getUser() {
        return this.user;
    }

    /**
     * Sets the user.
     *
     * @param user user.
     */
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Status{"
                + ", id=" + id
                + ", name='" + name
                + "dateTime=" + dateTime
                + '}';
    }
}
