package ru.mezgin.tracker.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.mezgin.tracker.model.User;
import ru.mezgin.tracker.repository.UserRepository;

import java.util.List;

/**
 * The class UserRestController.
 *
 * @author Alexander Mezgin
 * @version 1.0
 * @since 19.04.2018
 */
@RestController
public class UserRestController {

    /**
     * User repository.
     */
    private UserRepository userRepository;

    /**
     * The constructor.
     *
     * @param userRepository user repository.
     */
    @Autowired
    public UserRestController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * The mapping for path "/admins" (GET).
     *
     * @return list al users.
     */
    @RequestMapping(value = "/admins",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<User> getUsers() {
        return this.userRepository.getAll();
    }

    /**
     * The mapping for path "/admins/{userLogin}" (GET).
     *
     * @param userLogin user login.
     * @return user.
     */
    @RequestMapping(value = "/admins/{userLogin}",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public User getUserByLogin(@PathVariable("userLogin") String userLogin) {
        return this.userRepository.getByLogin(userLogin);
    }

    /**
     * The mapping for path "/admins/{userId}" (DELETE).
     *
     * @param userId user ID.
     */
    @RequestMapping(value = "/admins/{userId}",
            method = RequestMethod.DELETE,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public void deleteUser(@PathVariable("userId") Integer userId) {
        this.userRepository.delete(userId);
    }

    /**
     * The mapping for path "/admins" (POST).
     *
     * @param user user.
     * @return saved user.
     */
    @RequestMapping(value = "/admins",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public User createUser(@RequestBody User user) {
        return this.userRepository.save(user);
    }

    /**
     * The mapping for path "/admins" (PUT).
     *
     * @param user user.
     * @return updated user.
     */
    @RequestMapping(value = "/admins",
            method = RequestMethod.PUT,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public User updateUser(@RequestBody User user) {
        return this.userRepository.save(user);
    }
}
