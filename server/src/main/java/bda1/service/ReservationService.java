package bda1.service;

import bda1.dao.ReservationDAO;
import bda1.model.Reservation;
import bda1.utils.DBConnection;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Path("reservation")
public class ReservationService extends BaseService<Reservation> {

    public ReservationService() {
        super(new ReservationDAO(DBConnection.getDBConnection()));
    }


    @POST
    @Path("/{rid}/transport/{tid}/")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public Response addTransport(@PathParam("rid") int rId, @PathParam("tid") int tId) {
        try {
            ((ReservationDAO) dao).addTransport(rId, tId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Response.status(Response.Status.OK).entity(String.valueOf(true)).build();
    }

    @DELETE
    @Path("/{rid}/transport/{tid}/")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public Response removeTransport(@PathParam("rid") int rId, @PathParam("tid") int tId) {
        try {
            ((ReservationDAO) dao).removeTransport(rId, tId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Response.status(Response.Status.OK).entity(String.valueOf(true)).build();
    }


}
