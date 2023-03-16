package com.savewater.backend.repository;

import java.util.Optional;

import com.savewater.backend.models.ERole;
import com.savewater.backend.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}
