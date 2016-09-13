package academy.softserve.edu.dao;

import academy.softserve.edu.dao.interfaces.DaoFactory;
import academy.softserve.edu.dao.interfaces.UserDao;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLDaoFactory implements DaoFactory {

    private static final String USERNAME = "oms";
    private static final String PASSWORD = "1qaz2wsx";
    private static final String DB_URL = "jdbc:mysql://192.168.56.101:3306/oms";

    public MySQLDaoFactory() throws SQLException {
        final Driver driver = new com.mysql.jdbc.Driver();
        DriverManager.registerDriver(driver);
    }

    @Override
    public final Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
    }

    @Override
    public final UserDao getUserDao(final Connection connection) {
        return new MySQLUserDao(connection);
    }

}
