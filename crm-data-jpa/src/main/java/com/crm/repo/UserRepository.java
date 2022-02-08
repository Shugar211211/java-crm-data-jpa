package com.crm.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.crm.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	@Query("FROM User c WHERE c.email= :email AND c.password= :password")
	Optional<User> findByEmailAndPassword(@Param("email") String email, 
											  @Param("password") String password);

}
