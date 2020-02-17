package com.jtm.notesapp.repositories;

import com.jtm.notesapp.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    public Set<Role> findByRole(String role);
}
