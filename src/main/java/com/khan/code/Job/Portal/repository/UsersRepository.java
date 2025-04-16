package com.khan.code.Job.Portal.repository;


import com.khan.code.Job.Portal.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Integer> {
}
