package com.khan.programmer.Job.Portal.services;
import com.khan.programmer.Job.Portal.entity.JobSeekerProfile;
import com.khan.programmer.Job.Portal.entity.RecruiterProfile;
import com.khan.programmer.Job.Portal.entity.Users;
import com.khan.programmer.Job.Portal.repository.JobSeekerProfileRepository;
import com.khan.programmer.Job.Portal.repository.RecruiterProfileRepository;
import com.khan.programmer.Job.Portal.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.Optional;

@Service
public class UserService {


    private final UsersRepository usersRepository;
    private final JobSeekerProfileRepository jobSeekerProfileRepository;
    private final RecruiterProfileRepository recruiterProfileRepository;


    @Autowired
    public UserService(UsersRepository usersRepository, JobSeekerProfileRepository jobSeekerProfileRepository, RecruiterProfileRepository recruiterProfileRepository) {
        this.usersRepository = usersRepository;
        this.jobSeekerProfileRepository = jobSeekerProfileRepository;
        this.recruiterProfileRepository = recruiterProfileRepository;
    }

    public Users addNew(Users users) {
        users.setActive(true);
        users.setRegistrationDate(new Date(System.currentTimeMillis()));
        Users savedUser  = usersRepository.save(users);
        int userTypeId = users.getUserTypeId().getUserTypeId();
        if (userTypeId == 1) {
            recruiterProfileRepository.save(new RecruiterProfile(savedUser));
        } else if (userTypeId == 2) {
            jobSeekerProfileRepository.save(new JobSeekerProfile(savedUser));
        }

        return savedUser;
    }
    public Optional<Users> getUserByEmail(String email) {
        return usersRepository.findByEmail(email);
    }
}
