package bda1.services;

import bda1.model.Reservation;
import bda1.model.Transport;
import bda1.utils.HttpConnection;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;

import javax.ws.rs.core.MediaType;
import java.util.Set;

public class ReservationService extends BaseService<Reservation> {

    public ReservationService() {
        super("/reservation", Reservation.class);
    }

    public Set<TransportHelper> findTransportByReservation(int id) {
        String path = "/transport/reservation/" + id;
        ClientResponse response = HttpConnection.getResource()
                .path(path)
                .accept(MediaType.APPLICATION_JSON)
                .get(ClientResponse.class);
        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
        }
        Transport t = new Transport() {
            @Override
            public int calculerNbrSiegesDispo() {
                return 0;
            }
        };
        return response.getEntity(new GenericType<Set<TransportHelper>>() {
        });
    }

    public boolean addTransport(int rId, int tId) {
        ClientResponse response = HttpConnection.getResource()
                .path("/reservation/" + rId + "/transport/" + tId)
                .accept(MediaType.TEXT_PLAIN)
                .type(MediaType.TEXT_PLAIN)
                .post(ClientResponse.class, "");
        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
        }
        return Boolean.parseBoolean(response.getEntity(String.class));
    }

    public boolean removeTransport(int rId, int tId) {
        ClientResponse response = HttpConnection.getResource()
                .path("/reservation/" + rId + "/transport/" + tId)
                .accept(MediaType.TEXT_PLAIN)
                .type(MediaType.TEXT_PLAIN)
                .delete(ClientResponse.class, "");
        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
        }
        return Boolean.parseBoolean(response.getEntity(String.class));
    }

}

class TransportHelper extends Transport {

    @Override
    public int calculerNbrSiegesDispo() {
        return 0;
    }
}