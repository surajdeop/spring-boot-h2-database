package com.database.repository;

import org.springframework.data.repository.CrudRepository;

import com.database.model.Entity;

public interface EntityRepository extends CrudRepository<Entity, String> {

}
