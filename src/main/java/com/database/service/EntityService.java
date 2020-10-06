/**
 * 
 */
package com.database.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.database.model.Entity;
import com.database.model.ListEntity;
import com.database.repository.EntityRepository;

/**
 * @author Surajdeo
 */
@Service
public class EntityService {
    @Autowired
    private EntityRepository entityRepository;

    // getting all Entity records
    public ListEntity getListEntity() {
        ListEntity listEntity = new ListEntity();
        List<Entity> entities = listEntity.getEntities();
        entityRepository.findAll().forEach( entity -> entities.add( entity ) );
        return listEntity;
    }

    // getting a specific record
    public Entity getEntityById( String clientId ) {
        return entityRepository.findById( clientId ).get();
    }

    public void saveOrUpdate( Entity entity ) {
        entityRepository.save( entity );
    }

    // deleting a specific record
    public void delete( String clientId ) {
        entityRepository.deleteById( clientId );
    }
}
