package com.database;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.database.model.Entity;
import com.database.model.ListEntity;

public class EntityServiceControllerTest extends AbstractTest {
    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void getEntityList() throws Exception {
        String saveUri = "/entity/save";
        Entity entity = getTestEntity();
        System.out.println( entity.getClientId() );

        String inputJson = super.mapToJson( entity );
        mvc.perform( MockMvcRequestBuilders.post( saveUri ).contentType( MediaType.APPLICATION_JSON_VALUE ).content( inputJson ) ).andReturn();

        String uri = "/entity/";
        MvcResult mvcResult = mvc.perform( MockMvcRequestBuilders.get( uri ).accept( MediaType.APPLICATION_JSON_VALUE ) ).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals( 200, status );
        String content = mvcResult.getResponse().getContentAsString();
        ListEntity entitylist = super.mapFromJson( content, ListEntity.class );
        assertTrue( entitylist.getEntities().size() > 0 );
    }

    @Test
    public void createEntity() throws Exception {
        String uri = "/entity/save";
        Entity entity = getTestEntity();
        System.out.println( entity.getClientId() );

        String inputJson = super.mapToJson( entity );
        System.out.println( inputJson );
        MvcResult mvcResult = mvc.perform( MockMvcRequestBuilders.post( uri ).contentType( MediaType.APPLICATION_JSON_VALUE ).content( inputJson ) ).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals( 200, status );
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals( content, "Entity is created successfully" );
    }

    @Test
    public void getEntityByClientId() throws Exception {
        String saveUri = "/entity/save";
        Entity entity = getTestEntity();
        System.out.println( entity.getClientId() );

        String inputJson = super.mapToJson( entity );
        mvc.perform( MockMvcRequestBuilders.post( saveUri ).contentType( MediaType.APPLICATION_JSON_VALUE ).content( inputJson ) ).andReturn();

        String uri = "/entity/" + entity.getClientId();
        MvcResult mvcResult = mvc.perform( MockMvcRequestBuilders.get( uri ).accept( MediaType.APPLICATION_JSON_VALUE ) ).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals( 200, status );
        String content = mvcResult.getResponse().getContentAsString();
        Entity expectedEntity = super.mapFromJson( content, Entity.class );
        assertEquals( expectedEntity, entity );

    }

    @Test
    public void deleteEntityByClientId() throws Exception {
        String saveUri = "/entity/save";
        Entity entity = getTestEntity();
        System.out.println( entity.getClientId() );

        String inputJson = super.mapToJson( entity );
        mvc.perform( MockMvcRequestBuilders.post( saveUri ).contentType( MediaType.APPLICATION_JSON_VALUE ).content( inputJson ) ).andReturn();

        String uri = "/entity/" + entity.getClientId();
        MvcResult mvcResult = mvc.perform( MockMvcRequestBuilders.delete( uri ).accept( MediaType.APPLICATION_JSON_VALUE ) ).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals( 200, status );
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals( content, "Entity is deleted successfully" );

    }

    private static Entity getTestEntity() {
        Entity entity = new Entity();
        entity.setClientId( getTestClientId() );

        entity.setCardNumber( getCardNumber() );
        entity.setTransactionAmount( new Random().nextDouble() * 1000 );
        entity.setTransactionDate( new Date() );
        return entity;
    }

    private static String getTestClientId() {
        return RandomStringUtils.randomAlphanumeric( 8 ) + "-" + RandomStringUtils.randomAlphanumeric( 4 ) + "-" + RandomStringUtils.randomNumeric( 4 ) + "-" + RandomStringUtils.randomAlphanumeric( 4 ) + "-" + RandomStringUtils.randomAlphanumeric( 12 );
    }

    private static String getCardNumber() {
        return RandomStringUtils.randomNumeric( 4 ) + "-" + RandomStringUtils.randomNumeric( 4 ) + "-" + RandomStringUtils.randomNumeric( 4 ) + "-" + RandomStringUtils.randomNumeric( 4 );
    }
}
