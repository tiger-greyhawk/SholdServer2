package ru.rsoft.shold.backend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import ru.rsoft.shold.core.entity.User;
import ru.rsoft.shold.core.entity.UserRole;
import ru.rsoft.shold.core.repository.RoleRepository;
import ru.rsoft.shold.core.repository.UserRepository;
import ru.rsoft.shold.backend.security.UserService;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Admin on 27.02.2017.
 */
@Service
public class UserServiceImpl implements UserService {

    //@Autowired
    //private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set<UserRole> roles = new HashSet<>();
        roles.add(roleRepository.getOne(1L));
        user.setRoles(roles);
        user.setEnabled(true);
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
