package repository;

import model.thing.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepository implements BaseRepository<Category> {

    private Connection connection;

    public CategoryRepository(Connection connection) {
        this.connection = connection;
    }

    public Integer insert(Category category) {
        String insertCategory = "INSERT INTO category (name, super_category_id) VALUES (?, ?)" +
                "returning id;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertCategory);
            preparedStatement.setString(1, category.getName());
            preparedStatement.setInt(2, category.getSuperCategory().getId());
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                return result.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String readById(int id) {
        String readCategoryById = "SELECT name FROM category WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(readCategoryById);
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                return result.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public Category read(Category category) {
        String readCategory = "SELECT * FROM category WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(readCategory);
            preparedStatement.setInt(1, category.getId());
            ResultSet result = preparedStatement.executeQuery();
            return mapTo(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Category> readAll() {
        String readCategory = "SELECT * FROM category WHERE super_category_id IS null ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(readCategory);
            ResultSet result = preparedStatement.executeQuery();
            return mapToList(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Category> readAllById(Integer id) {
        String readCategory = "SELECT * FROM category WHERE super_category_id = ? ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(readCategory);
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();
            return mapToList(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public Integer update(Category category) {
        String updateCategory = "UPDATE category SET name = ?, super_category_id = ? WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateCategory);
            preparedStatement.setString(1, category.getName());
            preparedStatement.setInt(2, category.getSuperCategory().getId());
            preparedStatement.setInt(3, category.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Integer delete(Category category) {
        String deleteCategory = "DELETE * FROM category WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(deleteCategory);
            preparedStatement.setInt(1, category.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Category mapTo(ResultSet result) {
        try {
            if (result.next()) {
                return new Category(result.getInt(1),
                        result.getString(2),
                        new Category(result.getInt(3),
                                readById(result.getInt(3))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Category> mapToList(ResultSet result) {
        List<Category> categories = new ArrayList<Category>();
        try {
            while (result.next()) {
                categories.add(new Category(result.getInt(1),
                        result.getString(2),
                        new Category(result.getInt(3),
                                readById(result.getInt(3)))));
            }
            return categories;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
