package repository;

import model.thing.Product;
import model.thing.ShoppingCart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartRepository implements BaseRepository<ShoppingCart> {
    private Connection connection;

    public ShoppingCartRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Integer insert(ShoppingCart shoppingCart) {
        String insertShoppingCart = "INSERT INTO shopping_cart (total_price) VALUES (?)" +
                "returning id;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertShoppingCart);
            preparedStatement.setDouble(1, shoppingCart.getTotalPrice());
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                return result.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ShoppingCart read(ShoppingCart shoppingCart) {
        String readShoppingCart = "SELECT * FROM shopping_cart WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(readShoppingCart);
            preparedStatement.setInt(1, shoppingCart.getId());
            ResultSet result = preparedStatement.executeQuery();
            return mapTo(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ShoppingCart> readAll() {
        String readShoppingCart = "SELECT * FROM shopping_cart";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(readShoppingCart);
            ResultSet result = preparedStatement.executeQuery();
            return mapToList(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer update(ShoppingCart shoppingCart) {
        String updateShoppingCart = "UPDATE product SET total_price = ? WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateShoppingCart);
            preparedStatement.setDouble(1, shoppingCart.getTotalPrice());
            preparedStatement.setInt(2, shoppingCart.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer delete(ShoppingCart shoppingCart) {
        String deleteShoppingCart = "DELETE * FROM shopping_cart WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(deleteShoppingCart);
            preparedStatement.setInt(1, shoppingCart.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ShoppingCart mapTo(ResultSet result) {
        try {
            if (result.next()) {
                return new ShoppingCart(result.getInt(1),
                        result.getDouble(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ShoppingCart> mapToList(ResultSet result) {
        List<ShoppingCart> shoppingCarts = new ArrayList<>();
        try {
            while (result.next()) {
                shoppingCarts.add(new ShoppingCart(
                        result.getInt(1),
                        result.getDouble(2)));
            }
            return shoppingCarts;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
