package ru.rsoft.shold.backend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.rsoft.shold.core.entity.User;
import ru.rsoft.shold.core.entity.UserRole;
import ru.rsoft.shold.core.repository.UserRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Admin on 26.02.2017.
 */
@Component
@Transactional(propagation = Propagation.REQUIRED)
public class UserDetailsServiceImpl implements UserDetailsService {

    //@Autowired
    private final UserRepository userRepository;


    @Autowired
    UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

/*
    UserDetailsServiceImpl(){}
*/
    @Override
    //@Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        for (UserRole userRole : user.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(userRole.getName()));
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }
}
