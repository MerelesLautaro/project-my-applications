package com.lautadev.MyApplications.service;

import com.lautadev.MyApplications.model.UserSec;
import com.lautadev.MyApplications.repository.IUserSecRepository;
import com.lautadev.MyApplications.throwable.EntityNotFoundException;
import com.lautadev.MyApplications.util.NullAwareBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserSecService implements IUserSecService{
    @Autowired
    private IUserSecRepository userSecRepository;

    @Override
    public void saveUser(UserSec userSec) {
        if(userSec != null) userSecRepository.save(userSec);
    }

    @Override
    public List<UserSec> getUsers() {
        return userSecRepository.findAll();
    }

    @Override
    public Optional<UserSec> findUser(Long id) {
        return userSecRepository.findById(id);
    }

    @Override
    public void deleteUser(Long id) {
        userSecRepository.deleteById(id);
    }

    @Override
    public void editUser(Long id, UserSec userSec) {
        UserSec userSecEdit = userSecRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity Not Found"));

        NullAwareBeanUtils.copyNonNullProperties(userSec,userSecEdit);

        this.saveUser(userSecEdit);
    }
}
