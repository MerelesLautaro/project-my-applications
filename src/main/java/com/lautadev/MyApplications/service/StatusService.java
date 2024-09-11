package com.lautadev.MyApplications.service;

import com.lautadev.MyApplications.model.Status;
import com.lautadev.MyApplications.repository.IStatusRepository;
import com.lautadev.MyApplications.throwable.EntityNotFoundException;
import com.lautadev.MyApplications.util.NullAwareBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatusService implements IStatusService{
    @Autowired
    private IStatusRepository statusRepository;

    @Override
    public void saveStatus(Status status) {
        if(status != null) statusRepository.save(status);
    }

    @Override
    public List<Status> getStatuses() {
        return statusRepository.findAll();
    }

    @Override
    public Optional<Status> findStatus(Long id) {
        return statusRepository.findById(id);
    }

    @Override
    public void deleteStatus(Long id) {
        statusRepository.deleteById(id);
    }

    @Override
    public void editStatus(Long id, Status status) {
        Status statusEdit = statusRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity Not Found"));

        NullAwareBeanUtils.copyNonNullProperties(status,statusEdit);

        this.saveStatus(statusEdit);
    }
}
