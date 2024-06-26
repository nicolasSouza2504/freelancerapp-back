package services.login;

import com.google.gson.Gson;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import dto.Session;
import model.UserLogin;
import repositories.UserRepository;
import services.redis.RedisService;
import utils.UtilErrorRest;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class LoginService {

    @Inject
    UserRepository userRepository;

    @Inject
    RedisService redisService;

    @Transactional
    public Session login(String jsonUser) {

        List<String> validations = new ArrayList<>();

        UserLogin userLogin = new Gson().fromJson(jsonUser, UserLogin.class);

        validateLogin(userLogin);

        UserLogin userDb = userRepository.findByEmail(userLogin.getEmail());

        if (userDb != null) {

            Boolean rigthPassword = validatePassword(userLogin, userDb);

            if (rigthPassword) {

                Session session = new Session(UUID.randomUUID().toString(), userDb.getUserName());

                redisService.set(session.getAuthToken(), new Gson().toJson(session));

                return session;

            } else {
                UtilErrorRest.throwResponseError("Senha incorreta!");
            }

        } else {
            UtilErrorRest.throwResponseError("Usuário não encontrado!", 404);
        }

        return null;

    }

    private Boolean validatePassword(UserLogin userLogin, UserLogin userDb) {

        String salt = userDb.getSaltPassword();

        String concatenedPassSalt = userLogin.getPassword() + salt;

        String hashedPasswordLogin = DigestUtils.md5Hex(concatenedPassSalt);

        return hashedPasswordLogin.equalsIgnoreCase(userDb.getPassword());

    }

    private void validateLogin(UserLogin userLogin) {

        if (userLogin != null) {

            if (StringUtils.isEmpty(userLogin.getPassword())) {
                UtilErrorRest.throwResponseError("Password is required");
            }

            if (StringUtils.isEmpty(userLogin.getEmail())) {
                UtilErrorRest.throwResponseError("Email is required");
            }

        } else {
            UtilErrorRest.throwResponseError("Password and name are required");
        }

    }

}
