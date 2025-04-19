package com.khan.code.Job.Portal.entity;

import jakarta.persistence.*;

@Entity
public class JobCompany {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    private String name;
    private String logo;
    @jakarta.persistence.Id
    private Long id;

    public JobCompany() {
    }

    public JobCompany(Integer id, String name, String logo) {
        Id = id;
        this.name = name;
        this.logo = logo;
    }

    @Override
    public String toString() {
        return "JobCompany{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", logo='" + logo + '\'' +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
