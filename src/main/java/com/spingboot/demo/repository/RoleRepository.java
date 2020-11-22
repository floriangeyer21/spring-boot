package com.spingboot.demo.repository;

import com.spingboot.demo.domain.Role;
import com.spingboot.demo.domain.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role getRoleByRoleName(RoleName name);
}
