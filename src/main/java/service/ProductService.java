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

    @Override
    public List<Product> readAll() {
        return productRepository.readAll();
    }

    @Override
    public Integer update(Product product) {
        return productRepository.update(product);
    }

    @Override
    public Integer delete(Product product) {
        return productRepository.delete(product);
    }
}
