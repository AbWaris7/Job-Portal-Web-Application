package com.khan.code.Job.Portal.services;

import com.khan.code.Job.Portal.entity.JobSeekerProfile;
import com.khan.code.Job.Portal.repository.JobSeekerProfileRepository;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class JobSeekerProfileService {

    private final JobSeekerProfileRepository jobSeekerProfileRepository;

    public JobSeekerProfileService(JobSeekerProfileRepository jobSeekerProfileRepository) {
        this.jobSeekerProfileRepository = jobSeekerProfileRepository;
    }

    public Optional<JobSeekerProfile> getOne(Integer id) {
        return jobSeekerProfileRepository.findById(id);
    }

    public JobSeekerProfile addNew(JobSeekerProfile jobSeekerProfile) {

        return jobSeekerProfileRepository.save(jobSeekerProfile);
    }
}
