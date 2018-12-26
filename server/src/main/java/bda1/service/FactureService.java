package bda1.service;

import bda1.dao.FactureDAO;
import bda1.model.Facture;
import bda1.utils.DBConnection;

import javax.ws.rs.Path;


@Path("facture")
public class FactureService extends BaseService<Facture> {

    public FactureService() {
        super(new FactureDAO(DBConnection.getDBConnection()));
    }
}
