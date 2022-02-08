package service;

import model.thing.OrderToProduct;
import repository.OrderToProductRepository;

import java.sql.Connection;
import java.util.List;

public class OrderToProductService implements BaseService<OrderToProduct> {

    private Connection connection;
    private OrderToProductRepository orderToProductRepository;

    public OrderToProductService(Connection connection) {
        this.connection = connection;
        this.orderToProductRepository = new OrderToProductRepository(this.connection);
    }

    @Override
    public Integer insert(OrderToProduct orderToProduct) {
        return orderToProductRepository.insert(orderToProduct);
    }

    @Override
    public OrderToProduct read(OrderToProduct orderToProduct) {
        return orderToProductRepository.read(orderToProduct);
    }

    @Override
    public List<OrderToProduct> readAll() {
        return orderToProductRepository.readAll();
    }

    @Override
    public void update(OrderToProduct orderToProduct) {
         orderToProductRepository.update(orderToProduct);
    }

    @Override
    public void delete(OrderToProduct orderToProduct) {
         orderToProductRepository.delete(orderToProduct);
    }
}
