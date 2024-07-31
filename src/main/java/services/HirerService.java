package services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import model.Hirer;

@ApplicationScoped
public class HirerService {

    @Inject
    EntityManager manager;

    @Transactional
    public void save(Hirer hirer) {
        manager.persist(hirer);
    }

}
