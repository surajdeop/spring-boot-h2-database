package com.database.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.database.model.Entity;
import com.database.model.ListEntity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author Surajdeo Prasad
 */
@RequestMapping ( value = "/entity" )
@Api ( tags = "entity-api" )
public interface EntityApi {
    public static final Logger logger = LoggerFactory.getLogger( EntityApi.class );
    public static final String PROCESS_ID = "PROCESS_ID";

    /**
     * End point to get all entity
     *
     * @return ResponseEntity Content of License configuration file
     */
    @ApiOperation ( value = "get all entity", response = ListEntity.class )
    @ApiResponses ( value = {@ApiResponse ( code = 200, message = "Successfully read all entity" ), @ApiResponse ( code = 500, message = "Failed to get entity" )} )
    @GetMapping ( value = "/", produces = "application/json" )
    public ResponseEntity<ListEntity> getEntities();

    @ApiOperation ( value = "Add a new Entity to the store", nickname = "saveEntity", notes = "", tags = {"entity-api",} )
    @ApiResponses ( value = {@ApiResponse ( code = 405, message = "Invalid input" )} )
    @PostMapping ( value = "/save", produces = "application/json", consumes = "application/json" )
    ResponseEntity<String> saveEntity( @ApiParam ( value = "Entity object that needs to be added to the store", required = true ) @Valid @RequestBody Entity body );

    @ApiOperation ( value = "Find entity by client id", nickname = "getEntityByClientId", notes = "Returns a single entity", response = Entity.class, tags = {"entity-api",} )
    @ApiResponses ( value = {@ApiResponse ( code = 200, message = "successful operation", response = Entity.class ),
                             @ApiResponse ( code = 400, message = "Invalid ID supplied" ),
                             @ApiResponse ( code = 404, message = "Entity not found" )} )
    @GetMapping ( value = "/{clientId}", produces = "application/json" )
    ResponseEntity<Entity> getEntityByClientId( @ApiParam ( value = "Client id of entity to return", required = true ) @PathVariable ( "clientId" ) String clientId );

    @ApiOperation ( value = "Find entity by client id", nickname = "deleteEntityByClientId", notes = "Returns a single deleted entity", response = Entity.class, tags = {"entity-api",} )
    @ApiResponses ( value = {@ApiResponse ( code = 200, message = "successful operation", response = String.class ),
                             @ApiResponse ( code = 400, message = "Invalid ID supplied" ),
                             @ApiResponse ( code = 404, message = "Entity not found" )} )
    @DeleteMapping ( value = "/{clientId}", produces = "application/json" )
    ResponseEntity<String> deleteEntityByClientId( @ApiParam ( value = "Client id of entity to Delete", required = true ) @PathVariable ( "clientId" ) String clientId );

}
