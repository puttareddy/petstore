package com.abc.pet.petstore.converter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import com.abc.pet.petstore.backend.model.Pet;
import com.abc.pet.petstore.converter.DozerParser;
import com.abc.pet.petstore.rest.rdo.PetDO;

@RunWith(MockitoJUnitRunner.class)
public class DozerParserTest {

    public void configureMapper(String... mappingFileUrls) {
        mapper.setMappingFiles(Arrays.asList(mappingFileUrls));
    }

    @InjectMocks
    private DozerParser parser;

    @Spy
    DozerBeanMapper mapper;

    @Before
    public void setUp() {
        mapper = new DozerBeanMapper();
        configureMapper("dozer-mapping.xml");
    }

    @Test
    public void parseObjectInputToObjectOutputTest() {
        Pet output = parser.parseObjectInputToObjectOutput(new PetDO(Long.valueOf(111), "tommy"), Pet.class);
        assertTrue(output.getId() == 111);
        assertEquals("tommy", output.getName());
    }

    @Test
    public void parserListObjectInputToObjectOutputTest() {
        List<Pet> output = parser.parserListObjectInputToObjectOutput(getPetList(), Pet.class);
        System.out.println(">>>>>>>>>>output is: >>>>>>>>>>>>>>" + output);
        assertEquals("tommy", output.get(0).getName());
        assertEquals("jimmy", output.get(1).getName());
        assertTrue(output.get(0).getId() == 1);
    }

    private List<PetDO> getPetList() {
        List<PetDO> list = new ArrayList<PetDO>();
        list.add(new PetDO(Long.valueOf(1), "tommy"));
        list.add(new PetDO(Long.valueOf(2), "jimmy"));

        return list;

    }
}