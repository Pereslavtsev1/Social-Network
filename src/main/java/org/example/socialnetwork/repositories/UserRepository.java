package org.example.socialnetwork.repositories;

import org.example.socialnetwork.models.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<ApplicationUser, Long> {
   Optional<ApplicationUser> findByUsername(String username);
   Optional<ApplicationUser> findByEmail(String email);
}
