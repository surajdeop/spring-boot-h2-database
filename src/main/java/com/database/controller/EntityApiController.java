/*
 * EntityApiController.java
 * 
 * Copyright (c) 2020 by General Electric Company. All rights reserved.
 * 
 * The copyright to the computer software herein is the property of
 * General Electric Company. The software may be used and/or copied only
 * with the written permission of General Electric Company or in accordance
 * with the terms and conditions stipulated in the agreement/contract
 * under which the software has been supplied.
 */
package com.database.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.database.model.Constants;
import com.database.model.Entity;
import com.database.model.ListEntity;
import com.database.service.EntityService;

/**
 * @author 212590608
 */
@RestController
public class EntityApiController implements EntityApi {
    public static final Logger logger = LoggerFactory.getLogger( EntityApiController.class );
    public static final String PROCESS_ID = "PROCESS_ID";
    @Autowired
    private EntityService entityService;

    @Override
    public ResponseEntity<ListEntity> getEntities() {
        MDC.put( PROCESS_ID, Constants.LIST_ENTITY );
        logger.info( "Getting all entities" );
        ListEntity listEntity = entityService.getListEntity();

        MDC.remove( PROCESS_ID );
        return new ResponseEntity<>( listEntity, HttpStatus.OK );
    }

    @Override
    public ResponseEntity<String> saveEntity( @Valid Entity entity ) {
        MDC.put( PROCESS_ID, Constants.ADD_ENTITY );
        logger.info( "Add new Entity" );
        entityService.saveOrUpdate( entity );
        MDC.remove( PROCESS_ID );
        return new ResponseEntity<>( "Entity is created successfully", HttpStatus.OK );
    }

    @Override
    public ResponseEntity<Entity> getEntityByClientId( String clientId ) {
        MDC.put( PROCESS_ID, Constants.ENTITY_BY_CLIENT_ID );
        logger.info( "Get Entity by client id" );
        try {
            Entity entity = entityService.getEntityById( clientId );
            return new ResponseEntity<>( entity, HttpStatus.OK );
        } catch ( Exception e ) {
            return new ResponseEntity<>( HttpStatus.NOT_FOUND );
        } finally {
            MDC.remove( PROCESS_ID );
        }
    }

    @Override
    public ResponseEntity<String> deleteEntityByClientId( String clientId ) {
        MDC.put( PROCESS_ID, Constants.DELETE_ENTITY_BY_CLIENT_ID );
        logger.info( "Delete Entity by client id" );
        try {
            entityService.delete( clientId );
            return new ResponseEntity<>( "Entity is deleted successfully", HttpStatus.OK );
        } catch ( Exception e ) {
            return new ResponseEntity<>( HttpStatus.NOT_FOUND );
        } finally {
            MDC.remove( PROCESS_ID );
        }
    }
}
