package com.survey.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.survey.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
