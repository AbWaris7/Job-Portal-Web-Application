package com.khan.programmer.Job.Portal.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "skills")
public class Skills {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String experience_level;
    private String years_of_experience;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "job_seeker_profile")

    private JobSeekerProfile jobSeekerProfile;

    public Skills() {
    }

    public Skills(int id, String name, String experience_level, String years_of_experience, JobSeekerProfile jobSeekerProfile) {
        this.id = id;
        this.name = name;
        this.experience_level = experience_level;
        this.years_of_experience = years_of_experience;
        this.jobSeekerProfile = jobSeekerProfile;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExperience_level() {
        return experience_level;
    }

    public void setExperience_level(String experience_level) {
        this.experience_level = experience_level;
    }

    public String getYears_of_experience() {
        return years_of_experience;
    }

    public void setYears_of_experience(String years_of_experience) {
        this.years_of_experience = years_of_experience;
    }

    public JobSeekerProfile getJobSeekerProfile() {
        return jobSeekerProfile;
    }

    public void setJobSeekerProfile(JobSeekerProfile jobSeekerProfile) {
        this.jobSeekerProfile = jobSeekerProfile;
    }

    @Override
    public String toString() {
        return "Skills{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", experience_level='" + experience_level + '\'' +
                ", years_of_experience='" + years_of_experience + '\'' +
                ", jobSeekerProfile=" + jobSeekerProfile +
                '}';
    }


}
