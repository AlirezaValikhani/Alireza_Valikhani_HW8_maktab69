package service;

import model.thing.ShoppingCart;
import repository.ShoppingCartRepository;

import java.sql.Connection;
import java.util.List;

public class ShoppingCartService implements BaseService<ShoppingCart> {
    private Connection connection;
    private ShoppingCartRepository shoppingCartRepository;

    public ShoppingCartService(Connection connection) {
        this.connection = connection;
        this.shoppingCartRepository = new ShoppingCartRepository(this.connection);
    }

    @Override
    public Integer insert(ShoppingCart shoppingCart) {
        return shoppingCartRepository.insert(shoppingCart);
    }

    @Override
    public ShoppingCart read(ShoppingCart shoppingCart) {
        return null;
    }

    @Override
    public List<ShoppingCart> readAll() {
        return null;
    }

    @Override
    public void update(ShoppingCart shoppingCart) {

    }

    @Override
    public void delete(ShoppingCart shoppingCart) {

    }
}
