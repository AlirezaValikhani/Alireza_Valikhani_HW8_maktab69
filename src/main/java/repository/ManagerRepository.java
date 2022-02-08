package repository;

import model.human.Manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ManagerRepository implements BaseRepository<Manager> {

    private Connection connection;

    public ManagerRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Integer insert(Manager manager) {
        String insertManager = "INSERT INTO customer (first_name, last_name, user_name," +
                "password, email, salary) VALUES (?, ?, ?, ?, ?, ?)" +
                "returning id;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertManager);
            preparedStatement.setString(1, manager.getFirstName());
            preparedStatement.setString(2, manager.getLastname());
            preparedStatement.setString(3, manager.getUserName());
            preparedStatement.setString(4, manager.getPassword());
            preparedStatement.setString(5, manager.getEmail());
            preparedStatement.setDouble(7, manager.getSalary());
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                return result.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Manager readByUsername(String username) {
        String readManager = "SELECT * FROM manager WHERE user_name = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(readManager);
            preparedStatement.setString(1, username);
            ResultSet result = preparedStatement.executeQuery();
            return mapTo(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Manager read(Manager manager) {
        String readManager = "SELECT * FROM manager WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(readManager);
            preparedStatement.setInt(1, manager.getId());
            ResultSet result = preparedStatement.executeQuery();
            return mapTo(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Manager> readAll() {
        String readManager = "SELECT * FROM manager";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(readManager);
            ResultSet result = preparedStatement.executeQuery();
            return mapToList(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Manager manager) {
        String updateManager = "UPDATE manager SET first_name = ?, last_name = ?," +
                "user_name = ?, password = ?, email = ?, salary = ? WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateManager);
            preparedStatement.setString(1, manager.getFirstName());
            preparedStatement.setString(2, manager.getLastname());
            preparedStatement.setString(3, manager.getUserName());
            preparedStatement.setString(4, manager.getPassword());
            preparedStatement.setString(5, manager.getEmail());
            preparedStatement.setDouble(7, manager.getSalary());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Manager manager) {
        String deleteManager = "DELETE * FROM manager WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(deleteManager);
            preparedStatement.setInt(1, manager.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Manager mapTo(ResultSet result) {
        try {
            if (result.next()) {
                return new Manager(result.getInt(1),
                        result.getString(2),
                        result.getString(3),
                        result.getString(4),
                        result.getString(5),
                        result.getString(6),
                        result.getDouble(7));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Manager> mapToList(ResultSet result) {
        List<Manager> managers = new ArrayList<>();
        try {
            while (result.next()) {
                managers.add(new Manager(result.getInt(1),
                        result.getString(2),
                        result.getString(3),
                        result.getString(4),
                        result.getString(5),
                        result.getString(6),
                        result.getDouble(7)));
            }
            return managers;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
