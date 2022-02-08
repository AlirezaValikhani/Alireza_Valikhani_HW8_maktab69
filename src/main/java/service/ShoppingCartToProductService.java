package service;

import model.thing.ShoppingCartToProduct;
import repository.ShoppingCartToProductRepository;

import java.sql.Connection;
import java.util.List;

public class ShoppingCartToProductService implements BaseService<ShoppingCartToProduct> {

    private Connection connection;

    private ShoppingCartToProductRepository shoppingCartToProductRepository;

    public ShoppingCartToProductService(Connection connection) {
        this.connection = connection;
        this.shoppingCartToProductRepository = new ShoppingCartToProductRepository(this.connection);
    }


    @Override
    public Integer insert(ShoppingCartToProduct shoppingCartToProduct) {
        return shoppingCartToProductRepository.insert(shoppingCartToProduct);
    }

    @Override
    public ShoppingCartToProduct read(ShoppingCartToProduct shoppingCartToProduct) {
        return shoppingCartToProductRepository.read(shoppingCartToProduct);
    }

    @Override
    public List<ShoppingCartToProduct> readAll() {
        return shoppingCartToProductRepository.readAll();
    }

    @Override
    public void update(ShoppingCartToProduct shoppingCartToProduct) {
         shoppingCartToProductRepository.update(shoppingCartToProduct);
    }

    @Override
    public void delete(ShoppingCartToProduct shoppingCartToProduct) {
         shoppingCartToProductRepository.delete(shoppingCartToProduct);
    }
}
