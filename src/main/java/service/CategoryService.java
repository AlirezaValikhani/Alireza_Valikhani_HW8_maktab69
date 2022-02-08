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

    public Integer insertSuperCategory(Category category) {
        return categoryRepository.insertSuperCategory(category);
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

    public Category readBySuperCategoryId(Integer id) {
        return categoryRepository.readBySuperCategoryId(id);
    }

    public List<Category> readAllByName(String name) {
        return categoryRepository.readAllByName(name);
    }

    @Override
    public void update(Category category) {
        categoryRepository.update(category);
    }

    public void updateSuperCategoryName(Category category) {
        categoryRepository.updateBySuperCategoryName(category);
    }

    public void updateSuperCategoryId(Category category) {
        categoryRepository.updateSuperCategoryId(category);
    }

    @Override
    public void delete(Category category) {
        categoryRepository.delete(category);
    }

}
