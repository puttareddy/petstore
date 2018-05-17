package com.abc.pet.petstore.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.abc.pet.petstore.rest.rdo.PetDO;
import com.abc.pet.petstore.service.PetStoreService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class PetStoreServiceTest {

    @Autowired
    PetStoreService service;

    @Before
    public void setUp() {

    }

    @Test
    public void setsIdOnSave() {
        PetDO sample = service.create(new PetDO(Long.valueOf(1), "Pinky"));
        assertThat(sample.getId()).isNotNull();
    }

    // TODO: Other tests can follow the same

}
