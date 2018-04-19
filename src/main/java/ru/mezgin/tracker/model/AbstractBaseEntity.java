package ru.mezgin.tracker.model;

import org.springframework.data.domain.Persistable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * The abstract class AbstractBaseEntity.
 *
 * @author Alexander Mezgin
 * @version 1.0
 * @since 17.04.2018
 */
@MappedSuperclass
public abstract class AbstractBaseEntity implements Persistable<Integer> {

    /**
     * Id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    protected Integer id;

    /**
     * Name.
     */
    @NotBlank
    @Size(min = 2, max = 100)
    @Column(name = "name", nullable = false)
    protected String name;

    /**
     * The default constructor.
     */
    protected AbstractBaseEntity() {
    }

    /**
     * The constructor.
     *
     * @param id   id.
     * @param name name.
     */
    protected AbstractBaseEntity(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean isNew() {
        return this.id == null;
    }

    @Override
    public Integer getId() {
        return this.id;
    }

    /**
     * Set id.
     *
     * @param id id.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Get name.
     *
     * @return name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Set name.
     *
     * @param name name.
     */
    public void setName(String name) {
        this.name = name;
    }
}
