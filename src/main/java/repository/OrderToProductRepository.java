package repository;

import model.thing.Category;
import model.thing.Order;
import model.thing.OrderToProduct;
import model.thing.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderToProductRepository implements BaseRepository<OrderToProduct> {

    private Connection connection;

    public OrderToProductRepository(Connection connection) {
        this.connection = connection;
    }

    public Integer insert(OrderToProduct orderToProduct) {
        String insertOrderToProduct = "INSERT INTO order_to_product (order_id, product_id, stock," +
                "total_price) VALUES (?, ?, ?, ?)" +
                "returning id;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertOrderToProduct);
            preparedStatement.setInt(1, orderToProduct.getOrder().getId());
            preparedStatement.setInt(2, orderToProduct.getProduct().getId());
            preparedStatement.setInt(3, orderToProduct.getQuantity());
            preparedStatement.setDouble(4, orderToProduct.getTotalPrice());
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                return result.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public OrderToProduct read(OrderToProduct orderToProduct) {
            String readOrderToProduct = "SELECT * FROM order_to_product otp" +
                    " INNER JOIN product p ON otp.product_id = p.id WHERE id = ?";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(readOrderToProduct);
                preparedStatement.setInt(1, orderToProduct.getId());
                ResultSet result = preparedStatement.executeQuery();
                return mapTo(result);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return null;
    }

    public List<OrderToProduct> readAll() {
        String readOrderToProduct = "SELECT * FROM order_to_product otp" +
                " INNER JOIN product p ON otp.product_id = p.id";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(readOrderToProduct);
            ResultSet result = preparedStatement.executeQuery();
            return mapToList(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(OrderToProduct orderToProduct) {
        String updateOrderToProduct = "UPDATE order_to_product SET stock = ? WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateOrderToProduct);
            preparedStatement.setInt(1, orderToProduct.getQuantity());
            preparedStatement.setInt(2, orderToProduct.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(OrderToProduct orderToProduct) {
        String deleteOrderToProduct = "DELETE * FROM product WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(deleteOrderToProduct);
            preparedStatement.setInt(1, orderToProduct.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public OrderToProduct mapTo(ResultSet result) {
        try {
            if (result.next()) {
                return new OrderToProduct(result.getInt(1),
                        new Order(result.getInt(2)),
                        new Product(result.getInt(3),
                        result.getString(7),
                        result.getString(8),
                        result.getDouble(9),
                        result.getInt(10),
                        new Category(result.getInt(11))),
                        result.getInt(4),
                        result.getDouble(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<OrderToProduct> mapToList(ResultSet result) {
        List<OrderToProduct> orderToProducts = new ArrayList<>();
        try {
            while (result.next()) {
                 orderToProducts.add(new OrderToProduct(result.getInt(1),
                         new Order(result.getInt(2)),
                         new Product(result.getInt(3),
                         result.getString(7),
                         result.getString(8),
                         result.getDouble(9),
                         result.getInt(10),
                         new Category(result.getInt(11))),
                         result.getInt(4),
                         result.getDouble(5)));
            }
            return orderToProducts;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
