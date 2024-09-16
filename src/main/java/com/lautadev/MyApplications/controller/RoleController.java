package com.lautadev.MyApplications.controller;

import com.lautadev.MyApplications.model.Role;
import com.lautadev.MyApplications.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/role")
@PreAuthorize("permitAll()")
public class RoleController {
    @Autowired
    private IRoleService roleService;

    @PostMapping("/save")
    public ResponseEntity<String> saveRole(@RequestBody Role role) {
        roleService.saveRole(role);
        return ResponseEntity.ok("Role saved Successfully");
    }

    @GetMapping("/get")
    public ResponseEntity<List<Role>> getRoles(){
        return ResponseEntity.ok(roleService.getRoles());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Role> findRole(@PathVariable Long id){
        Optional<Role> role = roleService.findRole(id);
        return role.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteRole(@PathVariable Long id){
        roleService.deleteRole(id);
        return ResponseEntity.ok("Role deleted");
    }

    @PatchMapping("/edit/{id}")
    public ResponseEntity<Role> editRole(@PathVariable Long id, @RequestBody Role role){
        roleService.editRole(id,role);
        Optional<Role> roleEdit = roleService.findRole(role.getId());
        return roleEdit.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }
}
