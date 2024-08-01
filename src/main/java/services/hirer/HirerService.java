package services.hirer;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import model.Hirer;
import services.GenericService;

@ApplicationScoped
public class HirerService extends GenericService {

    @Inject
    EntityManager manager;

    @Transactional
    public void save(Hirer hirer) {
        manager.persist(hirer);
    }

    @Transactional
    public void update(Hirer hirer) {
        manager.merge(hirer);
    }

}
