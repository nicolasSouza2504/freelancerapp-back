package resources.hirer;

import com.google.gson.Gson;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.Hirer;
import repositories.HirerRepository;
import services.hirer.HirerService;

@Path("/hirer")
public class HirerResource {

    @Inject
    HirerRepository hirerRepository;

    @Inject
    HirerService hirerService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        return Response.ok(new Gson().toJson(hirerRepository.findAll())).status(200).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(String jsonHirer) {

        Hirer hirer = new Gson().fromJson(jsonHirer, Hirer.class);

        if (hirer.getId() != null) {
            hirerService.update(hirer);
        } else {
            hirerService.save(hirer);
        }

        return Response.ok("Hirer has been saved with success").status(200).build();

    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Long id) {

        hirerService.delete(id);

        return Response.ok("Hirer has been saved with success").status(200).build();

    }

}
