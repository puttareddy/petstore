package com.abc.pet.petstore.rest.rdo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PetDO {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public PetDO() {

    }

    public PetDO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @ApiModelProperty(readOnly = true)
    @JsonProperty("id")
    Long id;

    @ApiModelProperty
    @JsonProperty("name")
    String name;

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
