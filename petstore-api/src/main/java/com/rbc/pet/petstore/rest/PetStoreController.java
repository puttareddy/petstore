package com.rbc.pet.petstore.rest;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rbc.pet.petstore.rest.rdo.PetDO;
import com.rbc.pet.petstore.service.PetStoreService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * http://localhost:8080/swagger-ui.html
 *
 * API: http://PetDOstore.swagger.io/#/PetDO
 */
@RestController
@RequestMapping("/api")
public class PetStoreController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PetStoreService petService;

    /*@ApiOperation(value = "Find all pets (by name)", nickname = "getPets", produces = "application/json")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Return a list of pets", response = PetDO.class, responseContainer = "List") })
    @ApiImplicitParams({
        @ApiImplicitParam(name = "name", value = "Pet's name (substring)", dataType = "string", paramType = "query", defaultValue = "n") })
    @GetMapping("/pet")
    List<PetDO> getPets(@RequestParam(name = "name", required = false) String name) {
        log.info("Get pets. Name: {}", name);
        return name != null ? petService.getPetsByName(name) : petService.getAllPets();
    }

    @ApiOperation(value = "Add a new pet to the store", nickname = "createPet", consumes = "application/json")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Created", response = PetDO.class),
        @ApiResponse(code = 510, message = "Response Validation Failed", response = void.class) })
    @RequestMapping(value = "/pet", produces = {
        "application/json" }, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    PetDO create(@RequestBody PetDO pet) {
        log.info("Create: {}", pet);
        return petService.create(pet);
    }

    @ApiOperation(value = "Find pet by ID", nickname = "getPetById", produces = "application/json")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "petId", value = "Pet's id", dataType = "long", paramType = "path", defaultValue = "11") })
    @GetMapping("/pet/{petId}")
    PetDO getPetById(@PathVariable Long petId, HttpServletResponse response) {
        log.info("Get by Id: {}", petId);
        PetDO pet = petService.getPetById(petId);
        log.trace("Pet: {}", pet);
        if (pet == null) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
        }
        return pet;
    }

    @ApiOperation(value = "Update pet in the store", nickname = "updatePet")
    @PutMapping("/pet")
    void update(@RequestBody PetDO pet, HttpServletResponse response) {
        log.info("Update: {}", pet);
        if (petService.update(pet) != 1) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
        }
    }

    @ApiOperation(value = "Delete a pet", nickname = "deletePet")
    @ApiImplicitParams({ @ApiImplicitParam(name = "petId", value = "Pet's id", dataType = "long", paramType = "path") })
    @DeleteMapping("/pet/{petId}")
    void delete(@PathVariable Long petId, HttpServletResponse response) {
        log.info("Delete: {}", petId);
        petService.delete(petId);
        if (petService.delete(petId) != 1) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
        }
    }*/
    @RequestMapping(value = "/pet", method = RequestMethod.GET)
    public List<PetDO> findAllPets() {
        return this.petService.getAllPets();
    }

    @RequestMapping(value = "/pet", method = RequestMethod.POST)
    public PetDO addPetToStore(@RequestBody PetDO pet) {
        return this.petService.create(pet);
    }

    @RequestMapping(value = "/pet/{petId}", method = RequestMethod.GET)
    public PetDO findPetById(@PathVariable("petId") Long petId) {
        return this.petService.getPetById(petId);
    }

    @RequestMapping(value = "/pet/{petId}", method = RequestMethod.DELETE)
    public void deletePet(@PathVariable("petId") Long petId) {
        this.petService.delete(petId);
    }

    @RequestMapping(value = "/pet", method = RequestMethod.PATCH)
    public void updatePet(@RequestBody PetDO pet) {
        this.petService.update(pet);
    }
    
}