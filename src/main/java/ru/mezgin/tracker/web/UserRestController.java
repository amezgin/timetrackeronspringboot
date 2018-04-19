package ru.mezgin.tracker.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.mezgin.tracker.model.User;
import ru.mezgin.tracker.repository.UserRepository;

import java.util.List;

@Controller
public class UserRestController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/users",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<User> getUsers() {
        List<User> list = this.userRepository.getAll();
        return list;
    }

    @RequestMapping(value = "/users/{userLogin}",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public User getUserByLogin(@PathVariable("userLogin") String userLogin) {
        return this.userRepository.getByLogin(userLogin);
    }

    @RequestMapping(value = "/users/{userId}",
            method = RequestMethod.DELETE,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public void deleteUser(@PathVariable("userId") Integer userId) {
        this.userRepository.delete(userId);
    }

    @RequestMapping(value = "/users",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public User createUser(@RequestBody User user) {
        return this.userRepository.save(user);
    }

    @RequestMapping(value = "/users",
            method = RequestMethod.PUT,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public User updateUser(@RequestBody User user) {
        System.out.println(user.getName());
        return this.userRepository.save(user);
    }
}
