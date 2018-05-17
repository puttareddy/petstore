package com.abc.pet.petstore;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.abc.pet.petstore.backend.repo.PetStoreRepository;
import com.abc.pet.petstore.converter.DozerParser;
import com.abc.pet.petstore.rest.rdo.PetDO;
import com.fasterxml.jackson.core.JsonProcessingException;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class PetStoreEndpointsTest {

    final String BASE_PATH = "http://localhost:8080/api/pet";

    @Autowired
    private PetStoreRepository repository;

    @Spy
    DozerParser dozerParser;

    private RestTemplate restTemplate;

    @Before
    public void setUp() throws Exception {
        repository.deleteAll();
        restTemplate = new RestTemplate();
    }

    @Test
    public void testCreatePerson() throws JsonProcessingException {
        // when
        PetDO pet = new PetDO(Long.valueOf(11), "tommy");
        // given
        PetDO response = restTemplate.postForObject(BASE_PATH, pet, PetDO.class);
        // then
        Assert.assertEquals("tommy", response.getName());
    }

    @Test
    public void testFindOne() throws IOException {
        // when
        PetDO pet = new PetDO(Long.valueOf(11), "diego");
        PetDO response = restTemplate.postForObject(BASE_PATH, pet, PetDO.class);
        // given
        PetDO person = restTemplate.getForObject(BASE_PATH + "/11", PetDO.class);
        // Then
        Assert.assertNotNull(person);
        Assert.assertTrue(person.getId() == 11);
        Assert.assertEquals("diego", person.getName());
    }

    public void testUpdatePerson() throws IOException {
        // when
        PetDO pet = new PetDO(Long.valueOf(100), "dummy");
        PetDO response = restTemplate.postForObject(BASE_PATH, pet, PetDO.class);
        // given
        PetDO person = restTemplate.getForObject(BASE_PATH + "/100", PetDO.class);
        person.setName("dummy Updated");
        restTemplate.put(BASE_PATH, person);
        // Then
        PetDO person2 = restTemplate.getForObject(BASE_PATH + "/100", PetDO.class);
        Assert.assertNotNull(person2);
        Assert.assertEquals("dummy Updated", person2.getName());

    }

    @Test
    public void testFindAll() throws IOException {
        // when
        PetDO pet = new PetDO(Long.valueOf(1), "jimmy");
        PetDO response = restTemplate.postForObject(BASE_PATH, pet, PetDO.class);
        // given
        List<PetDO> persons = restTemplate.getForObject(BASE_PATH, List.class);
        // Then
        Assert.assertNotNull(persons);
        Assert.assertTrue(persons.size() > 0);

    }

}