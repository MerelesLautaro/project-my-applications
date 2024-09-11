package com.lautadev.MyApplications.controller;

import com.lautadev.MyApplications.model.Permission;
import com.lautadev.MyApplications.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    private IPermissionService permissionService;

    @PostMapping("/save")
    public ResponseEntity<String> savePermission(@RequestBody Permission permission){
        permissionService.savePermission(permission);
        return ResponseEntity.ok("Permission saved successfully");
    }

    @GetMapping("/get")
    public ResponseEntity<List<Permission>> getPermissions(){
        return ResponseEntity.ok(permissionService.getPermissions());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Permission> findPermission(@PathVariable Long id){
        Optional<Permission> permission = permissionService.findPermission(id);
        return permission.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePermission(@PathVariable Long id){
        permissionService.deletePermission(id);
        return ResponseEntity.ok("Permission deleted");
    }

    @PatchMapping("/edit/{id}")
    public ResponseEntity<Permission> editPermission(@PathVariable Long id, @RequestBody Permission permission){
        permissionService.editPermission(id,permission);
        Optional<Permission> permissionEdit = permissionService.findPermission(permission.getId());
        return permissionEdit.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }
}
