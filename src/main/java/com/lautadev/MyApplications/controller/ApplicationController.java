package com.lautadev.MyApplications.controller;

import com.lautadev.MyApplications.model.Application;
import com.lautadev.MyApplications.service.IApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/application")
@PreAuthorize("permitAll()")
public class ApplicationController {

    @Autowired
    private IApplicationService applicationService;

    @PostMapping("/save")
    public ResponseEntity<String> saveApplication(@RequestBody Application application){
        applicationService.saveApplication(application);
        return ResponseEntity.ok("Application saved successfully");
    }

    @GetMapping("/get")
    public ResponseEntity<List<Application>> getApplications(){
        return ResponseEntity.ok(applicationService.getApplications());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Application> findApplication(@PathVariable Long id){
        Optional<Application> application = applicationService.findApplication(id);
        return application.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteApplication(@PathVariable Long id){
        applicationService.deleteApplication(id);
        return ResponseEntity.ok("Application deleted");
    }

    @PatchMapping("/edit/{id}")
    public ResponseEntity<Application> editApplication(@PathVariable Long id, @RequestBody Application application){
        applicationService.editApplication(id,application);
        Optional<Application> applicationEdit = applicationService.findApplication(application.getId());
        return applicationEdit.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
