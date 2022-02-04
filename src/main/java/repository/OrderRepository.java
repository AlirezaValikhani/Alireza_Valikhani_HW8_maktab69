package repository;

import model.human.Customer;
import model.thing.Order;
import model.thing.Status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository implements BaseRepository<Order> {
    private Connection connection;

    public OrderRepository(Connection connection) {
        this.connection = connection;
    }

    public Integer insert(Order order) {
        String insertOrder = "INSERT INTO order (order_date, customer_id, status," +
                ", total_price) VALUES (?, ?, ?, ?, ?, ?)" +
                "returning id;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertOrder);
            preparedStatement.setDate(1, order.getOrderDate());
            preparedStatement.setInt(2, order.getCustomer().getId());
            preparedStatement.setString(3, order.getStatus().toString());
            preparedStatement.setDouble(4, order.getTotalPrice());
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                return result.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Order read(Order order) {
        String readOrder = "SELECT * FROM orders o INNER JOIN customer cs ON o.customer_id = cs.id WHERE o.id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(readOrder);
            preparedStatement.setInt(1, order.getId());
            ResultSet result = preparedStatement.executeQuery();
            return mapTo(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Order> readAll() {
        String readOrder = "SELECT * FROM orders o INNER JOIN customer cs ON o.customer_id = cs.id";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(readOrder);
            ResultSet result = preparedStatement.executeQuery();
            return mapToList(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Integer update(Order order) {
        String updateOdrer = "UPDATE order SET order_date = ?, customer_id = ?" +
                ", status = ? WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateOdrer);
            preparedStatement.setDate(1, order.getOrderDate());
            preparedStatement.setInt(2, order.getCustomer().getId());
            preparedStatement.setString(3, order.getStatus().toString());
            preparedStatement.setInt(4, order.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Integer delete(Order order) {
        String deleteOrder = "DELETE * FROM order WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(deleteOrder);
            preparedStatement.setInt(1, order.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Order mapTo(ResultSet result) {
        try {
            if (result.next()) {
                return new Order(result.getInt(1),
                        result.getDate(2),
                        new Customer(result.getInt(3),
                                result.getString(9),
                                result.getString(10),
                                result.getString(11),
                                result.getString(12),
                                result.getDouble(15)),
                        Status.valueOf(result.getString(4)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Order> mapToList(ResultSet result) {
        List<Order> orders = new ArrayList<Order>();
        try {
            while (result.next()) {
                orders.add(new Order(result.getInt(1),
                                result.getDate(2),
                        new Customer(result.getInt(3),
                                result.getString(9),
                                result.getString(10),
                                result.getString(11),
                                result.getString(12),
                                result.getDouble(15)),
                                Status.valueOf(result.getString(4))));
            }
            return orders;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
