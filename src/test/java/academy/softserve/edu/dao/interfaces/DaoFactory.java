package academy.softserve.edu.dao.interfaces;

import java.sql.Connection;
import java.sql.SQLException;

//TODO rename, that is not a factory
public interface DaoFactory {

    Connection getConnection() throws SQLException;

    UserDao getUserDao(final Connection connection);

    ProductDao getProductDao(final Connection connection);

    OrderDao getOrderDao(final Connection connection);

    OrderItemDao getOrderItemDao(final Connection connection);

}
