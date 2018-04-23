package ru.mezgin.tracker.model;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.IOException;

/**
 * The class Role.
 *
 * @author Alexander Mezgin
 * @version 1.0
 * @since 17.04.2018
 */
@Entity
@Table(name = "user_roles")
public class Role extends AbstractBaseEntity {

    /**
     * The default constructor.
     */
    public Role() {
    }

    /**
     * The constructor.
     *
     * @param id   id.
     * @param name name.
     */
    public Role(Integer id, String name) {
        super(id, name);
    }

    /**
     * The constructor for parsing json.
     *
     * @param json json.
     * @throws IOException exeption.
     */
    public Role(String json) throws IOException {
        Role role = new ObjectMapper().readValue(json, Role.class);
        this.id = role.id;
        this.name = role.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Role role = (Role) o;

        return name.equals(role.name);
    }

    @Override
    public int hashCode() {
        int result = id;
        return result;
    }

    @Override
    public String toString() {
        return "Role{"
                + "id=" + id
                + ", name=" + name
                + '}';
    }
}