package service;

import model.human.Customer;
import repository.CustomerRepository;

import java.sql.Connection;
import java.util.List;

public class CustomerService implements BaseService<Customer> {
    private Connection connection;
    private CustomerRepository customerRepository;

    public CustomerService(Connection connection) {
        this.connection = connection;
        this.customerRepository = new CustomerRepository(this.connection);
    }


    @Override
    public Integer insert(Customer customer) {
        return customerRepository.insert(customer);
    }

    public Customer readByUsername(String username){
        return customerRepository.readByUsername(username);
    }

    @Override
    public Customer read(Customer customer) {
        return customerRepository.read(customer);
    }

    public Customer readBalance(Integer id) {
        return customerRepository.readBalance(id);
    }

    @Override
    public List<Customer> readAll() {
        return customerRepository.readAll();
    }

    @Override
    public void update(Customer customer) {
         customerRepository.update(customer);
    }

    public void updateBalance(Integer id, Double balance) {
        customerRepository.updateBalance(id,balance);
    }

    @Override
    public void delete(Customer customer) {
         customerRepository.delete(customer);
    }
}
