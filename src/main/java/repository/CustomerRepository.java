package repository;

import model.human.Customer;
import model.thing.ShoppingCart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepository implements BaseRepository<Customer> {

    private Connection connection;

    public CustomerRepository(Connection connection) {
        this.connection = connection;
    }

    public Integer insert(Customer customer) {
        String insertCustomer = "INSERT INTO customer (first_name, last_name, user_name," +
                "password, email, address, balance, shopping_cart_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)" +
                "returning id;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertCustomer);
            preparedStatement.setString(1, customer.getFirstName());
            preparedStatement.setString(2, customer.getLastname());
            preparedStatement.setString(3, customer.getUserName());
            preparedStatement.setString(4, customer.getPassword());
            preparedStatement.setString(5, customer.getEmail());
            preparedStatement.setString(6, customer.getAddress());
            preparedStatement.setDouble(7, customer.getBalance());
            preparedStatement.setInt(8, customer.getShoppingCart().getId());
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                return result.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Customer readByUsername(String username) {
        String readCustomer = "SELECT * FROM customer c INNER JOIN shopping_cart sc" +
                " ON c.shopping_cart_id = sc.id WHERE c.user_name = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(readCustomer);
            preparedStatement.setString(1, username);
            ResultSet result = preparedStatement.executeQuery();
            return mapTo(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Customer read(Customer customer) {
        String readCustomer = "SELECT * FROM customer c INNER JOIN shopping_cart sc" +
                " ON c.shopping_cart_id = sc.id WHERE c.id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(readCustomer);
            preparedStatement.setInt(1, customer.getId());
            ResultSet result = preparedStatement.executeQuery();
            return mapTo(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Customer readBalance(Integer id) {
        String readCustomer = "SELECT balance FROM customer WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(readCustomer);
            preparedStatement.setInt(1,id);
            ResultSet result = preparedStatement.executeQuery();
            if(result.next()) {
                return new Customer(result.getDouble("balance"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Customer> readAll() {
        String readCustomer = "SELECT * FROM customer c INNER JOIN shopping_cart sc " +
                "ON c.shopping_cart_id = sc.id";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(readCustomer);
            ResultSet result = preparedStatement.executeQuery();
            return mapToList(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(Customer customer) {
        String updateCategory = "UPDATE customer SET first_name = ?, last_name = ?" +
                ", user_name = ?, password = ?, email = ?, address = ?, balance = ? WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateCategory);
            preparedStatement.setString(1, customer.getFirstName());
            preparedStatement.setString(2, customer.getLastname());
            preparedStatement.setString(3, customer.getUserName());
            preparedStatement.setString(4, customer.getPassword());
            preparedStatement.setString(5, customer.getEmail());
            preparedStatement.setString(6, customer.getAddress());
            preparedStatement.setDouble(7, customer.getBalance());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateBalance(Integer id ,Double balance) {
        String updateCategory = "UPDATE customer SET balance = ? WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateCategory);
            preparedStatement.setDouble(1, balance);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(Customer customer) {
        String deleteCustomer = "DELETE * FROM customer WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(deleteCustomer);
            preparedStatement.setInt(1, customer.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Customer mapTo(ResultSet result) {
        try {
            if (result.next()) {
                return new Customer(result.getInt(1),
                        result.getString(2),
                        result.getString(3),
                        result.getString(4),
                        result.getString(5),
                        result.getString(6),
                        result.getString(7),
                        result.getDouble(8),
                        new ShoppingCart(result.getInt(9),
                        result.getDouble("total_price")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Customer> mapToList(ResultSet result) {
        List<Customer> customers = new ArrayList<Customer>();
        try {
            while (result.next()) {
                customers.add(new Customer(result.getInt(1),
                        result.getString(2),
                        result.getString(3),
                        result.getString(4),
                        result.getString(5),
                        result.getString(6),
                        result.getString(7),
                        result.getDouble(8),
                        new ShoppingCart(result.getInt(9),
                        result.getDouble("total_price"))));
            }
            return customers;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
