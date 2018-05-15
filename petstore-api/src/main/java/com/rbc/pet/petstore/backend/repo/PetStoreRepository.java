package com.rbc.pet.petstore.backend.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rbc.pet.petstore.backend.model.Pet;

@Repository
public interface PetStoreRepository extends CrudRepository<Pet, Long> {

    public abstract List<Pet> findByName(String name);
}
