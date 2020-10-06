package com.database.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author Surajdeo Prasad
 */
public class ListEntity {
    @JsonProperty ( "entities" )
    private List<Entity> entities = new ArrayList<>();

    public ListEntity addEntity( Entity entity ) {
        this.entities.add( entity );
        return this;
    }

    /**
     * @return the entities
     */
    @ApiModelProperty ( required = true, value = "" )
    @NotNull
    public List<Entity> getEntities() {
        return entities;
    }

    /**
     * @param entities the entities to set
     */
    public void setEntities( List<Entity> entities ) {
        this.entities = entities;
    }

}
