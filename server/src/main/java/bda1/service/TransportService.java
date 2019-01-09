package bda1.service;

import bda1.dao.TransportDAO;
import bda1.model.Transport;
import bda1.utils.DBConnection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.Set;

@Path("transport")
public class TransportService extends BaseService<Transport> {

    public TransportService() {
        super(new TransportDAO(DBConnection.getDBConnection()));
    }

    @GET
    @Path("/reservation/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Set<Transport> findByTransport(@PathParam("id") int id) {
        Set<Transport> obj = null;
        try {
            obj = ((TransportDAO) dao).findByTransport(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }


}
