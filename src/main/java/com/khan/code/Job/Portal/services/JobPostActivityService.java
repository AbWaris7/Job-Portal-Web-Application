package com.khan.code.Job.Portal.services;


import com.khan.code.Job.Portal.entity.JobCompany;
import com.khan.code.Job.Portal.entity.JobLocation;
import com.khan.code.Job.Portal.entity.JobPostActivity;
import com.khan.code.Job.Portal.repository.IRecruiterJob;
import com.khan.code.Job.Portal.repository.JobPostActivityRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobPostActivityService {

    private final JobPostActivityRepository jobPostActivityRepository;


    public JobPostActivityService(JobPostActivityRepository jobPostActivityRepository) {
        this.jobPostActivityRepository = jobPostActivityRepository;
    }

    public JobPostActivity addNew(JobPostActivity jobPostActivity) {


        return jobPostActivityRepository.save(jobPostActivity);
    }

    public List<RecruiterJobsDto> getRecruiterJobs(int recruiter) {

       List<IRecruiterJob> recruiterJobs =  jobPostActivityRepository.getRecruiterJobs(recruiter);

       List<RecruiterJobsDto> recruiterJobsDtos = new ArrayList<>();
       for(IRecruiterJob rec : recruiterJobs) {

           JobLocation loc = new JobLocation(rec.getLocationId(), rec.getCity(), rec.getState(), rec.getCountry());
           JobCompany comp = new  JobCompany(rec.getCompanyId(), rec.getName(), "");
           recruiterJobsDtos.add(new RecruiterJobsDto(rec.getTotalCandidates(), rec.getJob_post_id(), rec.getJob_title(), loc, comp));
       }
       return recruiterJobsDtos;
    }
}
