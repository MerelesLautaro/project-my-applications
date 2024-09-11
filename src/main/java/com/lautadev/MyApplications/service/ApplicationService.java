package com.lautadev.MyApplications.service;

import com.lautadev.MyApplications.model.Application;
import com.lautadev.MyApplications.repository.IApplicationRepository;
import com.lautadev.MyApplications.throwable.EntityNotFoundException;
import com.lautadev.MyApplications.util.NullAwareBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApplicationService implements IApplicationService{
    @Autowired
    private IApplicationRepository applicationRepository;

    @Override
    public void saveApplication(Application application) {
        if(application != null) applicationRepository.save(application);
    }

    @Override
    public List<Application> getApplications() {
        return applicationRepository.findAll();
    }

    @Override
    public Optional<Application> findApplication(Long id) {
        return applicationRepository.findById(id);
    }

    @Override
    public void deleteApplication(Long id) {
        applicationRepository.deleteById(id);
    }

    @Override
    public void editApplication(Long id, Application application) {
        Application applicationEdit = applicationRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity Not Found"));

        NullAwareBeanUtils.copyNonNullProperties(application,applicationEdit);

        this.saveApplication(applicationEdit);
    }
}
