package repositories;

import dto.ServerSession;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import model.Hirer;

import java.util.List;

@ApplicationScoped
public class HirerRepository {

    @Inject
    EntityManager entityManager;

    @Transactional
    public List<Hirer> findAll() {

        Long userId = ServerSession.getSession().getUserId();

        return entityManager.createQuery("SELECT h FROM Hirer h WHERE h.userLoginId = :userId", Hirer.class)
                            .setParameter("userId", userId)
                            .getResultList();

    }

}
