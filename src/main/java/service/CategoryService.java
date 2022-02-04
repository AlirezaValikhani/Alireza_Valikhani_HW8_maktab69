package service;

import model.thing.Category;
import repository.CategoryRepository;

import java.sql.Connection;
import java.util.List;

public class CategoryService implements BaseService<Category> {

    private Connection connection;
    private CategoryRepository categoryRepository;

    public CategoryService(Connection connection) {
        this.connection = connection;
        this.categoryRepository = new CategoryRepository(this.connection);
    }

    @Override
    public Integer insert(Category category) {
        return categoryRepository.insert(category);
    }

    @Override
    public Category read(Category category) {
        return categoryRepository.read(category);
    }

    @Override
    public List<Category> readAll() {
        return categoryRepository.readAll();
    }

    public List<Category> readAllById(Integer id) {
        return categoryRepository.readAllById(id);
    }

    @Override
    public Integer update(Category category) {
        return categoryRepository.update(category);
    }

    @Override
    public Integer delete(Category category) {
        return categoryRepository.delete(category);
    }

}
