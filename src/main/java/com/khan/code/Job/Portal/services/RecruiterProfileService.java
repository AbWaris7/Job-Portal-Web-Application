package com.khan.code.Job.Portal.services;

import com.khan.code.Job.Portal.entity.RecruiterProfile;
import com.khan.code.Job.Portal.entity.Users;
import com.khan.code.Job.Portal.repository.RecruiterProfileRepository;
import com.khan.code.Job.Portal.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RecruiterProfileService {

    private final RecruiterProfileRepository recruiterProfileRepository;
    private final UsersRepository usersRepository;
    @Autowired
    public RecruiterProfileService(RecruiterProfileRepository recruiterProfileRepository, UsersRepository usersRepository) {
        this.recruiterProfileRepository = recruiterProfileRepository;
        this.usersRepository = usersRepository;
    }

    public Optional<RecruiterProfile> getOne(Integer id) {
        return recruiterProfileRepository.findById(id);
    }

    public RecruiterProfile addNew(RecruiterProfile recruiterProfile) {
        return recruiterProfileRepository.save(recruiterProfile);

    }

    public RecruiterProfile getCurrentRecruiterProfile() {

       Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
       if(!(authentication instanceof AnonymousAuthenticationToken)) {
           String currentUsername = authentication.getName();
          Users users =  usersRepository.findByEmail(currentUsername).orElseThrow(()->new RuntimeException("User Not Found"));

          Optional<RecruiterProfile> recruiterProfile =  getOne(users.getUserId());
          return recruiterProfile.orElse(null);
       } else return null;
    }
}
