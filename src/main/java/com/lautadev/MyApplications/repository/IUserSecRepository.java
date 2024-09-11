package com.lautadev.MyApplications.repository;

import com.lautadev.MyApplications.model.UserSec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface IUserSecRepository extends JpaRepository<UserSec,Long> {
}
