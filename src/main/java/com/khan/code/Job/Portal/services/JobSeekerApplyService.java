package com.khan.code.Job.Portal.services;

import com.khan.code.Job.Portal.entity.JobPostActivity;
import com.khan.code.Job.Portal.entity.JobSeekerApply;
import com.khan.code.Job.Portal.entity.JobSeekerProfile;
import com.khan.code.Job.Portal.repository.JobSeekerApplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobSeekerApplyService {


    private final JobSeekerApplyRepository jobSeekerApplyRepository;

    @Autowired
    public JobSeekerApplyService(JobSeekerApplyRepository jobSeekerApplyRepository) {
        this.jobSeekerApplyRepository = jobSeekerApplyRepository;
    }

    public List<JobSeekerApply> getCandidatesJobs(JobSeekerProfile userAccountId) {

        return jobSeekerApplyRepository.findByUserId(userAccountId);
    }

    public List<JobSeekerApply> getJobCandidates(JobPostActivity job) {

        return jobSeekerApplyRepository.findByJob(job);
    }
}
