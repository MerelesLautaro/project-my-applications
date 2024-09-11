package com.lautadev.MyApplications.service;

import com.lautadev.MyApplications.model.Permission;
import com.lautadev.MyApplications.repository.IPermissionRepository;
import com.lautadev.MyApplications.throwable.EntityNotFoundException;
import com.lautadev.MyApplications.util.NullAwareBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PermissionService implements IPermissionService{
    @Autowired
    private IPermissionRepository permissionRepository;

    @Override
    public void savePermission(Permission permission) {
        if(permission!=null) permissionRepository.save(permission);
    }

    @Override
    public List<Permission> getPermissions() {
        return permissionRepository.findAll();
    }

    @Override
    public Optional<Permission> findPermission(Long id) {
        return permissionRepository.findById(id);
    }

    @Override
    public void deletePermission(Long id) {
        permissionRepository.deleteById(id);
    }

    @Override
    public void editPermission(Long id,Permission permission) {
        Permission permissionEdit = permissionRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity Not Found"));

        NullAwareBeanUtils.copyNonNullProperties(permission,permissionEdit);

        this.savePermission(permissionEdit);
    }
}
