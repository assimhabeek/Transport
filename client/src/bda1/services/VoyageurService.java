package bda1.services;

import bda1.model.Voyageur;
import bda1.utils.HttpConnection;
import com.sun.jersey.api.client.ClientResponse;

import javax.ws.rs.core.MediaType;
import java.util.Set;

public class VoyageurService extends BaseService<Voyageur> {

    public VoyageurService() {
        super("/voyageur", Voyageur.class);
    }

    public Set<Voyageur> findByReservation(int id) {
        String path = "/voyageur/reservation/" + id;
        ClientResponse response = HttpConnection.getResource()
                .path(path)
                .accept(MediaType.APPLICATION_JSON)
                .get(ClientResponse.class);
        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
        }

        return response.getEntity(getParameterizedSetType());
    }
}
