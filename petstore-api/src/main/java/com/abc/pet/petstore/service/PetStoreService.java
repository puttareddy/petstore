package com.abc.pet.petstore.service;

import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.pet.petstore.backend.model.Pet;
import com.abc.pet.petstore.backend.repo.PetStoreRepository;
import com.abc.pet.petstore.converter.DozerParser;
import com.abc.pet.petstore.rest.rdo.PetDO;

@Service
public class PetStoreService {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    PetStoreRepository petstoreRepository;

    // TODO: This is just a showcase to decouple the objects between rest layer vs backend layer
    @Autowired
    DozerParser parser;

    public List<PetDO> getPetsByName(String name) {
        log.info("find pet by name: {}", name);
        return parser.parserListObjectInputToObjectOutput(petstoreRepository.findByName(name), PetDO.class);
    }

    public List<PetDO> getAllPets() {
        return parser.parserListObjectInputToObjectOutput(petstoreRepository.findAll(), PetDO.class);
    }

    public PetDO create(PetDO petDO) {
        log.info("Creating a customer");

        // This could be used for any transformations
        Pet pet = parser.parseObjectInputToObjectOutput(petDO, Pet.class);
        if (pet.getId() == null) {
            // TODO: This should be created with Autogenereated numbers. For now, it is coded like
            // this as Mongo doesn't support autogeneration like JPA
            pet.setId(Long.valueOf(new Random().ints(10, (100 + 1)).findFirst().getAsInt()));
        }
        pet = petstoreRepository.save(pet);
        return parser.parseObjectInputToObjectOutput(pet, PetDO.class);
    }

    public PetDO getPetById(Long petId) {
        return parser.parseObjectInputToObjectOutput(petstoreRepository.findOne(petId), PetDO.class);
    }

    public Pet update(PetDO petDO) {
        return petstoreRepository.save(parser.parseObjectInputToObjectOutput(petDO, Pet.class));
    }

    public void delete(Long petId) {
        petstoreRepository.delete(petId);
    }

}
