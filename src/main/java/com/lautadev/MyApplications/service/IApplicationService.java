package com.lautadev.MyApplications.service;

import com.lautadev.MyApplications.model.Application;

import java.util.List;
import java.util.Optional;

public interface IApplicationService {
    public void saveApplication(Application application);
    public List<Application> getApplications();
    public Optional<Application> findApplication(Long id);
    public void deleteApplication(Long id);
    public void editApplication(Long id, Application application);
}
