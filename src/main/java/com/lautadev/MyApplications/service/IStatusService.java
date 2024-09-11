package com.lautadev.MyApplications.service;

import com.lautadev.MyApplications.model.Status;

import java.util.List;
import java.util.Optional;

public interface IStatusService {
    public void saveStatus(Status status);
    public List<Status> getStatuses();
    public Optional<Status> findStatus(Long id);
    public void deleteStatus(Long id);
    public void editStatus(Long id, Status status);
}
