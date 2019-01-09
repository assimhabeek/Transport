package bda1.dao;


import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public abstract class BaseDAO<T> extends DAO<T> {
    protected String tableName;

    public BaseDAO(Connection con, String tableName) {
        super(con);
        this.tableName = tableName;
    }

    public abstract T read(ResultSet result) throws SQLException;

    public abstract PreparedStatement write(PreparedStatement statement, T obj) throws SQLException;

    public abstract String buildInsertQuery();

    public abstract String buildUpdateQuery();


    private String buildLastInsertedQuery() {
        return String.format("SELECT %s_SEQUENCE.CURRVAL FROM DUAL", tableName);
    }

    public String buildSelectQuery() {
        return String.format("SELECT * FROM %s WHERE %s.ID=?", tableName, tableName);
    }


    public String buildSelectAllQuery() {
        return String.format("SELECT * FROM %s", tableName);
    }


    public String buildDeleteQuery() {
        return String.format("DELETE  FROM %s WHERE %s.ID=?", tableName, tableName);
    }


    @Override
    public T find(int id) throws SQLException {
        PreparedStatement stmt = con.prepareStatement(buildSelectQuery());
        stmt.setInt(1, id);
        ResultSet result = stmt.executeQuery();
        return result.next() ? read(result) : null;
    }

    public int lastInsertedId() throws SQLException {
        PreparedStatement stmt = con.prepareStatement(buildLastInsertedQuery());
        ResultSet result = stmt.executeQuery();
        return result.next() ? (int) result.getLong(1) : -1;
    }


    @Override
    public Set<T> findAll() throws SQLException {
        PreparedStatement stmt = con.prepareStatement(buildSelectAllQuery());
        ResultSet result = stmt.executeQuery();
        Set<T> records = new HashSet<>();
        while (result.next()) {
            records.add(read(result));
        }
        return records;
    }


    @Override
    public boolean update(T obj) throws Exception {
        PreparedStatement stmt = con.prepareStatement(buildUpdateQuery());
        write(stmt, obj);

        stmt.setInt(countOccurences(buildUpdateQuery(), '?', 0), getPrimaryKey(obj));
        return stmt.execute();
    }

    private static int countOccurences(String someString, char searchedChar, int index) {
        if (index >= someString.length()) {
            return 0;
        }

        int count = someString.charAt(index) == searchedChar ? 1 : 0;
        return count + countOccurences(
                someString, searchedChar, index + 1);
    }

    @Override
    public boolean create(T obj) throws SQLException {
        PreparedStatement stmt = con.prepareStatement(buildInsertQuery());
        write(stmt, obj);
        return stmt.execute();
    }

    @Override
    public boolean delete(T obj) throws Exception {
        PreparedStatement stmts = con.prepareStatement(buildDeleteQuery());
        stmts.setInt(1, getPrimaryKey(obj));
        return stmts.execute();
    }


    protected int getPrimaryKey(T obj) throws Exception {
        Field f = obj.getClass().getField("id");
        f.setAccessible(true);
        return f.getInt(obj);
    }
}
