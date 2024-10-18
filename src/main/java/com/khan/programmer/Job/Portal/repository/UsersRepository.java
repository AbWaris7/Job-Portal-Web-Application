package com.khan.programmer.Job.Portal.repository;

import com.khan.programmer.Job.Portal.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Integer> {


    Optional<Users> findByEmail(String email);
}
