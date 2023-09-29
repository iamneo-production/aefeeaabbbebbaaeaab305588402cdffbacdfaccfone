package com.example.springapp.repository;

import com.example.springapp.model.Door;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface DoorRepository extends JpaRepository<Door, Integer> {
    List<Door> findByLocation(String location);
    
    @Query("SELECT d FROM Door d WHERE d.accessType = :accessType")
    List<Door> findByAccessType(String accessType);
}
