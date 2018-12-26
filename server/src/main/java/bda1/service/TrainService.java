package bda1.service;

import bda1.dao.TrainDAO;
import bda1.model.Train;
import bda1.utils.DBConnection;

import javax.ws.rs.Path;


@Path("train")
public class TrainService extends BaseService<Train> {

    public TrainService() {
        super(new TrainDAO(DBConnection.getDBConnection()));
    }
}
