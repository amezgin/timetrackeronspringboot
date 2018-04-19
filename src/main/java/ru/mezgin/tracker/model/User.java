package ru.mezgin.tracker.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * The class User.
 *
 * @author Alexander Mezgin
 * @version 1.0
 * @since 17.04.2018
 */
@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = "name", name = "users_unique_name_idx")})
public class User extends AbstractBaseEntity {

    /**
     * Password.
     */
    @Column(name = "password", nullable = false)
    @NotBlank
    @Size(min = 5, max = 100)
    private String password;

    /**
     * Role.
     */
    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    @NotNull
    private Role role;

    /**
     * If the user is active, that setup true.
     */
    @Column(name = "enabled", nullable = false, columnDefinition = "bool default true")
    private boolean enabled = true;

    /**
     * The default constructor.
     */
    public User() {
    }

    /**
     * The constructor.
     *
     * @param id       id.
     * @param name     name.
     * @param password password.
     * @param role     role.
     * @param enabled  enabled.
     */
    public User(Integer id, String name, String password, Role role, boolean enabled) {
        super(id, name);
        this.password = password;
        this.role = role;
        this.enabled = enabled;
    }

    /**
     * Get the password.
     *
     * @return password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the password.
     *
     * @param password password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Get the role.
     *
     * @return role.
     */
    public Role getRole() {
        return this.role;
    }

    /**
     * Set the role.
     *
     * @param role role.
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * If the user is active, that setup true otherwise false.
     *
     * @return status the user.
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Set the user status.
     *
     * @param enabled If the user is active, that setup true otherwise false.
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;

        if (!name.equals(user.name)) {
            return false;
        }
        if (!password.equals(user.password)) {
            return false;
        }
        return enabled == user.enabled;
    }

    @Override
    public int hashCode() {
        int result = id;
        return result;
    }

    @Override
    public String toString() {
        return "User{"
                + "id=" + id
                + ", name=" + name
                + ", password=" + password
                + ", enabled=" + enabled
                + '}';
    }
}
