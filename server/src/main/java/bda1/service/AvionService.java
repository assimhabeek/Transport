package bda1.service;

import bda1.dao.AvionDAO;
import bda1.model.Avion;
import bda1.utils.DBConnection;

import javax.ws.rs.Path;


@Path("avion")
public class AvionService extends BaseService<Avion> {

    public AvionService() {
        super(new AvionDAO(DBConnection.getDBConnection()));
    }
}
