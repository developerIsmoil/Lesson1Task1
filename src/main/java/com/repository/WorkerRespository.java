package com.repository;

import com.entity.Address;
import com.entity.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkerRespository extends JpaRepository<Worker, Long> {
}
