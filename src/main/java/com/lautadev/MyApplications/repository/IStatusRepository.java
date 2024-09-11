package com.lautadev.MyApplications.repository;

import com.lautadev.MyApplications.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStatusRepository extends JpaRepository<Status,Long> {
}
