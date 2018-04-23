package ru.mezgin.tracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.mezgin.tracker.model.User;
import ru.mezgin.tracker.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * The class UserDetailsServiceImpl.
 *
 * @author Alexander Mezgin
 * @version 1.0
 * @since 19.04.2018
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    /**
     * Repository of users.
     */
    private UserRepository userRepository;

    /**
     * The constructor.
     *
     * @param userRepository repository.
     */
    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.getByLogin(userName);

        if (user == null) {
            throw new UsernameNotFoundException("User " + userName + " was not found in the database");
        }

        String roleName = user.getRole().getName();

        List<GrantedAuthority> grantList = new ArrayList<>();
        grantList.add(new SimpleGrantedAuthority(roleName));
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), grantList);

        return userDetails;
    }
}
