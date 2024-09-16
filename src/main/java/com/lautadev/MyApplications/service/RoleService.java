package com.lautadev.MyApplications.service;

import com.lautadev.MyApplications.model.Permission;
import com.lautadev.MyApplications.model.Role;
import com.lautadev.MyApplications.repository.IRoleRepository;
import com.lautadev.MyApplications.throwable.EntityNotFoundException;
import com.lautadev.MyApplications.util.NullAwareBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class RoleService implements IRoleService{
    @Autowired
    private IRoleRepository roleRepository;

    @Autowired
    private IPermissionService permissionService;

    @Override
    public Role saveRole(Role role) {
        Set<Permission> permissionsList = new HashSet<>();

        for(Permission permission: role.getPermissionsList() ){
            Permission readPermission = permissionService.findPermission(permission.getId()).orElse(null);
            if(readPermission!=null){
                permissionsList.add(readPermission);
            }
        }

        if(!permissionsList.isEmpty())
        {
            role.setPermissionsList(permissionsList);
            return roleRepository.save(role);
        }

        return role;
    }

    @Override
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Optional<Role> findRole(Long id) {
        return roleRepository.findById(id);
    }

    @Override
    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }

    @Override
    public Role editRole(Long id,Role role) {
        Role roleEdit = this.findRole(id).orElseThrow(() -> new EntityNotFoundException("Entity not found")) ;;

        NullAwareBeanUtils.copyNonNullProperties(role,roleEdit);

        return this.saveRole(roleEdit);
    }

    @Override
    public Set<Role> findRoleByName(String name) {
        List<Role> allRoles = this.getRoles();
        Set<Role> containRole = new HashSet<>();

        for(Role role: allRoles){
            if(Objects.equals(role.getRole(), name)){
                containRole.add(role);
            }
        }

        return containRole;
    }
}