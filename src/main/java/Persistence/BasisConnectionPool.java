/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alex
 */
public class BasisConnectionPool implements IConnectionPool {

    private final String URL = "jdbc:mysql://localhost:3306/datingapp";
    private final String USER = "dev";
    private final String PASSWORD = "dev";
    private List<Connection> connectionPool = new ArrayList<>();
    private List<Connection> usedConnections = new ArrayList<>();
    private final int INITIAL_POOL_SIZE = 20;

    public BasisConnectionPool() {
        try {
            for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
                connectionPool.add(createConnection());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Connection createConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    @Override
    public Connection getConnection() {
        Connection connection = connectionPool.remove(connectionPool.size() - 1);
        usedConnections.add(connection);
        return connection;
    }

    @Override
    public boolean releaseConnection(Connection connection) {
        connectionPool.add(connection);
        return usedConnections.remove(connection);
    }

}
