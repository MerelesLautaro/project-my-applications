package com.lautadev.MyApplications.service;

import com.lautadev.MyApplications.model.UserSec;

import java.util.List;
import java.util.Optional;

public interface IUserSecService {
    public void saveUser(UserSec userSec);
    public List<UserSec> getUsers();
    public Optional<UserSec> findUser(Long id);
    public void deleteUser(Long id);
    public void editUser(Long id, UserSec userSec);
}
