package bda1.dao;

/**
 * @author BDA2
 */

import java.sql.*;
import java.util.Set;

public abstract class DAO<T> {
    protected Connection con = null;

    public DAO(Connection con) {
        this.con = con;
    }

    public abstract boolean create(T obj) throws SQLException;

    public abstract boolean delete(T obj) throws Exception;

    public abstract boolean update(T obj) throws Exception;

    public abstract T find(int id) throws SQLException;

    public abstract Set<T> findAll() throws SQLException;


}

