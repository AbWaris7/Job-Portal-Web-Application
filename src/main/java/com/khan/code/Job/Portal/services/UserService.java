package com.khan.code.Job.Portal.services;

import com.khan.code.Job.Portal.entity.JobSeekerProfile;
import com.khan.code.Job.Portal.entity.RecruiterProfile;
import com.khan.code.Job.Portal.entity.Users;
import com.khan.code.Job.Portal.repository.JobSeekerProfileRepository;
import com.khan.code.Job.Portal.repository.RecruiterProfileRepository;
import com.khan.code.Job.Portal.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UserService {

    private final UsersRepository usersRepository;
    private final JobSeekerProfileRepository jobSeekerProfileRepository;
    private final RecruiterProfileRepository recruiterProfileRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UsersRepository usersRepository, JobSeekerProfileRepository jobSeekerProfileRepository, RecruiterProfileRepository recruiterProfileRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.jobSeekerProfileRepository = jobSeekerProfileRepository;
        this.recruiterProfileRepository = recruiterProfileRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Users addNew(Users user) {
        user.setActive(true);
        user.setRegistrationDate(new Date(System.currentTimeMillis()));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Users savedUser = usersRepository.save(user);
        int userTypeId = user.getUserTypeId().getUserTypeId();

        if(userTypeId == 1) {
            recruiterProfileRepository.save(new RecruiterProfile(savedUser));
        } else {
            jobSeekerProfileRepository.save(new JobSeekerProfile(savedUser));
        }
        return savedUser;
    }

    public Optional<Users> getUserByEmail(String email) {

        return usersRepository.findByEmail(email);
    }

    public Object getcurrentUserProfile() {

       Authentication authentication= SecurityContextHolder.getContext().getAuthentication();

       if(!(authentication instanceof AnonymousAuthenticationToken)) {
           String username = authentication.getName();
         Users users =   usersRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
         int userId = users.getUserId();

         if(authentication.getAuthorities().contains(new SimpleGrantedAuthority("Recruiter"))) {

             RecruiterProfile recruiterProfile = recruiterProfileRepository.findById(userId).orElse( new RecruiterProfile());
             return recruiterProfile;

         } else  {
          JobSeekerProfile jobSeekerProfile =  jobSeekerProfileRepository.findById(userId).orElse(new JobSeekerProfile());
           return jobSeekerProfile;
         }

       }
       return null;
    }
}
