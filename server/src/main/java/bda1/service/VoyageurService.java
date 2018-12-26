package bda1.service;

import bda1.dao.BaseDAO;
import bda1.dao.VoyageurDAO;
import bda1.model.Voyageur;
import bda1.utils.DBConnection;

import javax.ws.rs.Path;


@Path("voyageur")
public class VoyageurService extends BaseService<Voyageur> {

    public VoyageurService() {
        super(new VoyageurDAO(DBConnection.getDBConnection()));
    }
}
