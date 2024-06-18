package repositories;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import model.UserLogin;
import org.apache.commons.lang3.StringUtils;

@ApplicationScoped
public class UserRepository {


    @Inject
    EntityManager entityManager;

    @Transactional
    public UserLogin findByEmail(String email) {

        if (StringUtils.isNotEmpty(email)) {

            return entityManager.createQuery("SELECT u FROM UserLogin u WHERE UPPER(u.email) = :email", UserLogin.class)
                    .setParameter("email", email.toUpperCase())
                    .getResultList()
                    .stream()
                    .findFirst()
                    .orElse(null);

        } else {
            return null;
        }

    }

}

