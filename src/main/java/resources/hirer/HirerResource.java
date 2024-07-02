package resources.hirer;

import com.google.gson.Gson;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import dto.ServerSession;
import repositories.HirerRepository;
import utils.ResponseDto;

@Path("/hirer")
public class HirerResource {

    @Inject
    HirerRepository hirerRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        return Response.ok(new Gson().toJson(hirerRepository.findAll())).status(200).build();
    }

}
