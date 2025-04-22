package com.khan.code.Job.Portal.services;

import com.khan.code.Job.Portal.entity.JobPostActivity;
import com.khan.code.Job.Portal.entity.JobSeekerProfile;
import com.khan.code.Job.Portal.entity.JobSeekerSave;
import com.khan.code.Job.Portal.repository.JobSeekerSaveRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobSeekerSaveService {

    private final JobSeekerSaveRepository jobSeekerSaveRepository;

    public JobSeekerSaveService(JobSeekerSaveRepository jobSeekerSaveRepository) {
        this.jobSeekerSaveRepository = jobSeekerSaveRepository;
    }

    public List<JobSeekerSave> getCandidatesJob(JobSeekerProfile userAccountId) {
        return jobSeekerSaveRepository.findByUserId(userAccountId);
    }


    public List<JobSeekerSave> getJobCandidates(JobPostActivity jobPostActivity) {
        return jobSeekerSaveRepository.findByJob(jobPostActivity);
    }

    public void addNew(JobSeekerSave jobSeekerSave) {

        jobSeekerSaveRepository.save(jobSeekerSave);
    }
}
