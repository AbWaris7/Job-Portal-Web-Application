package com.khan.code.Job.Portal.repository;


import com.khan.code.Job.Portal.entity.JobPostActivity;
import com.khan.code.Job.Portal.entity.JobSeekerProfile;
import com.khan.code.Job.Portal.entity.JobSeekerSave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobSeekerSaveRepository extends JpaRepository<JobSeekerSave, Integer> {

    List<JobSeekerSave> findByUserId(JobSeekerProfile userAccountId);
    List<JobSeekerSave> findByJob(JobPostActivity job);
}
