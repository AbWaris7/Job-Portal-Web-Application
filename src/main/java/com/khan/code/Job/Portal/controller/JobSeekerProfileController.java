package com.khan.code.Job.Portal.controller;

import ch.qos.logback.core.util.StringUtil;
import com.khan.code.Job.Portal.entity.JobSeekerProfile;
import com.khan.code.Job.Portal.entity.Skills;
import com.khan.code.Job.Portal.entity.Users;
import com.khan.code.Job.Portal.repository.UsersRepository;
import com.khan.code.Job.Portal.services.JobSeekerProfileService;
import com.khan.code.Job.Portal.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
@RequestMapping("/job-seeker-profile")
public class JobSeekerProfileController {

    private JobSeekerProfileService jobSeekerProfileService;

    private UsersRepository usersRepository;

    @Autowired
    public JobSeekerProfileController(JobSeekerProfileService jobSeekerProfileService, UsersRepository usersRepository) {
        this.jobSeekerProfileService = jobSeekerProfileService;
        this.usersRepository = usersRepository;
    }

    @GetMapping("/")
    public String JobSeekerProfile(Model model) {
        JobSeekerProfile jobSeekerProfile = new JobSeekerProfile();
        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
        List<Skills> skills= new ArrayList<>();

        if(!(authentication instanceof AnonymousAuthenticationToken)) {
           Users users = usersRepository.findByEmail(authentication.getName()).orElseThrow(()->new UsernameNotFoundException("User Not Found"));
           Optional<JobSeekerProfile> seekerProfile =  jobSeekerProfileService.getOne(users.getUserId());

           if(seekerProfile.isPresent()) {
               jobSeekerProfile = seekerProfile.get();
               if(jobSeekerProfile.getSkills().isEmpty()) {
                   skills.add(new Skills());
                   jobSeekerProfile.setSkills(skills);
               }
           }
           model.addAttribute("skills", skills);
           model.addAttribute("profile", jobSeekerProfile);
        }
        return "job-seeker-profile";
    }

    @PostMapping("/addNew")
    public String addNew(JobSeekerProfile jobSeekerProfile, @RequestParam("image") MultipartFile image, @RequestParam("pdf") MultipartFile pdf, Model model) {

        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();

        if(!(authentication instanceof AnonymousAuthenticationToken)) {

            Users users = usersRepository.findByEmail(authentication.getName()).orElseThrow(()->new UsernameNotFoundException("User Not Found"));
            jobSeekerProfile.setUserId(users);
            jobSeekerProfile.setUserAccountId(users.getUserId());

        }

        List<Skills> skills= new ArrayList<>();
        model.addAttribute("profile", jobSeekerProfile);
        model.addAttribute("skills", skills);

        for(Skills skill : jobSeekerProfile.getSkills()) {
            skill.setJobSeekerProfile(jobSeekerProfile);
        }

        String imageName = "";
        String resumeName = "";

        if(!Objects.equals(image.getOriginalFilename(), "")) {
            imageName = StringUtils.cleanPath(Objects.requireNonNull(image.getOriginalFilename()));
            jobSeekerProfile.setProfilePhoto(imageName);
        }

        if(!Objects.equals(pdf.getOriginalFilename(), "")) {
            resumeName = StringUtils.cleanPath(Objects.requireNonNull(image.getOriginalFilename()));
            jobSeekerProfile.setProfilePhoto(resumeName);
        }
        JobSeekerProfile seekerProfile = jobSeekerProfileService.addNew(jobSeekerProfile);

        try {
            String uploadDir = "photos/candidate/" + jobSeekerProfile.getUserAccountId();
            if (!Objects.equals(image.getOriginalFilename(), "")) {
                FileUploadUtil.saveFile(uploadDir, imageName, image);
            }
            if (!Objects.equals(pdf.getOriginalFilename(), "")) {
                FileUploadUtil.saveFile(uploadDir, resumeName, pdf);
            }
        }
        catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        return "redirect:/dashboard/";

    }
}
