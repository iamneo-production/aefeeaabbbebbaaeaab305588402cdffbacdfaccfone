package com.example.springapp.service;

import com.example.springapp.model.Door;
import com.example.springapp.repository.DoorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DoorService {
   
   
    @Autowired
    public  DoorRepository doorRepository;

    

    public boolean addDoor(Door door) {
        
        return doorRepository.save(door)!=null?true:false;
    }

    public Door getDoorById(int doorId) {
        return doorRepository.findById(doorId).orElse(null);
    }
   
    public List<Door> getAllDoors() {
        return doorRepository.findAll();
    }

    public List<Door> getDoorsByAccessType(String accessType) {
        return doorRepository.findByAccessType(accessType);
    }

   
}
