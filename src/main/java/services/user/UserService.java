package services.user;

import com.google.gson.Gson;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import model.UserLogin;
import repositories.UserRepository;
import utils.UtilErrorRest;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class UserService {

    @Inject
    EntityManager entityManager;

    @Inject
    UserRepository userRepository;

    @Transactional
    public UserLogin save(String jsonUser) {

        UserLogin userLogin = new Gson().fromJson(jsonUser, UserLogin.class);

        validateUser(userLogin);

        setSaltAndEncryptedPassword(userLogin);

        entityManager.persist(userLogin);

        return userLogin;

    }

    @Transactional
    public void setSaltAndEncryptedPassword(UserLogin userLogin) {

        userLogin.setSaltPassword(RandomStringUtils.random(8));

        String concatenedPassSalt = userLogin.getPassword() + userLogin.getSaltPassword();

        userLogin.setPassword(DigestUtils.md5Hex(concatenedPassSalt));

    }

    @Transactional
    public void validateUser(UserLogin userLogin) {

        List<String> validations = new ArrayList<>();

        if (userLogin != null) {

            if (StringUtils.isEmpty(userLogin.getPassword())) {
                validations.add("Informe a senha!");
            }

            if (StringUtils.isEmpty(userLogin.getUserName())) {
                validations.add("Informe o nome!");
            }

            if (StringUtils.isEmpty(userLogin.getEmail())) {
                validations.add("Informe o email!");
            }

            if (StringUtils.isEmpty(userLogin.getCpfCnpj())) {
                validations.add("Informe o Cnpj/Cpf!");
            }

            UserLogin userSaved = userRepository.findByEmail(userLogin.getEmail());

            if (userSaved != null) {
                validations.add("Usuário ja cadastrado!");
            }

        } else {
            validations.add("Preencha todos os campos!");
        }

        UtilErrorRest.throwResponseError(validations, 400);

    }

}
