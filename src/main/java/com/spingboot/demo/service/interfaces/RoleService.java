package com.spingboot.demo.service.interfaces;

import com.spingboot.demo.domain.Role;
import com.spingboot.demo.domain.RoleName;
import java.util.List;

public interface RoleService {

    List<Role> addAll(List<Role> roles);

    Role getByName(RoleName name);
}
