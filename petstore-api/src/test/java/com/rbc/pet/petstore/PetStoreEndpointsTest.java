package com.rbc.pet.petstore;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rbc.pet.petstore.backend.model.Pet;
import com.rbc.pet.petstore.backend.repo.PetStoreRepository;
import com.rbc.pet.petstore.converter.DozerParser;
import com.rbc.pet.petstore.rest.rdo.PetDO;

// @RunWith(SpringRunner.class)
// @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class PetStoreEndpointsTest {

    final String BASE_PATH = "http://localhost:8080/api";

    @Autowired
    private PetStoreRepository repository;

    @Spy
    DozerParser dozerParser;

    private RestTemplate restTemplate;

    private ObjectMapper MAPPER = new ObjectMapper();

    @Before
    public void setUp() throws Exception {
        repository.deleteAll();

        repository.save(new Pet(Long.valueOf(1), "diego"));
        repository.save(new Pet(Long.valueOf(2), "tiago"));
        repository.save(new Pet(Long.valueOf(3), "leandro"));
        repository.save(new Pet(Long.valueOf(4), "tiago leandro"));

        restTemplate = new RestTemplate();
    }

    public void testCreatePerson() throws JsonProcessingException {
        restTemplate.delete(BASE_PATH + "/pet");

        PetDO pet = new PetDO(Long.valueOf(1), "diego");
        PetDO response = restTemplate.postForObject(BASE_PATH, pet, PetDO.class);
        Assert.assertEquals("diego", response.getName());
    }

    public void testFindOne() throws IOException {
        String response = restTemplate.getForObject(BASE_PATH + "/pet", String.class);
        List<PetDO> persons = MAPPER.readValue(response, MAPPER.getTypeFactory().constructCollectionType(List.class, PetDO.class));
        PetDO person = restTemplate.getForObject(BASE_PATH + "/pet/" + persons.get(1).getId(), PetDO.class);
        Assert.assertNotNull(person);
        Assert.assertEquals("tiago", person.getName());
    }

    public void testUpdatePerson() throws IOException {
        String response = restTemplate.getForObject(BASE_PATH + "/pets", String.class);
        List<PetDO> persons = MAPPER.readValue(response, MAPPER.getTypeFactory().constructCollectionType(List.class, PetDO.class));

        PetDO person = restTemplate.getForObject(BASE_PATH + "/" + persons.get(2).getId(), PetDO.class);
        person.setName("Tiago Updated");
        restTemplate.put(BASE_PATH, person);

        PetDO person2 = restTemplate.getForObject(BASE_PATH + "/" + persons.get(2).getId(), PetDO.class);
        Assert.assertNotNull(person2);
        Assert.assertEquals("Tiago Updated", person2.getName());

    }

    public void testFindAll() throws IOException {
        String response = restTemplate.getForObject(BASE_PATH + "/pets", String.class);
        List<PetDO> persons = MAPPER.readValue(response, MAPPER.getTypeFactory().constructCollectionType(List.class, PetDO.class));
        Assert.assertEquals("diego", persons.get(0).getName());
    }

}