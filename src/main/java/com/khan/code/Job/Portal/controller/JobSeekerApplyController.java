package com.khan.code.Job.Portal.controller;

import com.khan.code.Job.Portal.entity.JobPostActivity;
import com.khan.code.Job.Portal.services.JobPostActivityService;
import com.khan.code.Job.Portal.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class JobSeekerApplyController {

    private final JobPostActivityService jobPostActivityService;
    private final UserService userService;
    @Autowired

    public JobSeekerApplyController(JobPostActivityService jobPostActivityService, UserService userService) {
        this.jobPostActivityService = jobPostActivityService;
        this.userService = userService;
    }

    @GetMapping("job-details-apply/{id}")
    public String display(@PathVariable("id") int id, Model model) {

        JobPostActivity jobDetails = jobPostActivityService.getOne(id);
        model.addAttribute("jobDetails", jobDetails);
        model.addAttribute("user",userService.getcurrentUserProfile());
        return "job-details";
    }
}
