package bda1.service;

import bda1.dao.VoyageurDAO;
import bda1.model.Voyageur;
import bda1.utils.DBConnection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.Set;


@Path("voyageur")
public class VoyageurService extends BaseService<Voyageur> {

    public VoyageurService() {
        super(new VoyageurDAO(DBConnection.getDBConnection()));
    }

    @GET
    @Path("/reservation/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Set<Voyageur> findByReservation(@PathParam("id") int id) {
        Set<Voyageur> obj = null;
        try {
            obj = ((VoyageurDAO) dao).findByReservation(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }
}
