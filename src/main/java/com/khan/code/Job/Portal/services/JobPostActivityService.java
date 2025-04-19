package com.khan.code.Job.Portal.services;


import com.khan.code.Job.Portal.entity.JobPostActivity;
import com.khan.code.Job.Portal.repository.JobPostActivityRepository;
import org.springframework.stereotype.Service;

@Service
public class JobPostActivityService {

    private final JobPostActivityRepository jobPostActivityRepository;


    public JobPostActivityService(JobPostActivityRepository jobPostActivityRepository) {
        this.jobPostActivityRepository = jobPostActivityRepository;
    }

    public JobPostActivity addNew(JobPostActivity jobPostActivity) {


        return jobPostActivityRepository.save(jobPostActivity);
    }
}
