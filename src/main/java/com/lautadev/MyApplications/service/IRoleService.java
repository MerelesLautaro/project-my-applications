package com.lautadev.MyApplications.service;

import com.lautadev.MyApplications.model.Role;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface IRoleService {
    public Role saveRole(Role role);
    public List<Role> getRoles();
    public Optional<Role> findRole(Long id);
    public void deleteRole(Long id);
    public Role editRole(Long id,Role role);
    public Set<Role> findRoleByName(String name);
}
