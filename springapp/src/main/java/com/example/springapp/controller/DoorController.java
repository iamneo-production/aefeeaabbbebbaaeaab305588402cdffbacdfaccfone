package com.example.springapp.controller;

import com.example.springapp.model.Door;
import com.example.springapp.service.DoorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doors")
public class DoorController {
    @Autowired
    public  DoorService doorService;


    @PostMapping
    public boolean addDoor(@RequestBody Door door) {
        return doorService.addDoor(door);
    }

    @GetMapping("/{doorId}")
    public Door getDoorById(@PathVariable int doorId) {
        return doorService.getDoorById(doorId);
    }

    @GetMapping
    public List<Door> getAllDoors() {
        return doorService.getAllDoors();
    }

    @GetMapping("/by-access-type/{accessType}")
    public List<Door> getDoorsByAccessType(@PathVariable String accessType) {
        return doorService.getDoorsByAccessType(accessType);
    }

}
