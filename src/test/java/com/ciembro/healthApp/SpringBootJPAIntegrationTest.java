package com.ciembro.healthApp;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest()
public class SpringBootJPAIntegrationTest {

    @Autowired
    private GenericEntityRepository genericEntityRepository;

    @Test
    public void givenGenericEntityRepository_whenSaveAndRetrieveEntity_thenOK() {
        GenericEntity genericEntity = genericEntityRepository
                .save(new GenericEntity("test"));
        Optional<GenericEntity> foundEntity = genericEntityRepository
                .findById(genericEntity.getId());

        Assertions.assertTrue(foundEntity.isPresent());
        assertEquals(genericEntity.getValue(), foundEntity.get().getValue());
    }
}