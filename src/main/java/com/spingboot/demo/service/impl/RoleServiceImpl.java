package com.spingboot.demo.service.impl;

import com.spingboot.demo.domain.Role;
import com.spingboot.demo.domain.RoleName;
import com.spingboot.demo.repository.RoleRepository;
import com.spingboot.demo.service.interfaces.RoleService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> addAll(List<Role> roles) {
        return roleRepository.saveAll(roles);
    }

    @Override
    public Role getByName(RoleName name) {
        return roleRepository.getRoleByRoleName(name);
    }
}
