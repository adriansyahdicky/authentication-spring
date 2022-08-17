package com.project.authentication.repository;

import com.project.authentication.entity.DataUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DataUserRepository extends JpaRepository<DataUser, String> {
    Optional<DataUser> findByUsername(String username);
}
