package com.khan.programmer.Job.Portal.services;

import com.khan.programmer.Job.Portal.entity.Users;
import com.khan.programmer.Job.Portal.repository.UsersRepository;
import com.khan.programmer.Job.Portal.util.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UsersRepository usersRepository;
    @Autowired
    public CustomUserDetailService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = usersRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Could not find user " + username));
        return new CustomUserDetails(user);
    }
}
