package service;

import model.thing.Product;
import repository.ProductRepository;

import java.sql.Connection;
import java.util.List;

public class ProductService implements BaseService<Product> {
    private Connection connection;
    private ProductRepository productRepository;

    public ProductService(Connection connection) {
        this.connection = connection;
        this.productRepository = new ProductRepository(this.connection);
    }

    @Override
    public Integer insert(Product product) {
        return productRepository.insert(product);
    }

    @Override
    public Product read(Product product) {
        return productRepository.read(product);
    }

    public Product readById(Integer id) {
        return productRepository.readById(id);
    }

    @Override
    public List<Product> readAll() {
        return productRepository.readAll();
    }

    @Override
    public void update(Product product) {
         productRepository.update(product);
    }

    public void updateSuperCategoryId(Product product) {
         productRepository.updateSuperCategoryId(product);
    }

    @Override
    public void delete(Product product) {
         productRepository.delete(product);
    }
}
