package com.example.demo.consumer.controller;

import com.example.demo.models.entity.Owner;
import com.example.demo.models.entity.Pet;
import com.example.demo.services.OwnerService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/owners")
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @PostMapping
    public Owner addOwner(@RequestBody Owner owner) {
        return ownerService.addOwner(owner);
    }

    @GetMapping("/pets")
    public List<Pet> getAllPets() {
        return ownerService.getAllPets();
    }

    @PutMapping("/{ownerName}/pets")
    public Pet updatePetDetails(@PathVariable String ownerName, @RequestParam String petName) {
        return ownerService.updatePetDetails(ownerName, petName);
    }

    @DeleteMapping("/{ownerName}")
    public void deleteOwner(@PathVariable String ownerName) {
        ownerService.deleteOwner(ownerName);
    }
}
