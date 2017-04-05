package ru.rsoft.shold.backend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.rsoft.shold.core.dto.PlayerCreateDto;
import ru.rsoft.shold.core.dto.UserCreateDto;
import ru.rsoft.shold.core.entity.User;
import ru.rsoft.shold.core.repository.UserRepository;
//import ru.rsoft.shold.core.entity.Player;

import java.util.Collection;

/**
 * Created by Admin on 25.02.2017.
 */
//@Controller
//@Component
//@Transactional(propagation = Propagation.REQUIRED)
public class CustomJdbcDaoImpl  extends JdbcDaoImpl  implements IChangePassword {
/*
    @Autowired
    UserDetailsServiceImpl userDetailsServiceImpl;
*/
    //@Autowired
    //UserDetailsService userDetailsService;
    private UserRepository userRepository;

    @Autowired
    CustomJdbcDaoImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //CustomJdbcDaoImpl(){}

    public void myChangerPass(PlayerCreateDto playerCreateDto, String password){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        if (username == playerCreateDto.getNick())
        {
            changePassword(username, password);
        }
    }


    public void changePassword(String username, String password) {
//        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        //User user = userRepository.findByLogin(username);
//        User user = (User)userDetails;
//        user.setPassword(password);
        //userRepository.save(user);

        getJdbcTemplate().update("UPDATE USERS SET PASSWORD = ? WHERE USERNAME = ?",        password, username);
    }

    /*
    public UserDetails registration(UserCreateDto newUser) {

        UserDetails userDetails = new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return null;
            }

            @Override
            public String getPassword() {
                return null;
            }

            @Override
            public String getUsername() {
                return null;
            }

            @Override
            public boolean isAccountNonExpired() {
                return false;
            }

            @Override
            public boolean isAccountNonLocked() {
                return false;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return false;
            }

            @Override
            public boolean isEnabled() {
                return false;
            }
        };
        return userDetails;
    }*/

}
