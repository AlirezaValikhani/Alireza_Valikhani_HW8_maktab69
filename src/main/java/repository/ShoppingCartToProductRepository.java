package repository;

import model.thing.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartToProductRepository implements BaseRepository<ShoppingCartToProduct> {

    private Connection connection;

    public ShoppingCartToProductRepository(Connection connection) {
        this.connection = connection;
    }

    public Integer insert(ShoppingCartToProduct shoppingCartToProduct) {
        String insertShoppingCartToProduct = "INSERT INTO shopping_cart_to_product " +
                "(shopping_cart_id, product_id, quantity, total_price) VALUES (?, ?, ?, ?)" +
                "returning id;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertShoppingCartToProduct);
            preparedStatement.setInt(1, shoppingCartToProduct.getShoppingCart().getId());
            preparedStatement.setInt(2, shoppingCartToProduct.getProduct().getId());
            preparedStatement.setInt(3, shoppingCartToProduct.getQuantity());
            preparedStatement.setDouble(4, shoppingCartToProduct.getTotalPrice());
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                return result.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ShoppingCartToProduct read(ShoppingCartToProduct shoppingCartToProduct) {
        String readShoppingCartToProduct = "SELECT * FROM shopping_cart_to_product sctp" +
                " INNER JOIN product p ON sctp.product_id = p.id WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(readShoppingCartToProduct);
            preparedStatement.setInt(1, shoppingCartToProduct.getId());
            ResultSet result = preparedStatement.executeQuery();
            return mapTo(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<ShoppingCartToProduct> readAll() {
        String readShoppingCartToProduct = "SELECT * FROM shopping_cart_to_product sctp" +
                " INNER JOIN product p ON sctp.product_id = p.id";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(readShoppingCartToProduct);
            ResultSet result = preparedStatement.executeQuery();
            return mapToList(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Integer update(ShoppingCartToProduct shoppingCartToProduct) {
        String updateShoppingCartToProduct = "UPDATE shopping_cart_to_product SET quantity = ?" +
                " WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateShoppingCartToProduct);
            preparedStatement.setInt(1, shoppingCartToProduct.getQuantity());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Integer delete(ShoppingCartToProduct shoppingCartToProduct) {
        String deleteShoppingCartToProduct = "DELETE * FROM shopping_cart_To_product WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(deleteShoppingCartToProduct);
            preparedStatement.setInt(1, shoppingCartToProduct.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ShoppingCartToProduct mapTo(ResultSet result) {
        try {
            if (result.next()) {
                return new ShoppingCartToProduct(result.getInt(1),
                        new ShoppingCart(result.getInt(2)),
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

    public List<ShoppingCartToProduct> mapToList(ResultSet result) {
        List<ShoppingCartToProduct> shoppingCartToProducts = new ArrayList<>();
        try {
            while (result.next()) {
                shoppingCartToProducts.add(new ShoppingCartToProduct
                        (result.getInt(1),
                                new ShoppingCart(result.getInt(2)),
                                new Product(result.getInt(3),
                                        result.getString(7),
                                        result.getString(8),
                                        result.getDouble(9),
                                        result.getInt(10),
                                        new Category(result.getInt(11))),
                                result.getInt(4),
                                result.getDouble(5)));
            }
            return shoppingCartToProducts;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
