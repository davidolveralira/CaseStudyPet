package com.example.demo.services;

import com.example.demo.models.entity.Owner;
import com.example.demo.models.entity.Pet;
import com.example.demo.repository.OwnerRepository;
import com.example.demo.repository.PetRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwnerService {

    private final OwnerRepository ownerRepository;
    private final PetRepository petRepository;

    public OwnerService(OwnerRepository ownerRepository, PetRepository petRepository) {
        this.ownerRepository = ownerRepository;
        this.petRepository = petRepository;
    }

    public Owner addOwner(Owner owner) {
        return ownerRepository.save(owner);
    }

    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    @Transactional
    public Pet updatePetDetails(String ownerName, String petName) {
        Owner owner = ownerRepository.findByName(ownerName);
        if (owner != null) {
            Pet pet = owner.getPets().stream().findFirst().orElseThrow(() -> new RuntimeException("Pet not found"));
            pet.setName(petName);
            return petRepository.save(pet);
        }
        throw new RuntimeException("Owner not found");
    }

    public void deleteOwner(String ownerName) {
        Owner owner = ownerRepository.findByName(ownerName);
        if (owner != null) {
            ownerRepository.delete(owner);
        } else {
            throw new RuntimeException("Owner not found");
        }
    }
}