package com.project.authentication.service;

import com.project.authentication.entity.DataUser;
import com.project.authentication.repository.DataUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Slf4j
@Service
public class UserService implements UserDetailsService {

    @Autowired
    private DataUserRepository dataUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<DataUser> dataUser = dataUserRepository.findByUsername(username);

        if(dataUser.isEmpty()){
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User
                (dataUser.get().getUsername(), dataUser.get().getPassword(),
                        new ArrayList<>());
    }
}
