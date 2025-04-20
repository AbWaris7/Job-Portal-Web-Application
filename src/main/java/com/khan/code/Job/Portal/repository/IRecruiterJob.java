package com.khan.code.Job.Portal.repository;

public interface IRecruiterJob {

    Long getTotalCandidates();

    int getJob_post_id();

    String getJob_title();

    int getLocationId();

    String getCity();

    String getState();
    String getCountry();

    int getCompanyId();

    String getName();
}
