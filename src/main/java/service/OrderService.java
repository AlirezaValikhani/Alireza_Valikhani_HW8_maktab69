package service;

import model.thing.Order;
import repository.OrderRepository;

import java.sql.Connection;
import java.util.List;

public class OrderService implements BaseService<Order> {
    private Connection connection;
    private OrderRepository orderRepository;

    public OrderService(Connection connection) {
        this.connection = connection;
        this.orderRepository = new OrderRepository(this.connection);
    }

    @Override
    public Integer insert(Order order) {
        return orderRepository.insert(order);
    }

    @Override
    public Order read(Order order) {
        return orderRepository.read(order);
    }

    @Override
    public List<Order> readAll() {
        return orderRepository.readAll();
    }

    @Override
    public Integer update(Order order) {
        return orderRepository.update(order);
    }

    @Override
    public Integer delete(Order order) {
        return orderRepository.delete(order);
    }
}
