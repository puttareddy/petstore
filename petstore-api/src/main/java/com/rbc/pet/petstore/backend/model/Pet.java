package com.rbc.pet.petstore.backend.model;

import java.io.Serializable;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.Map;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Document(collection = "todolist")
public class Pet  {

    private static final long serialVersionUID = 1L;

    public Pet() {

    }

    public Pet(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    
    public Pet(String name) {
        this.name = name;
    }

    @Id
    Long id;

    String name;

    public Map<String, Pet> toMap() {
        Map<String, Pet> map = new HashMap<String, Pet>();
        map.put(id.toString(), this);
        return map;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Pet other = (Pet) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        }
        else
            if (!name.equals(other.name))
                return false;
        if (id == null) {
            if (other.id != null)
                return false;
        }
        else
            if (!id.equals(other.id))
                return false;
        return true;
    }

    @Override
    public String toString() {
        return "Pet [id=" + id + ", name=" + name + "]";
    }

}
