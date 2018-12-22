package bda1;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Path("/")
public class Hello {

/*
    Don't create an instance just inject the DAO
    @Inject
    private FactureDAO FactureDAO;
*/

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getMessage() {
        // TODO : find a way to return a json object and json array.
        return "Hello World";
    }


}