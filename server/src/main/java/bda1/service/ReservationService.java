package bda1.service;

import bda1.dao.ReservationDAO;
import bda1.model.Reservation;

import javax.ws.rs.Path;

@Path("reservation")
public class ReservationService extends BaseService<Reservation> {

    public ReservationService(ReservationDAO dao) {
        super(dao);
    }
}
