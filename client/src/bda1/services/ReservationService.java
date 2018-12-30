package bda1.services;

import bda1.model.Reservation;

public class ReservationService extends BaseService<Reservation> {

    public ReservationService() {
        super("/reservation", Reservation.class);
    }
}
