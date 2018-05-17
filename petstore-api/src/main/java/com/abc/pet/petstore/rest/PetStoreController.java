package com.abc.pet.petstore.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.abc.pet.petstore.rest.rdo.PetDO;
import com.abc.pet.petstore.service.PetStoreService;

/**
 * http://localhost:8080/swagger-ui.html
 *
 * API: http://petstore.swagger.io/#/PetDO
 */
@RestController
@RequestMapping("/api")
public class PetStoreController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PetStoreService petService;

    @RequestMapping(value = "/pet", method = RequestMethod.GET)
    public List<PetDO> findAllPets() {
    	log.info("Get All pets");
        return this.petService.getAllPets();
    }

    @RequestMapping(value = "/pet", method = RequestMethod.POST)
    public PetDO addPetToStore(@RequestBody PetDO pet) {
    	log.info("create Pet: {}", pet);
        return this.petService.create(pet);
    }

    @RequestMapping(value = "/pet/{petId}", method = RequestMethod.GET)
    public PetDO findPetById(@PathVariable("petId") Long petId) {
    	log.info("Get Pet by ID : {}", petId);
        return this.petService.getPetById(petId);
    }

    @RequestMapping(value = "/pet/{petId}", method = RequestMethod.DELETE)
    public void deletePet(@PathVariable("petId") Long petId) {
    	log.info("delete pet: {}", petId);
        this.petService.delete(petId);
    }

    @RequestMapping(value = "/pet", method = RequestMethod.PATCH)
    public void updatePet(@RequestBody PetDO pet) {
    	log.info("update pet: {}", pet);
        this.petService.update(pet);
    }
    
}