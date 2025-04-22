package com.khan.code.Job.Portal.services;

import com.khan.code.Job.Portal.entity.JobSeekerProfile;
import com.khan.code.Job.Portal.entity.Users;
import com.khan.code.Job.Portal.repository.JobSeekerProfileRepository;
import com.khan.code.Job.Portal.repository.UsersRepository;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class JobSeekerProfileService {

    private final JobSeekerProfileRepository jobSeekerProfileRepository;
    private final UsersRepository usersRepository;

    public JobSeekerProfileService(JobSeekerProfileRepository jobSeekerProfileRepository, UsersRepository usersRepository) {
        this.jobSeekerProfileRepository = jobSeekerProfileRepository;
        this.usersRepository = usersRepository;
    }

    public Optional<JobSeekerProfile> getOne(Integer id) {
        return jobSeekerProfileRepository.findById(id);
    }

    public JobSeekerProfile addNew(JobSeekerProfile jobSeekerProfile) {

        return jobSeekerProfileRepository.save(jobSeekerProfile);
    }

    public JobSeekerProfile getCurrentSeekerProfile() {

       Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();

       if(!(authentication instanceof AnonymousAuthenticationToken)) {
           String currentUsername = authentication.getName();
         Users user =   usersRepository.findByEmail(currentUsername).orElseThrow(()->new UsernameNotFoundException("User Not Found"));

        Optional<JobSeekerProfile> seekerProfile =  getOne(user.getUserId());
        return seekerProfile.orElse(null);
       } else return null;

    }
}
