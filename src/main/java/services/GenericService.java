package services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import model.GenericEntity;

import java.util.Date;

@ApplicationScoped
public class GenericService {

    @Inject
    EntityManager manager;

    @Transactional
    public void merge(GenericEntity entity) {

        entity.setUpdatedAt(new Date());

        manager.persist(entity);

    }

}
