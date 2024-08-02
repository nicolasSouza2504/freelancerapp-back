package services.hirer;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import model.Hirer;
import repositories.HirerRepository;
import services.GenericService;

@ApplicationScoped
public class HirerService extends GenericService {

    @Inject
    EntityManager manager;

    @Inject
    HirerRepository hirerRepository;

    @Transactional
    public void save(Hirer hirer) {
        manager.persist(hirer);
    }

    @Transactional
    public void update(Hirer hirer) {
        merge(hirer);
    }

    @Transactional
    public void delete(Long id) {

        Hirer hirer = hirerRepository.findById(id);

        manager.remove(hirer);

    }

}
