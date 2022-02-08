package repository;

import model.human.Customer;
import model.thing.Category;
import model.thing.Order;
import model.thing.Product;
import model.thing.Status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository implements BaseRepository<Product> {

    private Connection connection;

    public ProductRepository(Connection connection) {
        this.connection = connection;
    }

    public Integer insert(Product product) {
        String insertProduct = "INSERT INTO product (product_name, description, price," +
                " stock, super_category_id) VALUES (?, ?, ?, ?, ?)" +
                "returning id;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertProduct);
            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setString(2,product.getDescription());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.setInt(4, product.getStock());
            preparedStatement.setInt(5, product.getCategory().getSuperCategory().getId());
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                return result.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Product read(Product product) {
        String readProduct = "SELECT * FROM product p INNER JOIN " +
                "category c ON p.super_category_id = c.id  WHERE p.id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(readProduct);
            preparedStatement.setInt(1, product.getId());
            ResultSet result = preparedStatement.executeQuery();
            return mapTo(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Product readById(Integer id) {
        String readProduct = "SELECT * FROM product p INNER JOIN " +
                "category c ON p.super_category_id = c.id  WHERE p.id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(readProduct);
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();
            return mapTo(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Product> readAll() {
        String readProduct = "SELECT * FROM product p INNER JOIN " +
                "category c ON p.super_category_id = c.id";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(readProduct);
            ResultSet result = preparedStatement.executeQuery();
            return mapToList(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(Product product) {
        String updateProduct = "UPDATE product SET product_name = ?, description = ?" +
                ", price = ?, stock = ? WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateProduct);
            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.setDouble(4, product.getStock());
            preparedStatement.setInt(5, product.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateSuperCategoryId(Product product) {
        String updateProduct = "UPDATE product SET product_name = ?, description = ?" +
                ", price = ?, stock = ? , super_category_id = ? WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateProduct);
            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.setDouble(4, product.getStock());
            preparedStatement.setInt(5, product.getCategory().getSuperCategory().getId());
            preparedStatement.setInt(6, product.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(Product product) {
        String deleteProduct = "DELETE * FROM product WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(deleteProduct);
            preparedStatement.setInt(1, product.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Product mapTo(ResultSet result) {
        try {
            if (result.next()) {
                return new Product(result.getInt(1),
                        result.getString(2),
                        result.getString(3),
                        result.getDouble(4),
                        result.getInt(5),
                        new Category(result.getInt(6)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Product> mapToList(ResultSet result) {
        List<Product> products = new ArrayList<Product>();
        try {
            while (result.next()) {
                products.add(new Product(result.getInt(1),
                        result.getString(2),
                        result.getString(3),
                        result.getDouble(4),
                        result.getInt(5),
                        new Category(result.getInt(6))));
            }
            return products;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
