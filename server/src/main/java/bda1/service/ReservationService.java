package bda1.service;

import bda1.dao.ReservationDAO;
import bda1.model.Reservation;
import bda1.utils.DBConnection;

import javax.ws.rs.Path;

@Path("reservation")
public class ReservationService extends BaseService<Reservation> {

    public ReservationService() {
        super(new ReservationDAO(DBConnection.getDBConnection()));
    }
}
