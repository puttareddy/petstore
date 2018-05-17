package com.abc.pet.petstore.converter;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Component;

@Component
public class DozerParser {

    DozerBeanMapper mapper = new DozerBeanMapper();

    public <O, D> D parseObjectInputToObjectOutput(O originalObject, Class<D> destinationObject) {
        return parser(destinationObject, originalObject);
    }

    public <O, D> List<D> parserListObjectInputToObjectOutput(java.lang.Iterable<O> originalObjects, Class<D> destinationObject) {
        List<D> destinationObjects = new ArrayList<D>();
        for (Object originalObject : originalObjects) {
            destinationObjects.add(parser(destinationObject, originalObject));
        }
        return destinationObjects;
    }

    private <D> D parser(Class<D> destinationObject, Object originalObject) {
        return mapper.map(originalObject, destinationObject);
    }
}