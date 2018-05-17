package com.abc.pet.petstore.backend.repo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.abc.pet.petstore.backend.model.Pet;
import com.abc.pet.petstore.backend.repo.PetStoreRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class PetStoreRepositoryTest {

    @Autowired
    PetStoreRepository repository;

    Pet diego, tiago, leandro, tiagoLeandro;

    @Before
    public void setUp() {

        repository.deleteAll();

        diego = repository.save(new Pet(Long.valueOf(1), "diego"));
        tiago = repository.save(new Pet(Long.valueOf(2), "tiago"));
        leandro = repository.save(new Pet(Long.valueOf(3), "leandro"));
        tiagoLeandro = repository.save(new Pet(Long.valueOf(4), "tiago leandro"));
    }

    @Test
    public void setsIdOnSave() {
        Pet diego = repository.save(new Pet(Long.valueOf(1), "diego"));
        assertThat(diego.getId()).isNotNull();
    }

    @Test
    public void findsByName() {
        List<Pet> result = repository.findByName("tiago");
        assertThat(result).hasSize(1).extracting("name").contains("tiago");
    }

    /*@Test
    public void findsByExample() {
        // Given
        Pet probe = new Pet(null, "tiago");
        // When
        java.lang.Iterable<Pet> result = repository.findAll(Example.of(probe));
        // Then
        assertThat(result).hasSize(1).extracting("name").contains("tiago");
    }*/

    @After
    public void tearDown() throws Exception {
        repository.deleteAll();
    }
}
