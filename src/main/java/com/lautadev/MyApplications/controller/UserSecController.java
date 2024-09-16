package com.lautadev.MyApplications.controller;

import com.lautadev.MyApplications.model.UserSec;
import com.lautadev.MyApplications.service.IUserSecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@PreAuthorize("permitAll()")
public class UserSecController {
    @Autowired
    private IUserSecService userSecService;

    @PostMapping("/save")
    public ResponseEntity<String> saveUser(@RequestBody UserSec userSec){
        userSecService.saveUser(userSec);
        return ResponseEntity.ok("User saved successfully");
    }

    @GetMapping("/get")
    public ResponseEntity<List<UserSec>> getUsers(){
        return ResponseEntity.ok(userSecService.getUsers());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<UserSec> findUser(@PathVariable Long id){
        Optional<UserSec> userSec = userSecService.findUser(id);
        return userSec.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        userSecService.deleteUser(id);
        return ResponseEntity.ok("User deleted");
    }

    @PatchMapping("/edit/{id}")
    public ResponseEntity<UserSec> editUser(@PathVariable Long id, @RequestBody UserSec userSec){
        userSecService.editUser(id,userSec);
        Optional<UserSec> userSecEdit = userSecService.findUser(userSec.getId());
        return userSecEdit.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
