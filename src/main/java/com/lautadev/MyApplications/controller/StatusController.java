package com.lautadev.MyApplications.controller;

import com.lautadev.MyApplications.model.Status;
import com.lautadev.MyApplications.service.IStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/status")
@PreAuthorize("permitAll()")
public class StatusController {
    @Autowired
    private IStatusService statusService;

    @PostMapping("/save")
    public ResponseEntity<String> saveStatus(@RequestBody Status status){
        statusService.saveStatus(status);
        return ResponseEntity.ok("Status saved successfully");
    }

    @GetMapping("/get")
    public ResponseEntity<List<Status>> getStatuses(){
        return ResponseEntity.ok(statusService.getStatuses());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Status> findStatus(@PathVariable Long id){
        Optional<Status> status = statusService.findStatus(id);
        return status.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStatus(@PathVariable Long id){
        statusService.deleteStatus(id);
        return ResponseEntity.ok("Status deleted");
    }

    @PatchMapping("/edit/{id}")
    public ResponseEntity<Status> editStatus(@PathVariable Long id, @RequestBody Status status){
        statusService.editStatus(id,status);
        Optional<Status> statusEdit = statusService.findStatus(status.getId());
        return statusEdit.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

}
