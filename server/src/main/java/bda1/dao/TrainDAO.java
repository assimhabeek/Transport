package bda1.dao;

import bda1.model.Train;
import bda1.model.Transport;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TrainDAO extends BaseDAO<Train> {

    private TransportDAO transportDAO;

    public TrainDAO(Connection con) {
        super(con, "Train");
        this.transportDAO = new TransportDAO(con);
    }


    @Override
    public Train read(ResultSet result) throws SQLException {
        Transport parent = transportDAO.find(result.getInt("ID"));
        return new Train(result.getInt("id"),
                parent.getDateDepart(),
                parent.getDateArrivee(),
                parent.getNbrSiegesOccupes(),
                parent.getNbrSiegesTotal(),
                parent.getPrix(),
                result.getInt("NOMBRE_WAGONS"),
                result.getBoolean("VOITURE_CAFETERIA"));

    }

    @Override
    public PreparedStatement write(PreparedStatement statement, Train obj) throws SQLException {
        statement.setLong(1, obj.getId());
        statement.setInt(2, obj.getNbrWagons());
        statement.setBoolean(3, obj.isVoitureCafeteria());
        return statement;
    }

    @Override
    public String buildInsertQuery() {
        return "INSERT INTO TRAIN (ID,NOMBRE_WAGONS,VOITURE_CAFETERIA) Values (?,?,?)";
    }

    @Override
    public String buildUpdateQuery() {
        return "UPDATE TRAIN SET ID=?,NOMBRE_WAGONS=?,VOITURE_CAFETERIA=? WHERE ID=?";
    }

    @Override
    public boolean update(Train obj) throws Exception {
        transportDAO.update(obj);
        return super.update(obj);
    }

    @Override
    public boolean create(Train obj) throws SQLException {
        transportDAO.create(obj);
        obj.setId(transportDAO.lastInsertedId());
        return super.create(obj);
    }

    @Override
    public boolean delete(Train obj) throws Exception {
        super.delete(obj);
        return transportDAO.delete(obj);
    }

    @Override
    public int lastInsertedId() throws SQLException {
        return transportDAO.lastInsertedId();
    }

}
