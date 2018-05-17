package com.abc.pet.petstore.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PET")
public class Pet {

    private static final long serialVersionUID = -3565588482964320937L;

    public Pet() {

    }

    public Pet(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

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
}
